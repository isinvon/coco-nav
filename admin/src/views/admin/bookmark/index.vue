<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="网站标题" prop="title">
        <el-input
            v-model="queryParams.title"
            placeholder="请输入网站标题"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="网站图标" prop="icon">
        <el-input
            v-model="queryParams.icon"
            placeholder="请输入图标链接"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="网站链接" prop="url">
        <el-input
            v-model="queryParams.url"
            placeholder="请输入网站链接"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="Plus"
            @click="handleAdd"
            v-hasPermi="['admin:bookmark:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['admin:bookmark:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['admin:bookmark:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['admin:bookmark:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookmarkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <!--<el-table-column label="书签ID" align="center" prop="id"/>-->
      <el-table-column label="网站标题" align="center" prop="title"/>
      <el-table-column label="网站地址" align="center" prop="url"/>
      <el-table-column label="分类ID" align="center" prop="bookmarkCategoryId"/>
      <el-table-column label="网站图标" align="center" prop="icon"/>
      <el-table-column label="点击次数" align="center" prop="clickCount">
        <template #default="scope">
          <el-tag
              type="info"
              :closable="false"
              style="font-weight: bold;">
            {{ scope.row.clickCount }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="排序值" align="center" prop="sortOrder">
        <template #default="scope">
          <el-input-number
              v-model="scope.row.sortOrder"
              :min="1"
              label="排序值"
              @change="handleUpdateBookmarkSortOrder(scope.row)"
              v-hasPermi="['admin:bookmark:edit']"
          />
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <TagTool
              :value="scope.row.status"
              :options="bookmarkStatusTypeList"
              @click="handleUpdateBookmarkStatus(scope.row)"
              v-hasPermi="['admin:bookmark:edit']"
              style="cursor: pointer"
          />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['admin:bookmark:edit']">修改
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['admin:bookmark:remove']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改书签管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="bookmarkRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="网站标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入网站标题"/>
        </el-form-item>
        <el-form-item label="网站地址" prop="url">
          <el-input v-model="form.url" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="分类ID" prop="bookmarkCategoryId">
          <el-input v-model="form.bookmarkCategoryId" placeholder="请输入分类ID"/>
        </el-form-item>
        <el-form-item label="网站图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入网站图标"/>
        </el-form-item>
        <el-form-item label="排序值" prop="sortOrder">
          <el-input-number v-model="form.sortOrder" :min="1" label="排序值"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <ButtonGroupTool
              :options="bookmarkStatusTypeList"
              :modelValue="form.status"
              @update:modelValue="value => form.status = value"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Bookmark">
import {listBookmark, getBookmark, delBookmark, addBookmark, updateBookmark, indexBookmark} from "@/api/admin/bookmark";
import TagTool from "@/components/TagTool/index.vue";

const {proxy} = getCurrentInstance();

const bookmarkList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const bookmarkStatusTypeList = ref([]); // 书签状态类型
const bookmarkDeleteStatusList = ref([]); // 书签删除状态类型
const bookmarkDefaultIcon = ref(''); // 书签默认图标

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    title: null,
    url: null,
    bookmarkCategoryId: null,
    icon: null,
    clickCount: null,
    sortOrder: null,
    status: null,
  },
  rules: {
    title: [
      {required: true, message: "网站标题不能为空", trigger: "blur"}
    ],
    url: [
      {required: true, message: "网站地址不能为空", trigger: "blur"},
      // 校验
      {pattern: /^(https?|ftp|file):\/\/[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]/, message: '请输入正确的网址', trigger: 'blur'}
    ],
    bookmarkCategoryId: [
      {required: true, message: "分类ID不能为空", trigger: "blur"}
    ],
    createTime: [
      {required: true, message: "创建时间不能为空", trigger: "blur"}
    ],
    updateTime: [
      {required: true, message: "更新时间不能为空", trigger: "blur"}
    ]
  }
});

const {queryParams, form, rules} = toRefs(data);

/** Index */
function getIndex() {
  indexBookmark().then(response => {
    bookmarkStatusTypeList.value = response.data.bookmarkStatusTypeList;
    bookmarkDeleteStatusList.value = response.data.bookmarkDeleteStatusList;
    bookmarkDefaultIcon.value = response.data.bookmarkDefaultIcon;
  });
}

/** 查询书签管理列表 */
function getList() {
  loading.value = true;
  listBookmark(queryParams.value).then(response => {
    bookmarkList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    title: null,
    url: null,
    bookmarkCategoryId: null,
    icon: null,
    clickCount: null,
    sortOrder: 1,
    status: 1,
    isDeleted: 0,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("bookmarkRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加书签管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getBookmark(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改书签管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["bookmarkRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateBookmark(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addBookmark(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除书签管理编号为"' + _ids + '"的数据项？').then(function () {
    return delBookmark(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/bookmark/export', {
    ...queryParams.value
  }, `bookmark_${new Date().getTime()}.xlsx`)
}

/** 修改状态按钮操作 */
function handleUpdateBookmarkStatus(row) {
  // 拷贝插槽数据 row 到 form.value
  form.value = row;

  // 使用三元表达式切换 form.status
  form.value.status = row.status === 0 ? 1 : 0;

  // 执行更新操作
  if (form.value.id != null) {
    updateBookmark(form.value).then(response => {
      // 使用三元表达式显示消息
      form.value.status === 0
          ? proxy.$modal.msgWarning("书签已禁用")
          : proxy.$modal.msgSuccess("书签已启用");

      getList();
    });
  }
}

/** 修改排序按钮操作 */
function handleUpdateBookmarkSortOrder(row) {
  // 拷贝插槽数据 row 到 form.value
  form.value = row;

  // 执行更新操作
  if (form.value.id != null) {
    updateBookmark(form.value).then(response => {
      // 使用三元表达式显示消息
      form.value.sortOrder === 1
          ? proxy.$modal.msgSuccess("🔝书签已置顶")
          : null;
    });
  }
}


getList();
getIndex();
</script>

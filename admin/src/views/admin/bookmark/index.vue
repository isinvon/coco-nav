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
          placeholder="请输入网站图标"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="点击次数" prop="clickCount">
        <el-input
          v-model="queryParams.clickCount"
          placeholder="请输入点击次数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序值" prop="sortOrder">
        <el-input
          v-model="queryParams.sortOrder"
          placeholder="请输入排序值"
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
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:bookmark:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:bookmark:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:bookmark:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookmarkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="书签ID" align="center" prop="bookmarkId" />
      <el-table-column label="所属用户ID" align="center" prop="userId" />
      <el-table-column label="网站标题" align="center" prop="title" />
      <el-table-column label="网站地址" align="center" prop="url" />
      <el-table-column label="分类ID" align="center" prop="bookmarkCategoryId" />
      <el-table-column label="网站图标" align="center" prop="icon" />
      <el-table-column label="点击次数" align="center" prop="clickCount" />
      <el-table-column label="排序值" align="center" prop="sortOrder" />
      <el-table-column label="0-删除 1-正常" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:bookmark:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:bookmark:remove']">删除</el-button>
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
          <el-input v-model="form.title" placeholder="请输入网站标题" />
        </el-form-item>
        <el-form-item label="网站地址" prop="url">
          <el-input v-model="form.url" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="网站图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入网站图标" />
        </el-form-item>
        <el-form-item label="点击次数" prop="clickCount">
          <el-input v-model="form.clickCount" placeholder="请输入点击次数" />
        </el-form-item>
        <el-form-item label="排序值" prop="sortOrder">
          <el-input v-model="form.sortOrder" placeholder="请输入排序值" />
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
import { listBookmark, getBookmark, delBookmark, addBookmark, updateBookmark } from "@/api/admin/bookmark";

const { proxy } = getCurrentInstance();

const bookmarkList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userId: null,
    title: null,
    url: null,
    bookmarkCategoryId: null,
    icon: null,
    clickCount: null,
    sortOrder: null,
    status: null,
  },
  rules: {
    userId: [
      { required: true, message: "所属用户ID不能为空", trigger: "blur" }
    ],
    title: [
      { required: true, message: "网站标题不能为空", trigger: "blur" }
    ],
    url: [
      { required: true, message: "网站地址不能为空", trigger: "blur" }
    ],
    createTime: [
      { required: true, message: "创建时间不能为空", trigger: "blur" }
    ],
    updateTime: [
      { required: true, message: "更新时间不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

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
    bookmarkId: null,
    userId: null,
    title: null,
    url: null,
    bookmarkCategoryId: null,
    icon: null,
    clickCount: null,
    sortOrder: null,
    status: null,
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
  ids.value = selection.map(item => item.bookmarkId);
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
  const _bookmarkId = row.bookmarkId || ids.value
  getBookmark(_bookmarkId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改书签管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["bookmarkRef"].validate(valid => {
    if (valid) {
      if (form.value.bookmarkId != null) {
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
  const _bookmarkIds = row.bookmarkId || ids.value;
  proxy.$modal.confirm('是否确认删除书签管理编号为"' + _bookmarkIds + '"的数据项？').then(function() {
    return delBookmark(_bookmarkIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/bookmark/export', {
    ...queryParams.value
  }, `bookmark_${new Date().getTime()}.xlsx`)
}

getList();
</script>

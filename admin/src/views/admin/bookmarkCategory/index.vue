<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="分类名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入分类名称"
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
      <el-form-item label="分类颜色代码" prop="color">
        <el-input
          v-model="queryParams.color"
          placeholder="请输入分类颜色代码"
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
          v-hasPermi="['admin:bookmarkCategory:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:bookmarkCategory:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:bookmarkCategory:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:bookmarkCategory:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookmarkCategoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="分类ID" align="center" prop="bookmarkCategoryId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="分类名称" align="center" prop="name" />
      <el-table-column label="父分类ID" align="center" prop="parentId" />
      <el-table-column label="排序值" align="center" prop="sortOrder" />
      <el-table-column label="分类颜色代码" align="center" prop="color" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:bookmarkCategory:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:bookmarkCategory:remove']">删除</el-button>
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

    <!-- 添加或修改书签分类对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="bookmarkCategoryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="排序值" prop="sortOrder">
          <el-input v-model="form.sortOrder" placeholder="请输入排序值" />
        </el-form-item>
        <el-form-item label="分类颜色代码" prop="color">
          <el-input v-model="form.color" placeholder="请输入分类颜色代码" />
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

<script setup name="BookmarkCategory">
import { listBookmarkCategory, getBookmarkCategory, delBookmarkCategory, addBookmarkCategory, updateBookmarkCategory } from "@/api/admin/bookmarkCategory";

const { proxy } = getCurrentInstance();

const bookmarkCategoryList = ref([]);
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
    name: null,
    parentId: null,
    sortOrder: null,
    color: null,
  },
  rules: {
    userId: [
      { required: true, message: "用户ID不能为空", trigger: "blur" }
    ],
    name: [
      { required: true, message: "分类名称不能为空", trigger: "blur" }
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

/** 查询书签分类列表 */
function getList() {
  loading.value = true;
  listBookmarkCategory(queryParams.value).then(response => {
    bookmarkCategoryList.value = response.rows;
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
    bookmarkCategoryId: null,
    userId: null,
    name: null,
    parentId: null,
    sortOrder: null,
    color: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("bookmarkCategoryRef");
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
  ids.value = selection.map(item => item.bookmarkCategoryId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加书签分类";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _bookmarkCategoryId = row.bookmarkCategoryId || ids.value
  getBookmarkCategory(_bookmarkCategoryId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改书签分类";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["bookmarkCategoryRef"].validate(valid => {
    if (valid) {
      if (form.value.bookmarkCategoryId != null) {
        updateBookmarkCategory(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addBookmarkCategory(form.value).then(response => {
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
  const _bookmarkCategoryIds = row.bookmarkCategoryId || ids.value;
  proxy.$modal.confirm('是否确认删除书签分类编号为"' + _bookmarkCategoryIds + '"的数据项？').then(function() {
    return delBookmarkCategory(_bookmarkCategoryIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/bookmarkCategory/export', {
    ...queryParams.value
  }, `bookmarkCategory_${new Date().getTime()}.xlsx`)
}

getList();
</script>

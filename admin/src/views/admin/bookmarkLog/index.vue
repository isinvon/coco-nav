<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="操作类型" prop="action">
        <el-input
          v-model="queryParams.action"
          placeholder="请输入操作类型"
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
          v-hasPermi="['admin:bookmarkLog:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:bookmarkLog:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:bookmarkLog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:bookmarkLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookmarkLogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日志ID" align="center" prop="bookmarkLogId" />
      <el-table-column label="书签ID" align="center" prop="bookmarkId" />
      <el-table-column label="操作类型" align="center" prop="action" />
      <el-table-column label="操作人ID" align="center" prop="operatorId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:bookmarkLog:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:bookmarkLog:remove']">删除</el-button>
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

    <!-- 添加或修改书签操作日志对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="bookmarkLogRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="操作类型" prop="action">
          <el-input v-model="form.action" placeholder="请输入操作类型" />
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

<script setup name="BookmarkLog">
import { listBookmarkLog, getBookmarkLog, delBookmarkLog, addBookmarkLog, updateBookmarkLog } from "@/api/admin/bookmarkLog";

const { proxy } = getCurrentInstance();

const bookmarkLogList = ref([]);
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
    bookmarkId: null,
    action: null,
    operatorId: null,
  },
  rules: {
    bookmarkId: [
      { required: true, message: "书签ID不能为空", trigger: "blur" }
    ],
    action: [
      { required: true, message: "操作类型不能为空", trigger: "blur" }
    ],
    operatorId: [
      { required: true, message: "操作人ID不能为空", trigger: "blur" }
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

/** 查询书签操作日志列表 */
function getList() {
  loading.value = true;
  listBookmarkLog(queryParams.value).then(response => {
    bookmarkLogList.value = response.rows;
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
    bookmarkLogId: null,
    bookmarkId: null,
    action: null,
    operatorId: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("bookmarkLogRef");
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
  ids.value = selection.map(item => item.bookmarkLogId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加书签操作日志";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _bookmarkLogId = row.bookmarkLogId || ids.value
  getBookmarkLog(_bookmarkLogId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改书签操作日志";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["bookmarkLogRef"].validate(valid => {
    if (valid) {
      if (form.value.bookmarkLogId != null) {
        updateBookmarkLog(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addBookmarkLog(form.value).then(response => {
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
  const _bookmarkLogIds = row.bookmarkLogId || ids.value;
  proxy.$modal.confirm('是否确认删除书签操作日志编号为"' + _bookmarkLogIds + '"的数据项？').then(function() {
    return delBookmarkLog(_bookmarkLogIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/bookmarkLog/export', {
    ...queryParams.value
  }, `bookmarkLog_${new Date().getTime()}.xlsx`)
}

getList();
</script>

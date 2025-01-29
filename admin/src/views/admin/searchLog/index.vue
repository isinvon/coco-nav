<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="搜索关键词" prop="keyword">
        <el-input
          v-model="queryParams.keyword"
          placeholder="请输入搜索关键词"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="搜索时间" prop="searchTime">
        <el-date-picker clearable
          v-model="queryParams.searchTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择搜索时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="IP地址" prop="ip">
        <el-input
          v-model="queryParams.ip"
          placeholder="请输入IP地址"
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
          v-hasPermi="['admin:searchLog:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:searchLog:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:searchLog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:searchLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="searchLogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日志ID" align="center" prop="searchLogId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="搜索关键词" align="center" prop="keyword" />
      <el-table-column label="搜索时间" align="center" prop="searchTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.searchTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="IP地址" align="center" prop="ip" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:searchLog:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:searchLog:remove']">删除</el-button>
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

    <!-- 添加或修改搜索日志对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="searchLogRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="搜索关键词" prop="keyword">
          <el-input v-model="form.keyword" placeholder="请输入搜索关键词" />
        </el-form-item>
        <el-form-item label="搜索时间" prop="searchTime">
          <el-date-picker clearable
            v-model="form.searchTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择搜索时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="IP地址" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入IP地址" />
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

<script setup name="SearchLog">
import { listSearchLog, getSearchLog, delSearchLog, addSearchLog, updateSearchLog } from "@/api/admin/searchLog";

const { proxy } = getCurrentInstance();

const searchLogList = ref([]);
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
    keyword: null,
    searchTime: null,
    ip: null,
  },
  rules: {
    keyword: [
      { required: true, message: "搜索关键词不能为空", trigger: "blur" }
    ],
    searchTime: [
      { required: true, message: "搜索时间不能为空", trigger: "blur" }
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

/** 查询搜索日志列表 */
function getList() {
  loading.value = true;
  listSearchLog(queryParams.value).then(response => {
    searchLogList.value = response.rows;
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
    searchLogId: null,
    userId: null,
    keyword: null,
    searchTime: null,
    ip: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("searchLogRef");
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
  ids.value = selection.map(item => item.searchLogId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加搜索日志";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _searchLogId = row.searchLogId || ids.value
  getSearchLog(_searchLogId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改搜索日志";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["searchLogRef"].validate(valid => {
    if (valid) {
      if (form.value.searchLogId != null) {
        updateSearchLog(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSearchLog(form.value).then(response => {
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
  const _searchLogIds = row.searchLogId || ids.value;
  proxy.$modal.confirm('是否确认删除搜索日志编号为"' + _searchLogIds + '"的数据项？').then(function() {
    return delSearchLog(_searchLogIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/searchLog/export', {
    ...queryParams.value
  }, `searchLog_${new Date().getTime()}.xlsx`)
}

getList();
</script>

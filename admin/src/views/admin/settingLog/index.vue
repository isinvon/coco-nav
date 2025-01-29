<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设置项键名" prop="settingKey">
        <el-input
          v-model="queryParams.settingKey"
          placeholder="请输入设置项键名"
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
          v-hasPermi="['admin:settingLog:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:settingLog:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:settingLog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:settingLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="settingLogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日志ID" align="center" prop="settingLogId" />
      <el-table-column label="设置项键名" align="center" prop="settingKey" />
      <el-table-column label="旧值" align="center" prop="oldValue" />
      <el-table-column label="新值" align="center" prop="newValue" />
      <el-table-column label="操作人ID" align="center" prop="operatorId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:settingLog:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:settingLog:remove']">删除</el-button>
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

    <!-- 添加或修改设置变更日志对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="settingLogRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设置项键名" prop="settingKey">
          <el-input v-model="form.settingKey" placeholder="请输入设置项键名" />
        </el-form-item>
        <el-form-item label="旧值" prop="oldValue">
          <el-input v-model="form.oldValue" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="新值" prop="newValue">
          <el-input v-model="form.newValue" type="textarea" placeholder="请输入内容" />
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

<script setup name="SettingLog">
import { listSettingLog, getSettingLog, delSettingLog, addSettingLog, updateSettingLog } from "@/api/admin/settingLog";

const { proxy } = getCurrentInstance();

const settingLogList = ref([]);
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
    settingKey: null,
    oldValue: null,
    newValue: null,
    operatorId: null,
  },
  rules: {
    settingKey: [
      { required: true, message: "设置项键名不能为空", trigger: "blur" }
    ],
    newValue: [
      { required: true, message: "新值不能为空", trigger: "blur" }
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

/** 查询设置变更日志列表 */
function getList() {
  loading.value = true;
  listSettingLog(queryParams.value).then(response => {
    settingLogList.value = response.rows;
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
    settingLogId: null,
    settingKey: null,
    oldValue: null,
    newValue: null,
    operatorId: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("settingLogRef");
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
  ids.value = selection.map(item => item.settingLogId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加设置变更日志";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _settingLogId = row.settingLogId || ids.value
  getSettingLog(_settingLogId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改设置变更日志";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["settingLogRef"].validate(valid => {
    if (valid) {
      if (form.value.settingLogId != null) {
        updateSettingLog(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSettingLog(form.value).then(response => {
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
  const _settingLogIds = row.settingLogId || ids.value;
  proxy.$modal.confirm('是否确认删除设置变更日志编号为"' + _settingLogIds + '"的数据项？').then(function() {
    return delSettingLog(_settingLogIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/settingLog/export', {
    ...queryParams.value
  }, `settingLog_${new Date().getTime()}.xlsx`)
}

getList();
</script>

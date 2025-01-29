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
      <el-form-item label="操作时间" prop="logTime">
        <el-date-picker clearable
          v-model="queryParams.logTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择操作时间">
        </el-date-picker>
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
          v-hasPermi="['admin:friendlinkLog:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:friendlinkLog:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:friendlinkLog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:friendlinkLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="friendlinkLogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日志ID" align="center" prop="friendlinkLogId" />
      <el-table-column label="友链ID" align="center" prop="friendlinkId" />
      <el-table-column label="操作类型" align="center" prop="action" />
      <el-table-column label="操作人ID" align="center" prop="operatorId" />
      <el-table-column label="操作时间" align="center" prop="logTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.logTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:friendlinkLog:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:friendlinkLog:remove']">删除</el-button>
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

    <!-- 添加或修改友链操作日志对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="friendlinkLogRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="操作类型" prop="action">
          <el-input v-model="form.action" placeholder="请输入操作类型" />
        </el-form-item>
        <el-form-item label="操作时间" prop="logTime">
          <el-date-picker clearable
            v-model="form.logTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择操作时间">
          </el-date-picker>
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

<script setup name="FriendlinkLog">
import { listFriendlinkLog, getFriendlinkLog, delFriendlinkLog, addFriendlinkLog, updateFriendlinkLog } from "@/api/admin/friendlinkLog";

const { proxy } = getCurrentInstance();

const friendlinkLogList = ref([]);
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
    friendlinkId: null,
    action: null,
    operatorId: null,
    logTime: null,
  },
  rules: {
    friendlinkId: [
      { required: true, message: "友链ID不能为空", trigger: "blur" }
    ],
    action: [
      { required: true, message: "操作类型不能为空", trigger: "blur" }
    ],
    operatorId: [
      { required: true, message: "操作人ID不能为空", trigger: "blur" }
    ],
    logTime: [
      { required: true, message: "操作时间不能为空", trigger: "blur" }
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

/** 查询友链操作日志列表 */
function getList() {
  loading.value = true;
  listFriendlinkLog(queryParams.value).then(response => {
    friendlinkLogList.value = response.rows;
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
    friendlinkLogId: null,
    friendlinkId: null,
    action: null,
    operatorId: null,
    logTime: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("friendlinkLogRef");
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
  ids.value = selection.map(item => item.friendlinkLogId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加友链操作日志";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _friendlinkLogId = row.friendlinkLogId || ids.value
  getFriendlinkLog(_friendlinkLogId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改友链操作日志";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["friendlinkLogRef"].validate(valid => {
    if (valid) {
      if (form.value.friendlinkLogId != null) {
        updateFriendlinkLog(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addFriendlinkLog(form.value).then(response => {
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
  const _friendlinkLogIds = row.friendlinkLogId || ids.value;
  proxy.$modal.confirm('是否确认删除友链操作日志编号为"' + _friendlinkLogIds + '"的数据项？').then(function() {
    return delFriendlinkLog(_friendlinkLogIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/friendlinkLog/export', {
    ...queryParams.value
  }, `friendlinkLog_${new Date().getTime()}.xlsx`)
}

getList();
</script>

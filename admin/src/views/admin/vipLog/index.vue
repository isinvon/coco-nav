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
      <el-form-item label="VIP时长 (秒)" prop="duration">
        <el-input
          v-model="queryParams.duration"
          placeholder="请输入VIP时长 (秒)"
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
          v-hasPermi="['admin:vipLog:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:vipLog:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:vipLog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:vipLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="vipLogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日志ID" align="center" prop="id" />
      <el-table-column label="VIP记录ID" align="center" prop="vipId" />
      <el-table-column label="操作类型" align="center" prop="action" />
      <el-table-column label="VIP时长 (秒)" align="center" prop="duration" />
      <el-table-column label="操作人ID" align="center" prop="operatorId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:vipLog:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:vipLog:remove']">删除</el-button>
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

    <!-- 添加或修改VIP操作日志对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="vipLogRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="操作类型" prop="action">
          <el-input v-model="form.action" placeholder="请输入操作类型" />
        </el-form-item>
        <el-form-item label="VIP时长 (秒)" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入VIP时长 (秒)" />
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

<script setup name="VipLog">
import { listVipLog, getVipLog, delVipLog, addVipLog, updateVipLog } from "@/api/admin/vipLog";

const { proxy } = getCurrentInstance();

const vipLogList = ref([]);
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
    vipId: null,
    action: null,
    duration: null,
    operatorId: null,
  },
  rules: {
    vipId: [
      { required: true, message: "VIP记录ID不能为空", trigger: "blur" }
    ],
    action: [
      { required: true, message: "操作类型不能为空", trigger: "blur" }
    ],
    duration: [
      { required: true, message: "VIP时长 (秒)不能为空", trigger: "blur" }
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

/** 查询VIP操作日志列表 */
function getList() {
  loading.value = true;
  listVipLog(queryParams.value).then(response => {
    vipLogList.value = response.rows;
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
    vipId: null,
    action: null,
    duration: null,
    operatorId: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("vipLogRef");
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
  title.value = "添加VIP操作日志";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getVipLog(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改VIP操作日志";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["vipLogRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateVipLog(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addVipLog(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除VIP操作日志编号为"' + _ids + '"的数据项？').then(function() {
    return delVipLog(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/vipLog/export', {
    ...queryParams.value
  }, `vipLog_${new Date().getTime()}.xlsx`)
}

getList();
</script>

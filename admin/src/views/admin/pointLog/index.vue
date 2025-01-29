<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="变动数值" prop="amount">
        <el-input
          v-model="queryParams.amount"
          placeholder="请输入变动数值"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="变动后积分余额" prop="balance">
        <el-input
          v-model="queryParams.balance"
          placeholder="请输入变动后积分余额"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源模块" prop="source">
        <el-input
          v-model="queryParams.source"
          placeholder="请输入来源模块"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作类型" prop="action">
        <el-input
          v-model="queryParams.action"
          placeholder="请输入操作类型"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="变动说明" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入变动说明"
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
          v-hasPermi="['admin:pointLog:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:pointLog:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:pointLog:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:pointLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pointLogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日志ID" align="center" prop="pointLogId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="1-增加 2-扣除 3-冻结" align="center" prop="changeType" />
      <el-table-column label="变动数值" align="center" prop="amount" />
      <el-table-column label="变动后积分余额" align="center" prop="balance" />
      <el-table-column label="来源模块" align="center" prop="source" />
      <el-table-column label="操作类型" align="center" prop="action" />
      <el-table-column label="变动说明" align="center" prop="description" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:pointLog:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:pointLog:remove']">删除</el-button>
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

    <!-- 添加或修改积分流水对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="pointLogRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="变动数值" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入变动数值" />
        </el-form-item>
        <el-form-item label="变动后积分余额" prop="balance">
          <el-input v-model="form.balance" placeholder="请输入变动后积分余额" />
        </el-form-item>
        <el-form-item label="来源模块" prop="source">
          <el-input v-model="form.source" placeholder="请输入来源模块" />
        </el-form-item>
        <el-form-item label="操作类型" prop="action">
          <el-input v-model="form.action" placeholder="请输入操作类型" />
        </el-form-item>
        <el-form-item label="变动说明" prop="description">
          <el-input v-model="form.description" placeholder="请输入变动说明" />
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

<script setup name="PointLog">
import { listPointLog, getPointLog, delPointLog, addPointLog, updatePointLog } from "@/api/admin/pointLog";

const { proxy } = getCurrentInstance();

const pointLogList = ref([]);
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
    changeType: null,
    amount: null,
    balance: null,
    source: null,
    action: null,
    description: null,
  },
  rules: {
    userId: [
      { required: true, message: "用户ID不能为空", trigger: "blur" }
    ],
    changeType: [
      { required: true, message: "1-增加 2-扣除 3-冻结不能为空", trigger: "change" }
    ],
    amount: [
      { required: true, message: "变动数值不能为空", trigger: "blur" }
    ],
    balance: [
      { required: true, message: "变动后积分余额不能为空", trigger: "blur" }
    ],
    action: [
      { required: true, message: "操作类型不能为空", trigger: "blur" }
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

/** 查询积分流水列表 */
function getList() {
  loading.value = true;
  listPointLog(queryParams.value).then(response => {
    pointLogList.value = response.rows;
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
    pointLogId: null,
    userId: null,
    changeType: null,
    amount: null,
    balance: null,
    source: null,
    action: null,
    description: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("pointLogRef");
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
  ids.value = selection.map(item => item.pointLogId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加积分流水";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _pointLogId = row.pointLogId || ids.value
  getPointLog(_pointLogId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改积分流水";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["pointLogRef"].validate(valid => {
    if (valid) {
      if (form.value.pointLogId != null) {
        updatePointLog(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPointLog(form.value).then(response => {
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
  const _pointLogIds = row.pointLogId || ids.value;
  proxy.$modal.confirm('是否确认删除积分流水编号为"' + _pointLogIds + '"的数据项？').then(function() {
    return delPointLog(_pointLogIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/pointLog/export', {
    ...queryParams.value
  }, `pointLog_${new Date().getTime()}.xlsx`)
}

getList();
</script>

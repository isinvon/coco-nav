<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="支付名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入支付名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支付代码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入支付代码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手续费率" prop="rate">
        <el-input
          v-model="queryParams.rate"
          placeholder="请输入手续费率"
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
          v-hasPermi="['admin:paymentType:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:paymentType:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:paymentType:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:paymentType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="paymentTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="支付方式ID" align="center" prop="id" />
      <el-table-column label="支付名称" align="center" prop="name" />
      <el-table-column label="支付代码" align="center" prop="code" />
      <el-table-column label="手续费率" align="center" prop="rate" />
      <el-table-column label="配置参数" align="center" prop="config" />
      <el-table-column label="0-停用 1-启用" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:paymentType:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:paymentType:remove']">删除</el-button>
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

    <!-- 添加或修改支付方式对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="paymentTypeRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="支付名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入支付名称" />
        </el-form-item>
        <el-form-item label="支付代码" prop="code">
          <el-input v-model="form.code" placeholder="请输入支付代码" />
        </el-form-item>
        <el-form-item label="手续费率" prop="rate">
          <el-input v-model="form.rate" placeholder="请输入手续费率" />
        </el-form-item>
        <el-form-item label="配置参数" prop="config">
          <el-input v-model="form.config" type="textarea" placeholder="请输入内容" />
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

<script setup name="PaymentType">
import { listPaymentType, getPaymentType, delPaymentType, addPaymentType, updatePaymentType } from "@/api/admin/paymentType";

const { proxy } = getCurrentInstance();

const paymentTypeList = ref([]);
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
    name: null,
    code: null,
    rate: null,
    config: null,
    status: null,
  },
  rules: {
    name: [
      { required: true, message: "支付名称不能为空", trigger: "blur" }
    ],
    code: [
      { required: true, message: "支付代码不能为空", trigger: "blur" }
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

/** 查询支付方式列表 */
function getList() {
  loading.value = true;
  listPaymentType(queryParams.value).then(response => {
    paymentTypeList.value = response.rows;
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
    name: null,
    code: null,
    rate: null,
    config: null,
    status: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("paymentTypeRef");
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
  title.value = "添加支付方式";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getPaymentType(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改支付方式";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["paymentTypeRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePaymentType(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPaymentType(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除支付方式编号为"' + _ids + '"的数据项？').then(function() {
    return delPaymentType(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/paymentType/export', {
    ...queryParams.value
  }, `paymentType_${new Date().getTime()}.xlsx`)
}

getList();
</script>

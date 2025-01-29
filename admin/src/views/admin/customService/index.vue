<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客服昵称" prop="nickname">
        <el-input
          v-model="queryParams.nickname"
          placeholder="请输入客服昵称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="account">
        <el-input
          v-model="queryParams.account"
          placeholder="请输入联系方式"
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
          v-hasPermi="['admin:customService:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:customService:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:customService:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:customService:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customServiceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客服ID" align="center" prop="customServiceId" />
      <el-table-column label="客服昵称" align="center" prop="nickname" />
      <el-table-column label="1-QQ 2-微信 3-电话" align="center" prop="accountType" />
      <el-table-column label="联系方式" align="center" prop="account" />
      <el-table-column label="0-离线 1-在线" align="center" prop="onlineStatus" />
      <el-table-column label="排序值" align="center" prop="sortOrder" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:customService:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:customService:remove']">删除</el-button>
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

    <!-- 添加或修改客服信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="customServiceRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客服昵称" prop="nickname">
          <el-input v-model="form.nickname" placeholder="请输入客服昵称" />
        </el-form-item>
        <el-form-item label="联系方式" prop="account">
          <el-input v-model="form.account" placeholder="请输入联系方式" />
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

<script setup name="CustomService">
import { listCustomService, getCustomService, delCustomService, addCustomService, updateCustomService } from "@/api/admin/customService";

const { proxy } = getCurrentInstance();

const customServiceList = ref([]);
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
    nickname: null,
    accountType: null,
    account: null,
    onlineStatus: null,
    sortOrder: null,
  },
  rules: {
    nickname: [
      { required: true, message: "客服昵称不能为空", trigger: "blur" }
    ],
    accountType: [
      { required: true, message: "1-QQ 2-微信 3-电话不能为空", trigger: "change" }
    ],
    account: [
      { required: true, message: "联系方式不能为空", trigger: "blur" }
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

/** 查询客服信息列表 */
function getList() {
  loading.value = true;
  listCustomService(queryParams.value).then(response => {
    customServiceList.value = response.rows;
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
    customServiceId: null,
    nickname: null,
    accountType: null,
    account: null,
    onlineStatus: null,
    sortOrder: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("customServiceRef");
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
  ids.value = selection.map(item => item.customServiceId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加客服信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _customServiceId = row.customServiceId || ids.value
  getCustomService(_customServiceId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改客服信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["customServiceRef"].validate(valid => {
    if (valid) {
      if (form.value.customServiceId != null) {
        updateCustomService(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCustomService(form.value).then(response => {
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
  const _customServiceIds = row.customServiceId || ids.value;
  proxy.$modal.confirm('是否确认删除客服信息编号为"' + _customServiceIds + '"的数据项？').then(function() {
    return delCustomService(_customServiceIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/customService/export', {
    ...queryParams.value
  }, `customService_${new Date().getTime()}.xlsx`)
}

getList();
</script>

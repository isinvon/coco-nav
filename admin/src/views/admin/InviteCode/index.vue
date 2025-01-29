<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="邀请码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入邀请码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="过期时间" prop="expireTime">
        <el-date-picker clearable
          v-model="queryParams.expireTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择过期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTme">
        <el-date-picker clearable
          v-model="queryParams.updateTme"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择更新时间">
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
          v-hasPermi="['admin:InviteCode:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:InviteCode:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:InviteCode:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:InviteCode:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="InviteCodeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="邀请码ID" align="center" prop="inviteCodeId" />
      <el-table-column label="邀请码" align="center" prop="code" />
      <el-table-column label="创建人ID" align="center" prop="creatorId" />
      <el-table-column label="使用者ID" align="center" prop="userId" />
      <el-table-column label="0-未使用 1-已使用" align="center" prop="status" />
      <el-table-column label="过期时间" align="center" prop="expireTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.expireTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTme" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTme, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:InviteCode:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:InviteCode:remove']">删除</el-button>
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

    <!-- 添加或修改邀请码管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="InviteCodeRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="邀请码" prop="code">
          <el-input v-model="form.code" placeholder="请输入邀请码" />
        </el-form-item>
        <el-form-item label="过期时间" prop="expireTime">
          <el-date-picker clearable
            v-model="form.expireTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择过期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updateTme">
          <el-date-picker clearable
            v-model="form.updateTme"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择更新时间">
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

<script setup name="InviteCode">
import { listInviteCode, getInviteCode, delInviteCode, addInviteCode, updateInviteCode } from "@/api/admin/InviteCode";

const { proxy } = getCurrentInstance();

const InviteCodeList = ref([]);
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
    code: null,
    creatorId: null,
    userId: null,
    status: null,
    expireTime: null,
    updateTme: null
  },
  rules: {
    code: [
      { required: true, message: "邀请码不能为空", trigger: "blur" }
    ],
    creatorId: [
      { required: true, message: "创建人ID不能为空", trigger: "blur" }
    ],
    expireTime: [
      { required: true, message: "过期时间不能为空", trigger: "blur" }
    ],
    createTime: [
      { required: true, message: "创建时间不能为空", trigger: "blur" }
    ],
    updateTme: [
      { required: true, message: "更新时间不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询邀请码管理列表 */
function getList() {
  loading.value = true;
  listInviteCode(queryParams.value).then(response => {
    InviteCodeList.value = response.rows;
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
    inviteCodeId: null,
    code: null,
    creatorId: null,
    userId: null,
    status: null,
    expireTime: null,
    createTime: null,
    updateTme: null
  };
  proxy.resetForm("InviteCodeRef");
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
  ids.value = selection.map(item => item.inviteCodeId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加邀请码管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _inviteCodeId = row.inviteCodeId || ids.value
  getInviteCode(_inviteCodeId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改邀请码管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["InviteCodeRef"].validate(valid => {
    if (valid) {
      if (form.value.inviteCodeId != null) {
        updateInviteCode(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addInviteCode(form.value).then(response => {
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
  const _inviteCodeIds = row.inviteCodeId || ids.value;
  proxy.$modal.confirm('是否确认删除邀请码管理编号为"' + _inviteCodeIds + '"的数据项？').then(function() {
    return delInviteCode(_inviteCodeIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/InviteCode/export', {
    ...queryParams.value
  }, `InviteCode_${new Date().getTime()}.xlsx`)
}

getList();
</script>

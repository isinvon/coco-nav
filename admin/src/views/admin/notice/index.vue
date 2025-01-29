<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="通知标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入通知标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发送时间" prop="sendTime">
        <el-date-picker clearable
          v-model="queryParams.sendTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择发送时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="过期时间" prop="expireTime">
        <el-date-picker clearable
          v-model="queryParams.expireTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择过期时间">
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
          v-hasPermi="['admin:notice:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:notice:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:notice:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:notice:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="noticeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="通知ID" align="center" prop="noticeId" />
      <el-table-column label="通知标题" align="center" prop="title" />
      <el-table-column label="通知内容" align="center" prop="content" />
      <el-table-column label="1-系统公告 2-个人消息" align="center" prop="type" />
      <el-table-column label="发送人ID" align="center" prop="senderId" />
      <el-table-column label="发送时间" align="center" prop="sendTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.sendTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="过期时间" align="center" prop="expireTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.expireTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:notice:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:notice:remove']">删除</el-button>
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

    <!-- 添加或修改通知管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="noticeRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="通知标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入通知标题" />
        </el-form-item>
        <el-form-item label="通知内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="发送时间" prop="sendTime">
          <el-date-picker clearable
            v-model="form.sendTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择发送时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="过期时间" prop="expireTime">
          <el-date-picker clearable
            v-model="form.expireTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择过期时间">
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

<script setup name="Notice">
import { listNotice, getNotice, delNotice, addNotice, updateNotice } from "@/api/admin/notice";

const { proxy } = getCurrentInstance();

const noticeList = ref([]);
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
    title: null,
    content: null,
    type: null,
    senderId: null,
    sendTime: null,
    expireTime: null,
  },
  rules: {
    title: [
      { required: true, message: "通知标题不能为空", trigger: "blur" }
    ],
    content: [
      { required: true, message: "通知内容不能为空", trigger: "blur" }
    ],
    type: [
      { required: true, message: "1-系统公告 2-个人消息不能为空", trigger: "change" }
    ],
    senderId: [
      { required: true, message: "发送人ID不能为空", trigger: "blur" }
    ],
    sendTime: [
      { required: true, message: "发送时间不能为空", trigger: "blur" }
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

/** 查询通知管理列表 */
function getList() {
  loading.value = true;
  listNotice(queryParams.value).then(response => {
    noticeList.value = response.rows;
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
    noticeId: null,
    title: null,
    content: null,
    type: null,
    senderId: null,
    sendTime: null,
    expireTime: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("noticeRef");
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
  ids.value = selection.map(item => item.noticeId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加通知管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _noticeId = row.noticeId || ids.value
  getNotice(_noticeId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改通知管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["noticeRef"].validate(valid => {
    if (valid) {
      if (form.value.noticeId != null) {
        updateNotice(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addNotice(form.value).then(response => {
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
  const _noticeIds = row.noticeId || ids.value;
  proxy.$modal.confirm('是否确认删除通知管理编号为"' + _noticeIds + '"的数据项？').then(function() {
    return delNotice(_noticeIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/notice/export', {
    ...queryParams.value
  }, `notice_${new Date().getTime()}.xlsx`)
}

getList();
</script>

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
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
          v-hasPermi="['admin:feedback:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:feedback:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:feedback:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:feedback:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="feedbackList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="反馈ID" align="center" prop="feedbackId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="反馈类型ID" align="center" prop="feedbackTypeId" />
      <el-table-column label="反馈内容" align="center" prop="content" />
      <el-table-column label="0-待处理 1-已处理" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:feedback:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:feedback:remove']">删除</el-button>
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

    <!-- 添加或修改用户反馈对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="feedbackRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="反馈内容">
          <editor v-model="form.content" :min-height="192"/>
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

<script setup name="Feedback">
import { listFeedback, getFeedback, delFeedback, addFeedback, updateFeedback } from "@/api/admin/feedback";

const { proxy } = getCurrentInstance();

const feedbackList = ref([]);
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
    feedbackTypeId: null,
    content: null,
    status: null,
  },
  rules: {
    userId: [
      { required: true, message: "用户ID不能为空", trigger: "blur" }
    ],
    feedbackTypeId: [
      { required: true, message: "反馈类型ID不能为空", trigger: "blur" }
    ],
    content: [
      { required: true, message: "反馈内容不能为空", trigger: "blur" }
    ],
    createTime: [
      { required: true, message: "提交时间不能为空", trigger: "blur" }
    ],
    updateTime: [
      { required: true, message: "更新时间不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询用户反馈列表 */
function getList() {
  loading.value = true;
  listFeedback(queryParams.value).then(response => {
    feedbackList.value = response.rows;
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
    feedbackId: null,
    userId: null,
    feedbackTypeId: null,
    content: null,
    status: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("feedbackRef");
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
  ids.value = selection.map(item => item.feedbackId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加用户反馈";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _feedbackId = row.feedbackId || ids.value
  getFeedback(_feedbackId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改用户反馈";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["feedbackRef"].validate(valid => {
    if (valid) {
      if (form.value.feedbackId != null) {
        updateFeedback(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addFeedback(form.value).then(response => {
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
  const _feedbackIds = row.feedbackId || ids.value;
  proxy.$modal.confirm('是否确认删除用户反馈编号为"' + _feedbackIds + '"的数据项？').then(function() {
    return delFeedback(_feedbackIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/feedback/export', {
    ...queryParams.value
  }, `feedback_${new Date().getTime()}.xlsx`)
}

getList();
</script>

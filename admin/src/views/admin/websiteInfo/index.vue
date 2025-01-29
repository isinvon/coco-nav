<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="网站标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入网站标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="SEO关键词" prop="keywords">
        <el-input
          v-model="queryParams.keywords"
          placeholder="请输入SEO关键词"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备案号" prop="icp">
        <el-input
          v-model="queryParams.icp"
          placeholder="请输入备案号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系统版本" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入系统版本"
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
          v-hasPermi="['admin:websiteInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:websiteInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:websiteInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:websiteInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="websiteInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="信息ID" align="center" prop="websiteInfoId" />
      <el-table-column label="网站标题" align="center" prop="title" />
      <el-table-column label="SEO关键词" align="center" prop="keywords" />
      <el-table-column label="网站描述" align="center" prop="description" />
      <el-table-column label="备案号" align="center" prop="icp" />
      <el-table-column label="系统版本" align="center" prop="version" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:websiteInfo:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:websiteInfo:remove']">删除</el-button>
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

    <!-- 添加或修改网站信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="websiteInfoRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="网站标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入网站标题" />
        </el-form-item>
        <el-form-item label="SEO关键词" prop="keywords">
          <el-input v-model="form.keywords" placeholder="请输入SEO关键词" />
        </el-form-item>
        <el-form-item label="网站描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备案号" prop="icp">
          <el-input v-model="form.icp" placeholder="请输入备案号" />
        </el-form-item>
        <el-form-item label="系统版本" prop="version">
          <el-input v-model="form.version" placeholder="请输入系统版本" />
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

<script setup name="WebsiteInfo">
import { listWebsiteInfo, getWebsiteInfo, delWebsiteInfo, addWebsiteInfo, updateWebsiteInfo } from "@/api/admin/websiteInfo";

const { proxy } = getCurrentInstance();

const websiteInfoList = ref([]);
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
    keywords: null,
    description: null,
    icp: null,
    version: null,
  },
  rules: {
    title: [
      { required: true, message: "网站标题不能为空", trigger: "blur" }
    ],
    version: [
      { required: true, message: "系统版本不能为空", trigger: "blur" }
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

/** 查询网站信息列表 */
function getList() {
  loading.value = true;
  listWebsiteInfo(queryParams.value).then(response => {
    websiteInfoList.value = response.rows;
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
    websiteInfoId: null,
    title: null,
    keywords: null,
    description: null,
    icp: null,
    version: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("websiteInfoRef");
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
  ids.value = selection.map(item => item.websiteInfoId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加网站信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _websiteInfoId = row.websiteInfoId || ids.value
  getWebsiteInfo(_websiteInfoId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改网站信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["websiteInfoRef"].validate(valid => {
    if (valid) {
      if (form.value.websiteInfoId != null) {
        updateWebsiteInfo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addWebsiteInfo(form.value).then(response => {
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
  const _websiteInfoIds = row.websiteInfoId || ids.value;
  proxy.$modal.confirm('是否确认删除网站信息编号为"' + _websiteInfoIds + '"的数据项？').then(function() {
    return delWebsiteInfo(_websiteInfoIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/websiteInfo/export', {
    ...queryParams.value
  }, `websiteInfo_${new Date().getTime()}.xlsx`)
}

getList();
</script>

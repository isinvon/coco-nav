<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="VIP类型名称" prop="typeName">
        <el-input
          v-model="queryParams.typeName"
          placeholder="请输入VIP类型名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="有效期" prop="duration">
        <el-input
          v-model="queryParams.duration"
          placeholder="请输入有效期"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入价格"
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
          v-hasPermi="['admin:vipType:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:vipType:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:vipType:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:vipType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="vipTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="类型ID" align="center" prop="vipTypeId" />
      <el-table-column label="VIP类型名称" align="center" prop="typeName" />
      <el-table-column label="有效期" align="center" prop="duration" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="特权说明" align="center" prop="privileges" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:vipType:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:vipType:remove']">删除</el-button>
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

    <!-- 添加或修改VIP类型对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="vipTypeRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="VIP类型名称" prop="typeName">
          <el-input v-model="form.typeName" placeholder="请输入VIP类型名称" />
        </el-form-item>
        <el-form-item label="有效期" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入有效期" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="特权说明" prop="privileges">
          <el-input v-model="form.privileges" type="textarea" placeholder="请输入内容" />
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

<script setup name="VipType">
import { listVipType, getVipType, delVipType, addVipType, updateVipType } from "@/api/admin/vipType";

const { proxy } = getCurrentInstance();

const vipTypeList = ref([]);
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
    typeName: null,
    duration: null,
    price: null,
    privileges: null,
  },
  rules: {
    typeName: [
      { required: true, message: "VIP类型名称不能为空", trigger: "blur" }
    ],
    duration: [
      { required: true, message: "有效期不能为空", trigger: "blur" }
    ],
    price: [
      { required: true, message: "价格不能为空", trigger: "blur" }
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

/** 查询VIP类型列表 */
function getList() {
  loading.value = true;
  listVipType(queryParams.value).then(response => {
    vipTypeList.value = response.rows;
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
    vipTypeId: null,
    typeName: null,
    duration: null,
    price: null,
    privileges: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("vipTypeRef");
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
  ids.value = selection.map(item => item.vipTypeId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加VIP类型";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _vipTypeId = row.vipTypeId || ids.value
  getVipType(_vipTypeId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改VIP类型";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["vipTypeRef"].validate(valid => {
    if (valid) {
      if (form.value.vipTypeId != null) {
        updateVipType(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addVipType(form.value).then(response => {
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
  const _vipTypeIds = row.vipTypeId || ids.value;
  proxy.$modal.confirm('是否确认删除VIP类型编号为"' + _vipTypeIds + '"的数据项？').then(function() {
    return delVipType(_vipTypeIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/vipType/export', {
    ...queryParams.value
  }, `vipType_${new Date().getTime()}.xlsx`)
}

getList();
</script>

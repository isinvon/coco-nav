<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="等级名称" prop="gradeName">
        <el-input
          v-model="queryParams.gradeName"
          placeholder="请输入等级名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最低积分要求" prop="minPoints">
        <el-input
          v-model="queryParams.minPoints"
          placeholder="请输入最低积分要求"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="等级图标" prop="icon">
        <el-input
          v-model="queryParams.icon"
          placeholder="请输入等级图标"
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
          v-hasPermi="['admin:userGrade:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['admin:userGrade:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['admin:userGrade:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:userGrade:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userGradeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="等级ID" align="center" prop="id" />
      <el-table-column label="等级名称" align="center" prop="gradeName" />
      <el-table-column label="最低积分要求" align="center" prop="minPoints" />
      <el-table-column label="等级特权描述" align="center" prop="privileges" />
      <el-table-column label="等级图标" align="center" prop="icon" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['admin:userGrade:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['admin:userGrade:remove']">删除</el-button>
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

    <!-- 添加或修改用户等级对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="userGradeRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="等级名称" prop="gradeName">
          <el-input v-model="form.gradeName" placeholder="请输入等级名称" />
        </el-form-item>
        <el-form-item label="最低积分要求" prop="minPoints">
          <el-input v-model="form.minPoints" placeholder="请输入最低积分要求" />
        </el-form-item>
        <el-form-item label="等级特权描述" prop="privileges">
          <el-input v-model="form.privileges" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="等级图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入等级图标" />
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

<script setup name="UserGrade">
import { listUserGrade, getUserGrade, delUserGrade, addUserGrade, updateUserGrade } from "@/api/admin/userGrade";

const { proxy } = getCurrentInstance();

const userGradeList = ref([]);
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
    gradeName: null,
    minPoints: null,
    privileges: null,
    icon: null,
  },
  rules: {
    gradeName: [
      { required: true, message: "等级名称不能为空", trigger: "blur" }
    ],
    minPoints: [
      { required: true, message: "最低积分要求不能为空", trigger: "blur" }
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

/** 查询用户等级列表 */
function getList() {
  loading.value = true;
  listUserGrade(queryParams.value).then(response => {
    userGradeList.value = response.rows;
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
    gradeName: null,
    minPoints: null,
    privileges: null,
    icon: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("userGradeRef");
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
  title.value = "添加用户等级";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getUserGrade(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改用户等级";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["userGradeRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateUserGrade(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addUserGrade(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除用户等级编号为"' + _ids + '"的数据项？').then(function() {
    return delUserGrade(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/userGrade/export', {
    ...queryParams.value
  }, `userGrade_${new Date().getTime()}.xlsx`)
}

getList();
</script>

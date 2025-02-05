<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="地区名称" prop="name">
        <el-input
            v-model="queryParams.name"
            placeholder="请输入地区名称"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行政编码" prop="id">
        <el-input
            v-model="queryParams.id"
            placeholder="请输入行政编码"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="行政级别" prop="depth">
        <!--行政级别：1-省 2-市 3-区-->
        <el-button-group>
          <el-button
              :type="queryParams.depth === 1 ? 'danger' : 'default'"
              @click="queryParams.depth = 1">
            省
          </el-button>
          <el-button
              :type="queryParams.depth === 2 ? 'warning' : 'default'"
              @click="queryParams.depth = 2">
            市
          </el-button>
          <el-button
              :type="queryParams.depth === 3 ? 'success' : 'default'"
              @click="queryParams.depth = 3">
            区
          </el-button>
          <el-button
              :type="queryParams.depth === null ? 'info' : 'default'"
              @click="queryParams.depth = null">
            全部
          </el-button>
        </el-button-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['admin:area:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="areaList" @selection-change="handleSelectionChange">
      <!--<el-table-column type="selection" width="55" align="center" />-->
      <el-table-column label="排序" align="center" prop="sort">
        <template #default="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="行政编码" align="center" prop="id" width="80px"/>
      <el-table-column label="地区名称" align="center" prop="name"/>
      <el-table-column label="行政级别" align="center" prop="depth">
        <template #default="scope">
          <TagTool
              :value="scope.row.depth"
              :options="areaDepthTypeList"
          />
        </template>
      </el-table-column>
      <el-table-column label="上级地区ID" align="center" prop="parentId"/>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />
  </div>
</template>

<script setup name="Area">
import {listArea, getArea, delArea, addArea, updateArea, indexArea} from "@/api/admin/area";

const {proxy} = getCurrentInstance();

const areaList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const areaDepthTypeList = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 20,
    name: null,
    parentId: null,
    depth: null,
    id: null,
    sort: null,
  },
  rules: {
    name: [
      {required: true, message: "地区名称不能为空", trigger: "blur"}
    ],
    depth: [
      {required: true, message: "行政级别：1-省 2-市 3-区不能为空", trigger: "blur"}
    ],
    createTime: [
      {required: true, message: "创建时间不能为空", trigger: "blur"}
    ],
    updateTime: [
      {required: true, message: "更新时间不能为空", trigger: "blur"}
    ]
  }
});

const {queryParams, form, rules} = toRefs(data);

/**
 * 获取地区相关类型
 */
function getIndex() {
  indexArea().then(response => {
    console.log(areaDepthTypeList)
    areaDepthTypeList.value = response.data.areaDepthTypeList;
  });
}

/** 查询地区信息列表 */
function getList() {
  loading.value = true;
  listArea(queryParams.value).then(response => {
    areaList.value = response.rows;
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
    parentId: null,
    depth: null,
    sort: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("areaRef");
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
  title.value = "添加地区信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getArea(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改地区信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["areaRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateArea(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addArea(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/area/export', {
    ...queryParams.value
  }, `area_${new Date().getTime()}.xlsx`)
}

getList();
getIndex();
</script>

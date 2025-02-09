<template>
  <div class="app-container">
    <!-- 搜索表单：以操作描述为查询条件 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="操作描述" prop="operationDesc">
        <el-input
            v-model="queryParams.operationDesc"
            placeholder="请输入操作描述"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮行 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['admin:websiteInfoLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 日志列表表格 -->
    <el-table v-loading="loading" :data="websiteInfoLogList" @selection-change="handleSelectionChange">
      <!--<el-table-column type="selection" width="55" align="center" />-->
      <el-table-column label="日志ID" align="center" prop="id" />
      <el-table-column label="网站信息ID" align="center" prop="websiteInfoId" />
      <el-table-column label="操作描述" align="center" prop="operationDesc">
        <template #default="scope">
          <Tooltip
              placement="bottom"
              effect="customized"
              wrap-length="40"
              :tooltip-text="scope.row.operationDesc"
              class="custom-tooltip"
          >
            <el-text tag="b" class="w-150px mb-2" type="info" truncated>
              {{ scope.row.operationDesc }}
            </el-text>
          </Tooltip>
        </template>
      </el-table-column>
      <el-table-column label="操作人ID" align="center" prop="operatorId" />
      <el-table-column label="操作人名称" align="center" prop="operatorName" />
      <el-table-column label="操作时间" align="center" prop="createTime" />
    </el-table>

    <!-- 分页组件 -->
    <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />
  </div>
</template>

<script setup name="WebsiteInfoLog">
import { ref, reactive, toRefs, getCurrentInstance } from 'vue';
import {
  listWebsiteInfoLog,
  getWebsiteInfoLog,
  delWebsiteInfoLog,
  addWebsiteInfoLog,
  updateWebsiteInfoLog,
  // indexWebsiteInfoLog
} from '@/api/admin/websiteInfoLog';

const { proxy } = getCurrentInstance();

const websiteInfoLogList = ref([]);
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
    pageSize: 20,
    websiteInfoId: null,
    operationDesc: null,
    operatorId: null,
    operatorName: null,
    createTime: null,
  },
});

const { queryParams, form, rules } = toRefs(data);


/** 查询网站信息日志列表 */
function getList() {
  loading.value = true;
  listWebsiteInfoLog(queryParams.value).then(response => {
    websiteInfoLogList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

/** 获取网站信息日志相关映射数据（若有需要，可在此扩展，如下拉框数据等） */
// function getIndex() {
//   indexWebsiteInfoLog().then(response => {
//     // 根据需要处理返回的映射数据
//   });
// }

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

/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/websiteInfoLog/export', { ...queryParams.value }, `websiteInfoLog_${new Date().getTime()}.xlsx`);
}

// getIndex();
getList();
</script>

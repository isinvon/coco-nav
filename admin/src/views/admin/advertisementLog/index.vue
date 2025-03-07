<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="操作类型" prop="action">
        <el-input
          v-model="queryParams.action"
          placeholder="请输入操作类型"
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
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['admin:advertisementLog:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="advertisementLogList">
      <el-table-column label="日志ID" align="center" prop="id" />
      <el-table-column label="广告ID" align="center" prop="advertisementId" />
      <el-table-column label="操作类型" align="center" prop="action">
        <template #default="scope">
          <TagTool
              :value="scope.row.action"
              :options="advertisementLogActionTypeList"
          />
        </template>
      </el-table-column>

      <el-table-column label="操作详情" align="center" prop="actionDetails">
        <template #default="scope">
          <Tooltip
              placement="bottom"
              effect="customized"
              wrap-length="40"
              :tooltip-text="scope.row.actionDetails"
              class="custom-tooltip"
          >
            <el-text tag="b" class="w-150px mb-2" type="info" truncated>
              {{ scope.row.actionDetails }}
            </el-text>
          </Tooltip>
        </template>
      </el-table-column>

      <el-table-column label="操作人ID" align="center" prop="operatorId" />
      <el-table-column label="操作人" align="center" prop="operatorName" />
      <el-table-column label="操作时间" align="center" prop="createTime" />
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

<script setup name="AdvertisementLog">
import { listAdvertisementLog, getAdvertisementLog, delAdvertisementLog, addAdvertisementLog, updateAdvertisementLog ,indexAdvertisementLog} from "@/api/admin/advertisementLog";

const { proxy } = getCurrentInstance();

const advertisementLogList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
// 广告操作日志类型
const advertisementLogActionTypeList = ref([])

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 20,
    advertisementId: null,
    action: null,
    actionDetails: "",
    operatorId: null,
    operatorName: null,
    createTime: null,
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询广告操作日志列表 */
function getList() {
  loading.value = true;
  listAdvertisementLog(queryParams.value).then(response => {
    advertisementLogList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

/**
 * 获取广告日志操作类型
 */
function getIndex() {
  indexAdvertisementLog().then(response => {
    advertisementLogActionTypeList.value = response.data.advertisementLogActionTypeList;
  });
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

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/advertisementLog/export', {
    ...queryParams.value
  }, `advertisementLog_${new Date().getTime()}.xlsx`)
}

getList();
getIndex();
</script>

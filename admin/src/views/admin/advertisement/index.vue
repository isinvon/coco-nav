<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="广告标题" prop="title">
        <el-input
            v-model="queryParams.title"
            placeholder="请输入广告标题"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时间范围" prop="dateRange">
        <el-date-picker
            v-model="queryParams.dateRange"
            type="datetimerange"
            start-placeholder="请选择开始日期时间"
            end-placeholder="请选择结束日期时间"
            format="YYYY-MM-DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
            date-format="YYYY/MM/DD ddd"
            time-format="A HH:mm:ss"
            clearable
            style="width: 100%"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="广告状态" prop="status">
        <el-button-group>
          <el-button
              :type="queryParams.status === 1 ? 'success' : ''"
              @click="queryParams.status = 1">正常
          </el-button>
          <el-button
              :type="queryParams.status === null ? 'info' : ''"
              @click="queryParams.status = null">全部
          </el-button>
          <el-button
              :type="queryParams.status === 0 ? 'danger' : ''"
              @click="queryParams.status = 0">下架
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
            type="primary"
            plain
            icon="Plus"
            @click="handleAdd"
            v-hasPermi="['admin:advertisement:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['admin:advertisement:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['admin:advertisement:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['admin:advertisement:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="advertisementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="广告ID" align="center" prop="id" />
      <el-table-column label="广告标题" align="center" prop="title"/>
      <el-table-column label="广告内容" align="center" prop="content"/>
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <TagTool
              :value="scope.row.status"
              :options="advertisementStatusTypeList"
          />
        </template>
      </el-table-column>
      <el-table-column label="点击次数" align="center" prop="clickCount"/>
      <el-table-column label="排序值" align="center" prop="sortOrder"/>
      <el-table-column label="广告的宽度" align="center" prop="width"/>
      <el-table-column label="广告的高度" align="center" prop="height"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['admin:advertisement:edit']">修改
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['admin:advertisement:remove']">删除
          </el-button>
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

    <!-- 添加或修改广告管理对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="advertisementRef" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="广告标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入广告标题"/>
        </el-form-item>
        <el-form-item label="广告内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>

        <el-form-item label="时间范围" prop="dateRange">
          <el-date-picker
              v-model="form.dateRange"
              type="datetimerange"
              start-placeholder="请选择开始日期时间"
              end-placeholder="请选择结束日期时间"
              format="YYYY-MM-DD HH:mm:ss"
              value-format="YYYY-MM-DD HH:mm:ss"
              date-format="YYYY/MM/DD ddd"
              time-format="A HH:mm:ss"
              clearable
              style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <!--<el-form-item label="点击次数" prop="clickCount">-->
        <!--  <el-input v-model="form.clickCount" placeholder="请输入点击次数" />-->
        <!--</el-form-item>-->
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="0">下架</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="排序值" prop="sortOrder">
          <el-input v-model="form.sortOrder" placeholder="请输入排序值"/>
        </el-form-item>
        <el-form-item label="广告的宽度" prop="width">
          <el-input v-model="form.width" placeholder="请输入广告的宽度"/>
        </el-form-item>
        <el-form-item label="广告的高度" prop="height">
          <el-input v-model="form.height" placeholder="请输入广告的高度"/>
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


<script setup name="Advertisement">
import {
  listAdvertisement,
  getAdvertisement,
  delAdvertisement,
  addAdvertisement,
  updateAdvertisement,
  indexAdvertisement
} from "@/api/admin/advertisement";
import {parseTime} from "@/utils/ruoyi.js";
import TagTool from "@/components/TagTool/index.vue";

const {proxy} = getCurrentInstance();

const advertisementList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const advertisementStatusTypeList = ref([]); // 广告状态类型

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    title: null,
    content: null,
    startTime: null,
    endTime: null,
    dateRange: null,
    status: null,
    clickCount: null,
    sortOrder: null,
    width: null,
    height: null,
  },
  rules: {
    title: [
      {required: true, message: "广告标题不能为空", trigger: "blur"}
    ],
    content: [
      {required: true, message: "广告内容不能为空", trigger: "blur"}
    ],
    dateRange: [
      {required: true, message: "时间范围不能为空", trigger: "blur"}
    ],
    status: [
      {required: true, message: "状态不能为空", trigger: "blur"}
    ],
    width: [
      {required: true, message: "广告的宽度不能为空", trigger: "blur"}
    ],
    height: [
      {required: true, message: "广告的高度不能为空", trigger: "blur"}
    ],
    sortOrder: [
      {required: true, message: "排序值不能为空", trigger: "blur"}
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

/** 获取广告相关的类型 （index） */
function getIndex() {
  indexAdvertisement().then(response => {
    advertisementStatusTypeList.value = response.data.advertisementStatusTypeList;
  });
}

/** 查询广告管理列表 */
function getList() {
  loading.value = true;
  listAdvertisement(queryParams.value).then(response => {
    advertisementList.value = response.rows;
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
    title: null,
    content: null,
    startTime: null,
    endTime: null,
    dateRange: null,
    status: null,
    clickCount: null,
    sortOrder: null,
    width: null,
    height: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("advertisementRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;

  if (queryParams.value.dateRange != null) {
    // 将dateRange赋值给startTime和endTime
    queryParams.value.startTime = queryParams.value.dateRange[0];
    queryParams.value.endTime = queryParams.value.dateRange[1];
  }

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
  title.value = "添加广告管理";
}

/**
 * 修改按钮操作
 * 在获取广告详情后，将startTime和endTime映射到dateRange，使得时间范围组件能正确显示
 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getAdvertisement(_id).then(response => {
    // 将返回的数据赋值给表单
    form.value = response.data;
    // 如果存在startTime和endTime，则映射到dateRange数组中
    if (response.data.startTime && response.data.endTime) {
      form.value.dateRange = [response.data.startTime, response.data.endTime];
    }
    open.value = true;
    title.value = "修改广告管理🚩";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["advertisementRef"].validate(valid => {
    if (valid) {

      if (form.value.dateRange != null) {
        // 将dateRange赋值给startTime和endTime
        form.value.startTime = form.value.dateRange[0];
        form.value.endTime = form.value.dateRange[1];
      }

      if (form.value.id != null) {
        updateAdvertisement(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addAdvertisement(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除广告管理编号为"' + _ids + '"的数据项？').then(function () {
    return delAdvertisement(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('admin/advertisement/export', {
    ...queryParams.value
  }, `advertisement_${new Date().getTime()}.xlsx`)
}

getList();
getIndex();
</script>

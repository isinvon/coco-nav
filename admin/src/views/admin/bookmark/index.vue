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
      <!--描述-->
      <el-form-item label="描述" label-position="left" label-width="40px" prop="description">
        <el-input
            v-model="queryParams.description"
            placeholder="请输入描述"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="网站图标" prop="icon">
        <el-input
            v-model="queryParams.icon"
            placeholder="请输入图标链接"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="网站链接" prop="url">
        <el-input
            v-model="queryParams.url"
            placeholder="请输入网站链接"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <!--标签-->
      <el-form-item label="标签" prop="searchTagName">
        <el-autocomplete
            v-model="searchTagName"
            :fetch-suggestions="querySearchBookmarkTagNameAsync"
            placeholder="请输入标签"
            @select="handleSelectTag"
            clearable
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-button-group>
          <el-button
              :type="queryParams.status === 1 ? 'success' : ''"
              @click="queryParams.status = 1">已启用
          </el-button>
          <el-button
              :type="queryParams.status === null ? 'info' : ''"
              @click="queryParams.status = null">全部
          </el-button>
          <el-button
              :type="queryParams.status === 0 ? 'danger' : ''"
              @click="queryParams.status = 0">已禁用
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
            v-hasPermi="['admin:bookmark:add']"
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
            v-hasPermi="['admin:bookmark:edit']"
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
            v-hasPermi="['admin:bookmark:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['admin:bookmark:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookmarkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column fixed="left" label="书签ID" width="150" align="center" prop="id"/>
      <el-table-column label="网站标题" align="left" prop="title">
        <template #default="scope">
          <Tooltip
              placement="right"
              effect="customized"
              :tooltip-text="scope.row.title"
          >
            <el-text tag="b" class="w-150px mb-2" type="info" truncated>
              {{ scope.row.title }}
            </el-text>
          </Tooltip>
        </template>
      </el-table-column>
      <el-table-column label="网站地址" width="200" align="left" prop="url">
        <template #default="scope">
          <Tooltip
              placement="right"
              effect="customized"
              :tooltip-text="scope.row.url"
          >
            <a :href="scope.row.url" target="_blank">
              <el-text tag="b" class="w-150px mb-2" type="primary" truncated>
                {{ scope.row.url }}
              </el-text>
            </a>
          </Tooltip>
        </template>
      </el-table-column>
      <el-table-column label="分类ID" align="center" prop="bookmarkCategoryId"/>
      <el-table-column label="网站图标" align="center" prop="icon">
        <template #default="scope">
          <Tooltip
              placement="right"
              effect="customized"
              :tooltip-text="scope.row.icon"
          >
            <el-image
                :src="isHttpsUrl(scope.row.icon) ? scope.row.icon : bookmarkDefaultIcon"
                fit="contain"
                style="width: 30px; height: 30px;"
            >
              <template #error>
                <div class="image-slot">
                  <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 36 36">
                    <path fill="currentColor"
                          d="M18 6a12 12 0 1 0 12 12A12 12 0 0 0 18 6m-1.49 6a1.49 1.49 0 0 1 3 0v6.89a1.49 1.49 0 1 1-3 0ZM18 25.5a1.72 1.72 0 1 1 1.72-1.72A1.72 1.72 0 0 1 18 25.5"
                          class="clr-i-solid clr-i-solid-path-1"/>
                    <path fill="none" d="M0 0h36v36H0z"/>
                  </svg>
                </div>
              </template>
            </el-image>
          </Tooltip>
        </template>
      </el-table-column>
      <!--网址描述-->
      <el-table-column label="描述" width="300" align="left" prop="description">
        <template #default="scope">
          <Tooltip
              placement="bottom"
              effect="customized"
              wrap-length="40"
              :tooltip-text="scope.row.description"
              class="custom-tooltip"
          >
            <el-text tag="b" class="w-150px mb-2" type="info" truncated>
              {{ scope.row.description }}
            </el-text>
          </Tooltip>
        </template>
      </el-table-column>
      <el-table-column label="点击次数" align="center" prop="clickCount">
        <template #default="scope">
          <el-tag
              type="info"
              :closable="false"
              style="font-weight: bold;">
            {{ scope.row.clickCount }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="标签" align="center" width="250" prop="bookmarkTags">
        <template #default="scope">
          <!-- 遍历bookmarkTags数组，为每个标签对象创建一个el-tag -->
          <el-tag
              v-for="(tag, index) in scope.row.bookmarkTags"
              :key="index"
              type="info"
              style="margin: 5px;"
          >
            <!-- 显示标签对象的tagName属性 -->
            {{ tag.tagName }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="排序值" align="center" width="150" prop="sortOrder">
        <template #default="scope">
          <el-input-number
              v-model="scope.row.sortOrder"
              :min="1"
              label="排序值"
              @change="handleUpdateBookmarkSortOrder(scope.row)"
              v-hasPermi="['admin:bookmark:edit']"
          />
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="状态" align="center" prop="status">
        <template #default="scope">
          <TagTool
              :value="scope.row.status"
              :options="bookmarkStatusTypeList"
              @click="handleUpdateBookmarkStatus(scope.row)"
              v-hasPermi="['admin:bookmark:edit']"
              style="cursor: pointer"
          />
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['admin:bookmark:edit']">修改
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['admin:bookmark:remove']">删除
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

    <!-- 添加或修改书签管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <!--爬取网站信息按钮-->
      <el-form-item label="">
        <el-button type="primary" plain icon="Search" @click="urlCrawl(form.url)">
          爬取网站信息
        </el-button>
      </el-form-item>
      <el-form ref="bookmarkRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="网站标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入网站标题"/>
        </el-form-item>
        <el-form-item label="网站地址" prop="url">
          <el-input v-model="form.url" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="网址描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入网址描述"/>
        </el-form-item>
        <el-form-item label="分类ID" prop="bookmarkCategoryId">
          <el-input v-model="form.bookmarkCategoryId" placeholder="请输入分类ID"/>
        </el-form-item>
        <el-form-item label="网站图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入网站图标"/>
          <!-- 提示要不要使用默认图标, 截取 form.url 的第三个"/"前的域名+favicon.ico作为图标域名, 点击后填入form.icon-->
          <el-button v-show="isHttpsUrl(form.url) && form.url != null" type="primary" @click="getDefaultIcon(form.url)">
            使用 favicon.ico 图标
          </el-button>
        </el-form-item>
        <!--标签 bookmarkTags-->
        <el-form-item label="标签" prop="bookmarkTags">
          <el-input-tag v-model="bookmarkTagsStr" clearable trigger='Space' placeholder="输入标签, 然后按下Space确认"/>
        </el-form-item>
        <el-form-item label="排序值" prop="sortOrder">
          <el-input-number v-model="form.sortOrder" :min="1" label="排序值"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <ButtonGroupTool
              :options="bookmarkStatusTypeList"
              :modelValue="form.status"
              @update:modelValue="value => form.status = value"
          />
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

<script setup name="Bookmark">

import {
  listBookmark,
  getBookmark,
  delBookmark,
  addBookmark,
  updateBookmark,
  getUrlInfoByCrawler,
  indexBookmark
} from "@/api/admin/bookmark";
import TagTool from "@/components/TagTool/index.vue";
import {listBookmarkTagName} from "@/api/admin/bookmarkTag.js";

const {proxy} = getCurrentInstance();

const bookmarkList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const bookmarkStatusTypeList = ref([]); // 书签状态类型
const bookmarkDeleteStatusList = ref([]); // 书签删除状态类型
const bookmarkDefaultIcon = ref(''); // 书签默认图标
const bookmarkTagsStr = ref([]) // 标签
const searchTagName = ref("") // 用于搜索的标签名称

// 用于保存修改操作时的初始数据，用于判断数据是否有变化
const originalForm = ref(null);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    title: null,
    url: null,
    description: null,
    bookmarkCategoryId: null,
    icon: null,
    clickCount: null,
    sortOrder: null,
    status: null,
    isDeleted: null,
    bookmarkTags: [],
    createTime: null,
    updateTime: null,
  },
  rules: {
    title: [
      {required: true, message: "网站标题不能为空", trigger: "blur"}
    ],
    url: [
      {required: true, message: "网站地址不能为空", trigger: "blur"},
      // 校验
      {
        pattern: /^(https?|ftp|file):\/\/[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]/,
        message: '请输入正确的网址链接',
        trigger: 'blur'
      }
    ],
    // 描述
    description: [
      {required: true, message: "网站描述不能为空", trigger: "blur"},
    ],
    bookmarkCategoryId: [
      {required: true, message: "分类ID不能为空", trigger: "blur"}
    ],
    icon: [
      {required: true, message: "网站图标不能为空", trigger: "blur"},
      // 校验
      {
        pattern: /^(https?|ftp|file):\/\/[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]/,
        message: '请输入正确的图标链接',
        trigger: 'blur'
      }
    ],
    bookmarkTags: [
      {required: false, trigger: "blur"},
      // // 校验, 不允许特殊字符
      // {
      //   pattern: /^[a-zA-Z0-9\u4e00-\u9fa5]+$/,
      //   message: '标签只能包含中文、英文字母和数字',
      //   trigger: 'blur'
      // }
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
 * 获取初始化索引数据
 */
function getIndex() {
  indexBookmark().then(response => {
    bookmarkStatusTypeList.value = response.data.bookmarkStatusTypeList;
    bookmarkDeleteStatusList.value = response.data.bookmarkDeleteStatusList;
    bookmarkDefaultIcon.value = response.data.bookmarkDefaultIcon;
  });
}

/**
 * 查询书签管理列表
 */
function getList() {
  loading.value = true;
  listBookmark(queryParams.value).then(response => {
    bookmarkList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

/**
 * 取消按钮操作，关闭对话框并重置表单数据
 */
function cancel() {
  open.value = false;
  reset();
}

/**
 * 表单重置方法
 */
function reset() {
  form.value = {
    id: null,
    title: null,
    url: null,
    description: null,
    bookmarkCategoryId: null,
    icon: null,
    clickCount: null,
    sortOrder: 1,
    status: 1,
    isDeleted: 0,
    bookmarkTags: [],
    createTime: null,
    updateTime: null,
  };
  // 重置初始数据变量
  originalForm.value = null;
  proxy.resetForm("bookmarkRef");
  bookmarkTagsStr.value = [];
}

/**
 * 搜索按钮操作
 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/**
 * 重置按钮操作
 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

/**
 * 多选框选中数据
 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/**
 * 新增按钮操作
 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加书签管理";
}

/**
 * 修改按钮操作
 * @param {Object} row - 选中的书签数据对象
 */
function handleUpdate(row) {
  reset();
  // 获取待修改的书签ID
  const _id = row.id || ids.value;
  getBookmark(_id).then(response => {
    // 设置表单数据
    form.value = response.data;
    // 保存初始数据，用于后续判断是否有修改（深拷贝）
    originalForm.value = JSON.parse(JSON.stringify(response.data));
    // 将 bookmarkTags 转换为字符串数组
    bookmarkTagsStr.value = form.value.bookmarkTags.map(tag => tag.tagName);
    // 将 bookmarkTagsStr 放到 originalForm 中, 用于后续比较
    originalForm.value.bookmarkTags = bookmarkTagsStr.value.map(tag => ({tagName: tag}));
    open.value = true;
    title.value = "修改书签管理";
  });
}

/**
 * 提交按钮操作
 */
function submitForm() {
  // 表单校验
  proxy.$refs["bookmarkRef"].validate(valid => {
    if (valid) {
      // 判断是否为修改操作
      if (form.value.id != null) {

        // 转化为对象数组之后才能赋值给bookmarkTags
        form.value.bookmarkTags = bookmarkTagsStr.value.map(tag => ({tagName: tag}));

        // 判断表单数据是否有改变，如果没有改变则提示并不发起更新请求
        if (JSON.stringify(form.value) === JSON.stringify(originalForm.value)) {
          proxy.$modal.msg("没有任何改变");
          return;
        }

        // 执行修改操作
        updateBookmark(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {

        // 转化为对象数组之后才能赋值给bookmarkTags
        form.value.bookmarkTags = bookmarkTagsStr.value.map(tag => ({tagName: tag}));

        // 执行新增操作
        addBookmark(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/**
 * 删除按钮操作
 * @param {Object} row - 待删除的书签数据对象
 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除书签管理编号为"' + _ids + '"的数据项？').then(function () {
    return delBookmark(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/**
 * 导出按钮操作
 */
function handleExport() {
  proxy.download('admin/bookmark/export', {
    ...queryParams.value
  }, `bookmark_${new Date().getTime()}.xlsx`)
}

/**
 * 修改状态按钮操作
 * @param {Object} row - 待修改状态的书签数据对象
 */
function handleUpdateBookmarkStatus(row) {
  // 拷贝插槽数据 row 到 form.value
  form.value = row;
  // 使用三元表达式切换 form.status
  form.value.status = row.status === 0 ? 1 : 0;
  // 执行更新操作
  if (form.value.id != null) {
    updateBookmark(form.value).then(response => {
      // 使用三元表达式显示消息
      form.value.status === 0
          ? proxy.$modal.msgWarning("书签已禁用")
          : proxy.$modal.msgSuccess("书签已启用");
      getList();
    });
  }
}

/**
 * 修改排序按钮操作
 * @param {Object} row - 待修改排序的书签数据对象
 */
function handleUpdateBookmarkSortOrder(row) {
  // 拷贝插槽数据 row 到 form.value
  form.value = row;
  // 执行更新操作
  if (form.value.id != null) {
    updateBookmark(form.value).then(response => {
      // 使用三元表达式显示消息
      form.value.sortOrder === 1
          ? proxy.$modal.msgSuccess("🔝书签已置顶")
          : null;
    });
  }
}

/**
 * 判断是否是HTTPS链接
 * @param {String} url - 链接地址
 * @returns {Boolean} 是否为HTTPS链接
 */
const isHttpsUrl = (url) => {
  const urlPattern = /^(https?):\/\/[^\s/$.?#].[^\s]*$/i;
  return urlPattern.test(url);
};

/**
 * url信息爬虫方法
 * @param {String} url - 待爬取的链接地址
 */
function urlCrawl(url) {
  if (url) {
    // 校验是否为链接
    const urlPattern = /^(https?):\/\/[^\s/$.?#].[^\s]*$/i;
    let b = urlPattern.test(url);
    // 如果校验不通过
    if (!b) {
      proxy.$modal.msgWarning("请输入有效的网址");
      return;
    }
  } else {
    proxy.$modal.msgWarning("请先在网站地址框中输入链接");
    return;
  }

  getUrlInfoByCrawler(url).then(response => {
    if (response && response.data) {
      // 更新表单字段
      form.value.title = response.data.title || "未获取到标题";
      form.value.icon = response.data.icon || bookmarkDefaultIcon.value;
      form.value.description = response.data.description || "未获取到描述";
      // 爬取成功提示
      proxy.$modal.msgSuccess("爬取成功");
    } else {
      proxy.$modal.msgError("爬取失败，请检查URL是否有效");
    }
  }).catch(error => {
    proxy.$modal.msgError("爬取失败，请检查网络或URL");
  });
}


/**
 * 获取默认图标方法
 * 从给定的 url 中提取协议和域名部分，然后拼接 "/favicon.ico" 作为默认图标地址，
 * 点击按钮后将生成的地址赋值给 form.icon。
 * @param {String} url - 用户输入的网址
 */
function getDefaultIcon(url) {
  try {
    // 使用 URL 对象解析传入的 url
    const urlObj = new URL(url);
    // 获取 url 的 origin 部分（协议 + 域名 + 端口，例如：https://www.example.com）
    const origin = urlObj.origin;
    // 拼接默认图标地址
    const defaultIcon = origin + '/favicon.ico';
    // 将默认图标地址赋值给 form.icon
    form.value.icon = defaultIcon;
  } catch (error) {
    // 如果解析 url 失败，则提示用户输入正确的网址
    console.error("解析 URL 失败: ", error);
    // 此处可以使用弹框或者其他方式提示用户错误信息
    proxy.$modal.msgError("无法解析网址，请检查输入是否正确");
  }
}

/**
 * 异步查询书签标签名称列表
 * @param {string} queryString - 用户输入的查询字符串
 * @param {Function} cb - 回调函数，用于返回建议项
 */
function querySearchBookmarkTagNameAsync(queryString, cb) {
  // 调用后端接口获取标签名称列表
  listBookmarkTagName()
      .then(response => {
        if (response.code === 200) {
          // 将标签名称列表转换为符合 el-autocomplete 要求的格式
          const tagNames = response.data || [];
          const suggestions = tagNames.map(tagName => ({
            value: tagName, // value 是 el-autocomplete 显示的内容
          }));

          // 根据用户输入过滤建议项
          const filteredSuggestions = queryString
              ? suggestions.filter(suggestion =>
                  suggestion.value.toLowerCase().includes(queryString.toLowerCase())
              )
              : suggestions;

          // 调用回调函数返回过滤后的建议项
          cb(filteredSuggestions);
        } else {
          // 如果接口调用失败，返回空数组
          cb([]);
        }
      })
      .catch(error => {
        proxy.$modal.msgError("查询书签标签名称列表失败", error);
        cb([]);
      });
}

/**
 * 处理标签选择事件
 * @param {Object} selectedItem - 选中的建议项对象（如 { value: "影视" }）
 */
function handleSelectTag(selectedItem) {
  // 将选中的标签名同步到 queryParams.bookmarkTags
    queryParams.value.bookmarkTags = [{tagName: selectedItem.value}];
}

getList();
getIndex();
</script>

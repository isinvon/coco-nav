<template>
  <div class="banner" :style="{ background: bannerBackground }">
    <div class="search-container">
      <el-input
          v-model="searchQuery"
          placeholder="请输入搜索内容"
          clearable
          class="search-input"
          @keyup.enter="performSearch"
      >
        <template #prepend>
          <el-dropdown trigger="click" @command="setSearchEngine" class="dropdown">
            <el-button class="engine-btn">
              {{ currentEngine.name }}
              <i class="el-icon-arrow-down"></i>
            </el-button>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item
                    v-for="engine in searchEngines"
                    :key="engine.name"
                    :command="engine"
                >
                  {{ engine.name }}
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-input>
      <el-button
          circle
          class="settings-btn"
          @click="openSettings"
      >⚙️
      </el-button>
    </div>

    <el-dialog
        title="设置"
        v-model="settingsVisible"
        width="30%"
        :destroy-on-close="true"
    >
      <div class="settings-container">
        <el-form :model="settings" label-width="100px">
          <el-form-item label="背景颜色">
            <el-color-picker v-model="settings.background"/>
          </el-form-item>
          <el-form-item label="引擎列表">
            <el-input
                v-for="(engine, index) in settings.engines"
                :key="index"
                v-model="settings.engines[index].name"
                placeholder="引擎名称"
                class="engine-input"
            />
            <el-input
                v-for="(engine, index) in settings.engines"
                :key="'url-' + index"
                v-model="settings.engines[index].url"
                placeholder="引擎URL"
                class="engine-input"
            />
            <el-button type="text" icon="el-icon-circle-plus-outline" @click="addEngine">
              添加引擎
            </el-button>
          </el-form-item>
        </el-form>
      </div>
      <template #footer>
        <el-button @click="closeSettings">取消</el-button>
        <el-button type="primary" @click="saveSettings">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue";

const searchQuery = ref("");
const currentEngine = ref({
  name: "百度",
  url: "https://www.baidu.com/s?wd=",
});
const searchEngines = ref([
  {name: "百度", url: "https://www.baidu.com/s?wd="},
  {name: "Google", url: "https://www.google.com/search?q="},
  {name: "Bing", url: "https://www.bing.com/search?q="},
]);
const bannerBackground = ref("url('https://picsum.photos/2000/400') no-repeat center center / cover");
const settingsVisible = ref(false);

const settings = reactive({
  background: bannerBackground.value,
  engines: searchEngines.value.map((engine) => ({...engine})),
});

const performSearch = () => {
  if (searchQuery.value.trim() === "") return;
  const targetUrl = `${currentEngine.value.url}${encodeURIComponent(
      searchQuery.value
  )}`;
  window.open(targetUrl, "_blank");
};

const setSearchEngine = (engine) => {
  currentEngine.value = engine;
};

const openSettings = () => {
  settingsVisible.value = true;
};

const closeSettings = () => {
  settingsVisible.value = false;
};

const saveSettings = () => {
  searchEngines.value = settings.engines.filter(
      (engine) => engine.name && engine.url
  );
  bannerBackground.value = settings.background;
  closeSettings();
};

const addEngine = () => {
  settings.engines.push({name: "", url: ""});
};
</script>

<style lang="less" scoped>
.banner {
  width: 100%;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  border-radius: 10px;
  margin-bottom: 25px;
}

.search-container {
  display: flex;
  gap: 15px;
  align-items: center;

  .search-input {
    box-shadow: 0 0 0 0.1px #939393;
    border-radius: 12px;
    // 使用 :deep() 伪类选择器，设置 .el-input__wrapper 的样式来覆盖 el-input 默认样式, 实现 el-input 圆角
    :deep(.el-input__wrapper) {
      width: 600px;
      font-size: 16px;
      border-top-right-radius: 12px; /* 右上角设置圆角 */
      border-bottom-right-radius: 12px; /* 右下角设置圆角 */
      box-shadow: none;
    }
  }

  // 修改引擎选择按钮engine-btn的样式
  :deep(.el-input-group__prepend) {
    border-top-left-radius: 12px; /* 设置左上角圆角 */
    border-bottom-left-radius: 12px; /* 设置左下角圆角 */
  }

  .dropdown {
    .engine-btn {
      height: 45px; // 由engine-btn决定搜索框的高度,搜索框无需设置高度
      outline: none; /* 去除点击后的边框 */
      box-shadow: none; /* 去除可能的阴影效果 */
      color: #888888;
      font-size: 16px;
    }
  }

  .settings-btn {
    font-size: 30px;
    background: none;
    color: gray;
    border: none;
    cursor: pointer;

    &:hover {
      color: black;
      // 旋转
      transform: rotate(45deg);
    }

    &:focus {
      outline: none; /* 去除点击后的边框 */
      box-shadow: none; /* 去除可能的阴影效果 */
    }
  }
}

.settings-container {
  .engine-input {
    margin-bottom: 10px;
    width: 100%;

    &:focus {
      outline: none; /* 去除点击后的边框 */
      box-shadow: none; /* 去除可能的阴影效果 */
    }
  }
}
</style>

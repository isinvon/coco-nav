<template>
  <el-switch
      @click="toggleDarkMode"
      v-model="darkMode"
      :active-action-icon="Sunny"
      :inactive-action-icon="Moon"
      :size="size"
      class="theme-switch-btn"
  />
</template>

<script setup lang="js">
/**
 * @description: 暗色/夜间主题切换按钮
 * @author: sinvon
 * @since 2025年1月1日21:26:59
 */
import {ref} from 'vue'
import {Moon, Sunny} from '@element-plus/icons-vue'
import {useDarkThemeStore} from "@/store/darkThemeStore.js"; // 引入自定义钩子

const darkThemeStore = useDarkThemeStore()

const darkMode = ref(darkThemeStore.isDarkMode)

function toggleDarkMode() {
  console.log('darkMode', darkMode.value)
  darkThemeStore.toggleDarkMode(darkMode.value) // 调用自定义钩子来切换暗黑模式
}

const props = defineProps({
  /**
   * @type {'small'|'normal'|'large'}
   * @default small
   */
  size: {
    type: String,
    default: 'small'
  }
})
</script>

<style lang="less" scoped>
.theme-switch-btn {
  :deep(.el-icon) {
    font-size: 17px;
    color: #ffffff;
    background-color: #dcdcdc;
    border-radius: 1000px;
    padding: 1px;
  }

  --el-switch-on-color: #f1f1f1; /* 开启时的背景颜色，黄色 */
  --el-switch-off-color: #2c3e50; /* 关闭时的背景颜色，深灰色 */
  --el-switch-border-color: #bdc3c7; /* 边框颜色，浅灰色 */
}
</style>

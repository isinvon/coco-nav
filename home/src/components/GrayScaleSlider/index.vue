<template>
  <el-slider
      v-model="grayScaleLevel"
      :min="0"
      :max="100"
      @change="updateGrayScale"
      class="gray-scale-slider custom-slider"
  />
</template>

<script setup>
/**
 * 页面灰度调节组件
 * @author sinvon
 * @since 2025年1月1日17:08:13
 */
import {ref} from 'vue';
import {useGrayScaleStore} from '@/store/grayScaleStore';

defineOptions({
  name: 'GrayScaleSlider'
})

const grayScaleStore = useGrayScaleStore();  // 获取store

const grayScaleLevel = ref(grayScaleStore.grayScaleLevel);  // 获取初始化的灰度值

// 滑块值变化时更新store和localStorage
function updateGrayScale() {
  grayScaleStore.setGrayScaleLevel(grayScaleLevel.value);  // 更新灰度值到store
}
</script>
<style scoped lang="less">
.custom-slider {
  :deep(.el-slider__runway) {
    height: 2px;
    background-color: #d9d9d9; /* 背景颜色 */
  }

  :deep(.el-slider__button) {
    width: 15px; /* 滑块宽度 */
    border-color: #9d9d9d;
    border-radius: 5px; /* 圆角 */
  }

  :deep(.el-slider__bar) {
    height: 2px;
    background-color: #9d9d9d; /* 已滑动部分的颜色 */
  }
}
</style>

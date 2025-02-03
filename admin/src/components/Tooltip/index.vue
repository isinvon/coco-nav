<script setup>
/**
 * @description 自定义 Tooltip 组件
 * @param {String} tooltipText - 提示文本内容
 * @param {String} placement - 提示位置，可选值为 top、bottom、left、right
 * @param {String} effect - 主题效果，可选值为 dark、light、customized
 * @example <Tooltip tooltipText="提示文本" placement="bottom" effect="dark"></Tooltip>
 * @author sinvon
 * @since 2024年12月4日21:54:06
 * @module Tooltip
 */
// 定义接受的属性
defineProps({
  /**
   * @type {String}
   * @required
   */
  tooltipText: {
    type: String,
    required: true,
  },
  /**
   * @type {'top'|'bottom'|'left'|'right'}
   * @default 'bottom'
   */
  placement: {
    type: String,
    default: 'bottom', // 默认位置为 top
    validator: (value) => ['top', 'bottom', 'left', 'right'].includes(value),
  },
  // TODO - Bug: 当父组件调用 Tooltip 的时候,effect=customized 不生效
  /**
   * @type {'dark'|'light'|'customized'}
   * @default 'dark'
   */
  effect: {
    type: String,
    default: 'dark', // 默认主题效果为 dark
    validator: (value) => ['dark', 'light', 'customized'].includes(value),
  },
});
</script>

<template>
  <el-tooltip :content="tooltipText" :placement="placement" :effect="effect">
    <template #default>
      <slot>Default Tooltip</slot>
    </template>
  </el-tooltip>
</template>

<style lang="scss" scoped>
.el-popper.is-customized {
  // Set padding to ensure the height is 32px
  padding: 6px 12px;
  background: linear-gradient(90deg, rgb(159, 229, 151), rgb(204, 229, 129));

  .el-popper__arrow::before {
    background: linear-gradient(45deg, #b2e68d, #bce689);
    right: 0;
  }
}
</style>

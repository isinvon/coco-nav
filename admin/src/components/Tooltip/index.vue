<script setup>
import {computed} from "vue";

/**
 * @description 自定义 Tooltip 组件
 *
 * @param {string} tooltipText - 提示文本内容，必填
 * @param {('top'|'bottom'|'left'|'right')} [placement='bottom'] - 提示框位置，默认 'bottom'
 * @param {('dark'|'light'|'customized')} [effect='dark'] - 主题效果，默认 'dark'
 * @param {number} [wrapLength=30] - 多少个字符换行，默认 30
 *
 * @example <Tooltip tooltipText="这是一个提示" placement="top" effect="light" :wrapLength="20"></Tooltip>
 *
 * @author sinvon
 * @since 2024年12月4日21:54:06
 * @module Tooltip
 */
const props = defineProps({
  /**
   * @description 提示文本内容
   * @type {string}
   * @required
   */
  tooltipText: {
    type: String,
    required: true
  },

  /**
   * @description Tooltip 显示位置
   * @type {'top'|'bottom'|'left'|'right'}
   * @default 'bottom'
   */
  placement: {
    type: String,
    default: "bottom"
  },

  /**
   * @description Tooltip 主题效果
   * @type {'dark'|'light'|'customized'}
   * @default 'dark'
   */
  effect: {
    type: String,
    default: "dark"
  },

  /**
   * @description 多少个字符后自动换行
   * @type {number}
   * @default 30
   */
  wrapLength: {
    type: Number,
    default: 30
  },
});

/**
 * 计算格式化的文本，确保数据不会丢失。
 * 使用正则表达式按 `wrapLength` 进行切分，并用 `<br>` 连接，保证文本完整显示。
 * @type {import('vue').ComputedRef<string>}
 */
const formattedText = computed(() => {
  if (!props.tooltipText) return "";
  return props.tooltipText
      .match(new RegExp(`.{1,${props.wrapLength}}`, "g")) // 按 `wrapLength` 切分
      .join("<br>"); // 用 `<br>` 连接，保证换行
});
</script>

<template>
  <!-- Element Plus Tooltip 组件 -->
  <el-tooltip :content="formattedText" :placement="placement" :effect="effect" raw-content>
    <template #default>
      <slot>Default Tooltip</slot>
    </template>
  </el-tooltip>
</template>

<!-- 自定义 Tooltip 样式 -->
<style lang="scss">
.el-popper.is-customized {
  color: gray;
  font-weight: bold;
  padding: 6px 12px;
  background: linear-gradient(90deg, rgb(159, 229, 151), rgb(204, 229, 129));

  .el-popper__arrow::before {
    background: linear-gradient(45deg, #b2e68d, #bce689);
    right: 0;
  }
}
</style>

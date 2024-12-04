<template>
  <ul
      v-infinite-scroll="loadMore"
      class="infinite-list"
      style="overflow: auto"
  >
    <slot></slot>
  </ul>
</template>

<script setup>
/**
 * 无限滚动列表组件
 * @author sinvon
 * @since 2024年12月5日01:39:34
 * @module InfiniteScrollBox
 */

// 接收父组件传递的 `loadMore` 方法
defineProps({
  loadMore: {
    type: Function,
    required: true,
  },
})
</script>

<style scoped lang="less">
.infinite-list {
  height: 300px;
  padding: 0;
  margin: 0;
  list-style: none;
  overflow: auto;

}

.infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: var(--el-color-primary-light-9);
  margin: 10px;
  color: var(--el-color-primary);
}
</style>

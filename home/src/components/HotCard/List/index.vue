<script lang="js" setup>
/**
 * 热点列表
 * @author sinvon
 * @since 2024年12月5日23:30:44
 * @module List
 */
import {ref} from 'vue'

const count = ref(0)
const load = () => {
  count.value += 2
}
</script>

<template>
  <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">
    <li v-for="i in count" :key="i" class="infinite-list-item">{{ i }}</li>
  </ul>
</template>

<style lang="less" scoped>
.infinite-list {
  padding: 0;
  margin: -15px;
  height: 350px;
  list-style: none;
  /* 隐藏滚动条,但是保留滚动功能========开始========== */

  ::-webkit-scrollbar {
    width: 0; /* 对垂直滚动条 */
    height: 0; /* 对水平滚动条 */
  }

  -ms-overflow-style: none; /* IE 和 Edge */
  scrollbar-width: none; /* Firefox */

  &::-webkit-scrollbar {
    display: none;
  }

  /* 隐藏滚动条,但是保留滚动功能========结束========== */
}

.infinite-list .infinite-list-item {
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: var(--el-color-primary-light-9);
  margin: 10px;
  color: var(--el-color-primary);
}

.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}
</style>

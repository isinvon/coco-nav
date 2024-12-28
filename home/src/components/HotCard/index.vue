<script setup>
/**
 * @name HotCard
 * @module HotCard
 * @description 热点卡片组件（支持无限滚动功能）
 * @author sinvon
 * @since 2024年12月5日22:59:21
 */

import {useHotCardStore} from "@/store/hotCard.js";
import List from './List/index.vue'

const hotCardStore = useHotCardStore()
// 接收父组件传入的新闻数据
defineProps({
  newsList: {
    type: Array,
    required: true, // 确保父组件传入新闻列表
  },
});
</script>

<template>
  <el-card v-if="hotCardStore.isOpen" class="hot-card" style="max-width: 480px">
    <!--<h3>热点新闻</h3>-->
    <!-- 将新闻列表数据传递给子组件 -->
    <List :newsList="newsList"/>
  </el-card>
</template>

<style lang="less" scoped>
.hot-card {
  width: 350px;
  border-radius: 10px;
  padding: 0;
}

h3 {
  margin: 10px 0;
  text-align: center;
  font-weight: bold;
  color: var(--el-color-primary-dark-1);
}
</style>

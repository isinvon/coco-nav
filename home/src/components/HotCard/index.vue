<script setup>
/**
 * @name Card
 * @module Card
 * @description 卡片组件
 * @author sinvon
 * @since 2024年12月5日22:59:21
 */
import {useHotCardStore} from "@/store/hotCard.js";
import List from './List/index.vue'

const hotCardStore = useHotCardStore()
</script>

<template>
  <el-card class="hot-card" v-if="hotCardStore.isOpen" style="max-width: 480px">
    <List/>
  </el-card>
</template>

<style lang="less" scoped>
.hot-card {
  width: 350px;
  border-radius: 10px;
  padding: 0;
}
</style>

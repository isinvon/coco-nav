<template>
  <!--<el-button type="primary" style="margin-left: 16px" @click="drawer = true">-->
  <!--  open-->
  <!--</el-button>-->
  <el-drawer direction="ltr" v-model="leftDrawerStore.isOpen" title="我是左边的抽屉" :with-header="false">
    <span>Hi there!</span>
  </el-drawer>
</template>

<script lang="js" setup>
import { useLeftDrawerStore } from '@/store/leftDrawer'
const leftDrawerStore = useLeftDrawerStore()
</script>

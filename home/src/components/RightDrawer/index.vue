<template>
  <!--<el-button type="primary" style="margin-left: 16px" @click="drawer = true">-->
  <!--  open-->
  <!--</el-button>-->
  <el-drawer direction="rtl" v-model="rightDrawerStore.isOpen" title="我是右边的抽屉" :with-header="false">
    <span>Hi there!</span>
  </el-drawer>
</template>

<script lang="js" setup>
import { useRightDrawerStore } from '@/store/rightDrawer'
const rightDrawerStore = useRightDrawerStore()
</script>

<script lang="js" setup>
/**
 * @module RecycleBinDrawer
 * @description 回收站抽屉组件
 * @author sinvon
 * @since 2024年12月4日22:12:10
 */
import {useRecycleBinDrawerStore} from '@/store/recycleBinDrawer'

const recycleBinDrawerStore = useRecycleBinDrawerStore()
</script>

<template>
  <!--<el-button type="primary" style="margin-left: 16px" @click="drawer = true">-->
  <!--  open-->
  <!--</el-button>-->
  <el-drawer direction="rtl" v-model="recycleBinDrawerStore.isOpen" title="我是右边的抽屉" :with-header="false">
    <span>Hi there!</span>
  </el-drawer>
</template>

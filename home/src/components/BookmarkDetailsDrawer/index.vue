<template>
  <!--<el-button type="primary" style="margin-left: 16px" @click="drawer = true">-->
  <!--  open-->
  <!--</el-button>-->
  <el-drawer direction="btt" v-model="bookmarkDetailsDrawerStore.isOpen" title="书签详情抽屉" :with-header="false">
    <span>书签详情抽屉</span>
  </el-drawer>
</template>

<script lang="js" setup>
/**
 * @description 书签详情抽屉
 */
import {useBookmarkDetailsDrawerStore} from '@/store/bookmarkDetailsDrawer'

const bookmarkDetailsDrawerStore = useBookmarkDetailsDrawerStore()
</script>

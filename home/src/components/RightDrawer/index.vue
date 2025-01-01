<template>
  <!--<el-button type="primary" style="margin-left: 16px" @click="drawer = true">-->
  <!--  open-->
  <!--</el-button>-->
  <el-drawer direction="rtl" v-model="rightDrawerStore.isOpen" title="我是右边的抽屉" :with-header="false">
    <!--页面灰度调节器-->
    <GrayScaleSlider/>
  </el-drawer>
</template>

<script lang="js" setup>
import { useRightDrawerStore } from '@/store/rightDrawer'
import GrayScaleSlider from "@/components/GrayScaleSlider/index.vue";
const rightDrawerStore = useRightDrawerStore()
</script>

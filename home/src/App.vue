<script setup>
import {useHot} from '@/hooks/useHot'; // 引入热点钩子
import {useHotCardStore} from "@/store/hotCard.js"; // 引入 store
import HeaderMenu from "@/views/components/HeaderMenu/index.vue";
import Footer from "@/views/components/Footer/index.vue";
import newsList from "../mock/newList.js";
import HotCard from "@/components/HotCard/index.vue";
import RightDrawer from "@/components/RightDrawer/index.vue";
import LeftDrawer from "@/components/LeftDrawer/index.vue";
import LoadingSpinner from './components/LoadingSpinner/index.vue';
import {useLoading} from '@/hooks/useLoading';
import {useGrayScale} from "@/hooks/useGrayScale.js";
// import {useGrayScaleStore} from "@/store/grayScaleStore.js";

// 调用 useHot 钩子来处理点击外部区域的逻辑
useHot();

// 使用 useLoading 钩子
const {isLoading} = useLoading();


useGrayScale() // 调用 useGrayScale 钩子, 初始化灰度调节的逻辑
// const grayScale = useGrayScaleStore(); // 从store 中获取灰度调节的值,确保调节的时候实时更新页面灰度
</script>

<template>
    <!--灰度调节-->
    <!--<el-container-->
    <!--    class="common-layout"-->
    <!--    :style="{ filter: `grayscale(${grayScale.grayScaleLevel}%)`, transition: 'filter 0.3s ease' }"-->
    <!--&gt;-->
  <el-container>
      <el-header>
        <!--头部菜单-->
        <HeaderMenu/>
      </el-header>
      <el-main class="main-container">
        <!--路由-->
        <router-view></router-view>
        <!--页脚-->
        <Footer/>
      </el-main>
    </el-container>

    <!--抽屉-->
    <LeftDrawer/>
    <!--抽屉-->
    <RightDrawer/>

    <!--热点卡片-->
    <transition name="hot-card-animation">
      <HotCard
          v-if="useHotCardStore().isOpen"
          class="hot-card"
          :newsList="newsList"
      />
    </transition>

  <!-- 加载动画 -->
  <LoadingSpinner v-if="isLoading"/>
</template>

<style lang="less" scoped>
// 引入hotCard的样式
@import "views/style/hotCard";
</style>

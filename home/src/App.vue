<script setup>
import HeaderMenu from "@/views/components/HeaderMenu/index.vue";
import Footer from "@/views/components/Footer/index.vue";
import newsList from "../mock/newList.js";
import HotCard from "@/components/HotCard/index.vue";
import RightDrawer from "@/components/RightDrawer/index.vue";
import LeftDrawer from "@/components/LeftDrawer/index.vue";
</script>

<template>
  <div class="common-layout">
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
      <HotCard class="hot-card" :newsList="newsList"/>
    </transition>
  </div>
</template>

<style lang="less" scoped>
// 引入hotCard的样式
@import "views/style/hotCard";
</style>

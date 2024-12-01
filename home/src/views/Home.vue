<script setup lang="js">
import HeaderMenu from '@/views/components/HeaderMenu/index.vue'
import Main from '@/views/components/Main/index.vue'
import Footer from '@/views/components/Footer/index.vue'
import LeftDrawer from '@/components/LeftDrawer/index.vue'
import RightDrawer from '@/components/RightDrawer/index.vue'
import BookmarkDetailsDrawer from '@/components/BookmarkDetailsDrawer/index.vue'
import Anchor from '@/components/Anchor/index.vue'
</script>
<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <HeaderMenu/>
      </el-header>
      <el-main class="main-container">
        <!--锚点-->
        <Anchor class="home-anchor"/>
        <Main/>
        <!--页脚-->
        <Footer/>
      </el-main>
      <!--抽屉-->
      <LeftDrawer/>
      <!--抽屉-->
      <RightDrawer/>
      <!--标签详情抽屉-->
      <BookmarkDetailsDrawer/>
    </el-container>
  </div>
</template>

<style scoped lang="less">
.main-container {
  position: relative;
}

.home-anchor {
  position: fixed;
  z-index: 10;
  //  left: 2px;
  //  top: 50%;
  //  transform: translateY(-50%); // 垂直居中
  //  opacity: 0.8; /* 临时改为 1，测试锚点是否正常渲染 */
  width: 1px;
}

</style>

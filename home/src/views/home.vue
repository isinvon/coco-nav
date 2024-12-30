<script setup lang="js">
import Main from '@/views/components/Main/index.vue'
import BookmarkDetailsDrawer from '@/components/BookmarkDetailsDrawer/index.vue'
import Anchor from '@/components/Anchor/index.vue'
import AddBookmarkBox from '@/components/AddBookmarkBox/index.vue'
import DeleteMenu from '@/components/DeleteMenu/index.vue'
import RecycleBinDrawer from '@/components/RecycleBinDrawer/index.vue'
</script>
<template>
  <div class="common-layout">
    <!--锚点-->
    <Anchor class="home-anchor"/>
    <Main/>
    <!--标签详情抽屉-->
    <BookmarkDetailsDrawer/>
    <!--添加书签的框-->
    <AddBookmarkBox/>
    <!--删除书签环绕菜单 (内部逻辑: 状态为正在删除的时候触发)-->
    <DeleteMenu class="delete-menu"/>
    <!--回收站抽屉组件-->
    <RecycleBinDrawer/>

  </div>
</template>

<style scoped lang="less">


.home-anchor {
  position: fixed;
  z-index: 10;
  //  left: 2px;
  //  top: 50%;
  //  transform: translateY(-50%); // 垂直居中
  //  opacity: 0.8; /* 临时改为 1，测试锚点是否正常渲染 */
  width: 1px;
}

.delete-menu {
  z-index: 10;
  position: fixed; // 位置固定
  top: -2%;
  transition: top 0.3s ease; // 添加过渡效果
}

</style>

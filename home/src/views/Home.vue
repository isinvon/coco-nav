<script setup lang="js">
import HeaderMenu from '@/views/components/HeaderMenu/index.vue'
import Main from '@/views/components/Main/index.vue'
import Footer from '@/views/components/Footer/index.vue'
import LeftDrawer from '@/components/LeftDrawer/index.vue'
import RightDrawer from '@/components/RightDrawer/index.vue'
import BookmarkDetailsDrawer from '@/components/BookmarkDetailsDrawer/index.vue'
import Anchor from '@/components/Anchor/index.vue'
import AddBookmarkBox from '@/components/AddBookmarkBox/index.vue'
import DeleteMenu from '@/components/DeleteMenu/index.vue'
import RecycleBinDrawer from '@/components/RecycleBinDrawer/index.vue'
import HotCard from '@/components/HotCard/index.vue'


// 示例新闻数据
const newsList = [
  {
    id: 1,
    title: '新闻标题1',
    heat: 1000,
    pubTime: '2024-12-05',
    url: 'https://example.com/news1',
  },
  {
    id: 2,
    title: '新闻标题2',
    heat: 800,
    pubTime: '2024-12-05',
    url: 'https://example.com/news2',
  },
  {
    id: 3,
    title: '新闻标题3',
    heat: 600,
    pubTime: '2024-12-05',
    url: 'https://example.com/news3',
  },
  {
    id: 4,
    title: '新闻标题4',
    heat: 500,
    pubTime: '2024-12-05',
    url: 'https://example.com/news4',
  },
  {
    id: 5,
    title: '新闻标题5',
    heat: 400,
    pubTime: '2024-12-05',
    url: 'https://example.com/news5',
  },
  {
    id: 6,
    title: '新闻标题6',
    heat: 300,
    pubTime: '2024-12-05',
    url: 'https://example.com/news6',
  },
  {
    id: 7,
    title: '新闻标题7',
    heat: 200,
    pubTime: '2024-12-05',
    url: 'https://example.com/news7',
  },
  {
    id: 8,
    title: '新闻标题8',
    heat: 100,
    pubTime: '2024-12-05',
    url: 'https://example.com/news8',
  },
  {
    id: 9,
    title: '新闻标题9',
    heat: 90,
    pubTime: '2024-12-05',
    url: 'https://example.com/news9',
  },
  {
    id: 10,
    title: '新闻标题10',
    heat: 80,
    pubTime: '2024-12-05',
    url: 'https://example.com/news10',
  },
  {
    id: 11,
    title: '新闻标题11',
    heat: 70,
    pubTime: '2024-12-05',
    url: 'https://example.com/news11',
  },
  {
    id: 12,
    title: '新闻标题12',
    heat: 60,
    pubTime: '2024-12-05',
    url: 'https://example.com/news12',
  },
  {
    id: 13,
    title: '新闻标题13',
    heat: 50,
    pubTime: '2024-12-05',
    url: 'https://example.com/news13',
  },
];
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
      <!--添加书签的框-->
      <AddBookmarkBox/>
      <!--删除书签环绕菜单 (内部逻辑: 状态为正在删除的时候触发)-->
      <DeleteMenu class="delete-menu"/>
      <!--回收站抽屉组件-->
      <RecycleBinDrawer/>
      <!--热点卡片-->
      <HotCard class="hot-card" :newsList="newsList"/>
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

.delete-menu {
  z-index: 10;
  position: fixed; // 位置固定
  top: -2%;
  transition: top 0.3s ease; // 添加过渡效果
}

.hot-card {
  top: 80px;
  left: 40px;
  position: absolute;
  z-index: 100;
}
</style>

<script setup>
/**
 * @description Main page
 * @author sinvon
 * @since 2024年12月2日14:56:16
 */
import MIcon from "@/components/MIcon/index.vue";
import Banner from '@/views/components/Main/components/Banner/index.vue'
// 书签详情抽屉全局状态管理
import {useBookmarkDetailsDrawerStore} from '@/store/bookmarkDetailsDrawer';
import {Delete} from "@element-plus/icons-vue";
import {useDeleteBookmarkStore} from "@/store/deleteBookmark.js";
// import {watch} from "vue";

const bookmarkDetailsDrawerStore = useBookmarkDetailsDrawerStore();
const deleteBookmarkStore = useDeleteBookmarkStore();

// 切换抽屉状态
const toggleBookmarkDetailsDrawer = () => {
  if (!deleteBookmarkStore.isDeleting) {  // 只有在没有删除模式下才触发
    bookmarkDetailsDrawerStore.toggleDrawer();
  }
};

// 切换抽屉状态
// const toggleBookmarkDetailsDrawer = () => {
//   bookmarkDetailsDrawerStore.toggleDrawer();
// };


// 删除书签
const deleteBookmark = (bookmarkId) => {
  // console.log('bookmarkId: ',bookmarkId) // debug
  deleteBookmarkStore.deleteBookmark(bookmarkId);
};

const bookmarkList = [
  {
    category: '影视网站',
    href: '#movie-site',
    icon: 'https://element.eleme.cn/favicon.ico',
    id: 'category-movie-site',  // 固定生成类别 ID
    children: [
      {
        title: '腾讯视频',
        url: 'https://v.qq.com/',
        icon: 'https://v.qq.com/favicon.ico',
        description: '腾讯视频 - 提供最新的电影、电视剧和综艺节目',
        id: 'movie-site-1',  // 使用固定的方式生成子项 ID
      },
      {
        title: '优酷',
        url: 'https://www.youku.com/',
        icon: 'https://www.youku.com/favicon.ico',
        description: '优酷 - 国内领先的视频平台，提供影视、综艺、新闻等内容',
        id: 'movie-site-4',
      },
      {
        title: '哔哩哔哩',
        url: 'https://www.bilibili.com/',
        icon: 'https://www.bilibili.com/favicon.ico',
        description: '哔哩哔哩 - 以二次元文化为主的综合性视频网站',
        id: 'movie-site-7',
      },
      {
        title: '爱奇艺',
        url: 'https://www.iqiyi.com/',
        icon: 'https://www.iqiyi.com/favicon.ico',
        description: '爱奇艺 - 提供电视剧、电影、综艺等视频内容',
        id: 'movie-site-10',
      },
    ],
  },
  {
    category: '技术社区',
    href: '#tech-hub',
    icon: 'https://element.eleme.cn/favicon.ico',
    id: 'category-tech-hub',
    children: [
      {
        title: '牛客',
        url: 'https://www.nowcoder.com/',
        icon: 'https://www.nowcoder.com/favicon.ico',
        description: '牛客网 - 提供编程题库、面试题及在线编程平台',
        id: 'tech-hub-1',
      },
      {
        title: 'gitee',
        url: 'https://gitee.com/',
        icon: 'https://gitee.com/favicon.ico',
        description: 'Gitee - 国内领先的Git代码托管平台',
        id: 'tech-hub-4',
      },
      {
        title: '简书',
        url: 'https://www.jianshu.com/',
        icon: 'https://www.jianshu.com/favicon.ico',
        description: '简书 - 个人写作和阅读分享平台',
        id: 'tech-hub-7',
      },
      {
        title: 'CSDN',
        url: 'https://www.csdn.net/',
        icon: 'https://www.csdn.net/favicon.ico',
        description: 'CSDN - 中国领先的IT技术社区，提供技术教程和开发资源',
        id: 'tech-hub-10',
      },
      {
        title: '知乎',
        url: 'https://www.zhihu.com/',
        icon: 'https://www.zhihu.com/favicon.ico',
        description: '知乎 - 提供问答和知识分享的平台',
        id: 'tech-hub-13',
      },
    ],
  },
]

// 监听 selectedBookmarks 数组的变化 // debug
// watch(
//     () => deleteBookmarkStore.selectedBookmarks,
//     (newVal, oldVal) => {
//       console.log('selectedBookmarks 变化了:', newVal);  // 输出新值
//       console.log('之前的 selectedBookmarks:', oldVal);   // 输出旧值
//
//       // 如果 selectedBookmarks 数组有内容，可以执行其他操作
//       if (newVal.length > 0) {
//         // 比如，当有选中的书签时，可以启用删除按钮
//         console.log('有书签被选中:', newVal);
//       } else {
//         // 当没有选中的书签时，可以禁用删除按钮
//         console.log('没有选中的书签');
//       }
//     }
// );
</script>
<template>
  <div class="container">
    <!--横幅(内有搜索框)-->
    <Banner/>
    <div v-for="(category, index) in bookmarkList" :key="index" class="category-container">
      <!-- 分类名称 -->
      <div class="category-title">
        <MIcon class="category-title icon" :icon="category.icon"/>
        <h3 class="category-title text">{{ category.category }}</h3>
      </div>

      <!-- 书签列表 -->
      <div class="bookmark-list">
        <div
            v-for="(bookmark, bookmarkIndex) in category.children"
            :key="bookmarkIndex"
            class="bookmark"
            @click="toggleBookmarkDetailsDrawer"
        >
          <div class="bookmark-icon">
            <MIcon :icon="bookmark.icon"/>
          </div>
          <div class="bookmark-info">
            <div class="bookmark-title">{{ bookmark.title }}</div>
            <div class="bookmark-desc">{{ bookmark.description }}</div>
          </div>
          <!--单个删除按钮, 批量删除为false,删除为true的时候,开启-->
          <div class="bookmark-delete-button" v-show="deleteBookmarkStore.isDeleting && !deleteBookmarkStore.isMultipleChoiceDelete">
            <Delete @click="deleteBookmark(bookmark.id)" style="width: 2em; height: 2em; margin-right: 8px;margin-left: -5px; margin-bottom: 40px; color: darkred"/>
          </div>
          <!--复选框, 批量删除状态为false的时候会自动让isDeleting为true(在useDeleteBookmarkStore内部已经定义好逻辑了)-->
          <div class="bookmark-delete-multiple-checkbox" v-show="deleteBookmarkStore.isMultipleChoiceDelete">
            <input type="checkbox" class="checkbox" v-model="deleteBookmarkStore.selectedBookmarks" :value="bookmark.id" style="width: 2em; height: 2em; margin-right: 8px;margin-left: -5px; margin-bottom: 40px;"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
@import "./index";

</style>
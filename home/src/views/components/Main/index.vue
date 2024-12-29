<script setup>
/**
 * @description Main page
 * @author sinvon
 * @since 2024年12月2日14:56:16
 */
import MIcon from "@/components/MIcon/index.vue";
import Banner from '@/components/Banner/index.vue'
// 书签详情抽屉全局状态管理
import {useBookmarkDetailsDrawerStore} from '@/store/bookmarkDetailsDrawer';
import {Delete} from "@element-plus/icons-vue";
import {useDeleteBookmarkStore} from "@/store/deleteBookmark.js";

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
            <Delete style="width: 2em; height: 2em; margin-right: 8px;margin-left: -5px; margin-bottom: 40px; color: darkred"/>
          </div>
          <!--复选框, 批量删除状态为false的时候会自动让isDeleting为true(在useDeleteBookmarkStore内部已经定义好逻辑了)-->
          <div class="bookmark-delete-multiple-checkbox" v-show="deleteBookmarkStore.isMultipleChoiceDelete">
            <input type="checkbox" class="checkbox" v-model="selectedBookmarks" :value="bookmark.url" style="width: 2em; height: 2em; margin-right: 8px;margin-left: -5px; margin-bottom: 40px;"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
.category-container {
  margin-bottom: 25px;
}

.category-title {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #59636e;
  margin-top: 5px;
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 15px;
}

.bookmark-list {
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
}

// 每个书签块的样式
.bookmark {
  background-color: rgb(246, 246, 246);
  border-radius: 10px;
  display: flex;
  align-items: center; // 垂直居中对齐
  padding: 8px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  cursor: pointer;
  height: 40px; // 调整高度，确保布局合理
  width: 180px; /* 调整宽度，确保布局合理 */
}

.bookmark:hover {
  transform: translateY(-3px);
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2);
}

.bookmark-icon {
  font-size: 2rem;
  margin-right: 10px; // 保证图标和文本之间有间隔
  width: auto; // 让图标填充父容器,注意: 换成 100% 会让 margin-right 即使是为 0 也还是有间隔
  height: 100%; /* 使得图标容器的高度与父容器一致 */
}

.bookmark-info {
  display: flex;
  flex-direction: column; // 让标题和描述上下排列
  align-items: flex-start; // 靠左对齐
  justify-content: center; // 竖直方向居中对齐
}

.bookmark-title, .bookmark-desc {
  color: #59636e;
  margin: 0; // 去掉上下的默认间距
  white-space: nowrap; // 防止文本换行
  overflow: hidden; // 超出部分隐藏
  text-overflow: ellipsis; // 超出部分使用省略号显示
}

.bookmark-title {
  font-size: 12px;
  font-weight: bold;
  max-width: 130px; // 限制最大宽度，防止文字过长, 多的部分会被省略号显示
}

.bookmark-desc {
  font-size: 11px;
  font-weight: normal;
  max-width: 130px; // 限制最大宽度，防止文字过长, 多的部分会被省略号显示
}

</style>
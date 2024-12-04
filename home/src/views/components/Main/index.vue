<script setup>
/**
 * @description Main page
 * @author sinvon
 * @since 2024年12月2日14:56:16
 */
import MIcon from "@/components/MIcon/index.vue";
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
    children: [
      {
        title: '腾讯视频',
        url: 'https://v.qq.com/',
        icon: 'https://v.qq.com/favicon.ico',
        description: '腾讯视频 - 提供最新的电影、电视剧和综艺节目'
      },
      {
        title: '腾讯视频',
        url: 'https://v.qq.com/',
        icon: 'https://v.qq.com/favicon.ico',
        description: '腾讯视频 - 提供多样的影视内容和直播服务'
      },
      {
        title: '腾讯视频',
        url: 'https://v.qq.com/',
        icon: 'https://v.qq.com/favicon.ico',
        description: '腾讯视频 - 在线影视平台，提供高清影视资源'
      },
      {
        title: '优酷',
        url: 'https://www.youku.com/',
        icon: 'https://www.youku.com/favicon.ico',
        description: '优酷 - 国内领先的视频平台，提供影视、综艺、新闻等内容'
      },
      {
        title: '优酷',
        url: 'https://www.youku.com/',
        icon: 'https://www.youku.com/favicon.ico',
        description: '优酷 - 提供丰富的网络视频内容，覆盖多个领域'
      },
      {
        title: '优酷',
        url: 'https://www.youku.com/',
        icon: 'https://www.youku.com/favicon.ico',
        description: '优酷 - 观看最新电影、电视剧、综艺节目的平台'
      },
      {
        title: '哔哩哔哩',
        url: 'https://www.bilibili.com/',
        icon: 'https://www.bilibili.com/favicon.ico',
        description: '哔哩哔哩 - 以二次元文化为主的综合性视频网站'
      },
      {
        title: '哔哩哔哩',
        url: 'https://www.bilibili.com/',
        icon: 'https://www.bilibili.com/favicon.ico',
        description: '哔哩哔哩 - 提供弹幕视频，包含动画、游戏等内容'
      },
      {
        title: '哔哩哔哩',
        url: 'https://www.bilibili.com/',
        icon: 'https://www.bilibili.com/favicon.ico',
        description: '哔哩哔哩 - 深受年轻人喜爱的二次元视频社区'
      },
      {
        title: '爱奇艺',
        url: 'https://www.iqiyi.com/',
        icon: 'https://www.iqiyi.com/favicon.ico',
        description: '爱奇艺 - 提供电视剧、电影、综艺等视频内容'
      },
      {
        title: '爱奇艺',
        url: 'https://www.iqiyi.com/',
        icon: 'https://www.iqiyi.com/favicon.ico',
        description: '爱奇艺 - 在线视频平台，致力于高清影视的播放'
      },
      {
        title: '爱奇艺',
        url: 'https://www.iqiyi.com/',
        icon: 'https://www.iqiyi.com/favicon.ico',
        description: '爱奇艺 - 最新的电影、电视剧、综艺节目的在线播放平台'
      },
    ],
  },
  {
    category: '技术社区',
    href: '#tech-hub',
    icon: 'https://element.eleme.cn/favicon.ico',
    children: [
      {
        title: '牛客',
        url: 'https://www.nowcoder.com/',
        icon: 'https://www.nowcoder.com/favicon.ico',
        description: '牛客网 - 提供编程题库、面试题及在线编程平台'
      },
      {
        title: '牛客',
        url: 'https://www.nowcoder.com/',
        icon: 'https://www.nowcoder.com/favicon.ico',
        description: '牛客网 - 面向IT行业的招聘信息平台'
      },
      {
        title: '牛客',
        url: 'https://www.nowcoder.com/',
        icon: 'https://www.nowcoder.com/favicon.ico',
        description: '牛客网 - 提供在线面试和编程题库的平台'
      },
      {
        title: 'gitee',
        url: 'https://gitee.com/',
        icon: 'https://gitee.com/favicon.ico',
        description: 'Gitee - 国内领先的Git代码托管平台'
      },
      {
        title: 'gitee',
        url: 'https://gitee.com/',
        icon: 'https://gitee.com/favicon.ico',
        description: 'Gitee - 提供代码托管和版本控制服务'
      },
      {
        title: 'gitee',
        url: 'https://gitee.com/',
        icon: 'https://gitee.com/favicon.ico',
        description: 'Gitee - 开源项目托管平台，支持Git仓库管理'
      },
      {
        title: '简书',
        url: 'https://www.jianshu.com/',
        icon: 'https://www.jianshu.com/favicon.ico',
        description: '简书 - 个人写作和阅读分享平台'
      },
      {
        title: '简书',
        url: 'https://www.jianshu.com/',
        icon: 'https://www.jianshu.com/favicon.ico',
        description: '简书 - 提供创作、阅读与分享的社区平台'
      },
      {
        title: '简书',
        url: 'https://www.jianshu.com/',
        icon: 'https://www.jianshu.com/favicon.ico',
        description: '简书 - 记录生活点滴，分享创作的社交平台'
      },
      {
        title: 'CSDN',
        url: 'https://www.csdn.net/',
        icon: 'https://www.csdn.net/favicon.ico',
        description: 'CSDN - 中国领先的IT技术社区，提供技术教程和开发资源'
      },
      {
        title: 'CSDN',
        url: 'https://www.csdn.net/',
        icon: 'https://www.csdn.net/favicon.ico',
        description: 'CSDN - 提供编程技术文章、资源下载和编程交流平台'
      },
      {
        title: 'CSDN',
        url: 'https://www.csdn.net/',
        icon: 'https://www.csdn.net/favicon.ico',
        description: 'CSDN - IT技术学习与分享平台，包含博客、论坛和代码库'
      },
      {
        title: '知乎',
        url: 'https://www.zhihu.com/',
        icon: 'https://www.zhihu.com/favicon.ico',
        description: '知乎 - 提供问答和知识分享的平台'
      },
      {
        title: '知乎',
        url: 'https://www.zhihu.com/',
        icon: 'https://www.zhihu.com/favicon.ico',
        description: '知乎 - 高质量的中文问答平台，汇聚各类专家'
      },
      {
        title: '知乎',
        url: 'https://www.zhihu.com/',
        icon: 'https://www.zhihu.com/favicon.ico',
        description: '知乎 - 专注于知识分享与思想碰撞的平台'
      },
    ],
  },
  {
    category: '开发工具',
    href: '#dev-tools',
    icon: 'https://element.eleme.cn/favicon.ico',
    children: [
      {
        title: 'GitHub',
        url: 'https://github.com/',
        icon: 'https://github.com/favicon.ico',
        description: 'GitHub - 全球最大的开源代码托管平台'
      },
      {
        title: 'GitHub',
        url: 'https://github.com/',
        icon: 'https://github.com/favicon.ico',
        description: 'GitHub - 提供版本控制、代码托管和协作开发服务'
      },
      {
        title: 'GitHub',
        url: 'https://github.com/',
        icon: 'https://github.com/favicon.ico',
        description: 'GitHub - 开源代码社区和软件开发平台'
      },
      {
        title: 'GitLab',
        url: 'https://gitlab.com/',
        icon: 'https://gitlab.com/favicon.ico',
        description: 'GitLab - 提供Git代码托管、CI/CD和版本控制服务'
      },
      {
        title: 'GitLab',
        url: 'https://gitlab.com/',
        icon: 'https://gitlab.com/favicon.ico',
        description: 'GitLab - 强大的代码托管和开发工具，支持自动化部署'
      },
      {
        title: 'GitLab',
        url: 'https://gitlab.com/',
        icon: 'https://gitlab.com/favicon.ico',
        description: 'GitLab - 用于软件开发的Git仓库管理和协作平台'
      },
      {
        title: 'Bitbucket',
        url: 'https://bitbucket.org/',
        icon: 'https://bitbucket.org/favicon.ico',
        description: 'Bitbucket - 提供Git和Mercurial代码托管服务'
      },
      {
        title: 'Bitbucket',
        url: 'https://bitbucket.org/',
        icon: 'https://bitbucket.org/favicon.ico',
        description: 'Bitbucket - 一个用于代码托管和项目管理的平台'
      },
      {
        title: 'Bitbucket',
        url: 'https://bitbucket.org/',
        icon: 'https://bitbucket.org/favicon.ico',
        description: 'Bitbucket - 提供团队合作和源码管理'
      },
    ],
  },
  {
    category: '学习资源',
    href: '#learning-resources',
    icon: 'https://element.eleme.cn/favicon.ico',
    children: [
      {
        title: '菜鸟教程',
        url: 'https://www.runoob.com/',
        icon: 'https://www.runoob.com/favicon.ico',
        description: '菜鸟教程 - 免费的在线编程教程'
      },
      {
        title: '菜鸟教程',
        url: 'https://www.runoob.com/',
        icon: 'https://www.runoob.com/favicon.ico',
        description: '菜鸟教程 - 免费的在线编程教程'
      },
      {
        title: '菜鸟教程',
        url: 'https://www.runoob.com/',
        icon: 'https://www.runoob.com/favicon.ico',
        description: '菜鸟教程 - 免费的在线编程教程'
      },
      {
        title: '菜鸟教程',
        url: 'https://www.runoob.com/',
        icon: 'https://www.runoob.com/favicon.ico',
        description: '菜鸟教程 - 免费的在线编程教程'
      },
      {
        title: '菜鸟教程',
        url: 'https://www.runoob.com/',
        icon: 'https://www.runoob.com/favicon.ico',
        description: '菜鸟教程 - 免费的在线编程教程'
      },
    ]
  }
]

</script>
<template>
  <div class="container">
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
          <div class="bookmark-delete-button" v-show="deleteBookmarkStore.isDeleting">
            <Delete style="width: 2em; height: 2em; margin-right: 8px;margin-left: -5px; margin-bottom: 40px; color: darkred"/>
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
  align-items: center;  // 垂直居中对齐
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
  margin-right: 10px;  // 保证图标和文本之间有间隔
  width: auto; // 让图标填充父容器,注意: 换成 100% 会让 margin-right 即使是为 0 也还是有间隔
  height: 100%; /* 使得图标容器的高度与父容器一致 */
}

.bookmark-info {
  display: flex;
  flex-direction: column;  // 让标题和描述上下排列
  align-items: flex-start; // 靠左对齐
  justify-content: center; // 竖直方向居中对齐
}

.bookmark-title, .bookmark-desc {
  color: #59636e;
  margin: 0;  // 去掉上下的默认间距
  white-space: nowrap; // 防止文本换行
  overflow: hidden; // 超出部分隐藏
  text-overflow: ellipsis; // 超出部分使用省略号显示
}

.bookmark-title {
  font-size: 12px;
  font-weight: bold;
  max-width: 130px;  // 限制最大宽度，防止文字过长, 多的部分会被省略号显示
}

.bookmark-desc {
  font-size: 11px;
  font-weight: normal;
  max-width: 130px;  // 限制最大宽度，防止文字过长, 多的部分会被省略号显示
}

</style>
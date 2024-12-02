<script lang="js" setup>
/**
 *  @module BookmarkDetailsDrawer
 *  @description: 书签详情抽屉
 *  @author sinvon
 *  @date: 2024年12月1日23:50:54
 */
import {Share, Star} from '@element-plus/icons-vue'
import {useBookmarkDetailsDrawerStore} from '@/store/bookmarkDetailsDrawer'
// markdown 组件
import MarkdownIt from 'markdown-it';
import {computed, ref} from "vue";
// 相关导航组件
import RelateNavigation from "@/components/RelateNavigation/index.vue";
// 广告组件
import Advertisement from "@/components/Advertisement/index.vue";

const bookmarkDetailsDrawerStore = useBookmarkDetailsDrawerStore()

// Markdown 内容，可能是从 API 获取的
const markdownContent = ref(`
### 这是一个简单的Markdown文本。

### markdown图片测试

![Image](https://example.com/image.png)

## 链接直达测试

[Link](https://www.baidu.com)
`);

const md = new MarkdownIt();

const renderedMarkdown = computed(() => {
  return md.render(markdownContent.value);
});

console.log(md.render(markdownContent.value));


// 打开链接直达
const openLink = () => {
  window.open('https://www.baidu.com', '_blank')
}

// 分享书签
const shareBookmark = () => {
  console.log('分享书签')
}

// 收藏或取消收藏
const toggleBookmarkFavorite = () => {
  bookmark.isFavorite = !bookmark.isFavorite
}

// 提交反馈
const provideFeedback = () => {
  console.log('反馈')
}

// 模拟数据
const bookmark = {
  creationTime: '2024-01-01',
  updateTime: '2024-01-15',
  views: 120,
  isFavorite: false
}
</script>

<template>
  <el-drawer
      direction="btt"
      v-model="bookmarkDetailsDrawerStore.isOpen"
      title="书签详情"
      :with-header="false"
      size="60%"
      class="bookmark-details-drawer"
  >
    <div class="bookmark-details">
      <!-- 书签的大图 -->
      <div class="bookmark-image">
        <img src="https://picsum.photos/400/400" alt="Bookmark Image" class="large-image"/>
      </div>

      <!-- 书签的其他内容 -->
      <div class="bookmark-info">
        <p class="bookmark-title">书签标题</p>
        <p class="bookmark-description">
          这是书签的描述内容，可以是一些简短的文字，解释这个书签是什么内容。这里也可以描述书签的用途、如何操作等信息。
        </p>

        <!-- 书签标签 -->
        <div class="bookmark-tags">
          <el-tag class="tag" type="info">标签1</el-tag>
          <el-tag class="tag" type="info">标签2</el-tag>
        </div>

        <!-- 书签的创建时间和更新时间 -->
        <p class="bookmark-date">
          <span>创建时间: {{ bookmark.creationTime }}</span>
          <el-divider direction="vertical"/>
          <span>更新时间: {{ bookmark.updateTime }}</span>
        </p>

        <!-- 书签的浏览次数 -->
        <p class="bookmark-views">浏览次数: {{ bookmark.views }}</p>

        <!-- 分享和收藏按钮并列 -->
        <div class="button-group">
          <el-button round type="info" :icon="Share" @click="shareBookmark">
            分享
          </el-button>
          <el-button
              round
              type="info"
              :icon="Star"
              @click="toggleBookmarkFavorite"
          >
            {{ bookmark.isFavorite ? '取消收藏' : '收藏' }}
          </el-button>
        </div>

        <!-- 手机查看、链接直达和反馈按钮并列 -->
        <div class="button-group">
          <el-button round type="info" size="small">手机查看</el-button>
          <el-button round type="info" size="small" @click="openLink">链接直达</el-button>
          <el-button round type="info" size="small" @click="provideFeedback">反馈</el-button>
        </div>
      </div>
    </div>
    <!-- Markdown 文章格式描述 -->
    <div class="markdown-content">
      <!-- 使用 v-html 渲染解析后的 Markdown 内容 -->
      <div v-html="renderedMarkdown"></div>
    </div>
    <!--相关导航-->
    <div class="related-navigation">
      <RelateNavigation/>
    </div>
    <!--广告-->
    <div class="advertisement">
      <Advertisement/>
    </div>
  </el-drawer>
</template>


<style lang="less" scoped>
.bookmark-details {
  display: flex;
  justify-content: space-between;
  gap: 20px;
}

.bookmark-image {
  flex: 1;
  max-width: 300px;
  min-width: 300px;
}

.large-image {
  width: 100%;
  height: auto;
  border-radius: 8px;
}

.bookmark-info {
  flex: 2;
  display: flex;
  flex-direction: column;
}

.bookmark-title {
  margin: 0;
  font-size: 20px;
  font-weight: bold;
  color: #8d8d8d;
}

.bookmark-description {
  margin-bottom: 16px;
  color: #888888;
  line-height: 1.6;
}

.bookmark-tags {
}

.tag {
  margin-right: 8px;
}

.bookmark-date, .bookmark-views {
  font-size: 14px;
  margin-bottom: 0;
  color: #888888;
}

/* Flexbox布局使按钮内容居中 */
.button-group {
  display: flex;
  gap: 8px;
  margin-top: 16px;
}

.el-button {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 14px;
  height: 36px; /* 保证按钮高度一致 */
  //padding: 0 16px; // 适当的内边距
  outline: none; /* 去除按钮点击后出现的黑边框 */
}

.el-button:focus {
  outline: none; /* 确保按钮获得焦点时没有黑框 */
}

.el-button small {
  height: 32px;
}

.markdown-content {
  margin-top: 40px;
  padding: 0 30px;
  color: #777777;
}
</style>

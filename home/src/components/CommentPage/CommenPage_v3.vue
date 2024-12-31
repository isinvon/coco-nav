<script setup>
import {ref} from 'vue';
import MarkdownIt from 'markdown-it';
import hljs from 'highlight.js';
import markdownItHighlightjs from 'markdown-it-highlightjs';

/**
 * 通用页组件, markdown 卡片
 * 用于渲染 markdown 内容并应用模仿微信公众号的文章风格
 */

// 接收markdown内容作为 prop
const props = defineProps({
  content: {
    type: String,
    required: true
  }
});

// 创建markdown-it实例并启用高亮插件
const md = new MarkdownIt()
    .use(markdownItHighlightjs)
    .set({
      highlight: (str, lang) => {
        if (lang && hljs.getLanguage(lang)) {
          try {
            return '<pre class="hljs"><code>' + hljs.highlight(str, {language: lang}).value + '</code></pre>';
          } catch (__) {
          }
        }
        return '<pre class="hljs"><code>' + md.utils.escapeHtml(str) + '</code></pre>';
      }
    });

// 渲染后的markdown内容
const renderedMarkdown = ref(md.render(props.content));
</script>

<template>
  <div class="markdown-card">
    <div class="markdown-content" v-html="renderedMarkdown"></div>
  </div>
</template>

<style scoped>
/* 使markdown内容呈现类似微信公众号风格 */
.markdown-card {
  background: #fff;
  border-radius: 10px;
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
}

/* 图片样式，确保图片在容器内不会超出并带有圆角 */
::v-deep(.markdown-content img) {
  max-width: 100%; /* 确保图片的最大宽度不超过父容器 */
  height: auto; /* 自动调整高度以保持图片比例 */
  display: block; /* 使图片呈现块级元素，避免与文本同行显示 */
  margin: 20px 0; /* 添加上下间距 */
  border-radius: 8px; /* 圆角处理 */
  cursor: pointer; /* 鼠标悬停时显示指针 */
}

/* 标题样式 */
::v-deep(.markdown-content h1),
::v-deep(.markdown-content h2),
::v-deep(.markdown-content h3) {
  color: #333;
  font-weight: normal;
  margin-top: 40px;
  margin-bottom: 20px;
}

::v-deep(.markdown-content h1) {
  font-size: 28px;
}

::v-deep(.markdown-content h2) {
  font-size: 24px;
}

::v-deep(.markdown-content h3) {
  font-size: 20px;
}

/* 段落样式 */
::v-deep(.markdown-content p) {
  line-height: 1.8;
  color: #555;
  font-size: 16px;
  margin-bottom: 20px;
}

/* 引用样式 */
::v-deep(.markdown-content blockquote) {
  border-left: 4px solid #dfe2e5;
  margin: 20px 0;
  padding: 0 1em;
  color: #6a737d;
  font-style: italic;
}

/* 链接样式 */
::v-deep(.markdown-content a) {
  color: #409EFF;
  text-decoration: none;
}

::v-deep(.markdown-content a:hover) {
  text-decoration: underline;
}

/* 代码块高亮样式 */
::v-deep(.markdown-content pre.hljs) {
  background: #f5f5f5;
  border-radius: 6px;
  padding: 15px;
  overflow-x: auto;
  color: #333;
  border: 1px solid #ddd;
}

::v-deep(.markdown-content pre.hljs code) {
  white-space: pre-wrap; /* 保留空白符序列，但是正常地进行换行 */
}

::v-deep(.markdown-content .hljs-comment),
::v-deep(.markdown-content .hljs-quote) {
  color: #6a737d;
  font-style: italic;
}

::v-deep(.markdown-content .hljs-keyword),
::v-deep(.markdown-content .hljs-selector-tag),
::v-deep(.markdown-content .hljs-literal) {
  color: #d73a49;
  font-weight: bold;
}

::v-deep(.markdown-content .hljs-string),
::v-deep(.markdown-content .hljs-title),
::v-deep(.markdown-content .hljs-section) {
  color: #032f62;
}

::v-deep(.markdown-content .hljs-number),
::v-deep(.markdown-content .hljs-symbol),
::v-deep(.markdown-content .hljs-variable) {
  color: #e36209;
}

::v-deep(.markdown-content .hljs-type),
::v-deep(.markdown-content .hljs-class .hljs-title) {
  color: #005cc5;
}

::v-deep(.markdown-content .hljs-meta) {
  color: #c08d3c;
}

::v-deep(.markdown-content .hljs-strong) {
  font-weight: bold;
}

::v-deep(.markdown-content .hljs-emphasis) {
  font-style: italic;
}

/* 表格样式 */
::v-deep(.markdown-content table) {
  width: 100%;
  border-collapse: collapse;
  margin: 20px 0;
  border: 1px solid #ddd;
}

::v-deep(.markdown-content th),
::v-deep(.markdown-content td) {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

::v-deep(.markdown-content th) {
  background-color: #f4f4f4;
}

/* 列表样式 */
::v-deep(.markdown-content ul),
::v-deep(.markdown-content ol) {
  margin-left: 20px;
}

::v-deep(.markdown-content ul li),
::v-deep(.markdown-content ol li) {
  margin-bottom: 10px;
}

/* 增加列表项的样式 */
::v-deep(.markdown-content ul li) {
  list-style-type: disc;
}

::v-deep(.markdown-content ol li) {
  list-style-type: decimal;
}
</style>
<script setup>
import {ref, onMounted} from 'vue';
import MarkdownIt from 'markdown-it';
import hljs from 'highlight.js';
import markdownItHighlightjs from 'markdown-it-highlightjs';

/**
 * 通用页组件, markdown 卡片
 * 用于渲染 markdown 内容并应用模仿微信公众号的文章风格
 */

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

const renderedMarkdown = ref(md.render(props.content));

// 图片预览相关状态
const isPreviewVisible = ref(false);
const previewImageSrc = ref('');

const showPreview = (src) => {
  previewImageSrc.value = src;
  isPreviewVisible.value = true;
};

const closePreview = () => {
  isPreviewVisible.value = false;
};
</script>

<template>
  <div class="markdown-card">
    <div class="markdown-content" v-html="renderedMarkdown" @click="event => {
      const target = event.target;
      if (target.tagName === 'IMG') {
        showPreview(target.src);
      }
    }"></div>

    <!-- 图片预览层 -->
    <div v-if="isPreviewVisible" class="preview-overlay" @click="closePreview">
      <img :src="previewImageSrc" alt="Preview" class="preview-image"/>
    </div>
  </div>
</template>

<style lang="less" scoped>
@import "./index";

/* markdown内容的样式 */
.markdown-card {
  position: relative;
}

.markdown-content {
  /* 你的markdown内容样式 */
}

/* 图片预览覆盖层 */
.preview-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.preview-image {
  max-width: 90%;
  max-height: 90%;
  object-fit: contain;
}

/* 关闭预览层的效果 */
.preview-overlay:hover {
  cursor: pointer;
}
</style>

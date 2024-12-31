<script lang="js" setup>
import {ref, watch} from 'vue';
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

// 渲染的markdown内容
const renderedMarkdown = ref(md.render(props.content));

// 监听content变化，重新渲染markdown
watch(() => props.content, (newContent) => {
  renderedMarkdown.value = md.render(newContent);
});

// 图片预览相关状态
const isPreviewVisible = ref(false);
const previewImageSrc = ref('');

// 显示图片预览
const showPreview = (src) => {
  previewImageSrc.value = src;
  isPreviewVisible.value = true;
};

// 关闭图片预览
const closePreview = () => {
  isPreviewVisible.value = false;
};
</script>

<template>
  <div class="markdown-card">
    <div class="markdown-content" v-html="renderedMarkdown" @click="event => {
      const imgElement = event.target.closest('img');
      if (imgElement) {
        showPreview(imgElement.src);
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
</style>

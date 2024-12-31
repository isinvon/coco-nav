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

<style lang="less" scoped>
@import "./index";
</style>
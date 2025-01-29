<template>
  <div>
    <!-- 动态加载并渲染对应的页面 -->
    <component v-if="currentCommonPageComponent" :is="currentCommonPageComponent" :content="markdownContent"/>
    <!-- 如果没有有效的组件，提示错误 -->
    <div v-if="!isLoading && !currentCommonPageComponent">无效的模板版本</div>
  </div>
</template>

<script setup>
/**
 * 测试页面
 */
import * as SettingData from '../../../mock/setting.js'
import {onMounted, ref} from "vue";

const templateVersion = ref(0) // 模板版本
const currentCommonPageComponent = ref(null); // 当前加载的组件
const isLoading = ref(true); // 加载状态

// 动态加载的 CommonPage 组件数组
const commonPageComponents = [
  import('@/components/CommentPage/CommenPage_v1/index.vue'),
  import('@/components/CommentPage/CommenPage_v2/index.vue'),
  import('@/components/CommentPage/CommenPage_v3/index.vue'),
];

onMounted(() => {
  // 从设置中获取模板版本
  templateVersion.value = SettingData.setting.data.markdown.template

  // 动态加载相应的组件
  if (templateVersion.value > 0) {
    commonPageComponents[templateVersion.value - 1].then((module) => {
      currentCommonPageComponent.value = module.default;
      isLoading.value = false; // 加载完成，设置为false
    });
  } else {
    currentCommonPageComponent.value = null;
    isLoading.value = false; // 无效版本时也设置为false
  }
})

const markdownContent = `
# 项目文档

欢迎阅读项目文档。在这里你可以找到项目的简介、使用说明以及代码示例。

## 1. 简介

这是一个示例项目，旨在展示如何使用 Markdown 编写文档。本项目使用 JavaScript 作 编写文档。本项目使用 JavaScript 作 编写文档。本项目使用 JavaScript 作 编写文档。本项目使用 JavaScript 作 编写文档。本项目使用 JavaScript 作 编写文档。本项目使用 JavaScript 作 编写文档。本项目使用 JavaScript 作为核心语言，Vue.js 用于前端开发。

## 2. 安装

请按照以下步骤安装项目：

### 2.1 环境要求

- Node.js 版本 14 或更高
- npm 或 yarn

### 2.2 安装依赖

\`\`\`bash
npm install
# 或者
yarn install
\`\`\`

### 2.3 启动项目

\`\`\`bash
npm run dev
# 或者
yarn dev
\`\`\`

## 3. 示例代码

以下是一个 JavaScript 示例，展示如何使用 \`console.log\` 打印输出：

\`\`\`js
// 这是一个简单的 JavaScript 示例
console.log('Hello, Markdown!');
\`\`\`

## 4. 引用

> 这是一个引用文本，通常用于引用其他人的话语或文档内容。

> 可以包含多个段落，每段前都有 "> "。

## 5. 链接与图片

你可以插入链接和图片。

### 5.1 链接

[GitHub 官方网站](https://www.github.com)

### 5.2 图片

![示例图片](https://example.com/image.jpg)

![示例图片](https://api.r10086.com/%E5%9B%BE%E5%8C%85webp/%E5%8A%A8%E6%BC%AB%E7%BB%BC%E5%90%8818/img440.webp)

## 6. 列表

### 6.1 无序列表

- 项目初始化
- 安装依赖
- 启动开发环境

### 6.2 有序列表

1. 阅读文档
2. 安装项目
3. 启动项目

## 7. 表格

| 序号 | 名称    | 描述        |
|------|---------|-------------|
| 1    | 项目A   | 第一个项目  |
| 2    | 项目B   | 第二个项目  |

## 8. 代码块

\`\`\`python
# 这是一个 Python 示例
def greet():
    print("Hello, World!")
greet()
\`\`\`

## 9. 强调和粗体

- **粗体**: 使用两个星号包裹文本 **粗体文本**
- *斜体*: 使用一个星号包裹文本 *斜体文本*

## 10. 自定义标题

### 10.1 一级标题

# 一级标题

### 10.2 二级标题

## 二级标题

### 10.3 三级标题

### 三级标题
\`\`\`

你可以直接复制这段 Markdown 格式的代码，并将其粘贴到你的 Markdown 文件中。
`;
</script>

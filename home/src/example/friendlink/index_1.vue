<template>
  <div class="friend-container">
    <h1 class="title">友情链接</h1>

    <!-- 搜索框 -->
    <div class="search-container">
      <input
          v-model="searchQuery"
          type="text"
          placeholder="搜索友链..."
          class="search-input"
      />
    </div>

    <!-- 分类筛选 -->
    <div class="category-container">
      <span
          v-for="(category, index) in categories"
          :key="index"
          :class="['category', { active: selectedCategory === category }]"
          @click="selectCategory(category)"
      >
        {{ category }}
      </span>
    </div>

    <!-- 排序方式选择 -->
    <div class="sort-container">
      <label for="sort">排序方式:</label>
      <select v-model="sortOption" id="sort">
        <option value="name">按名称排序</option>
        <option value="date">按创建时间排序</option>
        <option value="views">按访问量排序</option>
      </select>
    </div>

    <!-- 友链列表 -->
    <div class="link-list">
      <div
          v-for="(link, index) in filteredAndSortedLinks"
          :key="index"
          class="link-item"
      >
        <a :href="link.url" target="_blank" class="link">
          <img v-if="link.icon" :src="link.icon" alt="icon" class="link-icon" />
          <span class="link-name">{{ link.name }}</span>
        </a>
        <p class="link-description">{{ link.description }}</p>
        <span class="link-date">创建时间: {{ formatDate(link.date) }}</span>
        <span v-if="link.updatedAt" class="link-date">更新: {{ formatDate(link.updatedAt) }}</span>
        <span v-if="link.category" class="link-category">{{ link.category }}</span>
        <span v-if="link.views" class="link-views">访问量: {{ link.views }}</span>
        <span v-if="link.author" class="link-author">作者: {{ link.author }}</span>
        <span v-if="link.rating" class="link-rating">评分: {{ link.rating }} / 5</span>
        <span v-if="link.tags" class="link-tags">标签: {{ link.tags.join(', ') }}</span>
      </div>
    </div>

    <!-- 添加友链按钮 -->
    <button @click="addLink" class="add-link-btn">添加友链</button>
  </div>
</template>

<script setup>
/**
 * 一个非常全面的 友链列表组件，包括搜索、分类、排序等功能。
 * 功能有:
 * 1. 评分, 访问量
 * 2. 标签
 */
import { ref, computed } from 'vue';

// 模拟的友链数据
const links = ref([
  {
    name: 'GitHub',
    url: 'https://github.com',
    icon: 'https://github.githubassets.com/images/modules/logos_page/Octocat.png',
    description: '全球最大的开发者社区',
    date: '2023-01-01',
    updatedAt: '2023-06-15',
    category: '开发者',
    views: 10234,
    author: 'GitHub',
    rating: 4.8,
    tags: ['热门', '推荐'],
  },
  {
    name: 'Vue.js',
    url: 'https://vuejs.org',
    icon: 'https://vuejs.org/images/logo.png',
    description: '前端框架',
    date: '2022-03-15',
    updatedAt: '2023-01-20',
    category: '开发者',
    views: 8234,
    author: 'Evan You',
    rating: 4.9,
    tags: ['推荐'],
  },
  {
    name: 'Stack Overflow',
    url: 'https://stackoverflow.com',
    icon: 'https://stackoverflow.com/favicon.ico',
    description: '编程问答社区',
    date: '2021-08-10',
    updatedAt: '2023-05-18',
    category: '工具',
    views: 23456,
    author: 'Stack Overflow',
    rating: 4.7,
    tags: ['热门', '社区'],
  },
  {
    name: 'MDN Web Docs',
    url: 'https://developer.mozilla.org',
    icon: 'https://developer.mozilla.org/favicon.ico',
    description: '前端开发文档',
    date: '2020-06-22',
    updatedAt: '2023-03-30',
    category: '工具',
    views: 12845,
    author: 'Mozilla',
    rating: 4.8,
    tags: ['推荐'],
  },
]);

// 分类列表
const categories = ref(['全部', '开发者', '工具', '社区']);
const selectedCategory = ref('全部');

// 搜索关键字
const searchQuery = ref('');

// 排序选项
const sortOption = ref('name');

// 筛选和排序后的友链
const filteredAndSortedLinks = computed(() => {
  // 筛选操作
  let filteredLinks = links.value.filter(link => {
    return (
        link.name.toLowerCase().includes(searchQuery.value.toLowerCase()) ||
        link.description.toLowerCase().includes(searchQuery.value.toLowerCase())
    ) && (selectedCategory.value === '全部' || link.category === selectedCategory.value);
  });

  // 排序操作
  if (sortOption.value === 'name') {
    filteredLinks.sort((a, b) => a.name.localeCompare(b.name));
  } else if (sortOption.value === 'date') {
    filteredLinks.sort((a, b) => new Date(b.date) - new Date(a.date));
  } else if (sortOption.value === 'views') {
    filteredLinks.sort((a, b) => b.views - a.views);
  }

  return filteredLinks;
});

// 选择分类
const selectCategory = (category) => {
  selectedCategory.value = category;
};

// 格式化日期
const formatDate = (date) => {
  const d = new Date(date);
  return `${d.getFullYear()}-${d.getMonth() + 1}-${d.getDate()}`;
};

// 添加友链（模拟）
const addLink = () => {
  links.value.push({
    name: '新的友链',
    url: 'https://example.com',
    icon: 'https://example.com/icon.png',
    description: '这是一个新的友链',
    date: new Date().toISOString().split('T')[0],
    updatedAt: new Date().toISOString().split('T')[0],
    category: '工具',
    views: 0,
    author: 'Example Author',
    rating: 0,
    tags: ['新'],
  });
};
</script>

<style lang="less" scoped>
.friend-container {
  padding: 20px;
  background-color: #f6f8fa; /* GitHub 淡灰色背景 */
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
  max-width: 900px;
  margin: 0 auto;
}

.title {
  font-size: 28px;
  color: #24292f; /* GitHub 标题颜色 */
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}

.search-container {
  margin-bottom: 20px;
  text-align: center;
}

.search-input {
  padding: 8px 16px;
  font-size: 16px;
  border-radius: 4px;
  border: 1px solid #d1d5da;
  width: 60%;
  max-width: 400px;
}

.category-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.category {
  background-color: #e1e4e8;
  padding: 5px 10px;
  margin: 0 5px;
  border-radius: 4px;
  cursor: pointer;
}

.category.active {
  background-color: #0366d6;
  color: white;
}

.sort-container {
  text-align: center;
  margin-bottom: 20px;
}

.link-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
}

.link-item {
  background-color: #ffffff;
  border-radius: 8px;
  padding: 12px;
  border: 1px solid #e1e4e8;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
}

.link-item:hover {
  transform: translateY(-4px);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
}

.link {
  display: flex;
  align-items: center;
  color: #0366d6;
  text-decoration: none;
}

.link:hover {
  color: #0056b3;
}

.link-icon {
  width: 24px;
  height: 24px;
  margin-right: 10px;
}

.link-name {
  font-size: 16px;
  color: #24292f;
}

.link-description {
  font-size: 14px;
  color: #586069;
  margin-top: 8px;
}

.link-date {
  font-size: 12px;
  color: #6a737d;
  margin-top: 8px;
}

.link-category {
  font-size: 12px;
  color: #2cbe4e;
  margin-top: 8px;
}

.link-views {
  font-size: 12px;
  color: #6a737d;
  margin-top: 8px;
}

.link-author {
  font-size: 12px;
  color: #0366d6;
  margin-top: 8px;
}

.link-rating {
  font-size: 12px;
  color: #f5a623;
  margin-top: 8px;
}

.link-tags {
  font-size: 12px;
  color: #6a737d;
  margin-top: 8px;
}

.add-link-btn {
  display: block;
  width: 200px;
  margin: 30px auto;
  padding: 10px;
  font-size: 16px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.add-link-btn:hover {
  background-color: #218838;
}
</style>

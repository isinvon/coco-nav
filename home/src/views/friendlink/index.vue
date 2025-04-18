<template>
  <div class="friend-container">

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
      <label for="sort">排序方式 : </label>
      <select v-model="sortOption" id="sort">
        <option value="name">按名称排序</option>
        <option value="date">按创建时间排序</option>
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
        <div class="link-meta">
          <span class="link-date">{{ formatDate(link.date) }}</span>
          <span v-if="link.category" class="link-category">{{ link.category }}</span>
        </div>
      </div>
    </div>

    <!-- 添加友链按钮 -->
    <button @click="addLink" class="add-link-btn">添加友链</button>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

// 模拟的友链数据
const links = ref([
  { name: 'Element', url: 'https://element-plus.org', icon: 'https://element-plus.org/images/element-plus-logo.svg', description: '全球最大的开发者社区', date: '2023-01-01', category: '开发者' },
  { name: 'Vue.js', url: 'https://vuejs.org', icon: 'https://vuejs.org/images/logo.png', description: '前端框架', date: '2022-03-15', category: '开发者' },
  { name: 'Stack Overflow', url: 'https://stackoverflow.com', icon: 'https://stackoverflow.com/favicon.ico', description: '编程问答社区', date: '2021-08-10', category: '工具' },
  { name: 'MDN Web Docs', url: 'https://developer.mozilla.org', icon: 'https://developer.mozilla.org/favicon.ico', description: '前端开发文档', date: '2020-06-22', category: '工具' },
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
    category: '工具',
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
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif;
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
  font-size: 14px;
}

.category.active {
  background-color: #0366d6;
  color: white;
}

.sort-container {
  text-align: center;
  margin-bottom: 20px;
  font-size: 14px;
  color: #666666;
}

.sort-container select {
  padding: 8px 16px;
  font-size: 14px;
  border-radius: 4px;
  border: 1px solid #d1d5da;
  color: #666666;
  margin-left: 10px;
}

.link-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
}

.link-item {
  background-color: #ffffff;
  border-radius: 8px;
  padding: 15px;
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
  font-size: 16px;
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
  color: #666666;
  &:hover {
    color: #0366d6;
  }
}

.link-description {
  font-size: 14px;
  color: #586069;
  margin-top: 8px;
}

.link-meta {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.link-date {
  font-size: 12px;
  color: #6a737d;
}

.link-category {
  font-size: 12px;
  color: #2cbe4e;
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

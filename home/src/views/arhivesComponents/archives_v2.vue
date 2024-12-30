<template>
  <div class="archives-container">
    <!-- 搜索框和筛选条件 -->
    <div class="filter-container">
      <div class="search-bar">
        <input
            v-model="searchQuery"
            type="text"
            placeholder="搜索归档..."
            class="search-input"
        />
      </div>
      <!-- 日期和月份筛选 -->
      <div class="filter-options">
        <label for="date-filter" class="filter-label">筛选日期：</label>
        <input
            type="month"
            v-model="selectedMonth"
            id="date-filter"
            class="month-input"
        />
      </div>
    </div>

    <!-- 归档列表 -->
    <div class="archive-list">
      <div
          v-for="archive in filteredArchives"
          :key="archive.id"
          class="archive-item"
      >
        <!-- 归档内容 -->
        <div class="archive-content">
          <a :href="archive.link" class="archive-title" target="_blank">{{ archive.title }}</a>
          <span class="archive-description">{{ archive.description }}</span>
          <span class="archive-date">{{ formatDate(archive.date) }}</span>
        </div>
      </div>
    </div>

    <!-- 分页 -->
    <div class="pagination">
      <button
          :disabled="currentPage <= 1"
          @click="currentPage--"
          class="pagination-button"
      >
        上一页
      </button>
      <button
          :disabled="currentPage >= totalPages"
          @click="currentPage++"
          class="pagination-button"
      >
        下一页
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';

// 数据定义
const searchQuery = ref('');
const currentPage = ref(1);
const pageSize = 12;
const selectedMonth = ref('');
const archives = ref([
  { id: 1, title: 'Vue3 入门教程', description: '学习 Vue3 从基础到进阶的教程', date: '2024-11-15', tags: ['Vue', '前端'], link: 'https://example.com/vue3' },
  { id: 2, title: 'Java 编程技巧', description: '一些常用的 Java 编程技巧和最佳实践', date: '2024-10-10', tags: ['Java', '编程'], link: 'https://example.com/java' },
  { id: 3, title: 'Python 自动化测试', description: 'Python 在自动化测试中的应用', date: '2024-09-25', tags: ['Python', '测试'], link: 'https://example.com/python-test' },
  // 更多模拟数据...
]);

// 计算属性：过滤后的归档
const filteredArchives = computed(() => {
  const query = searchQuery.value.toLowerCase();
  let filteredBySearch = archives.value.filter(archive =>
      archive.title.toLowerCase().includes(query) ||
      archive.description.toLowerCase().includes(query)
  );

  if (selectedMonth.value) {
    filteredBySearch = filteredBySearch.filter(archive => archive.date.startsWith(selectedMonth.value));
  }

  return paginateArchives(filteredBySearch);
});

// 计算属性：总页数
const totalPages = computed(() => {
  return Math.ceil(archives.value.length / pageSize);
});

// 方法：日期格式化
const formatDate = (date) => {
  return new Date(date).toLocaleDateString();
};

// 方法：分页
const paginateArchives = (archives) => {
  const start = (currentPage.value - 1) * pageSize;
  return archives.slice(start, start + pageSize);
};
</script>

<style lang="less" scoped>
/* 归档页面容器 */
.archives-container {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif;
  padding: 16px;
  background-color: #f6f8fa; /* GitHub 浅灰色背景 */
}

/* 搜索框容器 */
.filter-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.search-bar {
  width: 80%; /* 限制搜索框宽度 */
  max-width: 500px; /* 最大宽度 */
  margin-bottom: 12px;
}

.search-input {
  width: 100%;
  padding: 6px;
  font-size: 13px;
  border: 1px solid #d1d5da; /* GitHub 边框色 */
  border-radius: 5px;
  background-color: #fff;
  color: #24292f; /* GitHub 主文本色 */
}

/* 日期筛选 */
.filter-options {
  display: flex;
  align-items: center;
  justify-content: center;
}

.filter-label {
  margin-right: 8px;
  font-size: 13px;
  color: #24292f;
}

.month-input {
  padding: 6px;
  font-size: 13px;
  border: 1px solid #d1d5da;
  border-radius: 5px;
  background-color: #fff;
}

/* 归档列表 */
.archive-list {
  margin-top: 10px;
}

.archive-item {
  padding: 8px;
  background-color: #ffffff;
  border: 1px solid #e1e4e8; /* 边框 */
  border-radius: 6px;
  transition: background-color 0.2s ease-in-out;
  margin-bottom: 10px; /* 每个书签间隔 */
}

.archive-item:hover {
  background-color: #f1f8ff; /* 鼠标悬浮时变色 */
}

/* 每个书签内容 */
.archive-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 13px;
  color: #24292f;
}

.archive-title {
  font-weight: 600;
  color: #0366d6; /* GitHub 蓝色链接色 */
  margin-right: 8px;  /* 标题与描述之间的间隔 */
  text-decoration: none;
  flex: 0 1 auto; /* 确保标题自适应宽度 */
}

.archive-description {
  color: #586069; /* 更浅的灰色 */
  flex: 1; /* 描述占据剩余空间 */
  margin-right: 10px;
  text-align: left; /* 将描述文本左对齐 */
}

.archive-date {
  color: #586069;
  font-size: 12px;
  margin-left: 10px; /* 右侧对齐时间 */
}

/* 分页按钮 */
.pagination {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.pagination-button {
  background-color: #f6f8fa;
  color: #0366d6;
  border: 1px solid #d1d5da;
  padding: 6px 12px;
  font-size: 13px;
  border-radius: 5px;
  cursor: pointer;
}

.pagination-button:disabled {
  background-color: #f1f8ff;
  color: #b6c2cc;
  cursor: not-allowed;
}

.pagination-button:hover {
  background-color: #e1e4e8;
}
</style>

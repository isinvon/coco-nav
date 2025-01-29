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
        <div class="archive-header">
          <h5 class="archive-title">{{ archive.title }}</h5>
        </div>
        <p class="archive-description">{{ archive.description }}</p>
        <div class="archive-tags">
          <span v-for="tag in archive.tags" :key="tag" class="archive-tag">{{ tag }}</span>
        </div>
        <!-- 底部包含时间和访问链接 -->
        <div class="archive-footer">
          <span class="archive-date">{{ formatDate(archive.date) }}</span>
          <a :href="archive.link" class="archive-link" target="_blank">访问链接</a>
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

const filteredArchives = computed(() => {
  const query = searchQuery.value.toLowerCase();
  const filteredBySearch = archives.value.filter(archive =>
      archive.title.toLowerCase().includes(query) ||
      archive.description.toLowerCase().includes(query)
  );

  const filteredByMonth = selectedMonth.value
      ? filteredBySearch.filter(archive => archive.date.startsWith(selectedMonth.value))
      : filteredBySearch;

  return paginateArchives(filteredByMonth);
});

const totalPages = computed(() => Math.ceil(archives.value.length / pageSize));

const formatDate = (date) => new Date(date).toLocaleDateString();

const paginateArchives = (archives) => {
  const start = (currentPage.value - 1) * pageSize;
  return archives.slice(start, start + pageSize);
};
</script>

<style lang="less" scoped>
/* 归档页面容器 */
.archives-container {
  background-color: #f6f8fa; /* GitHub 淡灰色背景 */
  border-radius: 10px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif;
  padding: 16px;
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
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(180px, 1fr)); /* 调整每列最小宽度 */
  gap: 10px;
}

.archive-item {
  padding: 8px;
  background-color: #ffffff;
  border: 1px solid #e1e4e8; /* 边框 */
  border-radius: 6px;
  transition: background-color 0.2s ease-in-out;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  height: 100px; /* 更小的高度 */
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.archive-item:hover {
  background-color: #f1f8ff; /* 鼠标悬浮时变色 */
}

.archive-header {
  margin-bottom: 8px; /* 标题底部间距 */
}

.archive-title {
  font-size: 13px;
  font-weight: 600;
  color: #0366d6; /* GitHub 蓝色链接色 */
  margin: 0;
}

.archive-description {
  font-size: 11px;
  color: #24292f;
  margin: 5px 0;
  height: 35px; /* 限制描述文本高度 */
  overflow: hidden;
}

.archive-tags {
  margin-top: 5px;
}

.archive-tag {
  background-color: #e1e4e8; /* 标签背景色 */
  color: #24292f;
  padding: 3px 6px;
  font-size: 9px;
  border-radius: 15px;
  margin-right: 5px;
  display: inline-block;
}

.archive-footer {
  display: flex;
  justify-content: space-between;
  margin-top: auto;
  font-size: 11px;
}

.archive-date {
  color: #586069; /* GitHub 深灰色文本 */
}

.archive-link {
  color: #0366d6;
  text-decoration: none;
}

.archive-link:hover {
  text-decoration: underline;
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

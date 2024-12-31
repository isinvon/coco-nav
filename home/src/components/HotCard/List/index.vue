<script lang="js" setup>
import {ref} from "vue";

/**
 * 热点列表 (支持无限滚动加载)
 * @author sinvon
 * @since 2024年12月5日23:30:44
 * @module List
 */

// 接收父组件传递的新闻列表
const props = defineProps({
  newsList: {
    type: Array,
    required: true, // 确保接收到数据
  },
});

const newListSplit = ref([]); // 已加载的新闻列表
let startIndex = 0; // 起始索引
const batchSize = 6; // 每次加载的数量

// 将新闻列表分割成小块，每次加载3条, (如果不启动无限滚动请直接去除v-infinite-scroll属性)
const load = () => {
  if (startIndex >= props.newsList.length) return; // 防止越界
  const endIndex = startIndex + batchSize;
  newListSplit.value.push(...props.newsList.slice(startIndex, endIndex));
  startIndex = endIndex; // 更新起始索引
};
</script>

<template>
  <ul
      v-infinite-scroll="load"
      class="infinite-list"
      style="overflow: auto"
  >
    <li
        v-for="(news, index) in newListSplit"
        :key="news.id"
        class="infinite-list-item"
        :class="{ hot: index < 3 }"
    >
      <!-- 渲染新闻标题，点击跳转新标签 -->
      <a :href="news.url" target="_blank">
        {{ news.title }}<span v-if="index < 3">🔥</span>
      </a>
    </li>
  </ul>
</template>

<style lang="less" scoped>
.infinite-list {
  padding: 0;
  margin: -15px;
  height: 350px;
  list-style: none;
  /* 隐藏滚动条,但是保留滚动功能========开始========== */

  ::-webkit-scrollbar {
    width: 0; /* 对垂直滚动条 */
    height: 0; /* 对水平滚动条 */
  }

  -ms-overflow-style: none; /* IE 和 Edge */
  scrollbar-width: none; /* Firefox */

  &::-webkit-scrollbar {
    display: none;
  }

  /* 隐藏滚动条,但是保留滚动功能========结束========== */
}

.infinite-list .infinite-list-item {
  border-radius: 10px;
  display: flex;
  align-items: center;
  height: 50px;
  margin: 10px;
  padding: 0 10px;
  color: var(--el-color-primary);
  background: var(--el-color-primary-light-9);
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);

  a {
    color: inherit;
    text-decoration: none;
    font-weight: bold;
    flex: 1;
  }

}

.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}

/* 高亮前三条新闻 */
.infinite-list .infinite-list-item.hot {
  background: #fef0f0; /* 红色背景 */
  color: #f56c6c;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}
</style>

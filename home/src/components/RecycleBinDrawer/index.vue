<script lang="js" setup>
/**
 * @module RecycleBinDrawer
 * @description 回收站抽屉组件
 * @author sinvon
 * @since 2024年12月4日22:12:10
 */
import InfiniteScroll from './InfiniteScrollBox/index.vue';
import {useRecycleBinDrawerStore} from '@/store/recycleBinDrawer';
import {ref} from 'vue';
import {throttle} from 'lodash';
import {ElMessageBox, ElMessage} from 'element-plus';
import {Delete} from "@element-plus/icons-vue";

const recycleBinDrawerStore = useRecycleBinDrawerStore();
const bookmarks = ref([
  // 模拟初始数据
  {
    title: '菜鸟教程1111111111111111111111111',
    url: 'https://www.runoob.com/',
    icon: 'https://www.runoob.com/favicon.ico',
    description: '菜鸟教程 - 免费的在线编程教程111111111111111111111111111'
  },
  {
    title: '开源中国',
    url: 'https://www.oschina.net/',
    icon: 'https://www.oschina.net/favicon.ico',
    description: 'Vue.js - The Progressive JavaScript Framework'
  }
]);

// 节流加载更多数据
const throttledLoad = throttle(() => {
  const mockData = Array.from({length: 5}, (_, i) => ({
    title: `书签 ${bookmarks.value.length + i + 1}`,
    url: `https://example${bookmarks.value.length + i + 1}.com`,
    icon: 'https://via.placeholder.com/16',
    description: `这是书签 ${bookmarks.value.length + i + 1}`
  }));
  bookmarks.value.push(...mockData);
}, 300);

// 清空回收站功能
const clearRecycleBin = () => {
  ElMessageBox.prompt(
      '请输入您的名字以确认清空回收站',
      '确认清空',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        inputPlaceholder: '输入名字'
      }
  )
      .then(({value}) => {
        if (value) {
          bookmarks.value = [];
          ElMessage.success('回收站已清空');
        } else {
          ElMessage.error('请输入名字以确认清空');
        }
      })
      .catch(() => ElMessage.info('清空操作已取消'));
};

// 永久删除单个书签
const deleteBookmark = (index) => {
  bookmarks.value.splice(index, 1);
  ElMessage.success('书签已永久删除');
};
</script>

<template>
  <el-drawer class="recycle-bin-drawer" direction="rtl" v-model="recycleBinDrawerStore.isOpen" title="回收站"
             :with-header="false">
    <!-- 清空回收站按钮 -->
    <div class="recycle-bin-header">
      <el-button type="danger" size="large" @click="clearRecycleBin">清空回收站</el-button>
    </div>

    <!-- 无限滚动书签列表 -->
    <InfiniteScroll class="infinite-list" :loadMore="throttledLoad">
      <div
          v-for="(bookmark, index) in bookmarks"
          :key="index"
          class="infinite-list-item"
      >
        <div class="bookmark-info">
          <img :src="bookmark.icon" alt="icon" class="bookmark-icon"/>
          <div class="bookmark-details">
            <a :href="bookmark.url" target="_blank" class="bookmark-title">{{ bookmark.title }}</a>
            <p class="bookmark-description">{{ bookmark.description }}</p>
          </div>
        </div>
        <el-button type="danger" size="default" @click="deleteBookmark(index)">永久删除</el-button>
      </div>
    </InfiniteScroll>
  </el-drawer>
</template>

<style scoped lang="less">
@import "./index";
</style>
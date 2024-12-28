<template>
  <el-drawer direction="ltr" v-model="leftDrawerStore.isOpen" title="我是左边的抽屉" :with-header="false">
    <div class="sidebar-content">
      <span>Hi there!</span>
      <UserInfoSection :userInfo="userInfo" />
      <SettingsButton @open-settings="showModal = true" />
      <SortOrderSection @update-sort-order="handleSortOrderChange" />
      <div class="settings-section">
        <h3>Recently Visited Bookmarks</h3>
        <WordcloudTag :data="recentBookmarks" />
      </div>
      <RecommendedBookmarks :bookmarks="recommendedBookmarks" />
      <AboutUsSection :aboutUsText="aboutUsText" />
    </div>

    <SettingsDialog v-model="showModal" />
  </el-drawer>
</template>

<script lang="js" setup>
import { ref } from 'vue';
import { useLeftDrawerStore } from '@/store/leftDrawer';
import UserInfoSection from './components/UserInfoSection.vue';
import SettingsButton from './components/SettingsButton.vue';
import SortOrderSection from './components/SortOrderSection.vue';
import WordcloudTag from './components/WordcloudTag.vue';
import RecommendedBookmarks from './components/RecommendedBookmarks.vue';
import AboutUsSection from './components/AboutUsSection.vue';
import SettingsDialog from './components/SettingsDialog.vue';

const leftDrawerStore = useLeftDrawerStore();
const showModal = ref(false);
const sortAscending = ref(true);

const userInfo = {
  name: 'John Doe',
  email: 'johndoe@example.com'
};

const recentBookmarks = [
  { name: 'Bookmark 1', view: 10 },
  { name: 'Bookmark 2', view: 20 },
  { name: 'Bookmark 3', view: 30 },
  { name: 'Bookmark 4', view: 40 },
  { name: 'Bookmark 5', view: 121110 },
  { name: 'Bookmark 6', view: 50 },
  { name: 'Bookmark 7', view: 80 },
  { name: 'Bookmark 8', view: 121110 },
  { name: 'Bookmark 9', view: 220 },
];

const recommendedBookmarks = [
  { title: 'Recommendation 1', url: 'https://example.com/recommendation1' },
  { title: 'Recommendation 2', url: 'https://example.com/recommendation2' },
  { title: 'Recommendation 3', url: 'https://example.com/recommendation3' }
];

const aboutUsText = 'Welcome to our Bookmark Navigation website. We provide a simple and efficient way to manage your bookmarks.';

const handleSortOrderChange = (newValue) => {
  sortAscending.value = newValue;
  // Handle sort order change logic here
};
</script>

<style scoped>
.sidebar-content {
  padding: 20px;
  color: #666666;
}
</style>

<template>
  <el-drawer direction="ltr" v-model="leftDrawerStore.isOpen" title="我是左边的抽屉" :with-header="false">
    <div class="sidebar-content">
      <span>Hi there!</span>
      <div class="settings-section">
        <h3>User Info</h3>
        <p>Name: John Doe</p>
        <p>Email: johndoe@example.com</p>
      </div>
      <div class="settings-section">
        <h3>Settings</h3>
        <button @click="showModal = true">Open Settings</button>
      </div>
      <div class="settings-section">
        <h3>Sort Order</h3>
        <label>
          <input type="checkbox" v-model="sortAscending"/>
          Sort Ascending
        </label>
      </div>
      <div class="settings-section">
        <h3>Recently Visited Bookmarks</h3>
        <!--<word-cloud-->
        <!--    :data="recentBookmarks"-->
        <!--    nameKey="name"-->
        <!--    valueKey="value"-->
        <!--    :color="['#1f77b4', '#ff7f0e', '#2ca02c', '#d62728', '#9467bd', '#8c564b']"-->
        <!--    :fontSize="[20, 60]"-->
        <!--    style="width: 100%; height: 300px;"-->
        <!--/>-->
        <word-cloud
            v-if="recentBookmarks.length"
            :data="recentBookmarks"
            nameKey="name"
            valueKey="value"
            :color="['#1f77b4', '#ff7f0e', '#2ca02c', '#d62728', '#9467bd', '#8c564b']"
            :fontSize="[20, 60]"
            style="width: 100%; height: 300px;"
        />
      </div>
      <div class="settings-section">
        <h3>Recommended Bookmarks</h3>
        <ul>
          <li v-for="(bookmark, index) in recommendedBookmarks" :key="index">
            <a :href="bookmark.url" target="_blank">{{ bookmark.title }}</a>
          </li>
        </ul>
      </div>
      <div class="settings-section">
        <h3>About Us</h3>
        <p>Welcome to our Bookmark Navigation website. We provide a simple and efficient way to manage your
          bookmarks.</p>
      </div>
    </div>

    <el-dialog v-model="showModal" title="Settings">
      <div>
        <h4>Theme</h4>
        <label>
          <input type="radio" name="theme" value="light" v-model="selectedTheme"/> Light Mode
        </label>
        <label>
          <input type="radio" name="theme" value="dark" v-model="selectedTheme"/> Dark Mode
        </label>
      </div>
      <div>
        <h4>Language</h4>
        <select v-model="selectedLanguage">
          <option value="en">English</option>
          <option value="zh">中文</option>
          <option value="fr">Français</option>
          <option value="es">Español</option>
        </select>
      </div>
      <div>
        <h4>Notifications</h4>
        <label>
          <input type="checkbox" v-model="enableNotifications"/> Enable Notifications
        </label>
      </div>
      <div>
        <h4>Cloud Sync</h4>
        <label>
          <input type="checkbox" v-model="enableSync"/> Enable Cloud Sync
        </label>
      </div>
      <div>
        <h4>Privacy</h4>
        <label>
          <input type="checkbox" v-model="shareData"/> Share Data with Third Parties
        </label>
      </div>
      <div>
        <h4>Help & Support</h4>
        <a href="#" @click.prevent="openHelp">Help Documentation</a><br/>
        <a href="#" @click.prevent="contactSupport">Contact Support</a>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="showModal = false">Cancel</el-button>
          <el-button type="primary" @click="saveSettings">Save</el-button>
        </span>
      </template>
    </el-dialog>
  </el-drawer>
</template>

<script lang="js" setup>
import {ref, watch} from 'vue';
import {useLeftDrawerStore} from '@/store/leftDrawer';
import WordCloud from 'vue-wordcloud';

const leftDrawerStore = useLeftDrawerStore();
const showModal = ref(false);
const sortAscending = ref(true);
const selectedTheme = ref('light');
const selectedLanguage = ref('en');
const enableNotifications = ref(false);
const enableSync = ref(false);
const shareData = ref(false);

const recentBookmarks = ref([
  {name: 'Bookmark 1', value: 10},
  {name: 'Bookmark 2', value: 20},
  {name: 'Bookmark 3', value: 30},
  {name: 'Bookmark 4', value: 40},
  {name: 'Bookmark 5', value: 50}
]);

const recommendedBookmarks = [
  {title: 'Recommendation 1', url: 'https://example.com/recommendation1'},
  {title: 'Recommendation 2', url: 'https://example.com/recommendation2'},
  {title: 'Recommendation 3', url: 'https://example.com/recommendation3'}
];

const saveSettings = () => {
  // 这里可以添加保存用户设置的逻辑
  console.log('Settings saved:', {
    sortAscending: sortAscending.value,
    theme: selectedTheme.value,
    language: selectedLanguage.value,
    notifications: enableNotifications.value,
    sync: enableSync.value,
    shareData: shareData.value
  });
  showModal.value = false;
};

const openHelp = () => {
  window.open('https://example.com/help', '_blank');
};

const contactSupport = () => {
  window.open('mailto:support@example.com?subject=Support%20Request', '_blank');
};

</script>

<style scoped>
.sidebar-content {
  padding: 20px;
}

.settings-section {
  margin-bottom: 20px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

label {
  display: block;
  margin-top: 5px;
}

select {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
}

a {
  color: #409EFF;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 10px;
}

.wordcloud-container {
  height: 300px;
}
</style>

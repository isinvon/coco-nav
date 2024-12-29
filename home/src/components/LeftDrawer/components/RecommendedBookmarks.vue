<template>
  <div class="settings-section">
    <h3>Recommended Bookmarks</h3>
    <ul>
      <li v-for="(bookmark, index) in bookmarks" :key="index">
        <a :href="bookmark.url" target="_blank">{{ bookmark.title }}</a>
      </li>
    </ul>
  </div>
</template>

<script lang="js" setup>

const props = defineProps({
  bookmarks: {
    type: Array,
    required: true
  }
});
</script>

<style scoped>
.settings-section {
  margin-bottom: 20px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 10px;
}

a {
  color: #409EFF;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>
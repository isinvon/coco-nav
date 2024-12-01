<script setup>
</script>

<template>
  <div>
    <div id="cssportal-grid">
      <div class="bookmark">
        <div class="bookmark-icon">🌐</div>
        <div class="bookmark-title">Google</div>
      </div>
      <div v-for="i in 30" class="bookmark">
        <div class="bookmark-icon">📘</div>
        <div class="bookmark-title">Facebook</div>
      </div>
      <div class="bookmark">
        <div class="bookmark-icon">🐦</div>
        <div class="bookmark-title">Twitter</div>
      </div>
      <div v-for="i in 30" class="bookmark">
        <div class="bookmark-icon">📺</div>
        <div class="bookmark-title">YouTube</div>
      </div>
      <div v-for="i in 30" class="bookmark">
        <div class="bookmark-icon">📷</div>
        <div class="bookmark-title">Instagram</div>
      </div>
      <div class="bookmark">
        <div class="bookmark-icon">🛒</div>
        <div class="bookmark-title">Amazon</div>
      </div>
      <div class="bookmark">
        <div class="bookmark-icon">🛒</div>
        <div class="bookmark-title">Ebay</div>
      </div>
      <div class="bookmark">
        <div class="bookmark-icon">🛒</div>
        <div class="bookmark-title">Walmart</div>
      </div>
      <!-- Add more bookmarks as needed -->
    </div>
  </div>
</template>

<style scoped lang="less">
#cssportal-grid {
  display: grid;
  grid-template-rows: repeat(12, 1fr);
  grid-template-columns: repeat(12, 1fr);
  gap: 20px;
  width: 100%;
  height: 100%;
}

.bookmark {
  background-color: rgb(246, 246, 246);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 8px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  cursor: pointer;
}

.bookmark:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.bookmark-icon {
  font-size: 2rem;
  margin-bottom: 10px;
}

.bookmark-title {
  font-size: 12px;
  font-weight: bold;
  color: #59636e;
}
</style>
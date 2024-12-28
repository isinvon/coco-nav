<template>
  <div class="settings-section">
    <h3>Settings</h3>
    <button @click="$emit('open-settings')">Open Settings</button>
  </div>
</template>

<script lang="js" setup>
// No script needed for this simple component
</script>

<style scoped>
.settings-section {
  margin-bottom: 20px;
}

button {
  padding: 5px 10px;
  cursor: pointer;
}
</style>
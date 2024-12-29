<template>
  <div class="settings-section">
    <h3>About Us</h3>
    <p>{{ aboutUsText }}</p>
  </div>
</template>

<script lang="js" setup>

const props = defineProps({
  aboutUsText: {
    type: String,
    required: true
  }
});
</script>

<style scoped>
.settings-section {
  margin-bottom: 20px;
}
</style>
<template>
  <div class="settings-section">
    <h3>User Info</h3>
    <p>Name: {{ userInfo.name }}</p>
    <p>Email: {{ userInfo.email }}</p>
  </div>
</template>

<script lang="js" setup>
import {defineProps} from 'vue';

const props = defineProps({
  userInfo: {
    type: Object,
    required: true
  }
});
</script>

<style scoped>
.settings-section {
  margin-bottom: 20px;
}
</style>
<template>
  <div class="settings-section">
    <h3>Sort Order</h3>
    <label>
      <input type="checkbox" v-model="sortAscending"/>
      Sort Ascending
    </label>
  </div>
</template>

<script lang="js" setup>
import {ref, watch} from 'vue';

const sortAscending = ref(false);

watch(sortAscending, (newValue) => {
  emit('update-sort-order', newValue);
});

const emit = defineEmits(['update-sort-order']);
</script>

<style scoped>
.settings-section {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-top: 5px;
}
</style>
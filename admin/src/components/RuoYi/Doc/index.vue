<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script setup>
const url = ref('https://github.com/isinvon/coco-nav/blob/main/README.md');

function goto() {
  window.open(url.value)
}
</script>
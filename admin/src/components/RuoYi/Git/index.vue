<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script setup>
const url = ref('https://github.com/isinvon/coco-nav');

function goto() {
  window.open(url.value)
}
</script>

<template>
  <div>
    <AboutPage :archiveData="archiveData"/>
  </div>
</template>

<script setup>
import AboutPage from './aboutComponents/AboutPage.vue';
import {archiveData} from '../../mock/archive.js';
</script>

<style lang="less" scoped>
</style>

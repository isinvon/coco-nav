<template>
  <div>
    <AboutPage :aboutData="aboutData"/>
  </div>
</template>

<script setup>
import AboutPage from './components/AboutPage.vue';
import {aboutData} from '../../../mock/about.js';
</script>

<style lang="less" scoped>
</style>

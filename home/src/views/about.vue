<template>
  <div>
    <AboutPage :aboutData="aboutData"/>
  </div>
</template>

<script setup>
import AboutPage from './aboutComponents/AboutPage.vue';
import {aboutData} from '../../mock/about.js';
</script>

<style lang="less" scoped>
</style>

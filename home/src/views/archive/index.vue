<template>
  <div>
    <Archives_v1 v-if="templateVersion === 1"/>
    <Archives_v2 v-if="templateVersion === 2"/>
  </div>
</template>

<script setup>
import Archives_v1 from "@/views/archive/components/archive_v1.vue";
import Archives_v2 from "@/views/archive/components/archive_v2.vue";
import * as SettingData from '../../../mock/setting.js';
import {onMounted, ref} from "vue";

// 归档的显示样式
const templateVersion = ref(0);

onMounted(() => {
  templateVersion.value = SettingData.setting.data.archives.template;
});
</script>

<style lang="less" scoped>

</style>
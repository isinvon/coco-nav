<template>
  <Comment :comments="commentData" :setting="settingData"></Comment>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import Comment from './messageComponents/Comment/index.vue';
import * as CommentData from '../../mock/message.js';
import * as SettingData from '../../mock/setting.js'

const commentData = ref([]);
const settingData = ref([]);

onMounted(() => {
  commentData.value = CommentData.comment.data;
  settingData.value = SettingData.setting.data;
});
</script>

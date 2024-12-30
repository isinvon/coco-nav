<template>
  <Comment :comments="commentData"></Comment>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import Comment from './messageComponents/Comment/index.vue';
import * as CommentData from '../../mock/message.js';

const commentData = ref([]);

onMounted(() => {
  commentData.value = CommentData.comment.data;
});
</script>

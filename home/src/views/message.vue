<template>
  <div class="container">
    <!--左边-->
    <el-row :gutter="20">
      <el-col :span="3">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
      <el-col :span="12">
        <div class="grid-content ep-bg-purple"/>
        <!-- 评论区整体结构 -->
        <div class="comment-section">
          <!-- 评论显示区 -->
          <Comment :comments="commentData" :setting="settingData"></Comment>
          <!-- 提交评论表单 -->
          <div class="comment-form">
            <textarea v-model="newComment" placeholder="请输入你的留言..." rows="3"></textarea>
            <button @click="submitComment" :disabled="isSubmitting">提交评论</button>
          </div>
        </div>
      </el-col>
      <!--右边-->
      <el-col :span="5">
        <div class="grid-content ep-bg-purple"/>
        <RightAside/>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
/**
 * 留言区组件
 */
import {ref, onMounted} from 'vue';
import Comment from './messageComponents/Comment/index.vue';
import RightAside from './messageComponents/RightAside/index.vue';
import * as CommentData from '../../mock/message.js';
import * as SettingData from '../../mock/setting.js';

const commentData = ref([]);
const settingData = ref([]);
const newComment = ref('');
const isSubmitting = ref(false);

onMounted(() => {
  commentData.value = CommentData.comment.data;
  settingData.value = SettingData.setting.data;
});

// 提交评论
const submitComment = () => {
  if (newComment.value.trim() === '') {
    return; // 评论为空时不提交
  }

  isSubmitting.value = true;

  // 假设这里是一个异步请求提交评论
  setTimeout(() => {
    // 模拟添加新评论到评论区
    const comment = {
      id: Date.now(),
      content: newComment.value,
      user: '匿名用户',
      avatar: 'https://randomuser.me/api/portraits/men/1.jpg', // 可以替换为实际头像
      time: new Date().toLocaleString(),
    };
    commentData.value.push(comment); // 将新评论加入评论数据
    newComment.value = ''; // 清空输入框
    isSubmitting.value = false; // 恢复按钮状态
  }, 1000);
};
</script>

<style lang="less" scoped>

.container {
  border-radius: 10px;
}

.comment-section {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
}

.comment-form {
  margin-top: 20px;
  max-width: 750px;
}

.comment-form textarea {
  width: 100%;
  padding: 10px;
  font-size: 14px;
  border: 1px solid #ddd;
  border-radius: 4px;
  resize: none;
}

.comment-form button {
  margin-top: 10px;
  padding: 8px 16px;
  background-color: #368a3a;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.comment-form button:disabled {
  background-color: #ccc;
}

.comment-form button:not(:disabled):hover {
  background-color: #45a049;
}

// ------------------

.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>

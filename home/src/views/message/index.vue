<template>
  <div class="container">
    <!--左边-->
    <el-row :gutter="20">
      <el-col :span="3">
      </el-col>
      <el-col :span="12">
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
import Comment from './components/Comment/index.vue';
import RightAside from './components/RightAside/index.vue';
import * as CommentData from '../../../mock/message.js';
import * as SettingData from '../../../mock/setting.js';
import { ElMessage } from 'element-plus'; // 引入Element Plus的Message组件

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
      fromAvatar: 'https://randomuser.me/api/portraits/men/1.jpg',
      date: new Date().toLocaleString(),  // 评论时间
      ownerId: 'talents100020', // 文章的id
      fromId: 'errhefe232213',  // 评论者id
      fromName: '程序猿小弟',   // 评论者昵称
      level: 5, // 评论者账号等级
      likeNum: 0, // 点赞人数
    };
    commentData.value.push(comment); // 将新评论加入评论数据
    newComment.value = ''; // 清空输入框
    isSubmitting.value = false; // 恢复按钮状态

    // 提交评论成功后弹出消息提示
    ElMessage.success('评论提交成功！');
  }, 1000);
};
</script>

<style lang="less" scoped>

.container {
  border-radius: 10px;
}

.comment-section {
  width: 100%;
  margin: 0 auto;
}

.comment-form {
  margin-top: 20px;
}

.comment-form textarea {
  width: 100%;
  padding: 10px;
  font-size: 14px;
  border: 1px solid #ddd;
  border-radius: 4px;
  resize: none;

  &:focus{
    outline: none; // 去除输入框的默认边框
    border: 1px solid #a9a9a9; // 添加新的边框样式
  }
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
  &:focus {
    outline: none;
  }
}

.comment-form button:disabled {
  background-color: #ccc;
}

.comment-form button:not(:disabled):hover {
  background-color: #45a049;
}

// ------------------
</style>

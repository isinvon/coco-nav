<template>
  <div class="container">
    <div class="comment" v-for="item in comments" :key="item.id">
      <div class="info">
        <img class="avatar" :src="item.fromAvatar" width="36" height="36"/>
        <div class="right">
          <div class="name">{{ item.fromName }}</div>
          <div class="date">{{ item.date }}</div>
        </div>
      </div>
      <div class="content">{{ item.content }}</div>
      <div class="control">
        <span class="like" :class="{ active: item.isLike }" @click="likeClick(item)">
          <i class="iconfont icon-like"></i>
          <span class="like-num">{{ item.likeNum > 0 ? item.likeNum + '人赞' : '赞' }}</span>
        </span>
        <span class="comment-reply" @click="showCommentInput(item)">
          <i class="iconfont icon-comment"></i>
          <span>回复</span>
        </span>
      </div>
      <div class="reply">
        <div class="item" v-for="reply in item.reply" :key="reply.id">
          <div class="reply-content">
            <span class="from-name">{{ reply.fromName }}</span><span>: </span>
            <span class="to-name">@{{ reply.toName }}</span>
            <span>{{ reply.content }}</span>
          </div>
          <div class="reply-bottom">
            <span>{{ reply.date }}</span>
            <span class="reply-text" @click="showCommentInput(item, reply)">
              <i class="iconfont icon-comment"></i>
              <span>回复</span>
            </span>
          </div>
        </div>
        <div class="write-reply" v-if="item.reply.length > 0" @click="showCommentInput(item)">
          <i class="el-icon-edit"></i>
          <span class="add-comment">添加新评论</span>
        </div>
        <transition name="fade">
          <div class="input-wrapper" v-if="showItemId === item.id">
            <el-input class="gray-bg-input"
                      v-model="inputComment"
                      type="textarea"
                      :rows="3"
                      autofocus
                      placeholder="写下你的评论">
            </el-input>
            <div class="btn-control">
              <span class="cancel" @click="cancel">取消</span>
              <el-button class="btn" type="success" round @click="commitComment">确定</el-button>
            </div>
          </div>
        </transition>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, defineProps} from 'vue';

// 定义 props
const props = defineProps({
  comments: {
    type: Array,
    required: true
  }
});

// 数据和方法
const inputComment = ref('');
const showItemId = ref('');

const likeClick = (item) => {
  if (item.isLike === null) {
    item.isLike = true;
    item.likeNum++;
  } else {
    if (item.isLike) {
      item.likeNum--;
    } else {
      item.likeNum++;
    }
    item.isLike = !item.isLike;
  }
};

const cancel = () => {
  showItemId.value = '';
};

const commitComment = () => {
  console.log(inputComment.value);
};

const showCommentInput = (item, reply = null) => {
  if (reply) {
    inputComment.value = `@${reply.fromName} `;
  } else {
    inputComment.value = '';
  }
  showItemId.value = item.id;
};
</script>

<style scoped lang="less">
@import "./index";
</style>

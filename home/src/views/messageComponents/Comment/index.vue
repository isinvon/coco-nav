<template>
  <div class="container">
    <div class="comment" v-for="item in comments" :key="item.id">
      <div class="info">
        <img class="avatar" :src="item.fromAvatar" width="36" height="36"/>
        <div class="right">
          <div class="name">
            {{ item.fromName }}
            <!--等级-->
            <el-tag
                v-if="commentSetting.showLevel"
                round
                effect="plain"
                size="small"
                class="level"
                style="color: #89949f;border-color: #89949f;font-weight: bold"
            >
              V{{ item.level }}
            </el-tag>
          </div>
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
            <span class="from-name">
              {{ reply.fromName }}
              <!--等级-->
              <el-tag
                  v-if="commentSetting.showLevel"
                  round
                  effect="plain"
                  size="small"
                  class="level"
              >
                V{{ item.level }}
              </el-tag>
            </span>
            <span>: </span>
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
        <!--<div class="write-reply" v-if="item.reply.length > 0" @click="showCommentInput(item)">-->
        <!--  <i class="el-icon-edit"></i>-->
        <!--  <span class="add-comment">添加新评论</span>-->
        <!--</div>-->
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
              <!-- 表情按钮-->
              <el-button @click="toggleEmojiPanel" circle class="emoji-button" type="text" color="gray">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24">
                  <path fill="currentColor"
                        d="M15.5 11q.625 0 1.063-.437T17 9.5t-.437-1.062T15.5 8t-1.062.438T14 9.5t.438 1.063T15.5 11m-7 0q.625 0 1.063-.437T10 9.5t-.437-1.062T8.5 8t-1.062.438T7 9.5t.438 1.063T8.5 11m3.5 6.5q1.7 0 3.088-.962T17.1 14h-1.65q-.55.925-1.462 1.463T12 16t-1.987-.537T8.55 14H6.9q.625 1.575 2.013 2.538T12 17.5m0 4.5q-2.075 0-3.9-.788t-3.175-2.137T2.788 15.9T2 12t.788-3.9t2.137-3.175T8.1 2.788T12 2t3.9.788t3.175 2.137T21.213 8.1T22 12t-.788 3.9t-2.137 3.175t-3.175 2.138T12 22m0-2q3.35 0 5.675-2.325T20 12t-2.325-5.675T12 4T6.325 6.325T4 12t2.325 5.675T12 20"/>
                </svg>
              </el-button>
              <div>
                <span class="cancel" @click="cancel">取消</span>
                <el-button class="btn" round @click="commitComment">确定</el-button>
              </div>
            </div>
          </div>
        </transition>
        <transition name="fade">
          <!--表情面板(表情选择器)-->
          <EmojiSelect class="emoji-picker-container" v-if="showEmojiPanel" @emoji-selected="insertEmoji"
                       :style="emojiPanelStyle"/>
        </transition>
      </div>
    </div>
  </div>
</template>

<script setup>
/**
 * 评论组件
 * @author: sinvon
 * @since 2024年12月31日19:40:07
 */
import {onMounted, onUnmounted} from 'vue';
import EmojiSelect from "./components/EmojiSelect.vue";
// 导入评论相关的钩子
import {useComment} from '@/hooks/useComment';

// 定义 props
const props = defineProps({
  comments: {
    type: Array,
    required: true
  },
  /**
   * 评论设置
   */
  setting: {
    type: Object,
    required: true,
  }
});

// 使用评论相关的钩子
const {
  inputComment,
  showItemId,
  showEmojiPanel,
  emojiPanelStyle,
  commentSetting,
  insertEmoji,
  toggleEmojiPanel,
  likeClick,
  cancel,
  commitComment,
  showCommentInput
} = useComment(props);

// 监听点击事件，关闭表情面板
const handleClickOutside = (event) => {
  const emojiPanel = document.querySelector('.emoji-picker-container');
  const emojiButton = document.querySelector('.emoji-button');

  if (emojiPanel && emojiPanel.contains(event.target)) return;
  if (emojiButton && emojiButton.contains(event.target)) return;

  showEmojiPanel.value = false;
};

// 在组件挂载时添加事件监听
onMounted(() => {
  document.addEventListener('click', handleClickOutside);
});

// 在组件卸载时移除事件监听
onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside);
});
</script>

<style scoped lang="less">
@import "./index";
</style>

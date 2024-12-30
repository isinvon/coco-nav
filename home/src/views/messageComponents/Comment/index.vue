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
import {ref, computed} from 'vue';
import EmojiSelect from "./components/EmojiSelect.vue";

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

// 数据和方法
const inputComment = ref('');
const showItemId = ref('');
const showEmojiPanel = ref(false);
const emojiPanelStyle = ref({});
const emojis = ['😀', '😂', '😍', '😢', '😎', '😜', '😇', '🤔', '🤗', '😅'];  // 可自定义表情

// 计算属性
const commentSetting = computed(() => {
  return {
    showLevel: props.setting?.message?.comment?.show_level || false, // 使用可选链, 不会抛出错误, 只会返回undefined, 而设置默认返回false
    // 以后可以扩展更多设置
    // ...
  };
});

// 插入表情
const insertEmoji = (emoji) => {
  inputComment.value += emoji;
  showEmojiPanel.value = false;  // 选择表情后关闭面板
};

// 显示/隐藏表情面板
const toggleEmojiPanel = (item) => {
  showEmojiPanel.value = !showEmojiPanel.value;

  if (showEmojiPanel.value) {
    // 获取按钮的位置
    const button = document.querySelector('.emoji-button');
    const rect = button.getBoundingClientRect();

    // 计算面板的位置，确保它在按钮下方
    emojiPanelStyle.value = {
      position: 'absolute',
      top: `${rect.bottom + window.scrollY}px`, // 需要考虑滚动条的偏移
      left: `${rect.left}px`,
      zIndex: 1000
    };
  }
};

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
//@import "./index";

// 全局背景色
@background-color: #f8f9fa; // GitHub轻灰背景
@primary-color: #24292f; // GitHub主色
@secondary-color: #586069; // 次要文字色
@border-color: #d0d7de; // 边框颜色
@button-color: #2d333b; // 按钮背景色

.container {
  padding: 10px;
  background-color: @background-color;
}

.comment {
  display: flex;
  flex-direction: column;
  padding: 10px;
  //border-bottom: 1px solid @border-color;
  background-color: #ffffff;
  border-radius: 8px;
  margin-bottom: 10px;

  .info {
    display: flex;
    align-items: center;
    margin-bottom: 10px;

    .avatar {
      border-radius: 50%;
      margin-right: 10px;
    }

    .right {
      display: flex;
      flex-direction: column;

      .name {
        color: @primary-color;
        font-weight: 500;
        font-size: 14px;
      }

      .date {
        color: @secondary-color;
        font-size: 12px;
      }
    }
  }

  .content {
    font-size: 14px;
    color: @primary-color;
    line-height: 20px;
    margin-bottom: 10px;
  }

  .control {
    display: flex;
    align-items: center;
    font-size: 14px;
    color: @secondary-color;

    .like {
      display: flex;
      align-items: center;
      margin-right: 20px;
      cursor: pointer;

      &:hover, &.active {
        color: @primary-color;
      }

      .iconfont {
        font-size: 16px;
        margin-right: 5px;
      }
    }

    .comment-reply {
      display: flex;
      align-items: center;
      cursor: pointer;

      &:hover {
        color: @primary-color;
      }

      .iconfont {
        font-size: 16px;
        margin-right: 5px;
      }
    }
  }

  .reply {
    margin-top: 10px;
    padding-left: 20px;
    border-left: 2px solid @border-color;

    .item {
      display: flex;
      flex-direction: column;
      margin: 10px 0;
      padding-bottom: 10px;
      border-bottom: 1px dashed @border-color;

      .reply-content {
        display: flex;
        align-items: center;
        font-size: 13px;
        color: @primary-color;

        .from-name {
          color: @primary-color;
          font-weight: bold;
        }

        .to-name {
          color: @primary-color;
          margin-left: 5px;
          margin-right: 5px;
        }
      }

      .reply-bottom {
        display: flex;
        align-items: center;
        font-size: 12px;
        color: @secondary-color;

        .reply-text {
          display: flex;
          align-items: center;
          margin-left: 10px;
          cursor: pointer;

          &:hover {
            color: @primary-color;
          }

          .icon-comment {
            margin-right: 5px;
          }
        }
      }
    }

    .write-reply {
      display: flex;
      align-items: center;
      font-size: 14px;
      color: @secondary-color;
      padding: 10px;
      cursor: pointer;

      &:hover {
        color: @primary-color;
      }

      .el-icon-edit {
        margin-right: 5px;
      }
    }

    .input-wrapper {
      padding: 10px;

      .gray-bg-input, .el-input__inner {
        background-color: #f1f3f5; /* 淡灰背景 */
        border-radius: 5px;
        border: 1px solid #dcdfe6;
      }

      .btn-control {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-top: 10px;

        .cancel {
          font-size: 16px;
          color: @secondary-color;
          margin-right: 20px;
          cursor: pointer;

          &:hover {
            color: @primary-color;
          }
        }

        .btn {
          font-size: 16px;
          background-color: @primary-color;
          color: #fff;
          border-radius: 5px;
          padding: 5px 10px;
        }
      }
    }

    .emoji-picker-container {
      border: 1px solid #eaeaea;
      border-radius: 12px;
      width: auto; // 你可以根据需求调整宽度
      height: auto;
      overflow-y: auto;
      position: absolute;
      top: 0;
      left: 0;
      z-index: 1000;
      margin-top: 5px;
    }

    // emoji 按钮样式
    .emoji-button {
      position: relative;
      color: #888888;

      &:hover {
        transform: scale(1.1);
      }

      &:focus {
        outline: none;
      }

      &:active {
        outline: none;
        //向下偏移
        transform: translateY(2px);
        color: #777777;
      }
    }

    .level {
      margin-left: 1px;
      margin-right: 5px;
      font-weight: bold;
      color: @secondary-color;
      border-color: @secondary-color;
    }
  }
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>

<template>
  <div class="container">
    <div class="comment" v-for="item in comments" :key="item.id">
      <div class="info">
        <img class="avatar" :src="item.fromAvatar" width="36" height="36"/>
        <div class="right">
          <div class="name">
            {{ item.fromName }}
            <!--等级-->
            <el-tag v-if="commentSetting.showLevel" type="warning" size="small">V{{ item.level }}</el-tag>
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
            <el-tag v-if="commentSetting.showLevel" type="warning" size="small">V{{ item.level }}</el-tag>
            </span><span>: </span>
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
import {ref, defineProps, computed} from 'vue';

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

// 计算属性
const commentSetting = computed(() => {
  return {
    showLevel: props.setting?.message?.comment?.show_level || false, // 使用可选链, 不会抛出错误, 只会返回undefined, 而设置默认返回false
    // 以后可以扩展更多设置
    // ...
  };
});

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
  border-bottom: 1px solid @border-color;
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
        justify-content: flex-end;
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
  }
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>

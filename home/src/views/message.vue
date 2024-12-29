<script setup>
import { ref, computed } from 'vue';
import { ElInput, ElButton, ElCard, ElAvatar, ElDivider, ElRow, ElCol, ElMessageBox, ElTag, ElAutocomplete } from 'element-plus';
import { useMessageStore } from '@/store/message.js'; // 假设这是状态管理文件

// 导入 Emoji 选择器组件
import EmojiPicker from 'vue-emoji-picker';

const messageStore = useMessageStore();
const newMessageContent = ref('');
const showEmojiPicker = ref(false);
const currentUser = ref(1); // 假设当前用户ID为1

// 提交留言
const addMessage = () => {
  if (newMessageContent.value.trim()) {
    const mentions = parseMentions(newMessageContent.value);
    messageStore.addMessage({ content: newMessageContent.value, userId: currentUser.value, mentions });
    newMessageContent.value = ''; // 清空输入框
    sendEmailNotification(mentions); // 触发邮件通知
  }
};

// 删除留言
const deleteMessage = (id) => {
  ElMessageBox.confirm('确定要删除这条留言吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    messageStore.deleteMessage(id);
  }).catch(() => { });
};

// 编辑留言
const editMessage = (message) => {
  newMessageContent.value = message.content;
  messageStore.deleteMessage(message.id); // 删除原来的留言
};

// 添加表情
const addEmoji = (emoji) => {
  newMessageContent.value += emoji.native;
  showEmojiPicker.value = false;
};

// 解析@提及用户
const parseMentions = (content) => {
  const regex = /@(\w+)/g;
  let match;
  const mentions = [];
  while ((match = regex.exec(content)) !== null) {
    mentions.push(match[1]); // 提取用户名
  }
  return mentions;
};

// 发送邮件通知（用户被提及时）
const sendEmailNotification = async (mentions) => {
  try {
    mentions.forEach((mention) => {
      console.log(`发送邮件通知给用户: @${mention}`);
    });
  } catch (error) {
    console.error('邮件发送失败', error);
  }
};

// 举报留言
const reportMessage = (id) => {
  ElMessageBox.prompt('请输入举报原因:', '举报留言', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    inputPattern: /.+/,
    inputErrorMessage: '请填写举报原因'
  }).then(({ value }) => {
    const message = messageStore.messages.find(msg => msg.id === id);
    if (message) {
      message.reports.push(value); // 添加举报记录
      console.log('举报已提交:', value);
    }
  }).catch(() => { });
};

// 获取用户等级
const getUserLevel = (userId) => {
  const user = messageStore.getUserById(userId);
  if (user.activity > 1000) {
    return '高级用户';
  } else if (user.activity > 500) {
    return '中级用户';
  }
  return '普通用户';
};

// 计算留言排序
const formattedMessages = computed(() => {
  return messageStore.messages
      .map(msg => ({
        ...msg,
        user: messageStore.getUserById(msg.userId),
        mentions: parseMentions(msg.content)
      }))
      .sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp));
});

// 用户列表（可供 @ 提及选择）
const mentionUsers = computed(() => {
  return messageStore.users.map(user => user.name);
});

// 点赞功能
const likeMessage = (id) => {
  messageStore.likeMessage(id);
};
</script>

<template>
  <div class="message-board">
    <!-- 留言板卡片 -->
    <el-card class="message-card">
      <h2 class="message-title">留言板</h2>

      <el-row :gutter="20" align="middle">
        <el-col :span="18">
          <el-input v-model="newMessageContent" placeholder="请输入您的留言" class="input-field" />
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="addMessage" class="submit-button">提交</el-button>
        </el-col>

        <el-col :span="2">
          <el-button @click="showEmojiPicker = !showEmojiPicker" class="emoji-button">选择表情</el-button>
        </el-col>
      </el-row>

      <!-- 表情选择器 -->
      <div v-if="showEmojiPicker" class="emoji-picker-container">
        <emoji-picker @emoji-click="addEmoji" />
      </div>

      <!-- 输入内容自动补全 -->
      <el-autocomplete
          v-model="newMessageContent"
          :fetch-suggestions="mentionUsers"
          placeholder="请输入内容"
          class="autocomplete-input"
      ></el-autocomplete>
    </el-card>

    <!-- 分隔线 -->
    <el-divider></el-divider>

    <!-- 留言展示 -->
    <transition-group name="list" tag="ul">
      <li v-for="message in formattedMessages" :key="message.id" class="message-item">
        <el-card class="message-card">
          <el-row :gutter="20" align="middle">
            <el-col :span="4">
              <el-avatar size="large" :src="message.user.avatar" class="user-avatar">{{ message.user.name.charAt(0) }}</el-avatar>
            </el-col>

            <el-col :span="20">
              <p class="message-info">
                <strong>{{ message.user.name }}</strong>
                <el-tag size="small" class="user-level">{{ getUserLevel(message.userId) }}</el-tag>
              </p>
              <p class="message-content">
                {{ message.content }}
                <el-tag v-for="mention in message.mentions" :key="mention" size="small" class="mention">@{{ mention }}</el-tag>
              </p>
              <small>{{ new Date(message.timestamp).toLocaleString() }}</small>
            </el-col>
          </el-row>

          <!-- 操作按钮 -->
          <div class="message-actions">
            <el-button type="danger" plain @click="deleteMessage(message.id)" v-if="message.userId === currentUser" class="delete-button">删除</el-button>
            <el-button type="warning" plain @click="reportMessage(message.id)" class="report-button">举报</el-button>
            <el-button type="info" plain @click="likeMessage(message.id)" class="like-button">
              点赞 <span>{{ message.likes || 0 }}</span>
            </el-button>
            <el-button type="primary" plain @click="editMessage(message)" class="edit-button">编辑</el-button>
          </div>
        </el-card>
      </li>
    </transition-group>
  </div>
</template>

<style lang="less">
.message-board {
  margin: 20px;
  font-family: 'Helvetica', sans-serif;
  color: #333;
}

.message-card {
  background-color: #ffffff;
  border: none;
  box-shadow: none;
  margin-bottom: 20px;
  border-radius: 12px;
  padding: 20px;
}

.message-title {
  font-size: 1.6rem;
  font-weight: 600;
  margin-bottom: 10px;
  color: #333;
}

.input-field {
  border-radius: 20px;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
}

.submit-button {
  width: 100%;
  background-color: #42b983;
  color: #fff;
  border-radius: 20px;
  border: none;
}

.emoji-button {
  margin-top: 10px;
  padding: 5px 10px;
  background-color: #f1f1f1;
  border-radius: 20px;
}

.autocomplete-input {
  margin-top: 10px;
  border-radius: 20px;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
}

.message-item {
  transition: all 0.3s ease-in-out;
}

.message-info {
  font-size: 1.1rem;
  color: #666;
}

.message-content {
  font-size: 1rem;
  color: #444;
  margin-top: 10px;
}

.user-avatar {
  border-radius: 50%;
}

.user-level {
  background-color: #e4e4e4;
  color: #666;
  margin-left: 10px;
  border-radius: 12px;
  font-size: 0.85rem;
}

.mention {
  background-color: #e0e0e0;
  color: #333;
  border-radius: 12px;
  margin-right: 5px;
}

.delete-button, .edit-button, .like-button, .report-button {
  margin-top: 10px;
}

.emoji-picker-container {
  position: absolute;
  top: 60px;
  left: 20px;
  background-color: #fff;
  border-radius: 12px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

.el-button {
  border-radius: 20px;
}

.list-enter-from, .list-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

.message-actions {
  margin-top: 10px;
  display: flex;
  justify-content: space-between;
}
</style>
// store/message.js
import { defineStore } from 'pinia';

export const useMessageStore = defineStore('message', {
    state: () => ({
        // 留言列表
        messages: [
            {
                id: 1,
                userId: 1,
                content: '这是第一条留言，@Alice 好久不见！',
                timestamp: new Date(),
                reports: [],
                mentions: ['Alice'], // 提及的用户
            },
            {
                id: 2,
                userId: 2,
                content: '第二条留言，大家好！',
                timestamp: new Date(),
                reports: [],
                mentions: [],
            },
        ],

        // 用户列表
        users: [
            { id: 1, name: 'Bob', avatar: 'https://randomuser.me/api/portraits/men/1.jpg', level: '高级用户', activity: 1200 },
            { id: 2, name: 'Alice', avatar: 'https://randomuser.me/api/portraits/women/2.jpg', level: '普通用户', activity: 400 },
        ],
    }),

    getters: {
        // 获取留言列表
        getMessages(state) {
            return state.messages;
        },

        // 获取用户列表
        getUsers(state) {
            return state.users;
        },

        // 根据用户ID获取用户信息
        getUserById: (state) => (userId) => {
            return state.users.find((user) => user.id === userId);
        },
    },

    actions: {
        // 添加留言
        addMessage(message) {
            const newMessage = {
                id: this.messages.length + 1, // 自增ID
                userId: message.userId,
                content: message.content,
                timestamp: new Date(),
                reports: [],
                mentions: message.mentions || [], // 提及的用户
            };
            this.messages.push(newMessage);
        },

        // 删除留言
        deleteMessage(messageId) {
            this.messages = this.messages.filter((msg) => msg.id !== messageId);
        },

        // 添加举报
        addReport(messageId, reportReason) {
            const message = this.messages.find((msg) => msg.id === messageId);
            if (message) {
                message.reports.push(reportReason);
            }
        },

        // 获取留言中的提及用户（解析@符号）
        getMentionedUsers(content) {
            const regex = /@(\w+)/g;
            let match;
            const mentions = [];
            while ((match = regex.exec(content)) !== null) {
                mentions.push(match[1]); // 提取用户名
            }
            return mentions;
        },

        // 发送邮件通知
        async sendEmailNotification(message) {
            // 这里可以调用邮件服务 API，例如 SendGrid、Mailgun，或者你自己的 SMTP 服务器
            console.log('Sending email notification for new message:', message);
            // 示例：发送邮件通知管理员
            const adminEmail = 'admin@example.com';
            const emailSubject = `新的留言: ${message.content}`;
            const emailBody = `
        用户: ${this.getUserById(message.userId).name}\n
        留言内容: ${message.content}\n
        提及用户: ${message.mentions.join(', ')}\n
        时间: ${new Date(message.timestamp).toLocaleString()}
      `;
            console.log(`发送邮件到 ${adminEmail}，主题: ${emailSubject}\n内容: ${emailBody}`);
            // 模拟邮件发送操作
            return new Promise((resolve) => setTimeout(resolve, 1000));
        },

        // 解析提及的用户并发送通知
        async notifyMentionedUsers(message) {
            const mentions = this.getMentionedUsers(message.content);
            for (const mention of mentions) {
                const user = this.users.find((u) => u.name === mention);
                if (user) {
                    // 发送通知给被提及的用户
                    console.log(`通知用户 ${user.name}: 您在留言中被提及，留言内容：${message.content}`);
                    // 这里可以进一步实现站内信、邮件等通知功能
                }
            }
        },
    },
});

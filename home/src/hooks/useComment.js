// useComment.js
import {computed, ref} from 'vue';

/**
 * 评论相关的钩子
 */
export function useComment(props) {
    const inputComment = ref('');
    const showItemId = ref('');
    const showEmojiPanel = ref(false);
    const emojiPanelStyle = ref({});
    const emojis = ['😀', '😂', '😍', '😢', '😎', '😜', '😇', '🤔', '🤗', '😅'];  // 可自定义表情

    // 评论设置的计算属性
    const commentSetting = computed(() => {
        return {
            showLevel: props.setting?.message?.comment?.show_level || false, // 使用可选链, 不会抛出错误, 只会返回undefined, 而设置默认返回false
        };
    });

    const insertEmoji = (emoji) => {
        inputComment.value += emoji;
        showEmojiPanel.value = false;  // 选择表情后关闭面板
    };

    const toggleEmojiPanel = (item) => {
        showEmojiPanel.value = !showEmojiPanel.value;

        if (showEmojiPanel.value) {
            const button = document.querySelector('.emoji-button');
            const rect = button.getBoundingClientRect();
            emojiPanelStyle.value = {
                position: 'absolute',
                top: `${rect.bottom + window.scrollY - 80}px`, // 需要考虑滚动条的偏移
                left: `${rect.left - 30}px`,
                zIndex: 1000,
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

    return {
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
    };
}

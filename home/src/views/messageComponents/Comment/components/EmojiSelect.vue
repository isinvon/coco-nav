<template>
  <div>
    <emoji-picker class="emoji-picker" @emoji-click="handleEmojiClick"/>
  </div>
</template>

<script setup>
/**
 * emoji 选择器
 * @author: sinvon
 * @since 2024年12月30日18:07:07
 */
import 'emoji-picker-element'; // 引入 emoji-picker-element
// 使用 defineEmit 定义 emit 函数
const emit = defineEmits(['emoji-selected']);

const handleEmojiClick = (event) => {
  // 使用 emit 函数将选中的表情符号传递给父组件
  emit('emoji-selected', event.detail.unicode);
};

</script>

<style lang="less" scoped>
.emoji-picker {
  width: 350px;
  height: 300px;
}
</style>

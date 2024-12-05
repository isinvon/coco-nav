<script setup>
/**
 * @author sinvon
 * @description 取消删除书签按钮(启动删除模式会显示)
 * @since 2024年12月3日00:33:22
 * @module CancelDeleteBookmarkButton
 */
import {useDeleteBookmarkStore} from '@/store/deleteBookmark'

const deleteBookmarkStore = useDeleteBookmarkStore()

// 切换删除状态
const toggleDeleteState = () => {
  deleteBookmarkStore.isDeleting = !deleteBookmarkStore.isDeleting
}
</script>

<template>
  <div class="cancel-delete-bookmark-button">
    <button
        v-show="deleteBookmarkStore.isDeleting"
        @click="toggleDeleteState"
        aria-label="Open global navigation menu"
        class="cancel-delete-bookmark-button__icon"
        type="button"
    >
      取消删除
    </button>
  </div>
</template>

<style scoped lang="less">
.cancel-delete-bookmark-button {
  display: flex;
  align-items: center;
  justify-content: flex-start; /* 左对齐按钮 */
  padding: 0 15px;

  &__icon {
    font-weight: bolder;
    background-color: transparent;
    border: none;
    padding: 0;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #59636e;
    transition: transform 0.3s ease, color 0.3s ease; /* 添加过渡动画(使按钮失去焦点的时候,恢复原状的过程也有动画) */

    // 鼠标悬浮时，图标颜色变绿, 颜色渐变动画, 大小变大1.05倍数动画
    &:hover {
      color: #a40e26;
      transform: scale(1.3);
      transition: transform 0.5s ease, color 0.5s ease;
    }

    // 点击按钮时，图标向下移动2px
    &:active {
      transform: translateY(5px);
      transition: transform 0.2s ease;
    }

    &:focus {
      outline: none; /* 去掉按钮的聚焦时外框 */
    }

    svg {
      transition: fill 0.2s ease;
      width: 18px;
      height: 18px;
    }
  }
}
</style>

<script setup>
/**
 * @author sinvon
 * @description 删除书签按钮
 * @since 2024年12月3日00:33:22
 * @module DeleteBookmarkButton
 */
import {useDeleteBookmarkStore} from '@/store/deleteBookmark'

const deleteBookmarkStore = useDeleteBookmarkStore()

// 切换删除状态
const toggleDeleteState = () => {
  deleteBookmarkStore.isDeleting = !deleteBookmarkStore.isDeleting
}
</script>

<template>
  <div class="delete-bookmark-button">
    <!--删除按钮(不启动删除模式的时候显示)-->
    <button
        v-show="!deleteBookmarkStore.isDeleting"
        @click="toggleDeleteState"
        aria-label="Open global navigation menu"
        class="delete-bookmark-button__icon"
        type="button"
    >
      <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24">
        <path fill="currentColor"
              d="M3 6.524c0-.395.327-.714.73-.714h4.788c.006-.842.098-1.995.932-2.793A3.68 3.68 0 0 1 12 2a3.68 3.68 0 0 1 2.55 1.017c.834.798.926 1.951.932 2.793h4.788c.403 0 .73.32.73.714a.72.72 0 0 1-.73.714H3.73A.72.72 0 0 1 3 6.524"/>
        <path fill="currentColor" fill-rule="evenodd"
              d="M11.596 22h.808c2.783 0 4.174 0 5.08-.886c.904-.886.996-2.34 1.181-5.246l.267-4.187c.1-1.577.15-2.366-.303-2.866c-.454-.5-1.22-.5-2.753-.5H8.124c-1.533 0-2.3 0-2.753.5s-.404 1.289-.303 2.866l.267 4.188c.185 2.906.277 4.36 1.182 5.245c.905.886 2.296.886 5.079.886m-1.35-9.811c-.04-.434-.408-.75-.82-.707c-.413.043-.713.43-.672.864l.5 5.263c.04.434.408.75.82.707c.413-.044.713-.43.672-.864zm4.329-.707c.412.043.713.43.671.864l-.5 5.263c-.04.434-.409.75-.82.707c-.413-.044-.713-.43-.672-.864l.5-5.264c.04-.433.409-.75.82-.707"
              clip-rule="evenodd"/>
      </svg>
    </button>
  </div>
</template>

<style scoped lang="less">
.delete-bookmark-button {
  display: flex;
  align-items: center;
  justify-content: flex-start; /* 左对齐按钮 */
  padding: 0 15px;

  &__icon {
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
      transform: scale(1.6);
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

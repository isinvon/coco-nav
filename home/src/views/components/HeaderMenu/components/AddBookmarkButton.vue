<script setup>
/**
 * 添加书签按钮
 * @author sinvon
 * @description 添加书签按钮
 * @since 2024年12月2日15:45:19
 * @module AddBookmarkButton
 */
import {useAddBookmarkBoxStore} from '@/store/addBookmarkBox'

const addBookmarkBoxStore = useAddBookmarkBoxStore()

// 切换抽屉状态
const toggleAddBookmarkBox = () => {
  addBookmarkBoxStore.toggleBox()
}
</script>

<template>
  <div class="add-bookmark-button">
    <button
        @click="toggleAddBookmarkBox"
        aria-label="Open global navigation menu"
        class="add-bookmark-button__icon"
        type="button">
      <svg xmlns="http://www.w3.org/2000/svg" width="25" height="32" viewBox="0 0 448 512">
        <path fill="currentColor"
              d="M416 208H272V64c0-17.67-14.33-32-32-32h-32c-17.67 0-32 14.33-32 32v144H32c-17.67 0-32 14.33-32 32v32c0 17.67 14.33 32 32 32h144v144c0 17.67 14.33 32 32 32h32c17.67 0 32-14.33 32-32V304h144c17.67 0 32-14.33 32-32v-32c0-17.67-14.33-32-32-32"/>
      </svg>
    </button>
  </div>
</template>

<style scoped lang="less">
.add-bookmark-button {
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

    &:hover {
      color: #105e10;
      transition: color 0.5s ease;
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

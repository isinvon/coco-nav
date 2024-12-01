<script setup>
/**
 * 展开抽屉的按钮
 */
import { useLeftDrawerStore } from '@/store/leftDrawer'

const leftDrawerStore = useLeftDrawerStore()

// 切换抽屉状态
const toggleDrawer = () => {
  leftDrawerStore.toggleDrawer()
}
</script>

<template>
  <div class="expand-button">
    <button
        @click="toggleDrawer"
        aria-label="Open global navigation menu"
        class="expand-button__icon"
        type="button">
      <svg
          aria-hidden="true"
          height="16"
          viewBox="0 0 16 16"
          version="1.1"
          width="16">
        <path d="M1 2.75A.75.75 0 0 1 1.75 2h12.5a.75.75 0 0 1 0 1.5H1.75A.75.75 0 0 1 1 2.75Zm0 5A.75.75 0 0 1 1.75 7h12.5a.75.75 0 0 1 0 1.5H1.75A.75.75 0 0 1 1 7.75ZM1.75 12h12.5a.75.75 0 0 1 0 1.5H1.75a.75.75 0 0 1 0-1.5Z"></path>
      </svg>
    </button>
  </div>
</template>


<style scoped lang="less">
.expand-button {
  display: flex;
  align-items: center;
  justify-content: flex-start; /* 左对齐按钮 */
  padding: 0 10px;
}

.expand-button__icon {
  background-color: transparent;
  border: none;
  padding: 0;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}

.expand-button__icon svg {
  fill: #59636e; /* 设置图标颜色与GitHub相似 */
  transition: fill 0.2s ease;
  width: 18px;
  height: 18px;
}

.expand-button__icon:hover svg {
  fill: #030303; /* 鼠标悬浮时改变图标颜色 */
}

.expand-button__icon:focus {
  outline: none; /* 去掉按钮的聚焦时外框 */
}
</style>
e>
<template>
  <div class="loading-spanner">
    <div class="load"></div>
  </div>
</template>

<script setup>
/**
 * 加载动画
 * @author sinvon
 * @since 2025年1月1日14:51:11
 */
</script>
<style lang="less" scoped>
.loading-spanner {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(164, 164, 164, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}

.load {
  width: 40px;
  aspect-ratio: 1;
  background: #ababab;
  clip-path: polygon(0 0, 100% 0, 100% 100%);
  animation: l2 2s infinite cubic-bezier(0.3, 1, 0, 1);
}

@keyframes l2 {
  25% {
    clip-path: polygon(0 0, 100% 0, 0 100%)
  }
  50% {
    clip-path: polygon(0 0, 100% 100%, 0 100%)
  }
  75% {
    clip-path: polygon(100% 0, 100% 100%, 0 100%)
  }
  100% {
    clip-path: polygon(100% 0, 100% 100%, 0 0)
  }
}
</style>
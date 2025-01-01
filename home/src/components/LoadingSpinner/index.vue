<template>
  <div class="loading-spinner">
    <div class="spinner"></div>
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
.loading-spinner {
position: fixed;
top: 0;
left: 0;
right: 0;
bottom: 0;
background: rgba(0, 0, 0, 0.3);
display: flex;
justify-content: center;
align-items: center;
}

.spinner {
border: 4px solid rgba(255, 255, 255, 0.3);
border-top: 4px solid #fff;
border-radius: 50%;
width: 50px;
height: 50px;
animation: spin 1s linear infinite;
}

@keyframes spin {
0% { transform: rotate(0deg); }
100% { transform: rotate(360deg); }
}
</style>
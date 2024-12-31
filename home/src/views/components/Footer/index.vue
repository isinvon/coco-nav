<script setup>
/**
 * Powered by coco-nav
 * Copyright 2024 Sinvon
 * You are not allowed to remove this attribution without permission.
 */
</script>

<template>
  <footer id="simple-footer">
    <ul class="footer-links">
      <!-- 版权部分 -->
      <li class="footer-copyright">
        <a href="https://github.com/isinvon/coco-nav" target="_blank">
          <!-- 添加垂直对齐样式 -->
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" class="footer-icon">
            <path fill="currentColor" d="M12 2A10 10 0 0 0 2 12c0 4.42 2.87 8.17 6.84 9.5c.5.08.66-.23.66-.5v-1.69c-2.77.6-3.36-1.34-3.36-1.34c-.46-1.16-1.11-1.47-1.11-1.47c-.91-.62.07-.6.07-.6c1 .07 1.53 1.03 1.53 1.03c.87 1.52 2.34 1.07 2.91.83c.09-.65.35-1.09.63-1.34c-2.22-.25-4.55-1.11-4.55-4.92c0-1.11.38-2 1.03-2.71c-.1-.25-.45-1.29.1-2.64c0 0 .84-.27 2.75 1.02c.79-.22 1.65-.33 2.5-.33s1.71.11 2.5.33c1.91-1.29 2.75-1.02 2.75-1.02c.55 1.35.2 2.39.1 2.64c.65.71 1.03 1.6 1.03 2.71c0 3.82-2.34 4.66-4.57 4.91c.36.31.69.92.69 1.85V21c0 .27.16.59.67.5C19.14 20.16 22 16.42 22 12A10 10 0 0 0 12 2"/>
          </svg>
        </a>
        © 2024 Sinvon
      </li>
      <!-- 其他链接 -->
      <li><a href="#">Home</a></li>
      <li><a href="#">About</a></li>
      <li><a href="#">Terms</a></li>
      <li><a href="#">Privacy</a></li>
      <li><a href="#">Security</a></li>
      <li><a href="#">Status</a></li>
      <li><a href="#">Docs</a></li>
      <li><a href="#">Contact GitHub</a></li>
      <li><a href="#">Pricing</a></li>
      <li><a href="#">API</a></li>
      <li><a href="#">Training</a></li>
      <li><a href="#">Blog</a></li>
    </ul>
    <!-- Power by -->
    <div class="footer-powered-by">
      <span>Powered by</span>
      <a href="https://github.com/isinvon/coco-nav" target="_blank">coco-nav</a>
    </div>
  </footer>
</template>

<style scoped lang="less">
@import './index';
/* 为 SVG 图标添加垂直对齐 */
.footer-icon {
  vertical-align: top; /* 确保图标与文本垂直居中对齐 */
  margin-right: 5px; /* 调整图标与文本之间的间距 */
}
</style>

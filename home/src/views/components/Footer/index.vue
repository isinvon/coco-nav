<script setup>
</script>

<template>
  <footer id="simple-footer">
    <ul class="footer-links">
      <!-- 版权部分 -->
      <li class="footer-copyright">
        <a href="https://github.com/isinvon/coco-nav" target="_blank">🐱</a>
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
  </footer>
</template>

<style scoped lang="less">
#simple-footer {
  font-size: 12px;
  font-family: Arial, sans-serif;
  text-align: center;
  padding: 10px 0;
  color: #59636e; /* 默认字体颜色 */
}

.footer-links {
  list-style: none;
  padding: 0;
  margin: 0;
  display: inline-flex;
  gap: 15px; /* 控制链接间距 */
}

.footer-links li {
  margin: 0;
}

.footer-links a {
  text-decoration: none; /* 初始无下划线 */
  color: #59636e; /* 默认颜色 */
  transition: color 0.3s ease, text-decoration 0.3s ease; /* 平滑过渡 */
}

.footer-links a:hover {
  color: #0073e6; /* 悬浮时链接颜色 */
  text-decoration: underline; /* 悬浮时显示下划线 */
}

/* 特定样式：覆盖全局样式，禁止 footer-copyright 内链接显示下划线 */
.footer-copyright a {
  text-decoration: none !important; /* 强制无下划线 */
  color: #59636e; /* 默认颜色 */
  transition: color 0.3s ease; /* 添加悬浮颜色变化 */
}

.footer-copyright a:hover {
  color: #0073e6; /* 悬浮时颜色变化 */
  text-decoration: none !important; /* 悬浮时也无下划线 */
}
</style>

<script setup>
/**
 * 这个Footer类似一个卡片样式的,然后版权信息分三列竖着
 */
</script>

<template>
  <footer id="github-footer">
    <div class="footer-container">
      <div class="footer-left">
        <a href="https://github.com" class="footer-logo">
          <svg aria-hidden="true" height="24" viewBox="0 0 16 16" width="24">
            <path fill-rule="evenodd" d="... (GitHub 图标路径)" fill="#FFFFFF"></path>
          </svg>
        </a>
        <p>&copy; 2024 GitHub, Inc.</p>
      </div>
      <div class="footer-right">
        <ul class="footer-links">
          <li><a href="#">Terms</a></li>
          <li><a href="#">Privacy</a></li>
          <li><a href="#">Security</a></li>
          <li><a href="#">Status</a></li>
          <li><a href="#">Docs</a></li>
        </ul>
        <ul class="footer-links">
          <li><a href="#">Contact GitHub</a></li>
          <li><a href="#">Pricing</a></li>
          <li><a href="#">API</a></li>
          <li><a href="#">Training</a></li>
          <li><a href="#">Blog</a></li>
        </ul>
      </div>
    </div>
  </footer>
</template>

<style scoped lang="less">
#github-footer {
  background-color: #f6f8fa;
  color: #646464;
  font-family: Arial, sans-serif;
  padding: 20px 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 6px;
}

.footer-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
  max-width: 1200px;
  flex-wrap: wrap;
}

.footer-left {
  flex: 1;
  min-width: 200px;
}

.footer-left p {
  margin: 8px 0;
  font-size: 14px;
}

.footer-left .footer-logo {
  display: flex;
  align-items: center;
  text-decoration: none;
  color: #c9d1d9;
}

.footer-left .footer-logo svg {
  margin-right: 8px;
}

.footer-right {
  flex: 2;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  min-width: 300px;
}

.footer-links {
  list-style: none;
  padding: 0;
  margin: 0;
}

.footer-links li {
  margin: 5px 0;
}

.footer-links a {
  color: #606060;
  text-decoration: none;
  font-size: 14px;
  transition: color 0.2s ease;
}

.footer-links a:hover {
  color: #58a6ff;
}

</style>
<template>
  <div class='card-holder'>
    <div class='card-wrapper'>
      <a href='#'>
        <div class='card bg-01'>
          <span class='card-content'>item #1</span>
        </div>
      </a>
    </div>
    <div class='card-wrapper'>
      <a href='#'>
        <div class='card bg-02'>
          <span class='card-content'>long menu item #2</span>
        </div>
      </a>
    </div>
    <div class='card-wrapper'>
      <a href='#'>
        <div class='card bg-03'>
          <span class='card-content'>menu item #3</span>
        </div>
      </a>
    </div>
    <div class='card-wrapper'>
      <a href='#'>
        <div class='card bg-04'>
          <span class='card-content'>item #4</span>
        </div>
      </a>
    </div>
    <div class='card-wrapper'>
      <a href='#'>
        <div class='card bg-05'>
          <span class='card-content'>menu item #5</span>
        </div>
      </a>
    </div>
    <div class='card-wrapper'>
      <a href='#'>
        <div class='card bg-06'>
          <span class='card-content'>long menu item #1</span>
        </div>
      </a>
    </div>
  </div>
</template>

<script setup>

</script>

<style lang="less" scoped>
a:link,
a:hover,
a:visited,
a:active {
  color: #fff;
  text-decoration: none;
}

body {
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
  background: #fff;
}

.card-holder {
  position: fixed;
  width: 0px;
  overflow: visible;
}

.card-wrapper {
  display: inline-block;
  float: right;
  clear: both;
}

.card {
  position: relative;
  left: 32px;
  padding: 16px 32px 16px 64px;
  margin: 8px;
  background: #fff;
  transition: all 0.3s ease-in-out 0.1s;
}

//添加导航的响应式效果
.card:hover {
  position: relative;
  left: 100%;
  margin-left: -32px;
  transition: all 0.3s ease-in-out;
}

.card-content {
  display: inline-block;
  color: #fff;
  font-family: 'Droid Sans', sans-serif;
  font-size: 16px;
  font-weight: bold;
  white-space: nowrap;
}

.bg-01 { background: #539770; }
.bg-02 { background: #4B7D74; }
.bg-03 { background: #8DC2BC; }
.bg-04 { background: #EDD6B4; }
.bg-05 { background: #BE7467; }
.bg-06 { background: #E2AE63; }
</style>
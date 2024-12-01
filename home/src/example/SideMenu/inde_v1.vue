<template>
  <nav>
    <ul class="menus">
      <li class="li" v-for="(menu, index) in menus" :key="index">
        <i :class="menu.icon" aria-hidden="true"></i>
        {{ menu.name }}
        <ul>
          <li v-for="(subItem, subIndex) in menu.subItems" :key="subIndex">
            <i :class="menu.icon" aria-hidden="true">{{ subItem }}</i>
          </li>
        </ul>
      </li>
    </ul>
  </nav>
</template>

<script setup>
import { ref } from 'vue';

// 菜单数据
const menus = ref([
  {
    name: 'QQ',
    icon: 'fa fa-qq',
    subItems: ['1号', '2号', '3号', '4号', '5号', '6号', '7号']
  },
  {
    name: 'weixin',
    icon: 'fa fa-weixin',
    subItems: ['1号', '2号', '3号', '4号', '5号', '6号', '7号', '8号', '9号', '10号']
  },
  {
    name: 'weibo',
    icon: 'fa fa-weibo',
    subItems: ['1号', '2号', '3号']
  },
  {
    name: 'tencent-weibo',
    icon: 'fa fa-tencent-weibo',
    subItems: ['1号', '2号', '3号', '4号', '5号']
  },
  {
    name: 'telegram',
    icon: 'fa fa-telegram',
    subItems: ['1号', '2号', '3号', '4号', '5号']
  }
]);
</script>

<style lang="less" scoped>
* {
  margin: 0;
  padding: 0;
}

body {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #7284a7;
}

ul {
  list-style: none;
}

nav {
  position: fixed;
  width: 300px;
  height: 650px;
  left: 0;
  background-color: #ffbb00;
  border-radius: 20px 20px 20px 0px;
}

.menus {
  margin: 50px 0;
  color: #fff;
}

.li {
  letter-spacing: 2px;
  font-size: 17px;
  font-weight: 600;
  padding: 16px 0;
  transition: 0.3s;
}

.menus li:hover {
  background-color: #ae00ff;
}

.li .fa {
  font-size: 20px;
  width: 50px;
  height: 20px;
  text-align: center;
  margin-left: 5px;
}

.li ul {
  border-radius: 20px;
  width: 0;
  height: 550px;
  padding: 50px 0;
  background-color: #a06993;
  position: absolute;
  top: 0;
  right: 0;
  overflow: hidden;
  transition: 0.3s;
}

.li ul li {
  padding: 16px 24px;
  white-space: nowrap;
  transition: 0.3s;
}

.li:hover ul {
  border-radius: 20px;
  width: 250px;
}

.li ul li:hover {
  background-color: #c28fb4;
}
</style>

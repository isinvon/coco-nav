<script setup>
/**
 * 头部菜单
 */
import ExpandButton from "@/views/components/HeaderMenu/components/ExpandButton.vue";
import Avatar from '@/components/Avatar/index.vue'
// 右边抽屉全局状态管理
import {useRightDrawerStore} from '@/store/rightDrawer'

const rightDrawerStore = useRightDrawerStore()

// 切换抽屉状态
const toggleDrawer = () => {
  rightDrawerStore.toggleDrawer()
}

function updateMenu() {
  if (document.getElementById('responsive-menu').checked === true) {
    document.getElementById('menu').style.borderBottomRightRadius = '0';
    document.getElementById('menu').style.borderBottomLeftRadius = '0';
  } else {
    document.getElementById('menu').style.borderRadius = '10px';
  }
}
</script>

<template>

  <nav id='menu'>
    <!-- ExpandButton 放置在最左侧 -->
    <ExpandButton/>

    <input type='checkbox' id='responsive-menu' @click='updateMenu()'><label></label>

    <ul>
      <li><a href='http://'>Home</a></li>
      <li><a class='dropdown-arrow' href='http://'>Products</a>
        <ul class='sub-menus'>
          <li><a href='http://'>Products 1</a></li>
          <li><a href='http://'>Products 2</a></li>
          <li><a href='http://'>Products 3</a></li>
          <li><a href='http://'>Products 4</a></li>
        </ul>
      </li>
      <li><a href='http://'>About</a></li>
      <li><a class='dropdown-arrow' href='http://'>Services</a>
        <ul class='sub-menus'>
          <li><a href='http://'>Services 1</a></li>
          <li><a href='http://'>Services 2</a></li>
          <li><a href='http://'>Services 3</a></li>
        </ul>
      </li>
      <li><a href='http://'>Contact Us</a></li>
      <!--头像-->
      <el-divider direction="vertical" style="margin-left: 5px"/>
      <Avatar @click='toggleDrawer' :src="'https://avatars.githubusercontent.com/u/102167772?v=4'"
              style="cursor: pointer"/>
    </ul>
  </nav>
</template>

<!--字体靠右版本-->
<style lang="less" scoped>
#menu {
  background: #f6f8fa;
  height: 54px;
  padding: 0 10px;
  display: flex;
  align-items: center;
  justify-content: space-between; /* 让按钮与导航链接两端对齐 */
  border-radius: 10px;
  border: 1px solid #d1d9e0;
}

#menu > ExpandButton {
  margin-right: 15px; /* 可根据需要调整按钮与导航菜单的间距 */
}

#menu ul {
  margin: 0;
  padding: 0;
  list-style: none;
  display: flex; /* 将列表元素水平排列 */
  align-items: center;
  text-align: right;
  border: 1px solid transparent;
  border-radius: 10px;
}

#menu li {
  display: inline-block;
  position: relative;
}

#menu a {
  display: block;
  line-height: 52px;
  padding: 0 14px;
  text-decoration: none;
  color: #59636e;
  font-size: 12px;
  transition: color 0.3s ease, background 0.3s ease; /* 鼠标悬停时颜色和背景色变化 */
}

#menu a.dropdown-arrow:after {
  content: "\25BE";
  margin-left: 5px;
}

#menu li a:hover {
  color: #59636e;
  background: #eaedf0;
}

#menu input {
  display: none;
}

#menu label {
  display: none;
}

#menu label:before {
  font-size: 1.6em;
  color: #59636E;
  content: "\2261";
  margin-left: 20px;
}

#menu ul.sub-menus {
  max-height: 0; /* 初始时隐藏菜单 */
  overflow: hidden; /* 隐藏溢出的内容 */
  width: 170px;
  background: #ececec;
  position: absolute;
  z-index: 99;
  display: block;
  transition: max-height 0.3s ease-in-out; /* 添加展开/收起的动画效果 */
}

#menu li:hover ul.sub-menus {
  max-height: 300px; /* 展开时的最大高度，根据需要调整 */
}

#menu ul.sub-menus li {
  display: block;
  text-align: left;
  width: 100%;
}

#menu ul.sub-menus a {
  color: #59636E;
  font-size: 12px;
}

#menu ul.sub-menus a:hover {
  background: #F2F3F4;
  color: #1F2328;
}

@media screen and (max-width: 800px) {
  #menu {
    position: relative;
  }

  #menu ul {
    background: #FFFFFF;
    position: absolute;
    top: 100%;
    right: 0;
    left: 0;
    z-index: 3;
    height: auto;
    display: none;
    text-align: left;
  }

  #menu ul.sub-menus {
    width: 100%;
    position: static;
  }

  #menu ul.sub-menus a {
    padding-left: 30px;
  }

  #menu li {
    display: block;
    float: none;
    width: auto;
  }

  #menu input, #menu label {
    position: absolute;
    top: 0;
    left: 0;
    display: block;
  }

  #menu input {
    z-index: 4;
  }

  #menu input:checked + label {
    color: #59636E;
  }

  #menu input:checked + label:before {
    content: "\00d7";
  }

  #menu input:checked ~ ul {
    display: block;
  }
}
</style>


<!--字体靠左版本-->
<!--<style lang="less" scoped>-->
<!--#menu {-->
<!--  background: #F6F8FA;-->
<!--  height: 54px;-->
<!--  padding-left: 18px;-->
<!--  border-radius: 10px;-->
<!--  border: 1px solid #D1D9E0;-->
<!--}-->
<!--#menu ul, #menu li {-->
<!--  margin: 0 auto;-->
<!--  padding: 0;-->
<!--  list-style: none-->
<!--}-->
<!--#menu ul {-->
<!--  width: 100%;-->
<!--  text-align: left;-->
<!--}-->
<!--#menu li {-->
<!--  display: inline-block;-->
<!--  position: relative;-->
<!--}-->
<!--#menu a {-->
<!--  display: block;-->
<!--  line-height: 52px;-->
<!--  padding: 0 14px;-->
<!--  text-decoration: none;-->
<!--  color: #59636E;-->
<!--  font-size: 12px;-->
<!--}-->
<!--#menu a.dropdown-arrow:after {-->
<!--  content: "\25BE";-->
<!--  margin-left: 5px;-->
<!--}-->
<!--#menu li a:hover {-->
<!--  color: #59636E;-->
<!--  background: #EAEDF0;-->
<!--}-->
<!--#menu input {-->
<!--  display: none;-->
<!--  margin: 0;-->
<!--  padding: 0;-->
<!--  height: 54px;-->
<!--  width: 100%;-->
<!--  opacity: 0;-->
<!--  cursor: pointer-->
<!--}-->
<!--#menu label {-->
<!--  display: none;-->
<!--  line-height: 52px;-->
<!--  text-align: center;-->
<!--  position: absolute;-->
<!--  left: 35px-->
<!--}-->
<!--#menu label:before {-->
<!--  font-size: 1.6em;-->
<!--  color: #59636E;-->
<!--  content: "\2261";-->
<!--  margin-left: 20px;-->
<!--}-->
<!--#menu ul.sub-menus{-->
<!--  height: auto;-->
<!--  overflow: hidden;-->
<!--  width: 170px;-->
<!--  background: #FFFFFF;-->
<!--  position: absolute;-->
<!--  z-index: 99;-->
<!--  display: none;-->
<!--}-->
<!--#menu ul.sub-menus li {-->
<!--  display: block;-->
<!--  text-align: left;-->
<!--  width: 100%;-->
<!--}-->
<!--#menu ul.sub-menus a {-->
<!--  color: #59636E;-->
<!--  font-size: 12px;-->
<!--}-->
<!--#menu li:hover ul.sub-menus {-->
<!--  display: block-->
<!--}-->
<!--#menu ul.sub-menus a:hover{-->
<!--  background: #F2F3F4;-->
<!--  color: #1F2328;-->
<!--}-->
<!--@media screen and (max-width: 800px){-->
<!--  #menu {position:relative}-->
<!--  #menu ul {background:#FFFFFF;position:absolute;top:100%;right:0;left:0;z-index:3;height:auto;display:none;text-align:left;}-->
<!--  #menu ul.sub-menus {width:100%;position:static;}-->
<!--  #menu ul.sub-menus a {padding-left:30px;}-->
<!--  #menu li {display:block;float:none;width:auto;}-->
<!--  #menu input, #menu label {position:absolute;top:0;left:0;display:block}-->
<!--  #menu input {z-index:4}-->
<!--  #menu input:checked + label {color:#59636E}-->
<!--  #menu input:checked + label:before {content:"\00d7"}-->
<!--  #menu input:checked ~ ul {display:block}-->
<!--}-->
<!--</style>-->

<!--字体居中版本-->
<!--<style lang="less" scoped>-->
<!--#menu {-->
<!--  background: #F6F8FA;-->
<!--  height: 54px;-->
<!--  border-radius: 10px;-->
<!--  border: 1px solid #D1D9E0;-->
<!--}-->
<!--#menu ul, #menu li {-->
<!--  margin: 0 auto;-->
<!--  padding: 0;-->
<!--  list-style: none-->
<!--}-->
<!--#menu ul {-->
<!--  width: 100%;-->
<!--  text-align: center;-->
<!--}-->
<!--#menu li {-->
<!--  display: inline-block;-->
<!--  position: relative;-->
<!--}-->
<!--#menu a {-->
<!--  display: block;-->
<!--  line-height: 52px;-->
<!--  padding: 0 14px;-->
<!--  text-decoration: none;-->
<!--  color: #59636E;-->
<!--  font-size: 12px;-->
<!--}-->
<!--#menu a.dropdown-arrow:after {-->
<!--  content: "\25BE";-->
<!--  margin-left: 5px;-->
<!--}-->
<!--#menu li a:hover {-->
<!--  color: #59636E;-->
<!--  background: #EAEDF0;-->
<!--}-->
<!--#menu input {-->
<!--  display: none;-->
<!--  margin: 0;-->
<!--  padding: 0;-->
<!--  height: 54px;-->
<!--  width: 100%;-->
<!--  opacity: 0;-->
<!--  cursor: pointer-->
<!--}-->
<!--#menu label {-->
<!--  display: none;-->
<!--  line-height: 52px;-->
<!--  text-align: center;-->
<!--  position: absolute;-->
<!--  left: 35px-->
<!--}-->
<!--#menu label:before {-->
<!--  font-size: 1.6em;-->
<!--  color: #59636E;-->
<!--  content: "\2261";-->
<!--  margin-left: 20px;-->
<!--}-->
<!--#menu ul.sub-menus{-->
<!--  height: auto;-->
<!--  overflow: hidden;-->
<!--  width: 170px;-->
<!--  background: #FFFFFF;-->
<!--  position: absolute;-->
<!--  z-index: 99;-->
<!--  display: none;-->
<!--}-->
<!--#menu ul.sub-menus li {-->
<!--  display: block;-->
<!--  text-align: left;-->
<!--  width: 100%;-->
<!--}-->
<!--#menu ul.sub-menus a {-->
<!--  color: #59636E;-->
<!--  font-size: 12px;-->
<!--}-->
<!--#menu li:hover ul.sub-menus {-->
<!--  display: block-->
<!--}-->
<!--#menu ul.sub-menus a:hover{-->
<!--  background: #F2F3F4;-->
<!--  color: #1F2328;-->
<!--}-->
<!--@media screen and (max-width: 800px){-->
<!--  #menu {position:relative}-->
<!--  #menu ul {background:#FFFFFF;position:absolute;top:100%;right:0;left:0;z-index:3;height:auto;display:none;text-align:left;}-->
<!--  #menu ul.sub-menus {width:100%;position:static;}-->
<!--  #menu ul.sub-menus a {padding-left:30px;}-->
<!--  #menu li {display:block;float:none;width:auto;}-->
<!--  #menu input, #menu label {position:absolute;top:0;left:0;display:block}-->
<!--  #menu input {z-index:4}-->
<!--  #menu input:checked + label {color:#59636E}-->
<!--  #menu input:checked + label:before {content:"\00d7"}-->
<!--  #menu input:checked ~ ul {display:block}-->
<!--}-->
<!--</style>-->
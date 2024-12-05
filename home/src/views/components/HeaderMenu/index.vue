<script setup>
/**
 * @description 头部菜单
 * @author sinvon
 * @since 2024年12月2日23:49:46
 * @module HeaderMenu
 */
import ExpandButton from "@/views/components/HeaderMenu/components/ExpandButton.vue";
import AddBookmarkButton from "@/views/components/HeaderMenu/components/AddBookmarkButton.vue";
import Avatar from '@/components/Avatar/index.vue'
// 右边抽屉全局状态管理
import {useRightDrawerStore} from '@/store/rightDrawer'
import HotButton from "./components/HotButton/index.vue";
import {useHotCardStore} from "@/store/hotCard.js";

const rightDrawerStore = useRightDrawerStore()
const hotCardStore = useHotCardStore()

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
    <div class="left-buttons">
      <!-- ExpandButton-->
      <ExpandButton/>
      <!--热点按钮-->
      <HotButton @click='hotCardStore.toggleHotCardOpenState()'/>
    </div>

    <input type='checkbox' id='responsive-menu' @click='updateMenu()'><label></label>

    <ul>
      <!--添加书签按钮-->
      <AddBookmarkButton/>
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
@background-color: #f6f8fa;
@submenu-background: #ececec;
@hover-background: #eaedf0;
@submenu-hover-background: #F2F3F4;
@text-color: #59636e;
@border-color: #d1d9e0;
@border-radius: 10px;
@font-size: 12px;
@line-height: 52px;
@menu-padding: 0 10px;
@submenu-width: 170px;
@submenu-max-height: 300px;
@icon-font-size: 1.6em;
@icon-margin-left: 20px;
@expand-button-margin: 15px;

#menu {
  background: @background-color;
  height: 54px;
  padding: @menu-padding;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-radius: @border-radius;
  border: 1px solid @border-color;

  .left-buttons {
    display: flex;
    align-items: center;
    // 靠左排列
    justify-content: flex-start;
  }

  ul {
    margin: 0;
    padding: 0;
    list-style: none;
    display: flex;
    align-items: center;
    text-align: right;
    border: 1px solid transparent;
    border-radius: @border-radius;

    li {
      display: inline-block;
      position: relative;

      a {
        display: block;
        line-height: @line-height;
        padding: 0 14px;
        text-decoration: none;
        color: @text-color;
        font-size: @font-size;
        transition: color 0.3s ease, background 0.3s ease;

        &:hover {
          color: @text-color;
          background: @hover-background;
        }

        &.dropdown-arrow:after {
          content: "\25BE";
          margin-left: 5px;
        }
      }

      &:hover {
        ul.sub-menus {
          max-height: @submenu-max-height;
        }
      }

      ul.sub-menus {
        max-height: 0;
        overflow: hidden;
        width: @submenu-width;
        background: @submenu-background;
        position: absolute;
        z-index: 99;
        display: block;
        transition: max-height 0.3s ease-in-out;

        li {
          display: block;
          text-align: left;
          width: 100%;

          a {
            color: @text-color;
            font-size: @font-size;

            &:hover {
              background: @submenu-hover-background;
              color: #1F2328;
            }
          }
        }
      }
    }
  }

  input {
    display: none;
  }

  label {
    display: none;

    &:before {
      font-size: @icon-font-size;
      color: @text-color;
      content: "\2261";
      margin-left: @icon-margin-left;
    }
  }
}

/* 响应式设计 */
@media screen and (max-width: 800px) {
  #menu {
    position: relative;

    ul {
      background: #FFFFFF;
      position: absolute;
      top: 100%;
      right: 0;
      left: 0;
      z-index: 3;
      height: auto;
      display: none;
      text-align: left;

      ul.sub-menus {
        width: 100%;
        position: static;

        a {
          padding-left: 30px;
        }
      }

      li {
        display: block;
        float: none;
        width: auto;
      }

      input,
      label {
        position: absolute;
        top: 0;
        left: 0;
        display: block;
      }

      input {
        z-index: 4;
      }

      input:checked + label {
        color: @text-color;
      }

      input:checked + label:before {
        content: "\00d7";
      }

      input:checked ~ ul {
        display: block;
      }
    }
  }
}

</style>


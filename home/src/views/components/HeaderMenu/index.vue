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
import MenuItem from "./components/MenuItem.vue";
import {headerMenuEnum} from "@/enum/headerMenuEunm.js";

const rightDrawerStore = useRightDrawerStore()
const hotCardStore = useHotCardStore()

import { useDarkThemeStore } from '@/store/darkThemeStore.js'; // 引入 Pinia store
import useDark from '@/hooks/useDark'; // 引入自定义钩子

// 获取状态管理的 store 和钩子
const darkThemeStore = useDarkThemeStore();  // 使用 Pinia store 获取 dark 状态
const { toggleDarkMode } = useDark(); // 使用钩子来控制暗黑模式

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
      <!-- 热点按钮 -->
      <HotButton class="hot-button" @click='hotCardStore.toggleHotCardOpenState()'/>
    </div>

    <input type='checkbox' id='responsive-menu' @click='updateMenu()'>
    <label></label>

    <ul>
      <!-- 添加书签按钮 -->
      <AddBookmarkButton/>

      <!-- 菜单项-->
      <MenuItem
          v-for="(menuItem, index) in headerMenuEnum"
          :key="index"
          :title="menuItem.title"
          :path="menuItem.path"
          :icon="menuItem.icon"
          :hasChildren="!!menuItem.children"
          :children="menuItem.children || []"
      />

      <!-- 头像 -->
      <el-divider direction="vertical" style="margin-left: 5px"/>
      <!-- 控制暗黑模式的按钮 -->
      <el-button @click="toggleDarkMode" type="text" circle>
        {{ darkThemeStore.isDarkMode ? '☀ ' : '🌙' }}
      </el-button>
      <el-divider direction="vertical" style="margin-left: 5px"/>
      <Avatar @click='toggleDrawer' :src="'https://avatars.githubusercontent.com/u/102167772?v=4'"
              style="cursor: pointer"/>
    </ul>
  </nav>
</template>

<style lang="less" scoped>
@text-color: #59636e;
@background-color: #f6f8fa;
@border-color: #d1d9e0;
@border-radius: 10px;
@font-size: 12px;
@line-height: 52px;
@menu-padding: 0 10px;
@icon-font-size: 1.6em;
@icon-margin-left: 20px;

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

      input:checked ~ ul {
        display: block;
      }
    }
  }
}

</style>

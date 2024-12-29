<template>
  <li :class="{'has-submenu': hasChildren}">
    <!-- 使用 router-link 替换 a 标签，以便支持路由跳转 -->
    <router-link :to="path" class="menu-link">
      <!-- 显示图标 -->
      <span v-if="icon" class="menu-icon">{{ icon }}</span>
      {{ title }}
      <!-- 子菜单箭头 -->
      <span v-if="hasChildren" class="dropdown-arrow"></span>
    </router-link>
    <!-- 子菜单 -->
    <ul v-if="hasChildren && children.length" class="sub-menus">
      <li v-for="(child, index) in children" :key="index">
        <!-- 使用 router-link 替换 a 标签 -->
        <router-link :to="child.path" class="submenu-link">
          <span v-if="child.icon" class="sub-menu-icon">{{ child.icon }}</span>
          {{ child.title }}
        </router-link>
      </li>
    </ul>
  </li>
</template>

<script setup>
/**
 * 菜单项
 * @author sinvon
 * @since 2024年12月29日17:00:14
 *
 */
const props = defineProps({
  title: String,
  path: String,
  icon: String,
  hasChildren: Boolean,
  children: {
    type: Array,
    default: () => []
  }
})
</script>

<style scoped lang="less">
@submenu-background: #ececec;
@submenu-hover-background: #F2F3F4;
@text-color: #59636e;
@font-size: 12px;
@line-height: 52px;
@submenu-max-height: 300px;
@submenu-width: 170px;
@hover-background: #eaedf0;
@border-radius: 10px;
@submenu-border-radius: 10px; /* 子菜单的圆角 */
@submenu-padding-left: 30px;

li {
  position: relative;

  //使 router-link 看起来像 a 标签
  .menu-link, .submenu-link {
    display: block;
    line-height: @line-height;
    padding: 0 14px;
    text-decoration: none;
    color: @text-color;
    font-size: @font-size;
    border-radius: @border-radius;
    transition: color 0.3s ease, background 0.3s ease, border-radius 0.3s ease;

    &:hover {
      background: @hover-background;
      border-radius: @border-radius;
    }

    .menu-icon {
      margin-right: 8px;
    }
  }

  &:hover {
    ul.sub-menus {
      max-height: @submenu-max-height;
    }
  }

  .dropdown-arrow:after {
    content: "\25BE";
    margin-left: 5px;
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
    border-radius: @submenu-border-radius;
    margin-top: 5px;
    left: 0; /* 使子菜单与父菜单对齐 */
    padding: 0; /* 去掉 padding-left，避免额外的空白 */
  }

  ul.sub-menus li {
    display: block;
    text-align: left;
    width: 100%;
    background: white;

    .submenu-link {
      color: @text-color;
      font-size: @font-size;
      padding-left: @submenu-padding-left; /* 给子菜单项添加缩进 */

      &:hover {
        background: @submenu-hover-background;
        color: #1F2328;
      }
    }
  }
}
</style>

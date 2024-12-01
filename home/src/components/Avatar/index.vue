<template>
  <el-avatar :src="src" :icon="icon" :size="size" :style="avatarStyle">
    <template v-if="text">{{ text }}</template>
  </el-avatar>
</template>

<script setup>
/**
 * 头像组件
 * @module components/Avatar
 * @property {String} src 头像图片地址
 * @property {Object|Function} icon 头像图标
 * @property {String} text 头像文字
 * @property {String|Number} size 头像大小
 * @property {String} bgColor 头像背景颜色
 * @property {String} fontColor 头像文字颜色
 * @property {'lighter' | 'normal' | 'bold' | 'bolder'} fontWeight 头像文字的字体粗细
 * @property {String} borderColor 头像边框颜色
 * @property {String|Number} borderWidth 头像边框宽度
 * @example
 * <Avatar src="https://avatars.githubusercontent.com/u/10227409?v=4" icon="user" text="小李" size="40" fontColor="#000" fontWeight="bold" />
 *
 * 说明:
 * 1. 若设置了 src 属性，则 icon 属性将失效。
 * 2. 若设置了 icon 属性，则 text 属性也将失效。
 * 3. 若同时设置了 src 和 icon 属性，则 icon 属性将失效。
 * 4. 若同时设置了 src 和 text 属性，则 text 属性将失效。
 * 5. 若设置了 text，则显示文字
 */

// 定义 props
const props = defineProps({
  /**
   * @type {String}
   * @default ''
   */
  src: {
    type: String,
    default: ''
  },
  /**
   * @type {Object|Function}
   * @default null
   */
  icon: {
    type: [Object, Function],
    default: null
  },
  /**
   * @type {String}
   * @default '头像'
   */
  text: {
    type: String,
    default: '头像'
  },
  /**
   * @type {String|Number}
   * @default '40'
   */
  size: {
    type: [String, Number],
    default: '40' // 默认头像大小为 40px
  },
  /**
   * @type {String}
   * @default '#d0cfcf'
   */
  bgColor: {
    type: String,
    default: '#d0cfcf' // 默认背景色为浅灰色
  },
  /**
   * @type {String}
   * @default '#ffffff'
   */
  fontColor: {
    type: String,
    default: '#ffffff' // 默认字体颜色为白色
  },
  /**
   * @type {'lighter'|'normal'|'bold'|'bolder'}
   * @default 'normal'
   */
  fontWeight: {
    type: String,
    default: 'normal', // 默认字体粗细为 normal
    validator(value) {
      // 允许的字体粗细值
      return ['lighter', 'normal', 'bold', 'bolder'].includes(value)
    }
  },
  /**
   * @type {String|Number}
   * @default '0px'
   * @description 头像边框宽度
   */
  borderWidth: {
    type: [String, Number],
    default: '0px' // 默认边框宽度为 0px
  },
  /**
   * @type {String}
   * @default '#ffffff'
   */
  borderColor: {
    type: String,
    default: '#ffffff' // 默认边框颜色为白色
  }
})

// 解构出属性
const {src, icon, text, size, bgColor, fontColor, fontWeight, borderColor, borderWidth} = props

// 计算头像的样式
const avatarStyle = {
  backgroundColor: bgColor, // 设置背景色
  backgroundImage: src ? 'none' : '', // 如果没有图片，背景图片为 none
  color: text ? fontColor : '', // 如果是文字头像，字体颜色为 fontColor
  fontWeight: text ? fontWeight : '', // 如果是文字头像，字体粗细为 fontWeight
  display: 'flex', // 使头像内容居中显示
  alignItems: 'center', // 垂直居中
  justifyContent: 'center', // 水平居中
  border: `${borderWidth} solid ${borderColor}` // 设置边框样式
}
</script>

<style scoped>
/* 可以根据需要添加样式 */
</style>

<script setup>
/**
 * @name BookmarkCategory
 * @description 书签类别选择器
 * @props {String} modelValue 选中的类别
 * @emits update:modelValue 选中的类别变化时触发
 * @example <BookmarkCategory v-model="selectedCategory" />
 * @author sinvon
 * @since 2024年12月2日21:26:27
 */
import {defineEmits, ref} from "vue";

const bookmarkCategory = ref('')

// 使用 defineEmits 来定义事件
const emit = defineEmits(['update:modelValue'])

// 监听 BookmarkCategory 的变化，并通过 emit 更新父组件的值
const handleCategoryChange = () => {
  emit('update:modelValue', bookmarkCategory.value)
}
</script>

<template>
  <div>
    <el-select
        v-model="bookmarkCategory"
        placeholder="请选择书签类别"
        @change="handleCategoryChange"
    >
      <el-option label="技术" value="tech"/>
      <el-option label="学习" value="study"/>
      <el-option label="娱乐" value="entertainment"/>
      <el-option label="其他" value="other"/>
    </el-select>
  </div>
</template>

<style scoped lang="less">
/* 你可以添加一些样式 */
</style>

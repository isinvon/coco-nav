<script setup>
/**
 * 标签输入框
 * @module TagInput
 * @author sinvon
 * @since 2024年12月2日21:16:54
 */
import {ref} from "vue";

// 接收父组件的 v-model 数据
const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  }
})
const bookmarkTagsStr = ref(props.modelValue) // 标签输入框内容
const bookmarkTags = ref([]) // 标签数组

// 定义emit事件
const emit = defineEmits(['update:modelValue', 'updateTags'])

// 标签输入处理
const handleTagInput = () => {
  const input = bookmarkTagsStr.value.trim()

  // 以逗号分隔标签
  const tags = input.split(',').map(tag => tag.trim()).filter(tag => tag.length > 0)

  // 更新标签数组
  bookmarkTags.value = tags

  // 更新标签字符串和标签数组
  emit('update:modelValue', bookmarkTagsStr.value)
  emit('updateTags', bookmarkTags.value)
}

// 删除标签
const removeTag = (tagToRemove) => {
  bookmarkTags.value = bookmarkTags.value.filter(tag => tag !== tagToRemove)
  emit('update:modelValue', bookmarkTagsStr.value)
  emit('updateTags', bookmarkTags.value)
}
</script>

<template>
  <div>
    <el-input
        v-model="bookmarkTagsStr"
        placeholder="输入多个标签，用英文逗号隔开"
        @input="handleTagInput"
        clearable
    />
    <div class="tags-container">
      <el-tag
          v-for="(tag, index) in bookmarkTags"
          :key="index"
          closable
          @close="removeTag(tag)"
          round
      >{{ tag }}
      </el-tag>
    </div>
  </div>
</template>

<style scoped lang="less">
.tags-container {
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;

  .el-tag {
    margin-right: 8px;
    margin-bottom: 8px;
  }
}
</style>

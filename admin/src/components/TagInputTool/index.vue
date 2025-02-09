<script setup>
/**
 * 标签输入框
 * @module TagInputTool
 * @author sinvon
 * @since 2025年2月9日16:14:55
 */
import {ref, watch} from "vue";

const props = defineProps({
  modelValue: {
    type: String,
    default: ""
  }
});

const emit = defineEmits(["update:modelValue", "updateTags"]);

const bookmarkTagsStr = ref(props.modelValue);
const bookmarkTags = ref([]);

// 监听输入框变化，解析标签
watch(bookmarkTagsStr, (newValue) => {
  bookmarkTags.value = newValue.split(",").map(tag => tag.trim()).filter(tag => tag.length > 0);
  emit("update:modelValue", bookmarkTagsStr.value);
  emit("updateTags", bookmarkTags.value);
}, {immediate: true});

// 监听外部 `modelValue` 变化，保持同步
watch(() => props.modelValue, (newValue) => {
  bookmarkTagsStr.value = newValue;
  bookmarkTags.value = newValue.split(",").map(tag => tag.trim()).filter(tag => tag.length > 0);
}, {immediate: true});

// 删除标签
const removeTag = (tagToRemove) => {
  bookmarkTags.value = bookmarkTags.value.filter(tag => tag !== tagToRemove);
  bookmarkTagsStr.value = bookmarkTags.value.join(",");
  emit("update:modelValue", bookmarkTagsStr.value);
  emit("updateTags", bookmarkTags.value);
};
</script>

<template>
  <div>
    <el-input
        v-model="bookmarkTagsStr"
        placeholder="输入多个标签，用英文逗号隔开"
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

<style scoped>
.tags-container {
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;
}

.el-tag {
  margin-right: 8px;
  margin-bottom: 8px;
}
</style>

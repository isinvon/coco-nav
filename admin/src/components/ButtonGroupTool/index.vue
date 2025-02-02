<template>
  <el-button-group>
    <el-button
        v-for="dict in options"
        :key="dict.value"
        :label="dict.value"
        :style="{ backgroundColor: currentValue === dict.value ? dict.color : '#e0e0e0', fontWeight: 'bold', color: 'white', border: 'none' }"
        @click="handleClick(dict.value)"
    >
      {{ dict.label }}
    </el-button>
  </el-button-group>
</template>

<script setup>
/**
 * 按钮组组件工具
 *
 * @author sinvon
 * @since 2025年2月3日07:33:14
 */

// 接收父组件传递的参数
const props = defineProps({
  options: {
    type: Array,
    required: true
  },
  modelValue: {
    type: [String, Number],
    required: true
  },
  prop: {
    type: String,
    default: 'status'
  }
});

// 事件处理，通知父组件更新状态
const emit = defineEmits(['update:modelValue']);

// 计算当前选中的值
const currentValue = computed(() => {
  return props.modelValue;
});

const handleClick = (value) => {
  // 更新父组件的状态值
  emit('update:modelValue', value);
};
</script>

<style scoped>
.el-button {
  font-weight: bold;
  color: white;
}
</style>

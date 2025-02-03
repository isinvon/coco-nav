<template>
  <!-- 如果匹配到对应的项，则显示 el-tag -->
  <el-tag
      v-if="matchedItem"
      :color="matchedItem.color"
      style="font-weight: bold; color: white; border: none"
  >
    {{ matchedItem.label }}
  </el-tag>
</template>

<script setup>
/**
 * @author sinvon
 * @description 状态标签组件
 * @since 2025年2月3日05:21:59
 */
import {computed} from 'vue'

/**
 * props:
 *   value: 当前状态值 (Number 或 String)
 *   options: 数组，每个对象需包含字段：
 *            - value: (Number or String)
 *            - label: (String)
 *            - color: (String) (支持 Element UI 的颜色格式，例如 "#E76F51")
 */
const props = defineProps({
  value: {
    type: [Number, String],
    required: true
  },
  options: {
    type: Array,
    default: () => []
  }
})

// 计算匹配项
const matchedItem = computed(() => {
  return props.options.find(item => item.value === props.value)
})
</script>

<style scoped>
/* 如有需要，可在此添加样式 */
</style>


<!--原型参考-->
<!--<el-table-column label="状态" align="center" prop="status">-->
<!--<template #default="scope">-->
<!--  <el-tag-->
<!--      v-if="advertisementStatusTypeList.some(item => item.value === scope.row.status)"-->
<!--      :key="scope.row.status"-->
<!--      :color="advertisementStatusTypeList.find(item => item.value === scope.row.status)?.color"-->
<!--      style="font-weight: bold;color: white;border: none"-->
<!--  >-->
<!--    {{ advertisementStatusTypeList.find(item => item.value === scope.row.status)?.label }}-->
<!--  </el-tag>-->
<!--</template>-->
<!--</el-table-column>-->
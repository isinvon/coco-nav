<template>
  <div class="wordcloud-container">
    <el-tag
        v-for="(bookmark, index) in data"
        :key="index"
        :type="getTypeByView(bookmark.view)"
        :effect="effect"
        class="tag-with-badge"
    >
      {{ bookmark.name }}
      <el-badge :value="bookmark.view" :max="999" class="badge-inside-tag">
        <i></i> <!-- Placeholder for badge -->
      </el-badge>
    </el-tag>
  </div>
</template>

<script lang="js" setup>
import {defineProps, computed} from 'vue';

const props = defineProps({
  /**
   * 数据
   */
  data: {
    type: Array,
    required: true
  },
  /**
   * tag样式
   * @type {'dark'|'plain'|'light'}
   *
   */
  effect: {
    type: String,
    default: 'light'
  }
});

// 计算最小值和最大值
const minView = computed(() => Math.min(...props.data.map(item => item.view)));
const maxView = computed(() => Math.max(...props.data.map(item => item.view)));

const getTypeByView = (view) => {
  if (view === minView.value) {
    return 'success'; // 最小值对应的类型
  } else if (view === maxView.value) {
    return 'danger'; // 最大值对应的类型
  } else {
    const range = maxView.value - minView.value;
    const normalizedValue = (view - minView.value) / range;
    if (normalizedValue <= 0.33) {
      return 'info';
    } else if (normalizedValue <= 0.66) {
      return 'warning';
    } else {
      return 'primary';
    }
  }
};
</script>

<style scoped lang="less">
.wordcloud-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  padding: 10px;
}

.tag-with-badge {
  position: relative;
  margin: 5px;
}

// 徽章的位置
.badge-inside-tag {
  position: absolute;
  top: -1px;
  right: 0;
  // 徽章样式
  :deep(.el-badge__content) {
    //background: #c44390;
  }
}
</style>




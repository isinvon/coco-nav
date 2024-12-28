<template>
  <div class="wordcloud-container">
    <el-tag
        v-for="(bookmark, index) in data"
        :key="index"
        :type="getRandomTagType()"
        :effect="effect"
    >
      {{ bookmark.name }}
    </el-tag>
  </div>
</template>

<script lang="js" setup>

/**
 * @author sinvon
 * @description 词云组件
 * @since 2024年12月29日00:04:41
 * @type {Prettify<Readonly<ExtractPropTypes<{data: {type: ArrayConstructor, required: boolean}}>>>}
 */
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

const getRandomTagType = () => {
  const types = ['success', 'info', 'warning', 'danger'];
  return types[Math.floor(Math.random() * types.length)];
};
</script>

<style lang="less" scoped>
.wordcloud-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  padding: 10px;
}
</style>




<script setup lang="js">
/**
 * @module components/Icon
 * @description 图标组件
 * @author sinvon
 * @since 2024年12月1日18:39:59
 */
import {Icon} from "@iconify/vue";

defineOptions({
  name: "MIcon",
});

// 判断是否是图片, 如果是图片, 则直接显示图片, 否则显示图标
const isImage = (url) => {
  return url && (url.startsWith("http") || url.startsWith("data:image"));
};

const props = defineProps({
  icon: {
    type: [String],
    default: "",
  },
  size: {
    type: [String, Number],
    default: 32,
  },
  color: {
    type: String,
    default: "",
  },
});
</script>

<template>
  <div class="rounded-full flex items-center">
    <!-- v-if to check if the icon is an image URL or an icon name -->
    <img v-if="isImage" :src="icon" :alt="icon" class="icon-size" :width="size" :height="size"/>
    <icon v-else :icon="icon" class="icon-size" :width="size" :height="size"/>
  </div>
</template>

<style lang="less" scoped>
.icon-size {
@apply shadow-sm rounded-full;
  color: v-bind(color);
  object-fit: fill;
}
</style>

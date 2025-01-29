<script setup>
/**
 * @author sinvon
 * @description 广告组件_v3
 * @example
 * @since 2024年12月2日14:31:24
 */
import {ref} from 'vue';

const ads = ref([
  {
    id: 1,
    title: '广告标题 1',
    description: '广告描述内容 1',
    image: 'https://via.placeholder.com/300x200',
    colSpan: 1,
    rowSpan: 2
  },
  {
    id: 2,
    title: '广告标题 2',
    description: '广告描述内容 2',
    image: 'https://via.placeholder.com/300x200',
    colSpan: 1,
    rowSpan: 1
  },
  {
    id: 3,
    title: '广告标题 3',
    description: '广告描述内容 3',
    image: 'https://picsum.photos/300/200',
    colSpan: 1,
    rowSpan: 1
  },
  {
    id: 4,
    title: '广告标题 4',
    description: '广告描述内容 4',
    image: 'https://via.placeholder.com/300x200',
    colSpan: 1,
    rowSpan: 1
  },
  {
    id: 5,
    title: '广告标题 5',
    description: '广告描述内容 5',
    image: 'https://via.placeholder.com/300x200',
    colSpan: 2,
    rowSpan: 1
  },
  {
    id: 6,
    title: '广告标题 6',
    description: '广告描述内容 6',
    image: 'https://via.placeholder.com/300x200',
    colSpan: 1,
    rowSpan: 1
  },
  {
    id: 7,
    title: '广告标题 7',
    description: '广告描述内容 7',
    image: 'https://via.placeholder.com/300x200',
    colSpan: 1,
    rowSpan: 1
  },
  {
    id: 8,
    title: '广告标题 8',
    description: '广告描述内容 8',
    image: 'https://via.placeholder.com/300x200',
    colSpan: 1,
    rowSpan: 1
  },
  {
    id: 9,
    title: '广告标题 9',
    description: '广告描述内容 9',
    image: 'https://via.placeholder.com/300x200',
    colSpan: 1,
    rowSpan: 1
  },
  {
    id: 10,
    title: '广告标题 10',
    description: '广告描述内容 10',
    image: 'https://via.placeholder.com/300x200',
    colSpan: 1,
    rowSpan: 1
  },
  {
    id: 11,
    title: '广告标题 11',
    description: '广告描述内容 11',
    image: 'https://via.placeholder.com/300x200',
    colSpan: 4,
    rowSpan: 1
  }
]);

// 计算字体大小
const getFontSize = (colSpan, rowSpan) => {
  const baseFontSize = 20; // 基础字体大小
  const colFactor = 1 + (colSpan - 1) * 0.2; // 根据colSpan调整字体大小, 对于 colSpan，如果是 1 列，字体大小不变，colSpan 每增加 1，字体增大 20%。
  const rowFactor = 1 + (rowSpan - 1) * 0.2; // 根据rowSpan调整字体大小, 对于 rowSpan，效果类似，每增加一行，字体增大 20%。
  return baseFontSize * colFactor * rowFactor;
};
</script>


<template>
  <div class="ads-container">
    <div class="ads-grid">
      <div
          v-for="(ad, index) in ads"
          :key="ad.id"
          class="ad"
          :style="{
          gridColumn: `span ${ad.colSpan}`,
          gridRow: `span ${ad.rowSpan}`
        }"
      >
        <div class="ad-content">
          <!-- 默认模糊的图片 -->
          <img :src="ad.image" alt="广告" class="ad-image" />
          <div class="ad-text">
            <h3
                class="ad-title"
                :style="{ fontSize: getFontSize(ad.colSpan, ad.rowSpan) + 'px' }"
            >
              {{ ad.title }}
            </h3>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
.ads-container {
  margin-top: 30px;
  border-radius: 12px;
}

.ads-grid {
  display: grid;
  grid-template-rows: repeat(4, minmax(100px, 1fr)); /* 每行最小高度为 100px，按内容自适应 */
  grid-template-columns: repeat(4, 1fr); /* 每列平分 */
  gap: 16px; /* 网格间距 */
  width: 100%;
  height: auto;
}

.ad {
  display: flex;
  flex-direction: column;
  justify-content: center; /* 垂直居中 */
  align-items: center; /* 水平居中 */
  position: relative;
  overflow: hidden;
  background-color: #f4f4f4;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.07);
  height: 100%; /* 高度完全填充父容器 */
  cursor: pointer;
}

.ad-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  position: relative;
  width: 100%;
  height: 100%; /* 使广告块填充父容器 */
  background: rgba(255, 255, 255, 0.4);
  backdrop-filter: blur(10px); /* 默认模糊效果 */
  border-radius: 8px;
  transition: all 0.3s ease;
}

/* 鼠标悬浮时去掉模糊并显示清晰图片 */
.ad:hover .ad-image {
  opacity: 1;
  filter: none; /* 悬浮时移除模糊效果 */
}

.ad-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  opacity: 0; /* 默认隐藏图片 */
  transition: opacity 0.4s ease, filter 0.4s ease;
  position: absolute;
  top: 0;
  left: 0;
  border-radius: 8px;
}

/* 广告文字 */
.ad-text {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  z-index: 1; /* 确保文字在上层 */
  color: #777777;
  width: 100%;
  height: 100%;
  transition: opacity 0.4s ease;
}

.ad-title {
  font-weight: normal;
  margin: 0;
}

/* 鼠标悬浮时隐藏文字 */
.ad:hover .ad-text {
  opacity: 0;
}
</style>
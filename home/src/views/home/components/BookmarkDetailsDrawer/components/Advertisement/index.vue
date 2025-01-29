<script setup>
/**
 * @author sinvon
 * @description 广告组件
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
          <!-- 背景图片，应用模糊效果 -->
          <div
              class="ad-background"
              :style="{ backgroundImage: `url(${ad.image})` }"
          ></div>
          <!-- 文字内容，始终清晰 -->
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
// 模糊程度
@blur: 6px;
// 鼠标悬浮时，文字透明度变化速度
@text-transition-speed-when-mouse-hover: 0.2s;
// 网格间距
@gap: 16px;
// 网格行数
@grid-rows: 4;
// 网格列数
@grid-columns: 4;
// 广告边框圆角
@ad-border-radius: 8px;
// 文字颜色
@ad-text-color: #f6f8fa;
// 背景图片淡入速度
@ad-background-appearance-speed: 0.4s;

.ads-container {
  margin-top: 30px;
  border-radius: 12px;

  .ads-grid {
    display: grid;
    grid-template-rows: repeat(@grid-rows, minmax(100px, 1fr));
    grid-template-columns: repeat(@grid-columns, 1fr);
    gap: @gap;
    width: 100%;
    height: auto;

    .ad {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      position: relative;
      overflow: hidden;
      background-color: #f4f4f4;
      border-radius: @ad-border-radius;
      height: 100%;
      cursor: pointer;
    }

    // 鼠标悬浮时，清晰显示背景图片
    .ad:hover .ad-background {
      filter: none; /* 清晰背景 */
    }

    .ad-content {
      position: relative;
      width: 100%;
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      border-radius: 8px;

      .ad-text {
        z-index: 1;
        display: flex;
        justify-content: center;
        align-items: center;
        position: absolute;
        color: @ad-text-color;
        width: 100%;
        height: 100%;
        opacity: 1;
        transition: opacity 0.4s ease;

        .ad-title {
          font-weight: normal;
          margin: 0;
        }
      }

      .ad-text:hover {
        opacity: 0;
        transition: opacity @text-transition-speed-when-mouse-hover ease;
      }
    }

    .ad-background {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background-size: cover;
      background-position: center;
      border-radius: 8px;
      filter: blur(@blur); /* 默认背景模糊,程度为6px */
      transition: filter @ad-background-appearance-speed ease-in-out;
    }
  }
}


</style>
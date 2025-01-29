<script setup>
/**
 * 相关导航
 * @displayName RelatedNavigation
 * @author sinvon
 * @since 2024年12月2日14:34:16
 */
import { ref } from 'vue';
import { Promotion } from '@element-plus/icons-vue';
import MIcon from '@/components/MIcon/index.vue'; // 假设 MIcon 组件已经创建

// 相关导航卡片数据
const cards = ref([
  {
    icon: 'https://element.eleme.cn/favicon.ico', // 替换为实际的图标
    title: '卡片标题 1',
    description: '卡片描述 1',
    url: 'https://element.eleme.cn/#/zh-CN/component/installation',
  },
  {
    icon: 'https://element.eleme.cn/favicon.ico',
    title: '卡片标题 2',
    description: '卡片描述 2',
    url: 'https://element.eleme.cn/#/zh-CN/component/installation',
  },
  {
    icon: 'https://element.eleme.cn/favicon.ico',
    title: '卡片标题 3',
    description: '卡片描述 3',
    url: 'https://element.eleme.cn/#/zh-CN/component/installation',
  },
  {
    icon: 'https://element.eleme.cn/favicon.ico',
    title: '卡片标题 4',
    description: '卡片描述 4',
    url: 'https://element.eleme.cn/#/zh-CN/component/installation',
  },
  {
    icon: 'https://element.eleme.cn/favicon.ico',
    title: '卡片标题 5',
    description: '卡片描述 5',
    url: 'https://element.eleme.cn/#/zh-CN/component/installation',
  },
  {
    icon: 'https://element.eleme.cn/favicon.ico',
    title: '卡片标题 6',
    description: '卡片描述 6',
    url: 'https://element.eleme.cn/#/zh-CN/component/installation',
  },
]);
</script>

<template>
  <div>
    <h3 class="related-navigation-title">
      <!--纸飞机图标-->
      <el-icon>
        <Promotion />
      </el-icon>
      <span>相关导航</span>
    </h3>
    <div class="related-navigation">
      <div class="card-container">
        <div v-for="(item, index) in cards" :key="index" class="card">
          <a v-if="item.url" :href="item.url" target="_blank" class="card-link">
            <div class="card-content">
              <MIcon :icon="item.icon" class="icon"/>
              <div class="text-container">
                <div class="title">{{ item.title }}</div>
                <div class="description">{{ item.description }}</div>
              </div>
            </div>
          </a>
          <div v-else class="card-content">
            <MIcon :icon="item.icon" class="icon"/>
            <div class="text-container">
              <div class="title">{{ item.title }}</div>
              <div class="description">{{ item.description }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
@card-bg-color: #f4f4f4;
@title-font-size: 12px;
@description-font-size: 11px;
@gap: 30px;
@card-radius: 8px;
@icon-size: 30px;

.related-navigation-title {
  font-size: 20px;
  color: #777777;
  display: flex;
  align-items: center;
  gap: 10px;
}

.related-navigation {
  cursor: pointer;

  .card-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: @gap;
  }

  .card {
    background-color: @card-bg-color;
    border-radius: @card-radius;
    padding: 15px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    // 悬浮动画
    &:hover {
      transform: scale(1.02);
      transition: transform 0.3s ease-in-out;
    }

    &:active {
      transform: scale(0.98);
    }

    .card-content {
      display: flex;
      gap: 10px;
    }

    .icon {
      flex-shrink: 0;
      width: @icon-size;
      height: @icon-size;
    }

    .text-container {
      display: flex;
      flex-direction: column;
    }

    .title {
      font-size: @title-font-size;
      font-weight: bold;
      color: #777777;
    }

    .description {
      font-size: @description-font-size;
      color: #777777;
    }
  }

  .card-link {
    text-decoration: none;
  }
}
</style>

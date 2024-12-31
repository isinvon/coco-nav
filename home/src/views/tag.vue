<!--抽离封装前-->
<!--<template>-->
<!--  <Wordcloud_v1 v-if="templateVersion === 1" :tags="tags"/>-->
<!--  <Wordcloud_v2 v-if="templateVersion === 2" :tags="tags"/>-->
<!--  <Wordcloud_v3 v-if="templateVersion === 3" :tags="tags"/>-->
<!--  <Wordcloud_v4 v-if="templateVersion === 4" :tags="tags"/>-->
<!--  <Wordcloud_v5 v-if="templateVersion === 5" :tags="tags"/>-->
<!--  <Wordcloud_v6 v-if="templateVersion === 6" :tags="tags"/>-->
<!--  &lt;!&ndash;如果超过5,就使用自定义的,el-tag&ndash;&gt;-->
<!--  <div v-else class="tag-container">-->
<!--    <el-tag v-for="tag in tags" :key="tag.text" class="tag-item" size="large" type="info" effect="light" style="font-size: 15px;padding: 10px;margin: 10px;color: #666666;background-color: #f6f6f6;">-->
<!--      <router-link :to="tag.path">{{ tag.text }}</router-link>-->
<!--    </el-tag>-->
<!--  </div>-->
<!--</template>-->

<!--<script setup>-->
<!--import Wordcloud_v1 from './tagComponents/Wordcloud_v1.vue';-->
<!--import Wordcloud_v2 from './tagComponents/Wordcloud_v2.vue';-->
<!--import Wordcloud_v3 from './tagComponents/Wordcloud_v3.vue';-->
<!--import Wordcloud_v4 from './tagComponents/Wordcloud_v4.vue';-->
<!--import Wordcloud_v5 from './tagComponents/Wordcloud_v5.vue';-->
<!--import Wordcloud_v6 from "./tagComponents/Wordcloud_v6.vue";-->
<!--import * as tagData from "../../mock/tag.js";-->
<!--import * as settingData from "../../mock/setting.js";-->
<!--import {onMounted, ref} from "vue";-->

<!--const templateVersion = ref(0);-->
<!--const tags = ref([]);-->

<!--onMounted(() => {-->
<!--  templateVersion.value = settingData.setting.data.tag.template;-->
<!--  tags.value = tagData.tags.data;-->
<!--});-->
<!--</script>-->

<!--抽离封装后-->

<template>
  <!-- 动态加载的 wordcloud 组件 -->
  <component :is="currentWordcloudComponent" :tags="tags" v-if="currentWordcloudComponent"/>
  <!-- 如果没有加载组件且标签数量超过5，显示自定义的 el-tag -->
  <div v-if="!isLoading && !currentWordcloudComponent && tags.length > 5" class="tag-container">
    <el-tag v-for="tag in tags" class="custom-tag" :key="tag.text" size="large" type="info" effect="light">
      <router-link :to="tag.path" class="custom-router-link">{{ tag.text }}</router-link>
    </el-tag>
  </div>
</template>

<script setup>
/**
 * 标签组件
 * @author sinvon
 * @since 2024年12月31日16:48:11
 */
import {ref, onMounted} from 'vue';
import * as tagData from '../../mock/tag.js';
import * as settingData from '../../mock/setting.js';

// 动态加载的 Wordcloud 组件数组
const wordcloudComponents = [
  import('./tagComponents/Wordcloud_v1.vue'),
  import('./tagComponents/Wordcloud_v2.vue'),
  import('./tagComponents/Wordcloud_v3.vue'),
  import('./tagComponents/Wordcloud_v4.vue'),
  import('./tagComponents/Wordcloud_v5.vue'),
  import('./tagComponents/Wordcloud_v6.vue'),
];

const templateVersion = ref(0);
const tags = ref([]);
const currentWordcloudComponent = ref(null);
const isLoading = ref(true);

onMounted(() => {
  templateVersion.value = settingData.setting.data.tag.template;
  tags.value = tagData.tags.data;

  // 动态加载相应的组件
  if (templateVersion.value <= 6 && templateVersion.value > 0) {
    wordcloudComponents[templateVersion.value - 1].then((module) => {
      currentWordcloudComponent.value = module.default;
      isLoading.value = false; // 加载完毕，设置为false
    });
  } else {
    currentWordcloudComponent.value = null;
    isLoading.value = false; // 无效模板时也设置为false
  }
});
</script>
<style lang="less" scoped>
.custom-tag {
  font-size: 15px;
  padding: 10px;
  margin: 10px;
  background-color: #f6f6f6;
  color: #808080;

  &:hover {
    color: #ffffff;
    background-color: #8f8f8f;
    transition: all 0.3s ease;
    transform: scale(1.1);
  }

  &:active {
    color: #ffffff;
    background-color: #8f8f8f;
    transition: all 0.3s ease;
    // 向下偏移
    transform: translateY(2px);
  }

}

/* 设置 router-link 的字体颜色 */
.custom-router-link {
  color: inherit; /* 继承父级 el-tag 的字体颜色 */
  text-decoration: none; /* 如果你想去掉下划线 */
}
</style>

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
  <component :is="currentWordcloudComponent" :tags="tags" v-if="currentWordcloudComponent"/>
  <!-- 如果超过5, 使用自定义的 el-tag -->
  <div v-else class="tag-container">
    <el-tag v-for="tag in tags" :key="tag.text" class="tag-item" size="large" type="info" effect="light" style="font-size: 15px;padding: 10px;margin: 10px;color: #666666;background-color: #f6f6f6;">
      <router-link :to="tag.path">{{ tag.text }}</router-link>
    </el-tag>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import * as tagData from '../../mock/tag.js';
import * as settingData from '../../mock/setting.js';

// 动态加载的 Wordcloud 组件数组
const wordcloudComponents = [
  import('./tagComponents/Wordcloud_v1.vue'),
  import('./tagComponents/Wordcloud_v2.vue'),
  import('./tagComponents/Wordcloud_v3.vue'),
  import('./tagComponents/Wordcloud_v4.vue'),
  import('./tagComponents/Wordcloud_v5.vue'),
];

const templateVersion = ref(0);
const tags = ref([]);
const currentWordcloudComponent = ref(null);

onMounted(() => {
  templateVersion.value = settingData.setting.data.tag.template;
  tags.value = tagData.tags.data;

  // 动态加载相应的组件
  if (templateVersion.value <= 6 && templateVersion.value > 0) {
    wordcloudComponents[templateVersion.value - 1].then((module) => {
      currentWordcloudComponent.value = module.default;
    });
  } else {
    currentWordcloudComponent.value = null;
  }
});
</script>

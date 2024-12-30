<template>
  <div class="about-page">
    <!-- 页面顶部介绍 -->
    <div class="about-header">
      <p style="font-size: 20px">{{ aboutData.data.header.welcome }}</p>
      <p style="font-size: 13px">{{ aboutData.data.header.intro }}</p>
    </div>

    <!-- 页面正文内容 -->
    <div class="about-content">
      <!-- 项目介绍 -->
      <section class="section project-intro">
        <h2>项目介绍</h2>
        <p>{{ aboutData.data.introduction }}</p>
      </section>

      <!-- 项目功能 -->
      <section class="section features">
        <h2>功能特点</h2>
        <ul>
          <li v-for="(feature, index) in aboutData.data.features" :key="index">{{ feature }}</li>
        </ul>
      </section>

      <!-- 技术栈 -->
      <section class="section tech-stack">
        <h2>技术栈</h2>
        <div class="tech-grid">
          <div v-for="(tech, index) in aboutData.data.techStack" :key="index" class="tech-item">
            <h3>{{ tech.name }}</h3>
            <p>{{ tech.details }}</p>
          </div>
        </div>
      </section>

      <!-- 项目开发者 -->
      <section class="section team">
        <h2>开发者信息</h2>
        <div class="team-members">
          <div v-for="(member, index) in aboutData.data.team" :key="index" class="team-member">
            <h3>{{ member.name }}</h3>
            <p>{{ member.role }}</p>
          </div>
        </div>
      </section>

      <!-- 发展历程 -->
      <section class="section history">
        <h2>发展历程</h2>
        <ul>
          <li v-for="(event, index) in aboutData.data.history" :key="index">
            <strong>{{ event.year }}年{{ event.month }}月：</strong> {{ event.description }}
          </li>
        </ul>
      </section>

      <!-- 联系我们 -->
      <section class="section contact">
        <h2>联系我们</h2>
        <ul>
          <li><strong>GitHub：</strong> <a :href="aboutData.data.contact.github" target="_blank">{{
              aboutData.data.contact.github
            }}</a></li>
          <li><strong>邮箱：</strong> <a :href="'mailto:' + aboutData.data.contact.email">{{
              aboutData.data.contact.email
            }}</a></li>
        </ul>
      </section>

      <!-- 贡献者 -->
      <section class="section contributors">
        <h2>贡献者</h2>
        <ul>
          <li v-for="(contributor, index) in aboutData.data.contributors" :key="index">{{ contributor }}</li>
        </ul>
      </section>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  aboutData: {
    type: Object,
    required: true
  }
})
</script>

<style lang="less" scoped>
/* 基础样式设置 */
.about-page {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: #333;
  padding: 20px;
  background-color: #f6f8fa; /* GitHub 轻灰背景 */
}

/* 页头样式 */
.about-header {
  background-color: #f3f4f6; /* GitHub 轻灰色 */
  padding: 20px;
  text-align: center;
  border-radius: 12px; /* 圆角 */
}

.about-header p {
  font-size: 1.2rem;
  color: #57606a; /* 淡灰色 */
  margin: 10px 0;
}

/* 主要内容区域样式 */
.about-content {
  margin-top: 30px;
  background-color: #fff;
  padding: 20px;
  border-radius: 12px; /* 圆角 */
}

/* 内容段落 */
.section {
  margin-bottom: 30px;
}

/* 小标题样式 */
.section h2 {
  font-size: 1.6rem;
  color: #57606a; /* GitHub 副标题颜色 */
  margin-bottom: 15px;
  font-weight: 600;
  line-height: 1.2;
  border-bottom: 2px solid #d0d7de; /* 标题下方加下划线 */
  padding-bottom: 5px;
}

/* 文本内容 */
.section p {
  font-size: 1rem;
  color: #6a737d; /* GitHub 文本颜色 */
  line-height: 1.8;
}

/* 功能特点列表 */
.section ul {
  list-style-type: none;
  padding: 0;
}

.section ul li {
  font-size: 1rem;
  color: #6a737d;
  margin-bottom: 12px;
  padding-left: 20px;
  position: relative;
}

.section ul li::before {
  content: "•";
  color: #0366d6; /* GitHub 链接颜色 */
  position: absolute;
  left: 0;
  top: 0;
}

/* 技术栈部分布局 */
.tech-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
}

.tech-item {
  background-color: #f6f8fa;
  padding: 15px;
  border-radius: 8px;
  text-align: center;
}

.tech-item h3 {
  color: #0366d6;
  font-size: 1.2rem;
  margin-bottom: 10px;
}

.tech-item p {
  color: #6a737d;
}

/* 团队成员 */
.team-members {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.team-member {
  background-color: #f6f8fa;
  padding: 15px;
  border-radius: 8px;
  text-align: center;
  flex: 1 1 200px;
}

.team-member h3 {
  color: #0366d6;
  font-size: 1.2rem;
  margin-bottom: 10px;
}

.team-member p {
  color: #6a737d;
}

/* 贡献者部分 */
.contributors ul {
  list-style-type: none;
  padding: 0;
}

.contributors ul li {
  font-size: 1rem;
  color: #6a737d;
  margin-bottom: 8px;
}

.contributors ul li::before {
  content: "•";
  color: #0366d6;
  margin-right: 10px;
}
</style>

<template>
  <div class="github-issue-sidebar">
    <div class="views">
      <span class="title">浏览量</span>
      <!--html换行标签-->
      <br>
      <span class="detail">1212</span>
    </div>
    <el-divider class="divider"/>
    <!--文章标签-->
    <div class="labels">
      <span class="title">标签</span>
      <br>
      <el-tag type="info" effect="light" round>question</el-tag>
    </div>
    <el-divider class="divider"/>

    <div class="share">
      <span class="title">分享</span>
      <br>
      <Share/>
    </div>
    <el-divider class="divider"/>

    <div class="milestone">
      <span class="title">Milestone</span>
      <br>
      <span class="detail">No milestone</span>
    </div>
    <el-divider class="divider"/>

    <div class="reviewer">
      <span class="title">
        评论用户 · {{1}}
      </span>
      <br>
      <!--如果有用户，就显示头像-->
      <el-avatar style="margin-top: 10px" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                 size="small"/>
      <br>
      <!--没有评论就显示占位文字-->
      <span class="detail">还没有人评论呢</span>
    </div>
    <el-divider class="divider"/>

    <div class="subscribe">
      <span class="title">订阅</span>
      <br>
      <el-button color="#368a3a" size="small" style="margin-top: 10px" round>Subscribe</el-button>
    </div>

    <div class="detail notification-message">
      <p>You're not receiving notifications from this thread.</p>
    </div>
    <el-divider class="divider"/>
  </div>
</template>

<script setup lang="js">
/**
 * 侧边栏
 */
import Share from "../RightAside/components/Share.vue";
</script>

<style lang="less" scoped>

.github-issue-sidebar {
  display: flex;
  flex-direction: column; // 垂直方向
  gap: 0px;
  padding: 10px;
  padding-left: 1px;
  //background-color: #f8f9fa; // 背景颜色
  //border: 1px solid #dee2e6; // 边框颜色
  // 字体靠左
  text-align: left;
}

.github-issue-sidebar div {
}

.github-issue-sidebar span {
  font-weight: 500;
}

.el-tag {
  margin-top: 10px;
  //margin-left: 10px;
}

.title {
  font-size: 12px;
  font-weight: 500;
  color: #5f6a6d;
}

.detail {
  font-size: 12px;
  color: #89949f;
}

// 分割线
.divider {
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
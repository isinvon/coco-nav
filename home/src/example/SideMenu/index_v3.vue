<script setup>

</script>

<template>
  <div class="banner">
    <h1>web前端开发公众号</h1>
  </div>

  <div class="hoverable-topic">

  </div>

  <div class="sidehoverbar">
    <a href="http://www.webqdkf.com/qdkf/" class="article">
      前端开发
      <i class="fa fa-edit"></i>
    </a>

    <a href="http://www.webqdkf.com/qdkj/" class="Interview">
      前端框架
      <i class="fa fa-file-o"></i>
    </a>

    <a href="http://www.yang5460.com" class="Scripter">
      杨小二小江湖
      <i class="fa fa-commenting"></i>
    </a>

    <a href="http://www.webqdkf.com/mb/" class="Suggested">
      网站模板
      <i class="fa fa-plus"></i>
    </a>

    <a href="http://www.webqdkf.com/tx/" class="Practice">
      代码案例
      <i class="fa fa-laptop"></i>
    </a>

    <a href="http://www.webqdkf.com/zyk/" class="Practice1">
      学习资源
      <i class="fa fa-camera"></i>
    </a>

    <a href="http://www.webqdkf.com/webdh.html" class="Practice2">
      网址导航
      <i class="fa fa-tasks"></i>
    </a>

    <a href="http://www.webqdkf.com/gksj.html" class="Practice3">
      极客时间
      <i class="fa fa-paste"></i>
    </a>
  </div>
</template>

<style lang="less" scoped>
.banner {
  text-align: center;
  //width: ;
}

h1 {
  color: #1ab1cc;
}

.sidehoverbar a {
  background-color: #1ab1cc;
  position: absolute;
  font-size: 22px;
  text-decoration: none;
  Color: white;
  padding: 10px;
  border-radius: 0px 25px 25px 0px;
  left: -190px;
  transition: 0.5s;
  opacity: 0.5;
}

.sidehoverbar a:hover {
  left: 0px;
  opacity: 1;
  background-color: #757a7b;
}

.sidehoverbar i {
  float: right;
}

.article {
  top: 50px;
  width: 210px;
  height: 30px;
}

.Interview {
  top: 110px;
  width: 210px;
  height: 30px;
}

.Scripter {
  top: 170px;
  width: 210px;
  height: 30px;
}

.Suggested {
  top: 230px;
  width: 210px;
  height: 30px;
}

.Practice {
  top: 290px;
  width: 210px;
  height: 30px;
}

.Practice1 {
  top: 350px;
  width: 210px;
  height: 30px;
}

.Practice2 {
  top: 410px;
  width: 210px;
  height: 30px;
}

.Practice3 {
  top: 470px;
  width: 210px;
  height: 30px;
}

.hoverable-topic {
  margin-left: 55px;
}
</style>
<template>
  <div class="container">
    <el-anchor
        class="anchor"
        :class="{ visible: isAnchorVisible }"
        :style="anchorStyle"
    >
      <div class="anchor-links">
        <el-anchor-link
            v-for="link in (anchorLinks.length > 0 ? anchorLinks : defaultAnchorLinks) "
            :key="link.href"
            :href="link.href"
        >
          {{ link.text }}
        </el-anchor-link>
      </div>
    </el-anchor>
  </div>
</template>

<script lang="js" setup>
import {computed, onMounted, onUnmounted, ref} from "vue";

const isAnchorVisible = ref(false); // 默认设置为不可见

// 如果 anchorLinks 是空的，则使用默认的
const defaultAnchorLinks = ref([
  {href: '#basic-usage', text: '影视网站'},
  {href: '#horizontal-mode', text: 'API接口'},
  {href: '#scroll-container', text: '编程播客'},
  {href: '#anchor-api', text: '开发工具'},
  {href: '#anchor-attributes', text: '书籍报刊'},
  {href: '#anchor-events', text: '摸鱼社区'},
  {href: '#anchor-slot', text: '技术社区'},
  {href: '#coding-challenges', text: '编程挑战'},
]);

const anchorLinks = [
  {href: '#basic-usage', text: '🐦影视网站'},
  {href: '#horizontal-mode', text: '🐱API接口'},
  {href: '#scroll-container', text: '🐍编程播客'},
  {href: '#anchor-api', text: '🔨开发工具'},
  {href: '#anchor-attributes', text: '📚书籍报刊'},
  {href: '#anchor-events', text: '🐟摸鱼社区'},
  {href: '#anchor-slot', text: '💻技术社区'},
  {href: '#coding-challenges', text: '🔪编程挑战'},
  {href: '#developer-resources', text: '⚙️开发者资源'},
  {href: '#community-forum', text: '🖱社区论坛'},
  {href: '#tech-tutorials', text: '技术教程'},
  {href: '#open-source-projects', text: '🎈开源项目'},
  {href: '#career-development', text: '🪣职业发展'},
  {href: '#software-tools', text: '🚌软件工具'},
  {href: '#web-development', text: '🤩Web开发'},
  {href: '#mobile-apps', text: '📱移动应用'},
  {href: '#data-science', text: '🔑数据科学'},
  {href: '#machine-learning', text: '🤖机器学习'},
  {href: '#cloud-computing', text: '🐔云计算'},
  {href: '#devops', text: '🧠DevOps'}
];


// 动态计算渐变样式
const anchorStyle = computed(() => {
  return {
    position: "fixed",
    zIndex: 5,
    left: "2px",
    top: "50%",
    transform: "translateY(-50%)",
    transition: "opacity 0.3s ease, visibility 0.3s ease, transform 0.3s ease", // 加入visibility和background的过渡
    opacity: isAnchorVisible.value ? 1 : 0, // 默认隐藏锚点，透明度为0
    visibility: isAnchorVisible.value ? "visible" : "hidden", // 控制可见性
    background: isAnchorVisible.value
        ? "linear-gradient(to right, rgba(194, 194, 194, 0.5), rgb(255, 255, 255) 50%, rgba(255, 255, 255, 0.96) 50%, rgba(255, 255, 255, 0.02))"
        : "none", // 默认不显示渐变色
  };
});


const threshold = 50; // 设置阈值为50px，鼠标靠近左边50px时显示锚点

let hideTimeout = null; // 定义一个隐藏超时变量

// 检查鼠标与锚点左边界的距离
const checkMouseProximity = (event) => {
  const anchorElement = document.querySelector(".anchor");
  if (anchorElement) {
    const anchorRect = anchorElement.getBoundingClientRect();

    // 获取鼠标X坐标
    const mouseX = event.clientX;

    // 计算鼠标与锚点左边界的水平距离
    const distance = mouseX - anchorRect.left;

    // 如果鼠标与锚点左边界的距离小于阈值，显示锚点
    if (distance <= threshold && distance >= 0) {
      isAnchorVisible.value = true;
    }
  }
};

// 检查鼠标是否离开整个.anchor区域
const checkMouseLeave = (event) => {
  const anchorElement = document.querySelector(".anchor");
  if (anchorElement) {
    const anchorRect = anchorElement.getBoundingClientRect();

    // 判断鼠标是否完全离开了锚点区域
    const isOutside =
        event.clientX < anchorRect.left ||
        event.clientX > anchorRect.right ||
        event.clientY < anchorRect.top ||
        event.clientY > anchorRect.bottom;

    // 如果鼠标完全离开锚点区域，延迟隐藏
    if (isOutside) {
      if (hideTimeout) {
        clearTimeout(hideTimeout); // 清除之前的延迟隐藏
      }
      hideTimeout = setTimeout(() => {
        isAnchorVisible.value = false; // 延迟执行隐藏
      }, 25); // 延迟300ms再隐藏
    }
  }
};
onMounted(() => {
  window.addEventListener("mousemove", checkMouseProximity); // 监听鼠标移动事件
  window.addEventListener("mousemove", checkMouseLeave); // 监听鼠标是否离开锚点区域
});

onUnmounted(() => {
  window.removeEventListener("mousemove", checkMouseProximity); // 移除鼠标移动事件
  window.removeEventListener("mousemove", checkMouseLeave); // 移除鼠标离开事件
});

// 检查鼠标是否离开锚点区域 (不加任何延迟检测的版本)
// const checkMouseProximity = (event) => {
//   const anchorElement = document.querySelector(".anchor");
//   if (anchorElement) {
//     const anchorRect = anchorElement.getBoundingClientRect();
//
//     // 判断鼠标是否在锚点区域内
//     const isInsideAnchorArea =
//         event.clientX >= anchorRect.left &&
//         event.clientX <= anchorRect.right &&
//         event.clientY >= anchorRect.top &&
//         event.clientY <= anchorRect.bottom;
//
//     // 如果鼠标完全离开了锚点区域，隐藏锚点
//     if (!isInsideAnchorArea) {
//       isAnchorVisible.value = false;
//     } else {
//       // 如果鼠标进入锚点区域，则显示锚点
//       isAnchorVisible.value = true;
//     }
//   }
// };

// 鼠标监听事件 (不加任何延迟检测的版本)
// onMounted(() => {
//   window.addEventListener("mousemove", checkMouseProximity); // 监听鼠标移动事件
// });
//
// onUnmounted(() => {
//   window.removeEventListener("mousemove", checkMouseProximity); // 移除鼠标移动事件
// });
</script>

<style scoped lang="less">
.container {
  width: 100%;
  height: 100vh;
}

.anchor {
  pointer-events: auto;
  transition: opacity 0.3s ease, visibility 0.3s ease, transform 0.3s ease; /* 将visibility和opacity的过渡加入 */
  visibility: hidden; /* 初始状态为隐藏，不占用空间 */
  opacity: 0; /* 初始状态透明 */
}

.anchor.visible {
  opacity: 1; /* 可见时设置为不透明 */
  visibility: visible; /* 可见时设置为可见 */
}

.anchor-links {
  display: flex;
  flex-direction: column;
  opacity: 0;
  transform: translateX(-10px);
  transition: opacity 0.3s ease, transform 0.3s ease;
}

.anchor.visible .anchor-links {
  opacity: 1;
  transform: translateX(2px); // 定义标记和字体的间隔
}

/* 使用::v-deep深度穿透样式(v-deep已弃用,改为:deep(<inner-selector>) */
.container:deep(.el-anchor__item) {
  font-size: 13px; /* 强制修改字体大小 */
  padding: 1px 0; /* 强制修改内边距 */
}

.container:deep(.el-anchor__link) {
  font-size: 13px; /* 强制修改链接字体大小 */
}
</style>

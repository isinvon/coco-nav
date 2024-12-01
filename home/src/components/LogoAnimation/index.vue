<script setup>

</script>

<template>
  <div class="wrapper">
    <svg>
      <text x="50%" y="50%" dy=".35em" text-anchor="middle">
        coco
      </text>
    </svg>
  </div>
</template>

<style scoped lang="less">
@import url("https://fonts.googleapis.com/css2?family=Signika&display=swap");

svg {
  font-family: "Signika", sans-serif;
  width: 100%;
  height: 100%;
}

svg text {
  animation: stroke 9s infinite alternate;
  stroke-width: 2;
  stroke: #1F2328;
  font-size: 50px;
}

@keyframes stroke {
  0% {
    fill: rgba(31, 35, 40, 0);
    stroke: rgba(31, 35, 40, 1);
    stroke-dashoffset: 25%;
    stroke-dasharray: 0 50%;
    stroke-width: 2;
  }
  70% {
    fill: rgba(31, 35, 40, 0);
    stroke: rgba(31, 35, 40, 1);
  }
  80% {
    fill: rgba(31, 35, 40, 0);
    stroke: rgba(31, 35, 40, 1);
    stroke-width: 3;
  }
  100% {
    fill: rgba(31, 35, 40, 1);
    stroke: rgba(31, 35, 40, 0);
    stroke-dashoffset: -25%;
    stroke-dasharray: 50% 0;
    stroke-width: 0;
  }
}

.wrapper {
  background-color: #F6F8FA
}

;

</style>
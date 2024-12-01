<script setup>

</script>

<template>
  <span data-tooltip="Tooltip help here!" data-flow="bottom">CSS Tooltips</span>
</template>

<style scoped lang="less">
[data-tooltip] {
  position: relative;
  pointer: cursor;
}

[data-tooltip]::before,
[data-tooltip]::after {
  text-transform: none;
  font-size: .9em;
  line-height: 1;
  position: absolute;
  display: none;
  opacity: 0;
}

[data-tooltip]::before {
  content: '';
  border: 6px solid transparent;
  z-index: 101;
}

[data-tooltip]::after {
  content: attr(data-tooltip);
  text-align: center;
  min-width: 3em;
  max-width: 21em;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  padding: 5px 7px;
  border-radius: 6px;
  background: #25292E;
  color: #FFFFFF;
  z-index: 100;
}

[data-tooltip]:hover::before,
[data-tooltip]:hover::after {
  display: block;
}

[data-tooltip='']::before,
[data-tooltip='']::after {
  display: none !important;
}

[data-tooltip]:not([data-flow])::before,
[data-tooltip][data-flow^="top"]::before {
  bottom: 100%;
  border-bottom-width: 0;
  border-top-color: #25292E;
}

[data-tooltip]:not([data-flow])::after,
[data-tooltip][data-flow^="top"]::after {
  bottom: calc(100% + 5px);
}

[data-tooltip]:not([data-flow])::before,
[data-tooltip]:not([data-flow])::after,
[data-tooltip][data-flow^="top"]::before,
[data-tooltip][data-flow^="top"]::after {
  left: 50%;
  transform: translate(-50%, -.4em);
}

[data-tooltip][data-flow^="bottom"]::before {
  top: 100%;
  border-top-width: 0;
  border-bottom-color: #25292E;
}

[data-tooltip][data-flow^="bottom"]::after {
  top: calc(100% + 5px);
}

[data-tooltip][data-flow^="bottom"]::before,
[data-tooltip][data-flow^="bottom"]::after {
  left: 50%;
  transform: translate(-50%, .4em);
}

[data-tooltip][data-flow^="left"]::before {
  top: 50%;
  border-right-width: 0;
  border-left-color: #25292E;
  left: calc(0em - 5px);
  transform: translate(-.5em, -50%);
}

[data-tooltip][data-flow^="left"]::after {
  top: 50%;
  right: calc(100% + 5px);
  transform: translate(-.4em, -50%);
}

[data-tooltip][data-flow^="right"]::before {
  top: 50%;
  border-left-width: 0;
  border-right-color: #25292E;
  right: calc(0em - 7px);
  transform: translate(.4em, -50%);
}

[data-tooltip][data-flow^="right"]::after {
  top: 50%;
  left: calc(100% + 5px);
  transform: translate(.5em, -50%);
}

@keyframes tooltip-vert {
  to {
    opacity: 1;
    transform: translate(-50%, 0);
  }
}

@keyframes tooltip-horz {
  to {
    opacity: 1;
    transform: translate(0, -50%);
  }
}

[data-tooltip]:not([data-flow]):hover::before,
[data-tooltip]:not([data-flow]):hover::after,
[data-tooltip][data-flow^="top"]:hover::before,
[data-tooltip][data-flow^="top"]:hover::after,
[data-tooltip][data-flow^="bottom"]:hover::before,
[data-tooltip][data-flow^="bottom"]:hover::after {
  animation: tooltip-vert .5s ease-out forwards;
}

[data-tooltip][data-flow^="left"]:hover::before,
[data-tooltip][data-flow^="left"]:hover::after,
[data-tooltip][data-flow^="right"]:hover::before,
[data-tooltip][data-flow^="right"]:hover::after {
  animation: tooltip-horz .5s ease-out forwards;
}

</style>
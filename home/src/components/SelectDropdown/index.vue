<script setup>

</script>

<template>
  <div class="custom-select">
    <select>
      <option>Select Country</option>
      <option>Australia</option>
      <option>New Zealand</option>
      <option>United Kingdom</option>
      <option>United States</option>
    </select>
    <div class="select-arrow"></div>
  </div>
</template>

<style scoped lang="less">
.custom-select {
  position: relative;
}

.custom-select select {
  appearance: none;
  -webkit-appearance: none;
  width: 100%;
  font-size: 12px;
  padding: 3px 8px 3px 8px;
  background-color: #F6F8FA;
  border: 1px solid #D1D9E0;
  border-radius: 6px;
  color: #1F2328;
  cursor: pointer;
  outline: none;
}

.custom-select select:focus {
  background: #F2F2F2;
  border: 1px solid #5A7EC7;
  border-radius: 5px;
}

.custom-select::after {
  content: "";
  position: absolute;
  pointer-events: none;
  top: 50%;
  right: 9px;
  transform: translate(0, -50%);
  width: 7px;
  height: 7px;
  background-color: #1F2328;
  clip-path: polygon(8% 17%, 0% 25%, 50% 84%, 100% 25%, 92% 17%, 50% 65%);
}
</style>
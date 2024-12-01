<script setup>

</script>

<template>
  <div class="custom_input">
    <svg xmlns="http://www.w3.org/2000/svg" class="svg_icon bi-search" viewBox="0 0 16 16">
      <path
          d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"></path>
    </svg>
    <input class="input" type="text" placeholder="Placeholder Text">
  </div>
</template>

<style scoped lang="less">
.custom_input {
  display: flex;
  align-items: center;
  position: relative;
  max-width: 100%;
}

.input {
  font-size: 12px;
  padding: 5px 10px;
  width: 100%;
  padding-left: 35px;
  outline: none;
  background: #FFFFFF;
  color: #1F2328;
  border: 1px solid #D1D9E0;
  border-radius: 6px;
  transition: .3s ease;
}

.input:focus {
  background: #F2F2F2;
  border: 1px solid #5A7EC7;
  border-radius: 10px;
}

.input::placeholder {
  color: #DDDDDD;
}

.svg_icon {
  position: absolute;
  left: 10px;
  fill: #59636E;
  width: 12px;
  height: 12px;
}

</style>
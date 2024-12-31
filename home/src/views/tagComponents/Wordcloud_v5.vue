<template>
  <div class="tag-cloud-container">
    <svg ref="wordCloudSVG" width="1200" height="1000">
      <!-- 定义光芒滤镜 -->
      <defs>
        <filter id="glow" x="-50%" y="-50%" width="200%" height="200%">
          <feGaussianBlur stdDeviation="10" result="blur"/>
          <feMerge>
            <feMergeNode in="blur"/>
            <feMergeNode in="SourceGraphic"/>
          </feMerge>
        </filter>
      </defs>
    </svg>
  </div>
</template>

<script setup>
/**
 * 词云组件, 朦胧轻灰墨色, 弹动动画
 */
import {onMounted, ref} from 'vue';
import cloud from 'd3-cloud';
import * as d3 from 'd3';
import {Filter} from "@element-plus/icons-vue";

const props = defineProps({
  tags: {
    type: Array,
    required: true
  }
});

const wordCloudSVG = ref(null);

onMounted(() => {
  generateWordCloud();
});

function generateWordCloud() {
  const svg = d3.select(wordCloudSVG.value);
  const maxTags = 80;
  const maxFontSize = 40;
  const minFontSize = 12;

  // 根据标签的数量动态调整字体大小和词间距
  const fontSizeScale = d3.scaleLinear()
      .domain([0, maxTags])
      .range([minFontSize, maxFontSize]);

  const words = props.tags.map(tag => ({
    text: tag.text,
    size: fontSizeScale(tag.weight),
    path: tag.path
  }));

  // 使用灰色渐变，调整为轻灰色调
  const colorScale = d3.scaleLinear()
      .domain([d3.min(words, d => d.size), d3.max(words, d => d.size)])
      .range(['#c4c4c4', '#A9A9A9']);  // 从浅灰到中灰的渐变

  cloud()
      .size([1200, 1000])  // 扩大词云区域
      .words(words)
      .padding(8)
      .rotate(() => (Math.random() > 0.5 ? 0 : 90))
      .font('Roboto')  // 更具个性的字体
      .fontSize(d => d.size)
      .on('end', draw)
      .start();

  function draw(words) {
    svg.append('g')
        .attr('transform', 'translate(600, 500)')  // 居中显示
        .selectAll('a')
        .data(words)
        .enter()
        .append('a')
        .attr('href', d => d.path)
        .attr('class', 'tag-link')
        .append('g')
        .attr('transform', d => `translate(${d.x}, ${d.y}) rotate(${d.rotate})`)
        .append('text')
        .style('font-size', d => d.size + 'px')
        .style('font-family', '"Roboto", sans-serif')  // 更具个性的字体
        .style('fill', d => colorScale(d.size))  // 使用灰色渐变色
        .style('font-weight', d => (d.size > maxFontSize * 0.8 ? 'bold' : 'normal')) // 动态字体粗细
        .attr('text-anchor', 'middle')
        .attr('filter', 'url(#glow)')  // 应用发光效果滤镜
        .text(d => d.text)
        .on('mouseover', function (event, d) {
          const gElement = d3.select(this.parentNode);
          const scaleFactor = 1.1;

          // 提升当前词的层级，确保它在其他词之上
          gElement.style('z-index', 1000);  // 动态增加 z-index，确保在最上层

          // 放大字体并给文字增加光效
          gElement.transition().duration(300).ease(d3.easeElasticOut)
              .attr('transform', `translate(${d.x}, ${d.y}) rotate(${d.rotate}) scale(${scaleFactor})`);

          d3.select(this)
              .transition().duration(300).ease(d3.easeElasticOut)
              .style('fill', '#000')  // 更加深色
              .style('text-shadow', '0px 0px 20px rgba(255, 255, 255, 1)');  // 加强光效（模拟光芒）
        })
        .on('mouseout', function (event, d) {
          const gElement = d3.select(this.parentNode);

          // 恢复原始层级
          gElement.style('z-index', 1);  // 恢复默认层级

          // 移除光效和放大效果
          gElement.transition().duration(300).ease(d3.easeElasticOut)
              .attr('transform', `translate(${d.x}, ${d.y}) rotate(${d.rotate}) scale(1)`);

          d3.select(this)
              .transition().duration(300).ease(d3.easeElasticOut)
              .style('fill', colorScale(d.size))  // 恢复颜色
              .style('text-shadow', 'none');  // 移除光效
        });
  }
}
</script>

<style lang="less" scoped>
.tag-cloud-container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fff;
}

.tag-link {
  cursor: pointer;
  text-decoration: none;
}

.tag-link text {
  transition: transform 0.3s ease, fill 0.3s ease;
}
</style>

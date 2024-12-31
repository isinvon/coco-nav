<template>
  <div class="tag-cloud-container">
    <svg ref="wordCloudSVG" width="1200" height="1000"></svg>
  </div>
</template>

<script setup>
/**
 * 词云组件, 轻灰色, 弹性动画
 */
import { onMounted, ref } from 'vue';
import cloud from 'd3-cloud';
import * as d3 from 'd3';

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
      .range(['#989898', '#9d9d9d']);  // 从浅灰到中灰的渐变

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
        .text(d => d.text)
        .on('mouseover', function (event, d) {
          const gElement = d3.select(this.parentNode);
          const scaleFactor = 1.1;
          gElement.transition().duration(300).ease(d3.easeElasticOut)
              .attr('transform', `translate(${d.x}, ${d.y}) rotate(${d.rotate}) scale(${scaleFactor})`);
          d3.select(this).transition().duration(300).ease(d3.easeElasticOut)
              .style('fill', '#000')
              .style('text-shadow', '2px 2px 5px rgba(0,0,0,0.3)');
        })
        .on('mouseout', function (event, d) {
          const gElement = d3.select(this.parentNode);
          gElement.transition().duration(300).ease(d3.easeElasticOut)
              .attr('transform', `translate(${d.x}, ${d.y}) rotate(${d.rotate}) scale(1)`);
          d3.select(this).transition().duration(300).ease(d3.easeElasticOut)
              .style('fill', colorScale(d.size))
              .style('text-shadow', 'none');
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

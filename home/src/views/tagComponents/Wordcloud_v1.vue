<template>
  <div class="tag-cloud-container">
    <svg ref="wordCloudSVG" width="1200" height="1000"></svg>
  </div>
</template>

<script setup>
/**
 * 词云组件, 轻灰色, 普通放大动画
 * @author sinvon
 * @since 2024年12月31日16:48:11
 */
import {onMounted, ref} from 'vue';
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
    size: fontSizeScale(tag.weight), // 动态计算字体大小
    path: tag.path
  }));

  const colorScale = d3.scaleLinear()
      .domain([d3.min(words, d => d.size), d3.max(words, d => d.size)])
      // 根据字体大小, 使用不同的颜色, 越大的字体颜色越浅
      .range(['#666666', '#d9d9d9']);

  cloud()
      .size([1200, 1000])  // 增加词云区域宽度和高度
      .words(words)
      .padding(8)
      .rotate(() => (Math.random() > 0.5 ? 0 : 90))
      .font('Arial')
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
        .style('font-family', 'Arial')
        .style('fill', d => colorScale(d.size))
        .attr('text-anchor', 'middle')
        .text(d => d.text)
        // 鼠标移入时放大和改变颜色
        .on('mouseover', function (event, d) {
          const gElement = d3.select(this.parentNode);
          const scaleFactor = 1.1;
          gElement
              .transition().duration(300)
              .ease(d3.easeCubicOut)
              .attr('transform', `translate(${d.x}, ${d.y}) rotate(${d.rotate}) scale(${scaleFactor})`);
          d3.select(this)
              .transition().duration(300)
              .ease(d3.easeCubicOut)
              .style('fill', '#000');
        })
        // 鼠标移出时恢复原始大小和颜色
        .on('mouseout', function (event, d) {
          const gElement = d3.select(this.parentNode);
          gElement
              .transition().duration(300)
              .ease(d3.easeCubicOut)
              .attr('transform', `translate(${d.x}, ${d.y}) rotate(${d.rotate}) scale(1)`);
          d3.select(this)
              .transition().duration(300)
              .ease(d3.easeCubicOut)
              .style('fill', colorScale(d.size));
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
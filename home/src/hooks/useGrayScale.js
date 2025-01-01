import { useGrayScaleStore } from '@/store/grayScaleStore';
import { ref, onMounted } from 'vue';

/**
 * 页面灰度调节
 * @author: sinvon
 * @since 2025年1月1日16:51:38
 */
export function useGrayScale() {
    const grayScaleStore = useGrayScaleStore();
    const grayScaleLevel = ref(grayScaleStore.grayScaleLevel);

    // 页面加载时直接从store获取灰度级别
    onMounted(() => {
        grayScaleLevel.value = grayScaleStore.grayScaleLevel;
    });

    return {
        grayScaleLevel,  // 这个值绑定到视图中
    }
}

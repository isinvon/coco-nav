/**
 * @description: 监听点击事件，判断是否点击了热点卡片以外的地方，如果是则关闭热点卡片
 * @author: sinvon
 * @since 2025年1月1日02:19:27
 */
import {onMounted, onUnmounted} from 'vue';
import {useHotCardStore} from '@/store/hotCard.js'; // 引入store

export function useHot() {
    const hotCardStore = useHotCardStore(); // 获取 store

    const handleClickOutside = (event) => {
        // 获取热点卡片和按钮的 DOM 元素
        const hotCardElement = document.querySelector('.hot-card'); // 获取热点卡片 DOM 元素
        const hotButtonElement = document.querySelector('.hot-button'); // 获取按钮 DOM 元素

        // 如果点击的是 .hot-card 或其子元素，则不做任何处理
        if (hotCardElement && hotCardElement.contains(event.target)) {
            return;
        }

        // 如果点击的是 hot-button 按钮以外的地方，调用 closeHotCard 方法(如果没有这一步,点击hot-button按钮就会无法启动热点卡片)
        if (hotButtonElement && hotButtonElement.contains(event.target)) {
            return;
        }

        // 如果点击的是其他地方，调用 closeHotCard 方法
        hotCardStore.closeHotCard();
    };

    // 在组件挂载时添加事件监听
    onMounted(() => {
        document.addEventListener('click', handleClickOutside);
    });

    // 在组件卸载时移除事件监听
    onUnmounted(() => {
        document.removeEventListener('click', handleClickOutside);
    });
}

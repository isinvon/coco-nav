import {defineStore} from 'pinia';
import Storage from '@/utils/Storage'; // 引入封装好的 Storage 工具

/**
 * 灰度调节共享状态
 * @author sinvon
 * @since 2025年1月1日16:37:01
 */
export const useGrayScaleStore = defineStore('grayScale', {
    state: () => ({
        // 从 localStorage 获取灰度级别，若没有则默认为 0
        grayScaleLevel: Storage.getItem('grayScaleLevel') || 0,
    }),
    actions: {
        setGrayScaleLevel(level) {
            this.grayScaleLevel = level;
            // 将灰度级别保存到 localStorage
            Storage.setItem('grayScaleLevel', level);
        }
    }
});

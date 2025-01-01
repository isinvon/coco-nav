// src/store/useDarkStore.js

import {defineStore} from 'pinia';

/**
 * 夜间/暗黑模式状态管理
 * @author sinvon
 * @since 2025年1月1日21:31:00
 * @type {StoreDefinition<"darkTheme", {isDarkMode: boolean}, {}, {toggleDarkMode(): void}>}
 */
export const useDarkThemeStore = defineStore('darkTheme', {
    state: () => ({
        isDarkMode: false,  // 默认为亮色模式
    }),
    actions: {
        toggleDarkMode() {
            this.isDarkMode = !this.isDarkMode;
            console.log('切换暗黑模式:', this.isDarkMode)
        },
    },
});

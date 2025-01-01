// src/store/useDarkStore.js

import {defineStore} from 'pinia';

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

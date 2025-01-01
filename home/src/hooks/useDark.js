// src/hooks/useDark.js

import { ref } from 'vue';
import { useDarkThemeStore } from '@/store/darkThemeStore.js';
import DarkReader from 'darkreader';

export default function useDark() {
    const darkStore = useDarkThemeStore();  // 使用状态管理

    // 切换暗黑模式
    const toggleDarkMode = () => {
        if (darkStore.isDarkMode) {
            // 禁用暗黑模式
            DarkReader.disable();
        } else {
            // 启用暗黑模式
            DarkReader.enable({
                brightness: 100,
                contrast: 90,
                sepia: 10,
            });
        }
        // 更新状态
        darkStore.toggleDarkMode();
    };

    return {
        toggleDarkMode,
    };
}

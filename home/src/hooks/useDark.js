import * as DarkReader from 'darkreader';
import {useDarkThemeStore} from "@/store/darkThemeStore.js";

export default function useDark() {
    const darkStore = useDarkThemeStore();  // 使用状态管理

    // 切换暗黑模式
    const toggleDarkMode = () => {
        console.log('DarkReader:', DarkReader); // 检查 DarkReader 是否加载成功

        if (DarkReader && DarkReader.enable) {
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
        } else {
            console.error("DarkReader is not loaded or initialized correctly.");
        }
    };

    return {
        toggleDarkMode,
    };
}

import * as DarkReader from 'darkreader';
import {useDarkThemeStore} from "@/store/darkThemeStore.js";

/**
 * 夜间/暗色主题的钩子
 * @author sinvon
 * @since 2025年1月1日21:30:40
 * @returns {{toggleDarkMode: toggleDarkMode}}
 */
export default function useDark() {
    const darkStore = useDarkThemeStore();  // 使用状态管理

    // 切换暗黑模式
    const toggleDarkMode = (event) => {
        // 计算鼠标点击位置
        const x = event.clientX;
        const y = event.clientY;
        // 计算最大圆的半径
        const endRadius = Math.hypot(
            Math.max(x, window.innerWidth - x),
            Math.max(y, window.innerHeight - y)
        );

        // 设置 CSS 变量，控制圆心和半径
        document.documentElement.style.setProperty('--x', `${x}px`);
        document.documentElement.style.setProperty('--y', `${y}px`);
        document.documentElement.style.setProperty('--r', `${endRadius}px`);

        // 使用 document.startViewTransition 进行页面过渡
        if (document.startViewTransition) {
            // 如果支持 View Transition API，则使用该方法进行过渡
            document.startViewTransition(() => {
                // 切换暗色模式
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
            });
        } else {
            // 如果不支持 View Transition API，直接切换主题
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
        }
    };

    return {
        toggleDarkMode,
    };
}

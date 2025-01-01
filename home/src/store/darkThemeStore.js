// src/store/useDarkStore.js

import {defineStore} from 'pinia';
import * as DarkReader from 'darkreader';
import Storage from '@/utils/Storage.js'

/**
 * 夜间/暗黑模式状态管理
 * @author sinvon
 * @since 2025年1月1日21:31:00
 * @type {StoreDefinition<"darkTheme", {isDarkMode: boolean}, {}, {toggleDarkMode(): void}>}
 */
export const useDarkThemeStore = defineStore('darkTheme', {
    state: () => ({
        isDarkMode: Storage.getItem('darkMode') || false,  // 默认为亮色模式
    }),
    actions: {
        toggleDarkMode(event) {
            this.isDarkMode = event;
            Storage.setItem('darkMode', event);
            console.log('切换暗黑模式:', this.isDarkMode)

            // ================= 暗黑切换的逻辑 ========= start ========
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
                        if (this.isDarkMode) {
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
                        Storage.setItem('darkMode', event);
                    } else {
                        console.error("DarkReader is not loaded or initialized correctly.");
                    }
                });
            } else {
                // 如果不支持 View Transition API，直接切换主题
                if (DarkReader && DarkReader.enable) {
                    if (this.isDarkMode) {
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
                    Storage.setItem('darkMode', event);
                } else {
                    console.error("DarkReader is not loaded or initialized correctly.");
                }
            }
            // ================= 暗黑切换的逻辑 ========= end ========
        },
    },
});

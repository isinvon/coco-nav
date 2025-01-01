/**
 * 初始化主题(每当刷新页面的时候从)
 * @author sinvon
 * @since 2025年1月1日23:51:39
 */
// 引入 darkThemeStore 用于管理主题
import {useDarkThemeStore} from "@/store/darkThemeStore.js";
// 引入 Storage 用于读取主题配置
import Storage from "@/utils/Storage.js";


export function initTheme() {
    const darkTheme = useDarkThemeStore()
    let item = Storage.getItem('darkMode') || false; // 从 localStorage 获取主题配置, 默认为 false(不开启暗黑模式)
    // 调用 toggleDarkMode 方法来切换主题
    darkTheme.toggleDarkMode(item)
}
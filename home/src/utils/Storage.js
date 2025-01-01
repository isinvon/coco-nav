/**
 * 浏览器本地存储工具封装
 * @author sinvon
 * @since 2025年1月1日17:38:11
 */
class Storage {
    setItem(key, value) {
        if (typeof value === "object") {
            value = JSON.stringify(value);
        }
        localStorage.setItem(key, value);
    }

    getItem(key) {
        const value = localStorage.getItem(key);
        try {
            return JSON.parse(value);
        } catch (e) {
            return value || null;
        }
    }

    removeItem(key) {
        localStorage.removeItem(key);
    }

    clear() {
        localStorage.clear();
    }
}

export default new Storage();

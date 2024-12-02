import {defineStore} from 'pinia'

/**
 * 添加书签组件 的 状态管理
 * @author sinvon
 * @since 2024年12月2日15:54:31
 */
export const useAddBookmarkBoxStore = defineStore('addBookmarkBox', {
    state: () => ({
        isOpen: false,
    }),
    actions: {
        toggleBox() {
            this.isOpen = !this.isOpen
        },
        openBox() {
            this.isOpen = true
        },
        closeBox() {
            this.isOpen = false
        },
    },
})

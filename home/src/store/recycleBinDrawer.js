import {defineStore} from 'pinia'

/**
 * 回收站的状态管理
 * @author sinvon
 * @since 2024年12月4日22:09:05
 * @file recycleBinDrawer.js
 */
export const useRecycleBinDrawerStore = defineStore('recycleBinDrawer', {
    state: () => ({
        isOpen: false,
    }),
    actions: {
        toggleRecycleBinDrawerState() {
            this.isOpen = !this.isOpen
        },
        openRecycleBinDrawer() {
            this.isOpen = true
        },
        closeRecycleBinDrawer() {
            this.isOpen = false
        },
    },
})

import {defineStore} from 'pinia'

/**
 * @description 热门卡片状态
 * @author sinvon
 * @since 2024年12月5日23:04:29
 * @file hotCard.js
 */
export const useHotCardStore = defineStore('hotCard', {
    state: () => ({
        isOpen: false,
    }),
    actions: {
        toggleHotCardOpenState() {
            this.isOpen = !this.isOpen
        },
        openHotCard() {
            this.isOpen = true
        },
        closeHotCard() {
            this.isOpen = false
        },
    },
})

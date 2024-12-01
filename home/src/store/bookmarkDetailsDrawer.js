import {defineStore} from 'pinia'

/**
 * @typedef {import('pinia').StoreDefinition} StoreDefinition
 * @description 书签详情抽屉
 * @type {StoreDefinition<"bookmarkDetailsDrawer", {isOpen: boolean}, {}, {openDrawer(): void, toggleDrawer(): void, closeDrawer(): void}>}
 */
export const useBookmarkDetailsDrawerStore = defineStore('bookmarkDetailsDrawer', {
    state: () => ({
        isOpen: false,
    }),
    actions: {
        toggleDrawer() {
            this.isOpen = !this.isOpen
        },
        openDrawer() {
            this.isOpen = true
        },
        closeDrawer() {
            this.isOpen = false
        },
    },
})

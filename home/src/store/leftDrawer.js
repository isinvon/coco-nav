import { defineStore } from 'pinia'

export const useLeftDrawerStore = defineStore('leftDrawer', {
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

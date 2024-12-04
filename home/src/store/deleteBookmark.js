import {defineStore} from 'pinia'

export const useDeleteBookmarkStore = defineStore('deleteBookmark', {
    state: () => ({
        isDeleting: false,              // 当前是否处于删除状态
        selectedBookmarks: [],          // 当前选中的书签（批量删除用）
        deletedBookmarks: [],          // 存储已删除的书签
        isConfirmingDelete: false,      // 是否弹出删除确认框
        recentlyDeleted: null,          // 最近删除的书签，用于撤回
    }),

    actions: {
        // 关闭删除状态
        closeDeleteState() {
            this.isDeleting = false
        },

        // 开启删除状态
        openDeleteState() {
            this.isDeleting = true
        },

        // 添加书签到已删除列表
        deleteBookmark(bookmark) {
            if (this.isConfirmingDelete) {
                // 如果需要确认删除，先弹出确认框
                // 弹出确认框的逻辑
                return
            }

            this.deletedBookmarks.push(bookmark) // 执行删除操作
            this.recentlyDeleted = bookmark      // 保存最近删除的书签
        },

        // 批量删除书签
        bulkDelete() {
            this.isDeleting = true
            // 执行批量删除操作
            this.deletedBookmarks.push(...this.selectedBookmarks)
            this.selectedBookmarks = []  // 清空选中的书签
            this.isDeleting = false
        },

        // 撤回删除
        undoDelete() {
            if (this.recentlyDeleted) {
                this.deletedBookmarks = this.deletedBookmarks.filter(b => b !== this.recentlyDeleted)
                this.recentlyDeleted = null  // 清空撤回的数据
            }
        },

        // 用户确认删除
        confirmDelete() {
            this.isConfirmingDelete = true
            // 用户确认后调用 deleteBookmark
            // 取消时调用 cancelDelete
        },

        // 取消删除操作
        cancelDelete() {
            this.isConfirmingDelete = false
        },

        // 从垃圾桶找回书签
        recoverBookmark(bookmark) {
            this.deletedBookmarks = this.deletedBookmarks.filter(b => b !== bookmark)
            // 重新将书签恢复到原位置或其他合适的地方
        },
    },

    getters: {
        // 获取所有已删除的书签
        getDeletedBookmarks: (state) => state.deletedBookmarks,

        // 获取是否有待恢复的书签
        hasDeletedBookmarks: (state) => state.deletedBookmarks.length > 0,
    }
})

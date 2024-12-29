// 完善撤销删除的功能
// useDeleteBookmarkStore.js

import {defineStore} from 'pinia';

export const useDeleteBookmarkStore = defineStore('deleteBookmark', {
    state: () => ({
        isDeleting: false,               // 当前是否处于删除状态
        isMultipleChoiceDelete: false,   // 当前是否处于多选删除状态
        selectedBookmarks: [],           // 当前选中的书签（批量删除用）
        deletedBookmarks: [],           // 存储已删除的书签
        isConfirmingDelete: false,       // 是否弹出删除确认框
        recentlyDeleted: null,           // 最近删除的书签，用于撤回
        previousSelectedBookmarks: [],  // 保存撤销时选中的书签
    }),

    actions: {
        // 关闭删除状态
        closeDeleteState() {
            this.isDeleting = false;
        },

        // 开启删除状态
        openDeleteState() {
            this.isDeleting = true;
        },

        // 切换删除状态
        toggleDeleteState() {
            this.isDeleting = !this.isDeleting;
        },

        // 开启多选删除状态
        openMultipleChoiceDelete() {
            this.isMultipleChoiceDelete = true;
            this.isDeleting = true;
        },

        // 关闭多选删除状态
        closeMultipleChoiceDelete() {
            this.isDeleting = false;
            this.isMultipleChoiceDelete = false;
        },

        // 关闭删除状态和多选删除状态
        closeDeleteAndMultipleDelete() {
            this.closeDeleteState();
            this.closeMultipleChoiceDelete();
        },

        // 批量删除书签
        bulkDeleteBookmarks() {
            // 保存当前选中的书签状态
            this.previousSelectedBookmarks = [...this.selectedBookmarks];
            this.deletedBookmarks.push(...this.selectedBookmarks);
            this.recentlyDeleted = this.selectedBookmarks;
            this.selectedBookmarks = [];
        },

        // 撤销最近一次删除
        undo() {
            if (this.recentlyDeleted) {
                // 恢复最近删除的书签
                this.selectedBookmarks.push(...this.recentlyDeleted);
                this.recentlyDeleted = null;
                // 移除已删除的书签
                this.deletedBookmarks = this.deletedBookmarks.filter(
                    (bookmark) => !this.recentlyDeleted.includes(bookmark)
                );
            }

            // 恢复之前的选中状态
            if (this.previousSelectedBookmarks.length > 0) {
                this.selectedBookmarks = [...this.previousSelectedBookmarks];
                this.previousSelectedBookmarks = [];
            }
        },
    },
});


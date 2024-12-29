<script setup>
/**
 * @author sinvon
 * @since 2024年12月3日11:16:20
 * @description 删除书签菜单 - 环绕动画组件
 * @module DeleteMenu
 *
 */
import Tooltip from '@/components/Tooltip/index.vue'
import {useDeleteBookmarkStore} from "@/store/deleteBookmark";
import {useRecycleBinDrawerStore} from '@/store/recycleBinDrawer'
import {ref} from "vue";

// 判断菜单是否是上一次收缩回去之后重新计时的第一次点击
const isFirstClickMenu = ref(false)

const recycleBinDrawerStore = useRecycleBinDrawerStore()
const deleteBookmarkStore = useDeleteBookmarkStore()

// 取消按钮点击时清空选中的书签
const clearSelectedBookmarks = () => {
  deleteBookmarkStore.selectedBookmarks = []  // 清空选中的书签
  deleteBookmarkStore.closeDeleteAndMultipleDelete()  // 关闭删除模式和多选删除模式
}

// 撤销操作
const undo = () => {
  deleteBookmarkStore.undo();
};

// 菜单按钮的操作逻辑
const menuOperationLogic = () => {
  // 将style='top=xxx'挂载到 .wrapper 上，用于控制动画效果
  if (!isFirstClickMenu.value) {
    document.querySelector('.wrapper').style.top = "5%"
    isFirstClickMenu.value = true
    // 第一次点击的时候显示删除模式
    deleteBookmarkStore.openDeleteState()
  }else {
    document.querySelector('.wrapper').style.top = "-2%"
    isFirstClickMenu.value = false
    deleteBookmarkStore.closeDeleteAndMultipleDelete()
  }
}
</script>

<template>
  <transition name="delete-menu">
    <div class="wrapper">
      <input class="hidden-trigger" id="toogle" type="checkbox">
      <Tooltip tooltipText="菜单" placement="bottom" effect="dark">
        <label @click="menuOperationLogic" class="circle" for="toogle">
          <svg xmlns="http://www.w3.org/2000/svg" style="color: #e8e8e8;width: 80px;height: 80px;" xml:space="preserve" viewBox="-6 -6 48 48"><path fill="currentColor" d="M10 20q-.825 0-1.412-.587T8 18t.588-1.412T10 16h10q.825 0 1.413.588T22 18t-.587 1.413T20 20zm0-6q-.825 0-1.412-.587T8 12t.588-1.412T10 10h10q.825 0 1.413.588T22 12t-.587 1.413T20 14zm0-6q-.825 0-1.412-.587T8 6t.588-1.412T10 4h10q.825 0 1.413.588T22 6t-.587 1.413T20 8zM4 8q-.825 0-1.412-.587T2 6t.588-1.412T4 4t1.413.588T6 6t-.587 1.413T4 8m0 6q-.825 0-1.412-.587T2 12t.588-1.412T4 10t1.413.588T6 12t-.587 1.413T4 14m0 6q-.825 0-1.412-.587T2 18t.588-1.412T4 16t1.413.588T6 18t-.587 1.413T4 20"/></svg>
        </label>
      </Tooltip>
      <div class="subs">
        <button class="sub-circle">
          <input class="hidden-sub-trigger" id="sub1" type="radio" name="sub-circle" value="1">
          <label for="sub1">1</label>
        </button>
        <button class="sub-circle">
          <input class="hidden-sub-trigger" id="sub2" type="radio" name="sub-circle" value="1">
          <label for="sub2">2</label>
        </button>
        <Tooltip tooltipText="回收站" placement="bottom" effect="dark">
          <button @click="recycleBinDrawerStore.openRecycleBinDrawer()" class="sub-circle">
            <input class="hidden-sub-trigger" id="sub3" type="radio" name="sub-circle" value="1">
            <label for="sub3">
              <!--回收站-->
              <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24"><path fill="currentColor" d="M8.55 1.443A1.49 1.49 0 0 0 6 2.49V5.5H4.624a.75.75 0 0 0-.746.83L5.36 19.994A2.25 2.25 0 0 0 7.596 22h8.808a2.25 2.25 0 0 0 2.237-2.008L20.118 6.33a.75.75 0 0 0-.745-.831H18V2.75A1.75 1.75 0 0 0 16.25 1h-3.5A1.75 1.75 0 0 0 11 2.75v1.17zM16.5 2.75V5.5h-4V2.75a.25.25 0 0 1 .25-.25h3.5a.25.25 0 0 1 .25.25M10.453 5.5H7.5V2.514zm1.34 4.91l-.67 1a.75.75 0 1 1-1.246-.835l.669-1a1.75 1.75 0 0 1 2.909 0l.669 1a.75.75 0 0 1-1.247.834l-.669-1a.25.25 0 0 0-.415 0m-1.9 3.218l-.364.607a.5.5 0 0 0 .43.757h.791a.75.75 0 0 1 0 1.5h-.792c-1.554 0-2.515-1.696-1.715-3.029l.364-.607a.75.75 0 1 1 1.287.772m4.474-1.03a.75.75 0 0 1 1.03.259l.363.607c.799 1.333-.162 3.028-1.716 3.028h-.794a.75.75 0 0 1 0-1.5h.794a.5.5 0 0 0 .43-.757l-.365-.607a.75.75 0 0 1 .258-1.03"/></svg>
            </label>
          </button>
        </Tooltip>
        <Tooltip tooltipText="撤销" placement="bottom" effect="dark">
          <button @click="undo" class="sub-circle">
            <input class="hidden-sub-trigger" id="sub4" type="radio" name="sub-circle" value="1">
            <label for="sub4">
              <!--撤销-->
              <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24"><g fill="none" fill-rule="evenodd"><path d="M24 0v24H0V0zM12.593 23.258l-.011.002l-.071.035l-.02.004l-.014-.004l-.071-.035q-.016-.005-.024.005l-.004.01l-.017.428l.005.02l.01.013l.104.074l.015.004l.012-.004l.104-.074l.012-.016l.004-.017l-.017-.427q-.004-.016-.017-.018m.265-.113l-.013.002l-.185.093l-.01.01l-.003.011l.018.43l.005.012l.008.007l.201.093q.019.005.029-.008l.004-.014l-.034-.614q-.005-.019-.02-.022m-.715.002a.02.02 0 0 0-.027.006l-.006.014l-.034.614q.001.018.017.024l.015-.002l.201-.093l.01-.008l.004-.011l.017-.43l-.003-.012l-.01-.01z"/><path fill="currentColor" d="M2.614 5.426A1.5 1.5 0 0 1 4 4.5h10a7.5 7.5 0 1 1 0 15H5a1.5 1.5 0 0 1 0-3h9a4.5 4.5 0 1 0 0-9H7.621l.94.94a1.5 1.5 0 0 1-2.122 2.12l-3.5-3.5a1.5 1.5 0 0 1-.325-1.634"/></g></svg>
            </label>
          </button>
        </Tooltip>
        <Tooltip tooltipText="删除" placement="bottom" effect="dark">
          <button class="sub-circle">
            <input class="hidden-sub-trigger" id="sub5" type="radio" name="sub-circle" value="1">
            <label for="sub5">
              <!--删除-->
              <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24"><path fill="currentColor" d="M7 21q-.825 0-1.412-.587T5 19V6q-.425 0-.712-.288T4 5t.288-.712T5 4h4q0-.425.288-.712T10 3h4q.425 0 .713.288T15 4h4q.425 0 .713.288T20 5t-.288.713T19 6v13q0 .825-.587 1.413T17 21zm3-4q.425 0 .713-.288T11 16V9q0-.425-.288-.712T10 8t-.712.288T9 9v7q0 .425.288.713T10 17m4 0q.425 0 .713-.288T15 16V9q0-.425-.288-.712T14 8t-.712.288T13 9v7q0 .425.288.713T14 17"/></svg>
            </label>
          </button>
        </Tooltip>
        <Tooltip tooltipText="取消" placement="bottom" effect="dark">
          <button @click="clearSelectedBookmarks" class="sub-circle">
            <input class="hidden-sub-trigger" id="sub6" type="radio" name="sub-circle" value="1">
              <label for="sub6">
                <!--取消-->
                  <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24"><path fill="currentColor" fill-rule="evenodd" d="M8.4 2h11.2A2.4 2.4 0 0 1 22 4.4v11.2a2.4 2.4 0 0 1-2.4 2.4H8.4A2.4 2.4 0 0 1 6 15.6V4.4A2.4 2.4 0 0 1 8.4 2m3.479 4.464a1 1 0 1 0-1.415 1.415L12.587 10l-2.122 2.121a1 1 0 0 0 1.415 1.415L14 11.414l2.121 2.122a1 1 0 1 0 1.415-1.415L15.414 10l2.122-2.121a1 1 0 0 0-1.415-1.415l-2.12 2.122z" clip-rule="evenodd"/><path fill="currentColor" d="M4 4a1 1 0 0 0-2 0v11.6C2 19.132 4.868 22 8.4 22H20a1 1 0 1 0 0-2H8.4A4.403 4.403 0 0 1 4 15.6z"/></svg>
              </label>
          </button>
        </Tooltip>
        <Tooltip tooltipText="批量选择" placement="bottom" effect="dark">
          <button @click="deleteBookmarkStore.openMultipleChoiceDelete()" class="sub-circle">
            <input class="hidden-sub-trigger" id="sub7" type="radio" name="sub-circle" value="1">
            <label for="sub7">
              <!--批量选择-->
              <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24"><path fill="currentColor" d="M14 2a8 8 0 0 0-8 8a8 8 0 0 0 8 8a8 8 0 0 0 8-8a8 8 0 0 0-8-8M4.93 5.82A8.01 8.01 0 0 0 2 12a8 8 0 0 0 8 8c.64 0 1.27-.08 1.88-.23c-1.76-.39-3.38-1.27-4.71-2.48A6 6 0 0 1 4 12c0-.3.03-.59.07-.89C4.03 10.74 4 10.37 4 10c0-1.44.32-2.87.93-4.18m13.16.26L19.5 7.5L13 14l-3.79-3.79l1.42-1.42L13 11.17"/></svg>
            </label>
          </button>
        </Tooltip>
        <button class="sub-circle">
          <input class="hidden-sub-trigger" id="sub8" type="radio" name="sub-circle" value="1">
          <label for="sub8">8</label>
        </button>
      </div>
    </div>
  </transition>
</template>

<style scoped lang="less">
@import url("./index");
</style>
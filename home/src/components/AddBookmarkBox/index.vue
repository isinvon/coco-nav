<script setup>
/**
 * 添加书签框
 * @author sinvon
 * @since 2024年12月2日16:51:36
 * @see el-dialog 组件参考 https://element-plus.org/zh-CN/component/dialog.html
 * @see 文件上传类型 accept 属性参考 https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/file
 */
import {reactive, ref} from 'vue'
import {useAddBookmarkBoxStore} from '@/store/addBookmarkBox'
import {showNotification} from "@/utils/notification";
import MarkdownUpload from '@/components/AddBookmarkBox/MarkdownUpload/index.vue'
import TagInput from '@/components/AddBookmarkBox/TagInput/index.vue'
import BookmarkCategory from '@/components/AddBookmarkBox/BookmarkCategory/index.vue'

const bookmarkTitle = ref('')
const bookmarkUrl = ref('')
const category = ref('');
const bookmarkDescription = ref('') // 书签描述
const tagsStr = ref('') // 标签
const tags = ref([]) // 标签输入框内容
const fileInfo = ref([]) // 文件上传信息

const addBookmarkBoxStore = useAddBookmarkBoxStore()

const closeAddBookmarkBox = () => {
  addBookmarkBoxStore.closeBox()
}

// 提交书签
const addBookmark = () => {
  // URL验证
  const urlPattern = /^(https?:\/\/)?([a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}([a-zA-Z0-9-._~:/?#[\]@!$&'()*+,;=]*)$/;
  if (!bookmarkTitle.value || !bookmarkUrl.value || !urlPattern.test(bookmarkUrl.value)) {
    showNotification('添加书签失败', '请填写有效的书签标题和URL', 'error', 'top-right', 300, 11, 2000)
    return
  }

  // 处理书签添加逻辑（保存到本地存储）
  const bookmark = {
    title: bookmarkTitle.value,
    url: bookmarkUrl.value,
    category: category.value,
    description: bookmarkDescription.value,
    tags: tags.value,
    fileInfo: fileInfo.value
  }
  localStorage.setItem(`bookmark-${bookmark.title}`, JSON.stringify(bookmark))

  console.log('添加书签:', bookmark)
  closeAddBookmarkBox() // 关闭框

  // 提交后清除输入框内容
  bookmarkTitle.value = ''
  bookmarkUrl.value = ''
  category.value = ''
  bookmarkDescription.value = ''
  tags.value = []
  fileInfo.value = []
}

// 处理TagInput组件传递的标签数组
const handleUpdateTags = (newTags) => {
  tags.value = newTags
}


const handleFileUploaded = (newfileInfo) => {
  // 处理接收到的文件数据，比如存储、显示等
  // console.log("从子组件接收到文件信息：", newfileInfo);
  fileInfo.value = newfileInfo
};

// 表单标签宽度 (表单内元素距离容器左边的距离)
const formLabelWidth = '100px'

const form = reactive({
  name: '',
  region: '',
  date1: '',
  date2: '',
  delivery: false,
  type: [],
  resource: '',
  desc: '',
})

// 自动填充书签标题（基于URL）
// watchEffect(() => {
//   if (bookmarkUrl.value && !bookmarkTitle.value) {
//     const urlParts = bookmarkUrl.value.split('/');
//     bookmarkTitle.value = urlParts[urlParts.length - 1]; // 自动使用URL的最后一部分作为书签标题
//   }
// })
</script>

<template>
  <div class="add-bookmark-box-container">
    <el-dialog
        overflow
        draggable
        :lock-scroll="false"
        v-model="addBookmarkBoxStore.isOpen"
        title="✨ 添加书签" width="600px"
        class="add-bookmark-box"
    >
      <el-form :model="form">
        <!-- 书签标题 -->
        <el-form-item label="书签标题" :label-width="formLabelWidth">
          <el-input class="bookmark-title" v-model="bookmarkTitle" placeholder="请输入书签标题" autocomplete="off"
                    clearable/>
        </el-form-item>

        <!-- 书签URL -->
        <el-form-item label="书签链接" :label-width="formLabelWidth">
          <el-input class="bookmark-url" v-model="bookmarkUrl" placeholder="请输入书签URL" autocomplete="off"
                    clearable/>
        </el-form-item>

        <!-- 书签类别 -->
        <el-form-item label="书签类别" :label-width="formLabelWidth">
          <BookmarkCategory class="bookmark-category" v-model="category" style="width: 50%"/>
        </el-form-item>

        <!-- 书签描述 -->
        <el-form-item label="书签描述" :label-width="formLabelWidth">
          <el-input class="bookmark-description" type="textarea" v-model="bookmarkDescription"
                    placeholder="请输入书签描述" rows="4" clearable/>
        </el-form-item>

        <!-- 书签标签 -->
        <el-form-item label="🔖 标签" :label-width="formLabelWidth">
          <TagInput class="bookmark-tag-input" v-model="tagsStr" @updateTags="handleUpdateTags" style="width: 100%"/>
        </el-form-item>

        <!-- Markdown上传 -->
        <el-form-item label="长文描述" :label-width="formLabelWidth">
          <MarkdownUpload class="bookmark-markdown-upload" @fileUploaded="handleFileUploaded" style="width: 100%"/>
        </el-form-item>
      </el-form>

      <template #footer>
        <div class="dialog-footer">
          <el-button @click="closeAddBookmarkBox" class="btn-close">取消</el-button>
          <el-button type="primary" @click="addBookmark" class="btn-confirm">确认</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped lang="less">
.add-bookmark-box {
  .el-form-item {
    margin-bottom: 20px;
  }

  .el-input, .el-select {
    border-radius: 6px; /* GitHub圆角 */

    .el-input__inner {
      background-color: #ffffff;
      border-color: #d1d9e0; /* GitHub边框色 */
      color: #24292f; /* 字体黑色 */

      &:focus {
        border-color: #0969da; /* GitHub链接蓝 */
      }
    }
  }

  .el-button {
    border-radius: 6px; /* GitHub圆角 */
    background-color: #ffffff;
    color: #24292f;
    border: 1px solid #d1d9e0; /* GitHub边框色 */
  }

  .dialog-footer {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-left: 80px;
    padding-right: 80px;
    padding-bottom: 10px;
  }

  .btn-close {
    color: #24292f; /* 默认字体为黑色 */
    background-color: #ffffff; /* 默认背景为白色 */
    border-color: #d1d9e0; /* 边框颜色 */
  }

  .btn-close:hover {
    color: #24292f; /* 鼠标悬浮时字体保持黑色 */
    background-color: #f3f3f3; /* 鼠标悬浮时背景变为 #eaedf0 */
  }

  .btn-confirm {
    background-color: #33a453;
    color: #fff;
  }

  .btn-confirm:hover {
    background-color: #1c8139;
  }

}
</style>

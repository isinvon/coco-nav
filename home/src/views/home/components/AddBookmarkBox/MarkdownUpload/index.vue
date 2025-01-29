<script setup>
/**
 * @description Markdown文件上传组件
 * @author sinvon
 * @since 2024年12月2日20:55:32
 */
import {ref} from "vue";
import {showNotification} from "@/utils/notification.js";
import {Delete} from "@element-plus/icons-vue";
// 定义自定义事件，用于传递数据给父组件
const emit = defineEmits(["fileUploaded"]);

// 是否已上传Markdown文件
const isMarkdownUploaded = ref(false);
// 上传提示信息
const uploadMessage = ref("拖拽文件到此处，或点击上传Markdown文件");

// 管理上传文件列表
const fileList = ref([]);

// 处理文件上传
const handleFileUpload = (file) => {
  if (file && file.raw) {
    const fileInfo = {
      name: file.name,
      size: file.size,
      lastModified: file.raw.lastModified, // 时间戳
      lastModifiedDate: new Date(file.raw.lastModifiedDate), // 标准国际日期
      type: file.raw.type,
      status: file.status, // 文件状态
      uid: file.uid,
      percentage: file.percentage, // 0 就是 ready 状态
    };
    // 更新文件列表
    fileList.value = [fileInfo]; // 用新文件替换旧文件
    // 通知父组件上传的文件信息
    emit("fileUploaded", fileInfo);

    showNotification(
        "文件准备就绪",
        `${file.name} 准备就绪!`,
        "success",
        "top-right",
        300,
        11,
        5000
    );
    isMarkdownUploaded.value = true;
    uploadMessage.value = "只允许上传一个Markdown文件";
  } else {
    showNotification(
        "文件上传失败",
        "请上传有效的Markdown文件",
        "warning",
        "top-right",
        300,
        11,
        2000
    );
  }
};

// 重置文件上传状态
const resetUpload = () => {
  isMarkdownUploaded.value = false;
  uploadMessage.value = "拖拽文件到此处，或点击上传Markdown文件";
  fileList.value = []; // 清空文件列表
};
</script>

<template>
  <div class="file-upload-container">
    <el-upload
        class="upload-demo"
        drag
        action="#"
        accept=".md"
        :auto-upload="false"
        :disabled="isMarkdownUploaded"
        @change="handleFileUpload"
        :file-list="fileList"
        limit="1"
    >
      <i class="el-icon-upload"></i>
      <span class="el-upload-text">{{ uploadMessage }}</span>
    </el-upload>
    <!-- 当文件上传成功后，显示“删除”按钮,点击删除按钮后清空文件列表 -->
    <el-button class="file-delete" type="text" v-if="isMarkdownUploaded" @click="resetUpload">
      <Delete style="width: 1em; height: 1em; margin-right: 8px; color: red"/>
    </el-button>
  </div>
</template>

<style scoped lang="less">
.file-upload-container {
  display: flex;
  align-items: center;
  justify-content: space-between;

  .upload-demo {
    max-width: 420px;
    min-width: 420px;
  }

  // 为了让删除按钮能够出现在文件列表的右边对其
  .file-delete {
    margin-top: 120px;
    margin-left: 2px;
  }
}

/* 样式部分 */
</style>


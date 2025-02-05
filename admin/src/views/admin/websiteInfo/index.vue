<template>
  <div class="app-container">
    <div v-loading="loading">
      <div v-for="item in websiteInfoList" :key="item.id" class="record-item" :class="{ editing: isEditing(item) }">
        <el-form :model="isEditing(item) ? editingRow : item" label-position="top" size="small" class="record-form">
          <el-form-item label="网站标题">
            <template v-if="isEditing(item)">
              <el-input v-model="editingRow.title" placeholder="请输入网站标题" class="custom-input" />
            </template>
            <template v-else>
              <span class="value">{{ item.title }}</span>
            </template>
          </el-form-item>
          <el-form-item label="SEO关键词">
            <template v-if="isEditing(item)">
              <el-input v-model="editingRow.keywords" placeholder="请输入SEO关键词" class="custom-input" />
            </template>
            <template v-else>
              <span class="value">{{ item.keywords }}</span>
            </template>
          </el-form-item>
          <el-form-item label="网站描述">
            <template v-if="isEditing(item)">
              <el-input type="textarea" v-model="editingRow.description" placeholder="请输入网站描述" class="custom-input" />
            </template>
            <template v-else>
              <span class="value">{{ item.description }}</span>
            </template>
          </el-form-item>
          <el-form-item label="备案号">
            <template v-if="isEditing(item)">
              <el-input v-model="editingRow.icp" placeholder="请输入备案号" class="custom-input" />
            </template>
            <template v-else>
              <span class="value">{{ item.icp }}</span>
            </template>
          </el-form-item>
          <el-form-item label="系统版本">
            <template v-if="isEditing(item)">
              <el-input v-model="editingRow.version" placeholder="请输入系统版本" class="custom-input" />
            </template>
            <template v-else>
              <span class="value">{{ item.version }}</span>
            </template>
          </el-form-item>
        </el-form>

        <div class="record-actions">
          <template v-if="isEditing(item)">
            <el-button size="small" type="primary" icon="Check" @click="save(item)" class="custom-btn">保存</el-button>
            <el-button size="small" icon="Close" @click="cancelEdit" class="custom-btn">取消</el-button>
          </template>
          <template v-else>
            <el-button size="small" type="primary" icon="Edit" @click="startEdit(item)" class="custom-btn">修改</el-button>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup name="WebsiteInfo">
import { ref, reactive, getCurrentInstance } from "vue";
import { listWebsiteInfo, updateWebsiteInfo } from "@/api/admin/websiteInfo";

const { proxy } = getCurrentInstance();

const websiteInfoList = ref([]);
const loading = ref(true);
const editingId = ref(null);
const editingRow = reactive({
  id: null,
  title: "",
  keywords: "",
  description: "",
  icp: "",
  version: ""
});

function isEditing(item) {
  return editingId.value === item.id;
}

function getList() {
  loading.value = true;
  listWebsiteInfo().then(response => {
    websiteInfoList.value = response.rows;
    loading.value = false;
  });
}

function startEdit(item) {
  if (editingId.value !== null) return;
  Object.assign(editingRow, item);
  editingId.value = item.id;
}

function cancelEdit() {
  editingId.value = null;
}

function save(item) {
  if (!editingRow.title || !editingRow.version) {
    proxy.$modal.msgError("网站标题和系统版本不能为空");
    return;
  }
  updateWebsiteInfo(editingRow).then(() => {
    proxy.$modal.msgSuccess("修改成功");
    editingId.value = null;
    getList();
  });
}

getList();
</script>

<style scoped>
.app-container {
  padding: 20px;
  max-width: 600px;
  margin: auto;
}

.record-item {
  background: #ffffff;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  transition: all 0.3s ease-in-out;
  border-left: 5px solid #409eff;
}

.record-item.editing {
  border-left: 5px solid #ff9800;
  box-shadow: 0 4px 15px rgba(255, 152, 0, 0.3);
}

.record-form {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.el-form-item {
  margin-bottom: 12px;
}

.value {
  display: block;
  padding: 10px;
  background: #f5f7fa;
  border-radius: 6px;
  font-size: 14px;
  color: #606266;
  transition: all 0.3s ease;
}

.record-actions {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  margin-top: 15px;
}

/* 自定义输入框 */
.custom-input {
  border-radius: 8px;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.custom-input:focus {
  border-color: #409eff !important;
  box-shadow: 0 0 8px rgba(64, 158, 255, 0.4);
}

/* 自定义按钮 */
.custom-btn {
  border-radius: 6px;
  transition: all 0.3s ease;
}

.custom-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
}
</style>

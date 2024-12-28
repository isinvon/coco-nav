<template>
  <el-dialog v-model="showModal" title="Settings">
    <div>
      <h4>Theme</h4>
      <label>
        <input type="radio" name="theme" value="light" v-model="selectedTheme"/> Light Mode
      </label>
      <label>
        <input type="radio" name="theme" value="dark" v-model="selectedTheme"/> Dark Mode
      </label>
    </div>
    <div>
      <h4>Language</h4>
      <select v-model="selectedLanguage">
        <option value="en">English</option>
        <option value="zh">中文</option>
        <option value="fr">Français</option>
        <option value="es">Español</option>
      </select>
    </div>
    <div>
      <h4>Notifications</h4>
      <label>
        <input type="checkbox" v-model="enableNotifications"/> Enable Notifications
      </label>
    </div>
    <div>
      <h4>Cloud Sync</h4>
      <label>
        <input type="checkbox" v-model="enableSync"/> Enable Cloud Sync
      </label>
    </div>
    <div>
      <h4>Privacy</h4>
      <label>
        <input type="checkbox" v-model="shareData"/> Share Data with Third Parties
      </label>
    </div>
    <div>
      <h4>Help & Support</h4>
      <a href="#" @click.prevent="openHelp">Help Documentation</a><br/>
      <a href="#" @click.prevent="contactSupport">Contact Support</a>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="closeDialog">Cancel</el-button>
        <el-button type="primary" @click="saveSettings">Save</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="js" setup>
import {ref, watch} from 'vue';

const props = defineProps({
  modelValue: Boolean
});

const emit = defineEmits(['update:modelValue']);

const showModal = ref(props.modelValue);

watch(showModal, (newValue) => {
  emit('update:modelValue', newValue);
});

const selectedTheme = ref('light');
const selectedLanguage = ref('en');
const enableNotifications = ref(false);
const enableSync = ref(false);
const shareData = ref(false);

const saveSettings = () => {
  console.log('Settings saved:', {
    theme: selectedTheme.value,
    language: selectedLanguage.value,
    notifications: enableNotifications.value,
    sync: enableSync.value,
    shareData: shareData.value
  });
  closeModal();
};

const openHelp = () => {
  window.open('https://example.com/help', '_blank');
};

const contactSupport = () => {
  window.open('mailto:support@example.com?subject=Support%20Request', '_blank');
};

const closeDialog = () => {
  showModal.value = false;
};
</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}

label {
  display: block;
  margin-top: 5px;
}

select {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
}

a {
  color: #409EFF;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}
</style>
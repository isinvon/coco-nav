// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import home from "@/views/home.vue";

const routes = [
  {
    path: '/',
    name: 'home',
    component: home,
  },
  {
    path: '/about',
    name: 'about',
    components: {
      default: () => import('@/views/about.vue'),
    }
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes, // 路由配置
});

export default router;

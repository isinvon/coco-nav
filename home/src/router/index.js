// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Home from "@/views/Home.vue";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  // {
  //   path: '/about',
  //   name: 'About',
  //   component: About,
  // },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes, // 路由配置
});

export default router;

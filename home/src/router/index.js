// src/router/index.js
import {createRouter, createWebHistory} from 'vue-router';
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
    {
        path: '/archive',
        name: 'archive',
        components: {
            default: () => import('@/views/archive.vue'),
        }
    },
    {
        path: '/friendlink',
        name: 'friendlink',
        components: {
            default: () => import('@/views/friendlink.vue'),
        }
    },
    {
        path: '/message',
        name: 'message',
        components: {
            default: () => import('@/views/message.vue'),
        }
    },
    {
        path: '/tag',
        name: 'tag',
        components: {
            default: () => import('@/views/tag.vue'),
        }
    },
    {
        path: '/test',
        name: 'test',
        components: {
            default: () => import('@/views/test.vue'),
        }
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes, // 路由配置
});

export default router;

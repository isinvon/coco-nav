// src/router/index.js
import {createRouter, createWebHistory} from 'vue-router';
import home from "@/views/home/index.vue";
// 进度条
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'// nprogress样式文件
import '@/styles/nprogress-bar.less' // 自定义的nprogress样式文件
// 进度条配置
NProgress.configure({
    easing: 'ease',  // 动画方式
    speed: 500,  // 递增进度条的速度
    showSpinner: false, // 是否显示加载ico
    trickleSpeed: 200, // 自动递增间隔
    minimum: 0.3 // 初始化时的最小百分比
})


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
            default: () => import('@/views/about/index.vue'),
        }
    },
    {
        path: '/archive',
        name: 'archive',
        components: {
            default: () => import('@/views/archive/index.vue'),
        }
    },
    {
        path: '/friendlink',
        name: 'friendlink',
        components: {
            default: () => import('@/views/friendlink/index.vue'),
        }
    },
    {
        path: '/message',
        name: 'message',
        components: {
            default: () => import('@/views/message/index.vue'),
        }
    },
    {
        path: '/tag',
        name: 'tag',
        components: {
            default: () => import('@/views/tag/index.vue'),
        }
    },
    {
        path: '/test',
        name: 'test',
        components: {
            default: () => import('@/views/test/index.vue'),
        }
    }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes, // 路由配置
});


//当路由开始跳转时
router.beforeEach((to, from, next) => {
    // 开启进度条
    NProgress.start();
    // 这个一定要加，没有next()页面不会跳转的。这部分还不清楚的去翻一下官网就明白了
    next();
});
//当路由跳转结束后
router.afterEach(() => {
    // 关闭进度条
    NProgress.done()
})

export default router;

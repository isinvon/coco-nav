import {ref} from 'vue';
import {useRouter} from 'vue-router';

/**
 * 自定义钩子：管理加载动画的显示和隐藏
 * @author sinvon
 * @since 2025年1月1日16:09:41
 */
export function useLoading() {
    const isLoading = ref(false);
    const router = useRouter();

    // 路由切换前显示加载动画
    router.beforeEach((to, from, next) => {
        isLoading.value = true; // 启动加载动画
        next();
    });

    // 路由切换完成后隐藏加载动画
    router.afterEach(() => {
        isLoading.value = false; // 隐藏加载动画
    });

    return {isLoading};
}

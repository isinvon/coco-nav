export const headerMenuEnum = [
    {
        name: 'home',
        icon: '🏠',
        title: '首页',
        path: '/'
    },
    {
        name: 'about',
        icon: '🔍',
        title: '关于',
        path: '/about'
    },
    {
        name: 'archives',
        icon: '📚',
        title: '归档',
        path: '/archives'
    },
    {
        name: 'tag',
        icon: '🏷️',
        title: '标签',
        path: '/tag'
    },
    {
        name: 'category',
        icon: '📂',
        title: '分类',
        path: '/category'
    },
    {
        name: 'products',
        icon: '📂',
        title: '产品',
        path: '/products',
        children: [
            {name: 'Product 1', icon: '📦', title: '产品1', path: '/products/1'},
            {name: 'Product 2', icon: '📦', title: '产品2', path: '/products/2'},
        ],
    },
    {
        name: 'friendlink',
        icon: '🤝',
        title: '友链',
        path: '/friendlink'
    },
    {
        name: 'message',
        icon: '💬',
        title: '留言',
        path: '/message'
    },
    {
        name: 'search',
        icon: '🔍',
        title: '搜索',
        path: '/search'
    },
]
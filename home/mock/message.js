// 模拟评论数据
const comment = {
    status: "成功",
    code: 200,
    data: [
        {
            id: 'comment0001', // 主键id
            date: '2024-12-30 08:30',  // 评论时间
            ownerId: 'talents100020', // 文章的id
            fromId: 'errhefe232213',  // 评论者id
            fromName: '程序猿小弟',   // 评论者昵称
            fromAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg', // 评论者头像
            level: 5, // 评论者账号等级
            likeNum: 10, // 点赞人数
            content: '这篇文章的逻辑简直乱得像狗屎，根本不适合新手！',  // 评论内容
            reply: [  // 回复，或子评论
                {
                    id: '34523244545',  // 主键id
                    commentId: 'comment0001',  // 父评论id，即父亲的id
                    fromId: 'observer223432',  // 评论者id
                    fromName: '代码哲学家',  // 评论者昵称
                    fromAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', // 评论者头像
                    level: 4, // 评论者账号等级
                    toId: 'errhefe232213',  // 被评论者id
                    toName: '程序猿小弟',  // 被评论者昵称
                    toAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg',  // 被评论者头像
                    content: '你这完全是在胡说八道吧？根本不懂代码的美，连基础都没搞清楚。',  // 评论内容
                    date: '2024-12-30 08:35'   // 评论时间
                },
                {
                    id: '34523244546', // 修改 id 避免重复
                    commentId: 'comment0001',
                    fromId: 'observer567422',
                    fromName: '愤怒的小鸟',
                    fromAvatar: 'http://imgsrc.baidu.com/imgad/pic/item/c2fdfc039245d688fcba1b80aec27d1ed21b245d.jpg',
                    level: 2, // 评论者账号等级
                    toId: 'observer223432',
                    toName: '代码哲学家',
                    toAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg',
                    content: '你也就会嘴炮，做点实事看看！别整天就知道批评别人。',  // 评论内容
                    date: '2024-12-30 08:50'
                }
            ]
        },
        {
            id: 'comment0002',
            date: '2024-12-30 09:00',
            ownerId: 'talents100020',
            fromId: 'errhefe232213',
            fromName: '毒蛇郭德纲',
            fromAvatar: 'http://ww1.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2q2p8pj30v90uzmzz.jpg',
            level: 4, // 评论者账号等级
            likeNum: 5,
            content: '文章写得太浅薄了，根本没深入探讨问题的本质，只是表面功夫。',  // 评论内容
            reply: [
                {
                    id: '34523244547', // 主键id
                    commentId: 'comment0002',
                    fromId: 'observer112233',
                    fromName: '硬核程序员',
                    fromAvatar: 'http://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg',
                    level: 3, // 评论者账号等级
                    toId: 'errhefe232213',
                    toName: '毒蛇郭德纲',
                    toAvatar: 'http://ww1.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2q2p8pj30v90uzmzz.jpg',
                    content: '你不觉得自己在说废话吗？文章是给入门的看的，怎么可能深奥？',  // 评论内容
                    date: '2024-12-30 09:10'
                }
            ]
        }
    ]
};

export { comment };
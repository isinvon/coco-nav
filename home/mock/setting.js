// 网站设置模拟数据
const setting = {
    status: "成功",
    code: 200,
    data: {
        // 留言区
        message: {
            // 评论区
            comment: {
                // 是否显示等级
                show_level: true,
            }
        },
        // 归档
        archives: {
            // 模板(归档的几种显示样式,例如块状,条状)
            template: 2
        },
        // 标签
        tag: {
            // 模板(标签的几种显示样式)
            template: 5
        },
        // markdown设置
        markdown: {
            // 模板(markdown的几种显示样式)
            template: 3
        }
    }
};

export {setting}

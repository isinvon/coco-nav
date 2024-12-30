// 归档
const archiveData = {
    status: "成功",
    code: 200,
    data: {
        header: {
            welcome: "欢迎使用我们的书签导航工具！",
            intro: "为您提供简洁高效的书签管理体验，帮助您节省宝贵的时间。"
        },
        introduction: `本书签导航工具旨在为用户提供一个简洁、易用的书签管理平台，帮助用户快速访问自己常用的网站，支持跨平台使用。我们致力于提供直观的界面和高效的功能，让每个用户都能轻松管理他们的互联网书签。
该项目使用了现代化的技术栈，结合了前端和后端技术的最新发展，旨在提供极致的用户体验与稳定性。无论是在浏览器内使用，还是在桌面端通过 pywebview 提供界面，您都能享受到一致的流畅体验。`,
        features: [
            "快速添加、编辑和删除书签",
            "支持书签分类管理，用户可根据兴趣和需求分组书签",
            "支持书签的搜索和过滤，帮助用户快速找到目标网站",
            "全平台支持，包括 Windows、MacOS、Linux、Android 和桌面端",
            "书签同步功能，确保您在不同设备间的书签一致性",
            "书签导入导出，便捷的备份与恢复",
            "自定义书签封面，提升视觉体验",
            "书签数据加密存储，保护您的隐私安全"
        ],
        techStack: [
            {name: "前端", details: "Vue.js"},
            {name: "后端", details: "Spring Boot"},
            {name: "桌面端", details: "pywebview"},
            {name: "容器化", details: "Docker"},
            {name: "数据库", details: "MySQL"},
            {name: "加密", details: "AES-256"}
        ],
        team: [
            {name: "张三", role: "项目经理 / 后端开发"},
            {name: "李四", role: "前端开发"},
            {name: "王五", role: "桌面端开发（pywebview）"},
            {name: "赵六", role: "运维工程师"}
        ],
        history: [
            {year: 2022, month: 1, description: "项目启动，完成书签管理基础功能"},
            {year: 2022, month: 6, description: "实现书签分类管理和书签同步功能"},
            {year: 2023, month: 3, description: "跨平台支持，开始支持桌面端应用"},
            {year: 2023, month: 12, description: "推出书签备份与恢复功能，支持多平台部署"}
        ],
        contact: {
            github: "https://github.com/isinvon/bookmark-tool",
            email: "contact@yourdomain.com"
        },
        contributors: [
            "张三 - 提供了后端架构设计及开发",
            "李四 - 前端界面设计与开发",
            "王五 - pywebview 桌面端开发",
            "赵六 - 提供了运维与部署支持"
        ]
    }
};

export {archiveData};

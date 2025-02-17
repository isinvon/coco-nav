import os

# 源文件夹路径，包含 ServiceImpl 文件的文件夹
source_folder = r'C:\Users\sinvon\Desktop\mycode\coco-nav\api\src\main\java\com\ruoyi\project\admin\service\impl'
# 目标文件夹路径，要创建 Service 文件的文件夹
target_folder = r'C:\Users\sinvon\Desktop\mycode\coco-nav\api\src\main\java\com\ruoyi\project\admin\service'
# 代码模板
template = """package com.ruoyi.common.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.model.domain.{domain};

/**
 * @author : sinvon
 * @since :  2025/1/31 上午3:37
 */
public interface {service_name} extends IService<{domain}> {{
}}
"""

def create_service_files():
    # 确保目标文件夹存在
    if not os.path.exists(target_folder):
        os.makedirs(target_folder)

    # 遍历源文件夹下的所有文件
    for filename in os.listdir(source_folder):
        if filename.endswith("ServiceImpl.java"):
            # 提取服务名，例如从 AdvertisementServiceImpl.java 提取 Advertisement
            service_base_name = filename.replace("ServiceImpl.java", "")
            service_name = f"{service_base_name}Service"
            domain = service_base_name

            # 生成服务接口文件的路径
            target_file_path = os.path.join(target_folder, f"{service_name}.java")

            # 填充模板
            content = template.format(service_name=service_name, domain=domain)

            # 写入文件
            with open(target_file_path, 'w', encoding='utf-8') as f:
                f.write(content)

            print(f"Created {target_file_path}")


if __name__ == "__main__":
    create_service_files()
    print("All service files created successfully.")
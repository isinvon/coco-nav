import os
import re

# 源目录路径，需要替换为实际目录路径
source_directory = r'C:\Users\sinvon\Desktop\mycode\coco-nav\api\src\main\java\com\ruoyi\project\admin\service\impl'

# 替换规则的正则表达式
pattern = r'public class (\w+)ServiceImpl implements (\w+)CustomService'
replacement_template = r'public class \1ServiceImpl extends ServiceImpl<\1Mapper,\1> implements \1CustomService, \1Service'


def generate_import_statements(class_name):
    """根据类名生成导入语句"""
    import_statements = [
        f"import com.ruoyi.project.admin.domain.{class_name};",
        f"import com.ruoyi.project.admin.service.{class_name}Service;",
        f"import com.ruoyi.project.admin.mapper.{class_name}Mapper;",
        "import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;"
    ]
    return import_statements


def add_imports(content, import_statements):
    """在文件中添加导入语句"""
    # 找到 package 语句的位置
    package_match = re.search(r'package\s+[\w.]+;', content)
    if package_match:
        insert_index = package_match.end() + 1
        # 检查导入语句是否已经存在，不存在则添加
        for import_stmt in import_statements:
            if import_stmt not in content:
                content = content[:insert_index] + import_stmt + '\n' + content[insert_index:]
    return content


def modify_java_files(directory):
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.java'):
                file_path = os.path.join(root, file)
                with open(file_path, 'r', encoding='utf-8') as f:
                    content = f.read()

                # 查找匹配的类定义
                match = re.search(pattern, content)
                if match:
                    class_name = match.group(1)
                    # 生成导入语句
                    import_stmts = generate_import_statements(class_name)
                    # 添加导入语句
                    content = add_imports(content, import_stmts)
                    # 进行替换
                    replacement = replacement_template
                    new_content = re.sub(pattern, replacement, content)

                    # 将修改后的内容写回文件
                    with open(file_path, 'w', encoding='utf-8') as f:
                        f.write(new_content)
                    print(f"Modified {file_path}")


if __name__ == "__main__":
    modify_java_files(source_directory)
    print("文件修改完成。")

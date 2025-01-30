import os
import re

def modify_mapper_files(directory):
    # 遍历指定目录下的所有文件
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.java'):
                file_path = os.path.join(root, file)
                with open(file_path, 'r', encoding='utf-8') as f:
                    content = f.read()

                # 检查是否已经替换且导入了 BaseMapper
                if re.search(r'public interface \w+Mapper extends BaseMapper<\w+>', content) and \
                        "import com.baomidou.mybatisplus.core.mapper.BaseMapper;" in content:
                    continue

                # 若未导入 BaseMapper 则插入导入语句
                if "import com.baomidou.mybatisplus.core.mapper.BaseMapper;" not in content:
                    # 找到 package 语句之后插入导入语句
                    package_match = re.search(r'package\s+[\w.]+;', content)
                    if package_match:
                        insert_index = package_match.end() + 1
                        import_statement = "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n"
                        content = content[:insert_index] + import_statement + content[insert_index:]

                # 修改 Mapper 接口
                new_content = re.sub(r'public interface (\w+)Mapper', r'public interface \1Mapper extends BaseMapper<\1>',
                                     content)

                # 这里对于 Service 实现类你给出的修改规律没有变化，如果有变化可以仿照上面添加相应的替换逻辑

                # 将修改后的内容写回文件
                with open(file_path, 'w', encoding='utf-8') as f:
                    f.write(new_content)

if __name__ == "__main__":
    # 指定要处理的目录，这里可以根据实际情况修改
    target_directory = r'C:\Users\sinvon\Desktop\mycode\coco-nav\api\src\main\java\com\ruoyi\project\admin\mapper'
    modify_mapper_files(target_directory)
    print("文件修改完成。")
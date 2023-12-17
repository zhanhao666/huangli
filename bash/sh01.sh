#!/bin/bash

# 指定要搜索的文件
file="sh02.sh"

# 指定要搜索的内容
search_content="bash"

# 创建一个新的文件来保存搜索结果
output_file="output.txt"
touch $output_file

# 搜索指定内容并输出结果到控制台
grep -n -a "$search_content" $file | awk -F: '{print $1 ":" $0}' >> $output_file

# 输出搜索结果的总数
echo "Total occurrences of '$search_content': $(grep -c "$search_content" $file)"

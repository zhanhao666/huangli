#!/bin/bash

# ָ��Ҫ�������ļ�
file="sh02.sh"

# ָ��Ҫ����������
search_content="bash"

# ����һ���µ��ļ��������������
output_file="output.txt"
touch $output_file

# ����ָ�����ݲ�������������̨
grep -n -a "$search_content" $file | awk -F: '{print $1 ":" $0}' >> $output_file

# ����������������
echo "Total occurrences of '$search_content': $(grep -c "$search_content" $file)"

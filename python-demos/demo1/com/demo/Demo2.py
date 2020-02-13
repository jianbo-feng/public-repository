#!/usr/local/python
import keyword
print("关键字列表：", keyword.kwlist)

print("Hello, world")

str = 'Runoob'
print(str)          # 输出字符串
print(str[0:-1])    # 输出第一个到倒数第二个的字符串
print(str[0])       # 输出字符串第一个字符
print(str[2:5])     # 输出从第三个开始到第五个字符
print(str[2:])      # 输出从第三个开始后的所有字符
print(str * 2)      # 输出字符串2次
print(str + '你好')   # 链接字符串

print('------------------------')

print('hello \n runoob')    # 使用反斜杠(\)+n转义特殊字符
print(r'hello \n runoob')   # 在字符串前添加一个r，表示原始字符串，不会发生转义
    # 这里的 r 指 raw，即 raw string。
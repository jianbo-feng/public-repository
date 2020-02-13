'''
导入 sys 模块的 argv、path 成员
'''
from sys import argv,path
print('================python from import===================================')
print('path:', path)

counter = 100   # 整数
miles = 1000.0  # 浮点型
name = 'runoob' # 字符串
中文变量 = '你好，🇨🇳'
a, b, c, d, e = 1, 2.0, 'runoob', True, 4+3j
print(counter)
print(miles)
print(name)
print(中文变量)
print(type(a), type(b), type(c), type(d), type(e))
a = 111
print(isinstance(a, int))

# type()不会认为子类是一种父类类型。
# isinstance()会认为子类是一种父类类型

list = ['123', 'abdc', 789, 2.23, True]
tinylist = [123, 'runoob']
print(list)
print(list + tinylist)
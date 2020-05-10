import requests

def deleteAll():
    # 全删
    for i in range(0,10):
        url = "http://localhost/demo/delete"
        data = {"id":i}
        res = requests.post(url=url,data=data)

# 增
url = "http://localhost/demo/add"
data = {"name":"qyanzh", "email":"qyanzh@qq.com"}
res = requests.post(url=url,data=data)
print("insert -> " + res.text)

# 获取id
l = res.text.split(' ')
l.reverse()
id = l[0]

# 查
url = "http://localhost/demo/all"
res = requests.get(url)
print("retrive -> " + res.text)

# 改
url = "http://localhost/demo/update"
data = {"id":id, "name":"qyanzh", "email":"031702420@fzu.edu.cn"}
res = requests.post(url=url,data=data)
print("update -> " + res.text)

# 再查
url = "http://localhost/demo/all"
res = requests.get(url)
print("retrive -> " + res.text)

# 删
url = "http://localhost/demo/delete"
data = {"id":id}
res = requests.post(url=url,data=data)
print("delete -> " + res.text)

deleteAll()

# 负载均衡测试
url = 'http://localhost/demo/who'
count = {}
for i in range(0,100):
    response = requests.get(url)
    if response.text in count:
        count[response.text] += 1
    else:
        count[response.text] = 0
print("count -> ",end='')
print(count)


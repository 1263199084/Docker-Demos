import requests

url = 'http://localhost'

count = {}
for i in range(0, 100):
    response = requests.get(url)
    
    if response.text in count:
        count[response.text] += 1
    else:
        count[response.text] = 0
    print(response.text)
print(count)


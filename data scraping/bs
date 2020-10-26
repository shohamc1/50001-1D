import requests
from bs4 import BeautifulSoup
import urllib.request,urllib

def downloadHTML(url):
        fakeHeaders = {
            'User-Agent': "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; AcooBrowser; .NET CLR 1.1.4322; .NET CLR 2.0.50727)"}
        request = urllib.request.Request(url, headers=fakeHeaders)
        proxy = urllib.request.ProxyHandler({'http': 'http://121.232.148.60:9000'})
        opener = urllib.request.build_opener(proxy)
        urllib.request.install_opener(opener)
        try:
            downloadHTML = urllib.request.urlopen(url)
        except:
            print("fail")
        else:
            return downloadHTML.read()

def saveResInfo(blst,html):
    print(html)
    soup= BeautifulSoup(html,'html.parser')
    res = soup.find('h1',attrs={'class':'fn'})
    for li in soup.find('ul',attrs={'class':'dish-list'}).find_all('li'):
        description=li.find('div',attrs={'class':'dish-info'}).find('p',attrs={'class':'dish-description e-description ingredients'}).text
        menu = li.find('div',attrs={'class':'dish-info'}).find('h3',attrs={'class':'dish-name fn p-name'}).text
        blst.append([res,menu,description])

def printResInfo(blst):
    print('-'*50)
    for i in range(len(blst)):
        res=blst[i]
        print("restaurant:{}\nmenu:{}\ndescription:{}\n".format(res[0].strip(),res[1].strip(),res[2].strip()))
        print('-'*50)

if __name__ == "__main__":
    path = 'fpd.txt'
    f = open(path,'rb')
    i = f.readline()
    ls = []

    while i:
        i = str(i)
        i = i.replace("\'","$")
        i = i.replace('\"',"$")
        if (i[:23]) == 'b$<a href=$/restaurant/':
            i = i[11:]
            for charindex in range(0,len(i)):
                if i[charindex] == '$':
                    ls.append('https://www.foodpanda.sg'+i[:charindex])
                    break
        i = f.readline()
    
    binfo=[]
    
    for i in ls:
        url = i
        html = downloadHTML(url)
        saveResInfo(binfo,html)
        printResInfo(binfo)

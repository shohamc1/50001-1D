from selenium.webdriver.support import select
from selenium import webdriver
import os

print ('Started script')


dir_path = os.path.dirname(os.path.realpath(__file__))
driver = webdriver.Firefox(executable_path=(dir_path + '\\geckodriver.exe'))

# chinese
driver.get('https://www.burpple.com/categories/sg/chinese')
res_list = driver.find_elements_by_class_name('collection-feed__body')

with open(dir_path + '\\generated_data\\chinese_10.txt', 'w') as f:
	f.write(res_list[0].text)

# malay
driver.get('https://www.burpple.com/categories/sg/malay')
res_list = driver.find_elements_by_class_name('collection-feed__body')

with open(dir_path + '\\generated_data\\malay_10.txt', 'w') as f:
	f.write(res_list[0].text)

# indian
driver.get('https://www.burpple.com/categories/sg/indian')
res_list = driver.find_elements_by_class_name('collection-feed__body')

with open(dir_path + '\\generated_data\\indian_10.txt', 'w') as f:
	f.write(res_list[0].text)

# desserts
driver.get('https://www.burpple.com/categories/sg/desserts')
res_list = driver.find_elements_by_class_name('collection-feed__body')

with open(dir_path + '\\generated_data\\desserts_10.txt', 'w') as f:
	f.write(res_list[0].text)

# seafood
driver.get('https://www.burpple.com/categories/sg/seafood')
res_list = driver.find_elements_by_class_name('collection-feed__body')

with open(dir_path + '\\generated_data\\seafood_10.txt', 'w') as f:
	f.write(res_list[0].text)

# fusion
driver.get('https://www.burpple.com/categories/sg/western')
res_list = driver.find_elements_by_class_name('collection-feed__body')

with open(dir_path + '\\generated_data\\fusion_10.txt', 'w') as f:
	f.write(res_list[0].text)
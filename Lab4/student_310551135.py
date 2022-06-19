from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

# Task "a"
driver.maximize_window()
driver.get("https://www.nycu.edu.tw/")

driver.find_element(By.XPATH, value = "//ul[@id='menu-1-9942884']/li[2]/a").click()
driver.find_element(By.XPATH, value="//ul[@class='su-posts su-posts-list-loop']/li[1]/a").click()

title = driver.find_element(By.CLASS_NAME, value = "single-post-title").text
print(title)

content = driver.find_element(By.CLASS_NAME, value = "entry-content").text
print(content)

# Task "b"
driver.switch_to.new_window("tab")
driver.get("https://www.google.com")

search_bar = driver.find_element(By.CLASS_NAME, value = "gLFyf")
search_bar.send_keys("310551135")
search_bar.submit()

div = driver.find_element(By.ID, value = "rso")
print((div.find_elements(By.TAG_NAME, value = "h3"))[1].text)

driver.close()
import csv
import decimal
import mysql.connector
from datetime import datetime
import random

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="toor",
  database="laptopecommerce"
)

# Open the CSV file and read its contents
with open('Amazon_Laptop_Specs.csv', 'r') as file:
    reader = csv.reader(file)
    
    next(reader)
    # Loop through each row and insert it into the database
    for row in reader:
        if row[1] == '':
            price = 30000.0
        else:
            price = float(decimal.Decimal(row[1]))

        if row[4] == '':
            date = '2022-07-05'
        else:
            date = datetime.strptime(row[4], "%d %B %Y").strftime("%Y-%m-%d")
        cate = row[0].split(' ')[0]

        des = 'rank ' + ('unknown' if row[3] == '' else row[3]) + ' | ' + ('25.2 x 36.2 x 2 Centimeters' if row[7] == "" else row[7]) + ' | ' + ('4.3' if row[11] == "" else row[11])

        image = f"/image/{str(random.randint(0, 14)).zfill(2)}.jpg"

        cursor = mydb.cursor()
        sql = """
                INSERT INTO products (name, price, image, description, create_date, category) 
                VALUES (%s, %s, %s, %s, %s, %s)
        """
        value = (row[0], price, image, des, date, cate)
        cursor.execute(sql, value)
        mydb.commit()

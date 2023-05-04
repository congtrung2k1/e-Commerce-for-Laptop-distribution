import csv
import decimal
import mysql.connector
from datetime import datetime

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

        cursor = mydb.cursor()
        sql = """
                INSERT INTO products (name, price, image, description, create_date, category) 
                VALUES (%s, %s, %s, %s, %s, %s)
        """
        value = (row[0], price, '', row[3], date, cate)
        cursor.execute(sql, value)
        mydb.commit()

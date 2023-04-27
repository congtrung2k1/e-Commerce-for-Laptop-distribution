import csv
import mysql.connector

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
        cursor = mydb.cursor()
        sql = "INSERT INTO mytable (col1, col2, col3) VALUES (%s, %s, %s)"
        values = (row[0], row[1], row[2])
        cursor.execute(sql, values)
        mydb.commit()

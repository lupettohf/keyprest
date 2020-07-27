import mysql.connector
import string
import random

def randomSegKey():
    letters = string.ascii_uppercase
    return ''.join(random.choice(letters) for i in range(4))

def randomKey():
    return randomSegKey() +'-'+ randomSegKey() +'-'+ randomSegKey() +'-'+ randomSegKey()

def populateTable(ProductID, Quantity):
    QUERY = "INSERT INTO `keys` (`product_id`, `key`, `sold`) VALUES (%i,%s,0)"

    db = mysql.connector.connect(
        host="192.168.1.200",
        user="keyprest",
        password="keyprest",
        database="keyprest"
    )

    cursor = db.cursor()

    for i in range(0, Quantity):
        values = (ProductID, randomKey())
        cursor.execute(QUERY, values)
        db.commit()

        print("Added row:", cursor.lastrowid)

    print("end.")

populateTable(1, 90)

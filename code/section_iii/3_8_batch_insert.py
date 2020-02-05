import sqlite3

conn = sqlite3.connect('thunderbird_manufacturing.db')

new_records = [
    ("Otter Antiques", "5732 Serenity Ln", "Addison", "TX", 75031, "COMMERCIAL"),
    ("North Exa Energy", "67 Hearst Dr", "Plano", "TX", 75093, "INDUSTRIAL"),
    ("City of Plano", "239 Plano Dr", "Plano", "TX", 75093,"GOVERNMENT")
]

# Batch insert all three records 
conn.executemany(
    "INSERT INTO CUSTOMER (CUSTOMER_NAME, ADDRESS, CITY, STATE, ZIP, CATEGORY) VALUES (?,?,?,?,?,?)",
    new_records)

# Commit new records 
conn.commit()
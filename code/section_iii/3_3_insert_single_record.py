from sqlalchemy import create_engine, text

engine = create_engine('sqlite:///thunderbird_manufacturing.db')
conn = engine.connect()

# INSERT a new record 
stmt = text("INSERT INTO CUSTOMER (CUSTOMER_NAME, ADDRESS, CITY, STATE, ZIP, CATEGORY) VALUES (:customerName,:address,:city,:state,:zip,:category)")

conn.execute(stmt, customerName = "Brittania Solutions, LLC",
                       address="2374 Collie Way",
                       city="Frisco",
                       state="TX",
                       zip= 75035,
                       category="COMMERCIAL"
                       )

# Check records to see if last one inserted
for r in conn.execute(text("SELECT * FROM CUSTOMER")):
    print(r)

conn.close()
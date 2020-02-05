from sqlalchemy import create_engine, text

engine = create_engine('sqlite:///thunderbird_manufacturing.db')
conn = engine.connect()

insertStmt = text("INSERT INTO CUSTOMER (CUSTOMER_NAME, ADDRESS, CITY, STATE, ZIP, CATEGORY) VALUES (:customerName,:address,:city,:state,:zip,:category)")

insertResults = conn.execute(insertStmt, customerName ="Brittania Solutions, LLC",
                             address="2374 Collie Way",
                             city="Frisco",
                             state="TX",
                             zip= 75035,
                             category="COMMERCIAL"
                             )

print("Last inserted record has CUSTOMER_ID {0}".format(insertResults.lastrowid))

conn.close()
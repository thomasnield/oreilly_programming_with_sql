from sqlalchemy import create_engine, text

engine = create_engine('sqlite:///thunderbird_manufacturing.db')
conn = engine.connect()

stmt = text("SELECT * FROM CUSTOMER")
results = conn.execute(stmt)

for record in results:
    print(record)

# by field name
# for record in results:
#   print("{0} - {1}".format(record["CUSTOMER_ID"], record["CUSTOMER_NAME"]))
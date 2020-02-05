from sqlalchemy import create_engine, text

engine = create_engine('sqlite:///thunderbird_manufacturing.db')
conn = engine.connect()

# DELETE records
deleteStmt = text("DELETE FROM CUSTOMER WHERE CUSTOMER_ID > :id")
conn.execute(deleteStmt, id=10)
print("Records deleted")

# UPDATE records
updateStmt = text("UPDATE CUSTOMER SET STATE = lower(STATE)")
conn.execute(updateStmt)
print("Records updated, STATE made lowercase")

# SELECT records to check them 
for r in conn.execute(text("SELECT * FROM CUSTOMER")):
    print(r)

# UPDATE records, make STATE UPPERCASE again
updateStmt = text("UPDATE CUSTOMER SET STATE = upper(STATE)")
conn.execute(updateStmt)


conn.close()
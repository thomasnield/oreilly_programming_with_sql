from sqlalchemy import create_engine, text

engine = create_engine('sqlite:///thunderbird_manufacturing.db')
conn = engine.connect()

new_records = [
    ("Otter Antiques", "5732 Serenity Ln", "Addison", "TX", 75031, "COMMERCIAL"),
    ("North Exa Energy", "67 Hearst Dr", "Plano", "TX", 75093, "INDUSTRIAL"),
    ("City of Plano", "239 Plano Dr", "Plano", "TX", 75093)
]

# Start Transaction
transaction = conn.begin()

try:
    stmt = text(
        "INSERT INTO CUSTOMER (CUSTOMER_NAME, ADDRESS, CITY, STATE, ZIP, CATEGORY) VALUES (:customerName,:address,:city,:state,:zip,:category)")

    # Insert records, but they won't persist until transaction is finalized
    for new_record in new_records:
        conn.execute(stmt, customerName=new_record[0],
                     address=new_record[1],
                     city=new_record[2],
                     state=new_record[3],
                     zip=new_record[4],
                     category=new_record[5]
                     )

    # Finalize transaction
    transaction.commit()

except:
    print("Transaction failed! Rolling back")
    transaction.rollback()

finally:
    conn.close()
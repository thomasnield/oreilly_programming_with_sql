from sqlalchemy import create_engine, text

engine = create_engine('sqlite:///thunderbird_manufacturing.db')
conn = engine.connect()

stmt = text("SELECT * FROM CUSTOMER WHERE STATE = :state AND CATEGORY = :category")
results = conn.execute(stmt, state='TX', category='COMMERCIAL')

for r in results:
    print(r)
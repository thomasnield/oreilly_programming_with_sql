from sqlalchemy import create_engine, text
import pandas as pd

engine = create_engine('sqlite:///thunderbird_manufacturing.db')
conn = engine.connect()

df = pd.read_sql("SELECT * FROM CUSTOMER", conn)
print(df)


# with options 

from sqlalchemy import create_engine, text
import pandas as pd

engine = create_engine('sqlite:///thunderbird_manufacturing.db')
conn = engine.connect()

df = pd.read_sql("SELECT CUSTOMER_ORDER_ID, ORDER_DATE FROM CUSTOMER_ORDER",
                 conn,
                 coerce_float=True,
                 parse_dates=["ORDER_DATE"])
print(df)
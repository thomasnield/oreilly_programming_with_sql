from sqlalchemy import create_engine, text
import pandas as pd

engine = create_engine('sqlite:///thunderbird_manufacturing.db')
conn = engine.connect()


df = pd.DataFrame(data=[
    ("Otter Antiques", "5732 Serenity Ln", "Addison", "TX", 75031, "COMMERCIAL"),
    ("North Exa Energy", "67 Hearst Dr", "Plano", "TX", 75093, "INDUSTRIAL"),
    ("City of Plano", "239 Plano Dr", "Plano", "TX", 75093,"GOVERNMENT")
    ],
    columns=["CUSTOMER_NAME","ADDRESS","CITY","STATE","ZIP","CATEGORY"]
)

df.to_sql("CUSTOMER",
          conn,
          if_exists="append",
          index=False)

library(DBI)
library(RSQLite)

db <- dbConnect(SQLite(), dbname='thunderbird_manufacturing.db')

new_record = c("Brittania Solutions, LLC", "2374 Collie Way","Frisco","TX",75035,"COMMERCIAL")

my_insert <- "INSERT INTO CUSTOMER (CUSTOMER_NAME, ADDRESS, CITY, STATE, ZIP, CATEGORY) VALUES (?,?,?,?,?,?)"
dbExecute(db, my_insert, params= new_record)


dbDisconnect(db)


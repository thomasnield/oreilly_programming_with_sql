library(DBI)
library(RSQLite)

db <- dbConnect(SQLite(), dbname='thunderbird_manufacturing.db')

sql <- "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?"

my_query <- dbSendQuery(db, sql, params = list(3))
my_data <- dbFetch(my_query, n = 1)

dbClearResult(my_query)
remove(my_query)
dbDisconnect(db)
print(my_data)
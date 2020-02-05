library(DBI)
library(RSQLite)

db <- dbConnect(SQLite(), dbname='thunderbird_manufacturing.db')

my_query <- dbSendQuery(db, "SELECT * FROM CUSTOMER LIMIT 1")
my_data <- dbFetch(my_query, n = 1)

dbClearResult(my_query)

remove(my_query)
dbDisconnect(db)
print(my_data)
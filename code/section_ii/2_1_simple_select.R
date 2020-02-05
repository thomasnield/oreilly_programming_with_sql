library(DBI)
library(RSQLite)

db <- dbConnect(SQLite(), dbname='thunderbird_manufacturing.db')

my_query <- dbSendQuery(db, "SELECT * FROM CUSTOMER")
my_data <- dbFetch(my_query, n = -1)

dbClearResult(my_query)
print(my_data)

remove(my_query)
dbDisconnect(db)
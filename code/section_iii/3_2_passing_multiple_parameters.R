library(DBI)
library(RSQLite)

db <- dbConnect(SQLite(), dbname='thunderbird_manufacturing.db')

sql <- "SELECT * FROM CUSTOMER WHERE STATE = ? AND CATEGORY = ?"

my_query <- dbSendQuery(db, sql, params = list('TX', 'COMMERCIAL'))
my_data <- dbFetch(my_query, n = -1)

dbClearResult(my_query)
remove(my_query)
dbDisconnect(db)
print(my_data)

library(DBI)
library(RSQLite)
library(dplyr)

db <- dbConnect(SQLite(), dbname='thunderbird_manufacturing.db')

my_query <- dbSendQuery(db, "SELECT * FROM CUSTOMER")
df <- dbFetch(my_query, n = -1) 

tx_only <- df %>% filter(df['STATE'] == 'TX')

dbClearResult(my_query)
print(tx_only)

dbDisconnect(db)

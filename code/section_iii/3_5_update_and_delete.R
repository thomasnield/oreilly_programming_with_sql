
library(DBI)
library(RSQLite)
library(dplyr)

db <- dbConnect(SQLite(), dbname='thunderbird_manufacturing.db')


# Perform delete of customer records with ID greater than 10 
my_delete <- "DELETE FROM CUSTOMER WHERE CUSTOMER_ID > ?"
dbExecute(db, my_delete, params= 10)


# Perform update of customer 
my_update <- "UPDATE CUSTOMER SET STATE = lower(STATE)"
dbExecute(db, my_update)


# Check Records 
my_query <- dbSendQuery(db, "SELECT * FROM CUSTOMER")
my_data <- dbFetch(my_query, n = -1)

# Cleanup 
dbClearResult(my_query)
print(my_data)
remove(my_query)
dbDisconnect(db)


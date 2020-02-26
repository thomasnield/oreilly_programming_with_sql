
library(DBI)
library(RSQLite)

db <- dbConnect(SQLite(), dbname='thunderbird_manufacturing.db')

# use try-catch-finally to handle errors 
# and always release connection even if error occured
tryCatch({ 
  my_query <- dbSendQuery(db, "SELECT * FROM CUSTOMER")
  my_data <- dbFetch(my_query, n = -1)
  
  dbClearResult(my_query)
  print(my_data)
  remove(my_query)
  },
  error = function(error_condition) { 
    print(error_condition)
  },
  finally = function() { 
    dbDisconnect(db)
  }
)



library(DBI)
library(RSQLite)

db <- dbConnect(SQLite(), dbname='thunderbird_manufacturing.db')

# Three new records in a DataFrame to insert 

new_records = data.frame(
  "CUSTOMER_NAME" = c("Zeta LLC", "Iota Construction", "Beta Solutions LTD"),
  "ADDRESS" = c("653 Kimberly Way", "12 Roth Road", "6742 Preston Rd"),
  "CITY" = c("Allen","Plano","Frisco"),
  "STATE" = c("TX","TX","TX"),
  "ZIP" = c(75032, 75035, 75002),
  "CATEGORY" = c("COMMERCIAL","INDUSTRIAL","COMMERCIAL")
)

# Appends records to existing table
# If table did not exist, it would create it 

# Do operations within a transaction
# If anything throws an error, it will roll back
dbBegin(db)

tryCatch({ 
    dbWriteTable(db, name="CUSTOMER", new_records, append=T)
  
    if (nrow(new_records) != 4) {
      stop("There are not 4 records!")
    }
    dbCommit(db) 
  },
  error = function(error_condition) { 
    print(error_condition)
    dbRollback(db)
  }
)

dbDisconnect(db)



# Pooling database connections in R
# Documentation: https://cran.r-project.org/web/packages/pool/pool.pdf

library(DBI)
library(RSQLite)
library(pool)

# Instead of creating a connection, we create a pool of connections
pool <- dbPool(
  drv = RSQLite::SQLite(),
  dbname = "thunderbird_manufacturing.db",
  minSize = 2, 
  maxSize = 10,
  idleTimeout = 30
  # host = "someserver.somewhere.com",
  # username = "guest",
  # password = "guest"
)

poolWithTransaction(pool, function(conn) { 
  new_record = c("Brittania Solutions, LLC", "2374 Collie Way","Frisco","TX",75035,"COMMERCIAL")
  my_insert <- "INSERT INTO CUSTOMER (CUSTOMER_NAME, ADDRESS, CITY, STATE, ZIP, CATEGORY) VALUES (?,?,?,?,?,?)"
  dbExecute(conn, my_insert, params= new_record)
})

# View all records in CUSTOMER to see if new record was added
conn = poolCheckout(pool)
my_query <- dbSendQuery(conn, "SELECT * FROM CUSTOMER")
my_data <- dbFetch(my_query, n = -1)
dbClearResult(my_query)

print(my_data)
poolReturn(conn)


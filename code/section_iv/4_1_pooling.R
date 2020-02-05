
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

conn = poolCheckout(pool)
my_query <- dbSendQuery(conn, "SELECT * FROM CUSTOMER")
my_data <- dbFetch(my_query, n = -1)
dbClearResult(my_query)

print(my_data)
poolReturn(conn)


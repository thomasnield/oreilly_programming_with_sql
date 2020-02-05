from sqlalchemy import create_engine, text
from threading import Thread

# Connection pooling
# Documentation: https://docs.sqlalchemy.org/en/13/core/pooling.html#api-documentation-available-pool-implementations
from sqlalchemy.pool import QueuePool

# pool_size - The number of connections to always have on hand
# max_overflow - the max number of connections beyond the pool_size
# poolclass - the type of pool to use 
# timeout - the number of seconds to wait for a connection to be available before giving up 
engine = create_engine('sqlite:///thunderbird_manufacturing.db', poolclass=QueuePool, pool_size=1, max_overflow=9)

# Here we kick off two queries simultaneously
# Where each gets its own connection from the pool, 
# And returns it to the pool when done 
def query_customers():
    conn = engine.connect()
    stmt = text("SELECT * FROM CUSTOMER")
    results = conn.execute(stmt)
    for r in results:
        print(r)
    conn.close() # return connection to pool


def query_products():
    conn = engine.connect()
    stmt = text("SELECT * FROM PRODUCT")
    results = conn.execute(stmt)
    for r in results:
        print(r)
    conn.close() # return connection to pool

# Call the two functions from two separate threads
thread1 = Thread(target=query_customers)
thread2 = Thread(target=query_products)

# Start and wait for the threads to finish 
thread1.start()
thread2.start()
thread1.join()
thread2.join()

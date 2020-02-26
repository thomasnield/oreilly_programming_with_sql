from sqlalchemy import create_engine, text

# Connection pooling
# Documentation: https://docs.sqlalchemy.org/en/13/core/pooling.html#api-documentation-available-pool-implementations
from sqlalchemy.pool import QueuePool

# pool_size - The number of connections to always have on hand
# max_overflow - the max number of connections beyond the pool_size
engine = create_engine('sqlite:///thunderbird_manufacturing.db', poolclass=QueuePool, pool_size=1, max_overflow=9)

try:
    # Query and iterate customers
    conn = engine.connect()
    stmt = text("SELECT * FROM CUSTOMER")

    results = conn.execute(stmt)

    for r in results:
        print(r)
except Exception as e:
    print("There was an error: {0}".format(e))
finally:
    conn.close()  # return connection to pool, regardless if error occurred or not
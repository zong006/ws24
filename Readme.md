#### Notes

to update two tables in a database connected by foreign keys simultaneously via a transaction 

1. Use keyholder and prepared statements in the repo class.
    - the id of orders table (primary key) is set to auto_increment. Table cols are: 
        - order_id (pk), 
        - order_date,
        - customer_name, 
        - ship_address, 
        - notes, 
        - tax
    - prepared statement use the query to insert values into orders table, but DO NOT INCLUDE THE AUTO GENERATED ID
    - the auto generated id is captured by keyholder, and returned. 

2. when transaction does not go through, the auto generated id is not rolled back, even if the entire transaction is. 
    - this is not an issue, since the concern here is uniqueness of the id (pk). auto generated ids' might not be sequential
    - insistence of sequentiality might also cause problems with multiple users, so sequentiality is not a required condition for a transaction


#### deploying to railway

- setup mysql on railway, 
    - create new user and password variables (if don't want to use root user in database)
    - get the connectin url and port (5digits) from settings in the form of <<connection url>>:<<port number>>
- setup new connection in dbver (with allow public key retrieval to true). this is the root user
    - use the url and port from above
    - add new user, with username and password as values of the environment variables added in railway
- another new connection, but use the username and password created from railway (this logs in using that username and pw)
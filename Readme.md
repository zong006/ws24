#### Notes

to update two tables in a database connected by foreign keys simultaneously via a transaction (further improvements to tables in the future..)

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
#### Issue description
 The connection of the database is disconnected.


#### Steps to reproduce the issue

1.  Check database port number.
2.  Change the correct port number.


#### What's the expected result?

- To connect successfully with the database sever.


#### What's the actual result?

- Connect successfully with SQL.


#### Additional details / screenshot

- Wile running the coding, the connection with the SQL is disconnected.
- Firstly, check the coding again and chek the port number of the sql.
- At first, we didn't notice that the port number of the sql is missing one digit.
- That's why we send one evening to solve this problem.
- After checking for the sevral time, we finally found that we miss one zero at the port number.

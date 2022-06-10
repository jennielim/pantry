# Pantry
## To run:
  * Clone this repository
  * Open the frontend folder in a text editor (like VS Code) and run it with "npm start"
  * Then open the backend folder in Intellij and run the PantryApplication.java file.
  * Next, create a database in PostgreSQL called 'pantry'. In terminal: 
```
  brew install postgresql # install homebrew if you don't have it
  psql postgres
    CREATE USER {user}
    CREATE DATABASE pantry
    GRANT ALL PRIVILEGES ON DATABASE pantry to {user}
  # to see database in terminal (or use Postman)
  SELECT * FROM pantry 
```
In the application.properties file (backend -> src -> main -> resources) alter the url if you changed it, and add your username and password from PostgreSQL.

Now it's all set up! 

## Features:
  * add items
  * remove items, but you can't remove more than you already have
  * sort in various ways
  * clear the database

## Built with:
React in the frontend, Spring boot for the REST API, and PostgreSQL for the database.

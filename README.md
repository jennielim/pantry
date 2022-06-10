# pantry
To run:
Open the frontend folder in a text editor (like VS Code) and run it with "npm start"
Then open the backend folder in Intellij and run the PantryApplication.java file.
Next, create a database in PostgreSQL called 'pantry'.
```
  brew install postgresql
  psql postgres
    CREATE USER {user}
    CREATE DATABASE pantry
    GRANT ALL PRIVILEGES ON DATABASE pantry to {user}
```
In the application.properties file (backend -> src -> main -> resources) alter the url if you changed it, and add your username and password.  
Now it's all set up!

Built with React in the frontend, Spring boot for the REST API, and PostgreSQL for the database.

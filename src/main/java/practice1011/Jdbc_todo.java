package practice1011;
import java.sql.DriverManager;
/**
 * 
 */
public class Jdbc_todo {

	public static void main(String[] args)throws Exception {
		/*
		 * This Java code demonstrates basic CRUD (Create, Read, Update, Delete) operations
		 * on an H2 in-memory database. It uses JDBC (Java Database Connectivity) to interact
		 * with the database.
		 */

		// Establish a connection to the H2 database.
		// "jdbc:h2:./todo" specifies the database file named 'todo' in the current directory.
		// "AUTO_SERVER=TRUE" allows multiple connections to the same database.
		java.sql.Connection connection = DriverManager.getConnection("jdbc:h2:./todo;AUTO_SERVER=TRUE");


		// SQL statement to create a table named 'TASK' if it doesn't already exist.
		// The table has two columns: 'id' (primary key, auto-incrementing) and 'name' (varchar).
		String createTableSql ="create table if not exists TASK (id identity primary key, name varchar)";
		// Create a Statement object to execute SQL queries.
		java.sql.Statement statement = connection.createStatement();
		// Execute the SQL statement to create the table.
		statement.execute(createTableSql);

		// The following lines are commented out in the original code, but show an example of a direct insert.
		/// String insertQuery  = "insert into TASK (name) values ('practise')";
		//statement.execute(insertQuery);

		// SQL statement for inserting data into the 'TASK' table using a PreparedStatement.
		// The '?' is a placeholder for the 'name' column, which will be set dynamically.
		String insertStatement = "insert into TASK (name) values (?)";
		// Create a PreparedStatement object. PreparedStatement helps prevent SQL injection
		// and allows for efficient execution of the same query multiple times with different parameters.
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
		// Set the value for the first placeholder (index 1) to "onemore".
		preparedStatement.setString(1, "onemore");
		// Execute the insert statement.
		preparedStatement.execute();

		// SQL statement for updating data in the 'TASK' table.
		// It updates the 'name' column to a new value where the current 'name' is 'onemore'.
		String updateCommand = "update TASK set name = ? where name ='onemore'";
		// Re-assign the preparedStatement object for the update operation.
		preparedStatement = connection.prepareStatement(updateCommand);
		// Set the value for the first placeholder (index 1) to "2more".
		preparedStatement.setString(1, "2more");
		// Execute the update statement.
		preparedStatement.execute();

		// SQL statement to select all records from the 'Task' table.
		String selectAllQuery = "select * from Task";
		// Execute the select query and get the results in a ResultSet object.
		java.sql.ResultSet resultSet = statement.executeQuery(selectAllQuery);
		// Iterate through the ResultSet to process each row.
		while(resultSet.next()){
			// Retrieve the value of the 'name' column for the current row and print it.
			System.out.print("to do item:" + resultSet.getString("name"));
		}

		// The following lines are commented out in the original code, but show examples of delete operations.
		// They demonstrate deleting by name or by id.
		/// String deleteQuery = " delete from TASK  where name = 'learn jakarta EE!' ";
		///statement.execute(deleteQuery);
		///String deleteQuery = " delete from TASK  where id = 1 ";
		//String deleteQuery = " delete from TASK  where id = 36";
		//statement.execute(deleteQuery);

	}

}

package practice1011;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class TaskRepo {

	private static DataSource getDatasource() {

		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:h2:./todo;AUTO_SERVER=TRUE");
		return dataSource;
	}

	public static void create(Task task) throws Exception {
		try (Connection connection = getDatasource().getConnection()) {
			// SQL statement for inserting data into the 'TASK' table using a
			// PreparedStatement.
			// The '?' is a placeholder for the 'name' column, which will be set
			// dynamically.
			String insertStatement = "insert into TASK (name) values (?)";
			// Create a PreparedStatement object. PreparedStatement helps prevent SQL
			// injection
			// and allows for efficient execution of the same query multiple times with
			// different parameters.
			java.sql.PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			// Set the value for the first placeholder (index 1) to "onemore".
			preparedStatement.setString(1, task.name);
			// Execute the insert statement.
			preparedStatement.execute();
		}

	}

	public static void deleteAll() throws Exception {
		try (Connection connection = getDatasource().getConnection()) {
			java.sql.Statement statement = connection.createStatement();
			 statement.executeUpdate("DELETE FROM TASK");

		}

	}

	public static void update(Task task) throws Exception {
		try (Connection connection = getDatasource().getConnection()) {
			String updateCommand = "update Task set name = ? where id = ?";
			java.sql.PreparedStatement preparedStatement = connection.prepareStatement(updateCommand);
			preparedStatement.setString(1, task.name);
			preparedStatement.setInt(2, task.id);
			preparedStatement.execute();

		}

	}

	public static List<Task> findAll() throws Exception {
		List<Task> tasks = new ArrayList();
		try (Connection connection = getDatasource().getConnection()) {

			java.sql.Statement statement = connection.createStatement();
			java.sql.ResultSet resultSet = statement.executeQuery("select * from TASK");
			while (resultSet.next()) {
				Task task = new Task(resultSet.getInt(1), resultSet.getString(2));
				tasks.add(task);
			}

		}
		return tasks;

	}

	public static void main(String[] args) {
		try {
			 TaskRepo.deleteAll();
			Task task = new Task("make more massa ");
			TaskRepo.create(task);

			Task task1 = new Task(40, "sleep");
			TaskRepo.update(task1);
			
			
			List <Task> tasks = TaskRepo.findAll();
			for(Task task2 : tasks) {
				System.out.println(task2.id);
				System.out.println(task2.name);
			}
			

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}

//explain connection pulling 
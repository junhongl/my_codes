package lijh.mysql.insert.batch;

import java.io.IOException;
import java.sql.SQLException;

public class InsertData {
	public static void main(String[] args) {
		String connString = "jdbc:mysql://localhost:3306/test";
		DBStoreHelper dbHelper = new DBStoreHelper(connString, "root", "Passw0rd");
		try {
			long startTime = System.currentTimeMillis();
			dbHelper.storeToDb();
			long endTime = System.currentTimeMillis();
			System.out.println("------------------");
			System.out.println("Total time is "  + (endTime - startTime) / 1000 + "s");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

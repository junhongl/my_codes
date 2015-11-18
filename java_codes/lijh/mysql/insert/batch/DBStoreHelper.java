package lijh.mysql.insert.batch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class DBStoreHelper {
	private String insert_sql;

	private String connectStr;
	private String username;
	private String password;

	public DBStoreHelper(String connectStr, String username, String password) {
		// connectStr = "jdbc:mysql://localhost:3306/db_ip";
		this.connectStr = connectStr;
		connectStr += "?useServerPrepStmts=false&rewriteBatchedStatements=true";
		insert_sql = "INSERT INTO user (name,addr,tel) VALUES (?,?,?)";
		this.username = username;
		this.password = password;
	}

	public void storeToDb() throws IOException, ClassNotFoundException,
			SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection(connectStr, username,
				password);
		conn.setAutoCommit(false);
		int count = 0;
		PreparedStatement psts = conn.prepareStatement(insert_sql);
		int totalRound = 1000;
		int recordNoPerRound = 1000;
		for (int i = 0; i < totalRound; i++) {
			long startTime = System.currentTimeMillis();
			for (int j = 0; j < recordNoPerRound; j++) {
				UUID userName = UUID.randomUUID();
				UUID userAddr = UUID.randomUUID();
				UUID userTel = UUID.randomUUID();
				psts.setString(1, userName.toString());
				psts.setString(2, userAddr.toString());
				psts.setString(3, userTel.toString());
				psts.addBatch();
				count++;
			}
			psts.executeBatch();
			conn.commit();
			long endTime = System.currentTimeMillis();
			System.out.println("" + i + "/" + totalRound + " patch total time is "  + (endTime - startTime) + "ms");
		}
		System.out.println("All down : " + count);
		conn.close();
	}
}

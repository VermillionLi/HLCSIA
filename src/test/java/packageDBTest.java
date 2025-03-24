import JDBC.SQLite;

import java.sql.SQLException;

public class packageDBTest {
    public static void main(String[] args) throws SQLException {
        SQLite db = new SQLite();
        String[] test = db.retrieveBadBrawlerTable();
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i] + ", ");
        }
    }
}

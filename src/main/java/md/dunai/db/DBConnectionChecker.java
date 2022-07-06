package md.dunai.db;

import java.sql.SQLException;

public class DBConnectionChecker implements Runnable {
    private DBConn dbConn;
    public DBConnectionChecker(DBConn dbConn) {
        this.dbConn = dbConn;
    }

    @Override
    public void run() {
        while (true){
            try {
                if (this.dbConn.isClosed()) {
                    this.dbConn.connect();
                }
                Thread.sleep(5 * 1000);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}

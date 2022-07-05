package md.dunai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements Runnable {
    private Connection conn;
    private final String url;

    public DBConnection(String url) {
        try {
            this.url = url;
            this.conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void run() {
        try {
            System.out.println("in thread");
            if(conn.isClosed()) {
                this.conn = DriverManager.getConnection(url);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConn() {
        return this.conn;
    }
}

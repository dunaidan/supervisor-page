package md.dunai.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

    private String url;
    private Connection conn;

    public DBConn(String url) throws SQLException {
        this.url = url;
        connect();
    }

    public void connect() throws SQLException {
        int retry = 0;
        while (true) {
            try {
                this.conn = DriverManager.getConnection(this.url);
                return;
            } catch (SQLException ex) {
                if (retry > 5) {
                    throw  ex;
                }
                retry++;
            }
        }


    }

    public boolean isClosed() throws SQLException {
       return this.conn.isClosed();
    }


    public Connection getConn() {
        return this.conn;
    }
}

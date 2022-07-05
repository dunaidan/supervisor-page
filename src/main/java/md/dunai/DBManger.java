package md.dunai;

import java.sql.*;

public class DBManger {
    private final DBConnection conn;
    private final Statement statement;

    public DBManger(String url) {
        try {
            this.conn = new DBConnection(url);
            this.statement = conn.getConn().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert (String query) {
        Thread connThread = new Thread(this.conn);
        connThread.start();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            if(e.getErrorCode() != 19) System.out.println(e.getMessage());
        }
    }

    public ResultSet select (String query) {
        Thread connThread = new Thread(this.conn);
        connThread.start();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }

    public void close() throws SQLException {
        this.statement.close();
        this.conn.getConn().close();
    }

}

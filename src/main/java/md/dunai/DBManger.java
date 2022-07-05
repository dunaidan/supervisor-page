package md.dunai;

import java.sql.*;

public class DBManger {
    private Connection conn;
    private Statement statement;

    public DBManger(String url) {
        try {
            this.conn = DriverManager.getConnection(url);
            this.statement = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert (String query) {
        try {
            statement.execute(query);
        } catch (SQLException e) {
            if(e.getErrorCode() != 19) System.out.println(e.getMessage());
        }
    }

    public ResultSet select (String query) {
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
        this.conn.close();
    }

}

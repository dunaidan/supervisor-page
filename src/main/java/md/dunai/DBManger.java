package md.dunai;

import md.dunai.db.DBConn;
import md.dunai.db.DBConnectionChecker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManger {
    private final DBConn conn;
    private final Statement statement;

    public DBManger(DBConn dbConn) {
        try {
            this.conn = dbConn;

            //opens a Thread that check if the connection to database is not closed
            DBConnectionChecker checker = new DBConnectionChecker(dbConn);
            Thread connThread = new Thread(checker);
            connThread.start();

            this.statement = conn.getConn().createStatement();

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
        this.conn.getConn().close();
    }

}

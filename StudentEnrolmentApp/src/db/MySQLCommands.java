package db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * MySQLCommands - A utility class for common MySQL database operations
 * Provides methods for executing SQL commands and queries
 */
public class MySQLCommands {

    /**
     * Execute a SQL query that returns results (SELECT)
     *
     * @param query The SQL query string
     * @return ResultSet containing the query results
     * @throws SQLException if a database access error occurs
     */
    public static ResultSet executeQuery(String query) throws SQLException {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            throw new SQLException("Failed to establish database connection");
        }

        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    /**
     * Execute a SQL query that returns results (SELECT) with parameters
     *
     * @param query The SQL query string with placeholders (?)
     * @param params The parameter values to bind
     * @return ResultSet containing the query results
     * @throws SQLException if a database access error occurs
     */
    public static ResultSet executeQuery(String query, Object... params) throws SQLException {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            throw new SQLException("Failed to establish database connection");
        }

        PreparedStatement pstmt = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
        return pstmt.executeQuery();
    }

    /**
     * Execute a SQL update/insert/delete command
     *
     * @param query The SQL command string
     * @return The number of rows affected
     * @throws SQLException if a database access error occurs
     */
    public static int executeUpdate(String query) throws SQLException {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            throw new SQLException("Failed to establish database connection");
        }

        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(query);
    }

    /**
     * Execute a SQL update/insert/delete command with parameters
     *
     * @param query The SQL command string with placeholders (?)
     * @param params The parameter values to bind
     * @return The number of rows affected
     * @throws SQLException if a database access error occurs
     */
    public static int executeUpdate(String query, Object... params) throws SQLException {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            throw new SQLException("Failed to establish database connection");
        }

        PreparedStatement pstmt = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
        return pstmt.executeUpdate();
    }

    /**
     * Execute a SQL command that doesn't return results (CREATE, DROP, ALTER, etc.)
     *
     * @param query The SQL command string
     * @return true if successful, false otherwise
     */
    public static boolean execute(String query) {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return false;
        }

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Test the database connection
     *
     * @return true if connection is successful, false otherwise
     */
    public static boolean testConnection() {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return false;
        }

        try {
            return conn.isValid(5); // Test with 5 second timeout
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the current database name
     *
     * @return The database name or null if error occurs
     */
    public static String getDatabaseName() {
        try {
            ResultSet rs = executeQuery("SELECT DATABASE()");
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get MySQL server version
     *
     * @return The server version string or null if error occurs
     */
    public static String getServerVersion() {
        Connection conn = DBConnection.getConnection();
        if (conn == null) {
            return null;
        }

        try {
            return conn.getMetaData().getDatabaseProductVersion();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Close a ResultSet and its associated resources
     *
     * @param rs The ResultSet to close
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                Statement stmt = rs.getStatement();
                rs.close();
                if (stmt != null) {
                    stmt.close();
                }
                Connection conn = stmt != null ? stmt.getConnection() : null;
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Close a Statement and its associated connection
     *
     * @param stmt The Statement to close
     */
    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                Connection conn = stmt.getConnection();
                stmt.close();
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Close a database connection
     *
     * @param conn The Connection to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

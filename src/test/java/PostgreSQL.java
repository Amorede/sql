import backend.DictNamesDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQL {

    static final String CONNECTION_STRING = "jdbc:postgresql://manny.db.elephantsql.com";

    static final String USERNAME = "xcstsovj";
    static final String PASSWORD = "KGkspXepb8z1vWY-biz8xHhm8jas9n4H";

    private static Connection connection = null;
    private static Statement statement = null;


    private static void connectToDB() throws SQLException {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            connection.close();
            statement.close();
            throw new IllegalStateException("Unable to connect to DataBase", e);
        }

    }

    private static ResultSet executeQuery(final String sqlQuery) {
        try {
            if (connection.isClosed() || connection != null) {
                connectToDB();
            }
            return statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to execute SQL Query", e);
        }
    }

    private static List<DictNamesDTO> getResultsAs(final ResultSet resultSet) throws SQLException {
        final List<DictNamesDTO> dbResults = new ArrayList<DictNamesDTO>();
        while (resultSet.next()) {
            final String id = resultSet.getString("id");
            final String name = resultSet.getString("name");

            System.out.println(id + name + "\n");

            final DictNamesDTO dictNamesDTO = new DictNamesDTO();

            dictNamesDTO.setId(id);
            dictNamesDTO.setName(name);

            dbResults.add(dictNamesDTO);
        }
        return dbResults;
    }

    public static void main(String[] args) throws SQLException {
        PostgreSQL.connectToDB();
        String sqlGetIdAndNames = "SELECT id, name FROM dict_names";
        List<DictNamesDTO> listIdAndNames = PostgreSQL.getResultsAs(executeQuery(sqlGetIdAndNames));
        for (DictNamesDTO element:  listIdAndNames) {
            String id = element.getId();
            String name = element.getName();

            String sqlIncert = String.format("INSERT INTO emails VALUES (%1$s, %2$s)", id, name);
            executeQuery(sqlIncert);
        }
    }
}
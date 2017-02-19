import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        Connection connection = getConnect("localhost", "5432", "", "postgres", "mac");

        PreparedStatement preparedStatement = null;
        /*preparedStatement = connection.prepareStatement("create table usr " +
                                                            "(id bigint not null, " +
                                                            "name varchar, " +
                                                            "password varchar)");
        preparedStatement.executeUpdate();*/

        /*preparedStatement = connection.prepareStatement(
                "insert into usr (id, name, password) " +
                        "values(?, ?, ?)");

        for(int index = 0; index < 10; index++){

            preparedStatement.setInt(1, index + 12);
            preparedStatement.setString(2, "name_" + (index + 10));
            preparedStatement.setString(3, "password_" + (index + 10));
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();*/

        preparedStatement = connection.prepareStatement(
                "select id as idUsr, name as nameUsr, password from usr");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            System.out.println("id: " + resultSet.getInt("idUsr"));
            System.out.println("name: " + resultSet.getString("nameUsr"));
            System.out.println("password: " + resultSet.getString("password"));
        }

    }

    public static Connection getConnect(String hostname, String port, String dbName, String username, String password) {

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":" + port + "/" + dbName,
                    username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return connection;
    }
}

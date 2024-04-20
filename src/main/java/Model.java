
import java.sql.*;
import java.util.ArrayList;

public class Model {

    public void model() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY," +
                    "name STRING," +
                    "age INTEGER);";
            conn.createStatement().executeUpdate(cmd);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<String> selectAll() {

        ArrayList<String> allRecords = new ArrayList<String>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "SELECT * FROM students;";
            ResultSet rs = conn.createStatement().executeQuery(cmd);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String s = String.format("%3d %10s %3d", id, name, age);
                allRecords.add(s);
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return allRecords;

    }


    public void updateRecord(int id, String name, int age) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = String.format("UPDATE students SET name = '%s', age = %d WHERE id = %d;", name, age, id);
            conn.createStatement().executeUpdate(cmd);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    public void deleteRecord(int id) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            conn.createStatement().executeUpdate(String.format("DELETE FROM students WHERE id = %d;", id));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    public void createRecord(String name, int age) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(String.format("INSERT INTO students(name, age) VALUES('%s',%d);", name, age));
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}

package tables;

import animals.Animal;
import data.AnimalsDataEnum;
import db.IDbConnection;
import db.MySqlConnector;
import tools.AnimalsFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTable implements ITable {

    private IDbConnection iDbConnection = null;

    public AnimalTable() throws SQLException, IOException {
        super("Animal");
        iDbConnection = new MySqlConnector();
        createTable();
    }

    private boolean isTableExist() throws SQLException, IOException {
        String sqlRequest = "show tables;";
        ResultSet table = iDbConnection.executeQueryWithAnswer(sqlRequest);
        while (table.next()) {
            String tableName = table.getString(1);
            if (tableName.equals(name)) {
                return true;
            }

        }
        return false;
    }

    public void createTable() throws SQLException, IOException {
        if (!isTableExist()) {
            String sqlRequest = String.format("CREATE TABLE %s (\n" +
                    "\tid BIGINT auto_increment NOT NULL PRIMARY KEY,\n" +
                    "\t`type` varchar(20) NOT NULL,\n" +
                    "\tname varchar(20) NOT NULL,\n" +
                    "\tcolor varchar(20) NULL,\n" +
                    "\tweight INT NULL,\n" +
                    "\tage INT NULL\n" +
                    ")", name);
            iDbConnection.executeQuery(sqlRequest);
        }
    }


    public List<Animal> findAll() {

        List<Animal> animals = new ArrayList<>();
        try {
            try (ResultSet rs = iDbConnection.executeQueryWithAnswer("SELECT * FROM " + name)) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    int weight = rs.getInt("weight");
                    String color = rs.getString("color");
                    String type = rs.getString("type");
                    int age = rs.getInt("age");

                    Animal animal = AnimalsFactory.animalsFactory(id, AnimalsDataEnum.valueOf(type), name, age, weight, color);
                    animals.add(animal);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return animals;
    }


    public List<Animal> findByType(AnimalsDataEnum animalType) {
        List<Animal> animals = new ArrayList<>();
        try {
            String query = String.format("SELECT * FROM %s  WHERE type = '%s'", name, animalType);
            try (ResultSet rs = iDbConnection.executeQueryWithAnswer(query)) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    int weight = rs.getInt("weight");
                    String color = rs.getString("color");
                    String type = rs.getString("type");
                    int age = rs.getInt("age");

                    Animal animal = AnimalsFactory.animalsFactory(id, AnimalsDataEnum.valueOf(type), name, age, weight, color);
                    animals.add(animal);

                }
                return animals;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("ERROR");
    }

    public Animal findById(long idSearch) {
        try {
            String query = String.format("SELECT * FROM %s  WHERE id = '%s'", name, idSearch);
            ResultSet rs = iDbConnection.executeQueryWithAnswer(query);
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int weight = rs.getInt("weight");
                String color = rs.getString("color");
                String type = rs.getString("type");
                int age = rs.getInt("age");

                return AnimalsFactory.animalsFactory(id, AnimalsDataEnum.valueOf(type), name, age, weight, color);
            }


        } catch (RuntimeException | SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void saveQuery(Animal animal) throws SQLException, IOException {
        String sqlQuery = String.format("INSERT INTO %s (type, name, color, weight, age) VALUES ('%s', '%s', '%s', %d, %d)", name, animal.getType(), animal.getName(), animal.getColor(), animal.getWeight(), animal.getAge());
        iDbConnection.executeQuery(sqlQuery);
    }

    public void updateQuery(Animal animal) throws SQLException, IOException {
        iDbConnection.executeQuery(String.format("UPDATE %s SET name = '%s', color = '%s', age = %d, weight = %d WHERE id = %d", name, animal.getName(), animal.getColor(), animal.getAge(), animal.getWeight(), animal.getId()));
    }
}

package com.example.projectlib.dao;

import com.example.projectlib.models.Human;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class HumanDAO {


    private Connection getConnection() throws SQLException {
            return DriverManager.getConnection(UtilNames.URL.getValue(), UtilNames.USERNAME.getValue(), UtilNames.PASSWORD.getValue());

    }

    public List<Human> getAll() {
        List<Human> humans = new ArrayList<>();
        String query = "SELECT * FROM human";
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){

            while (resultSet.next()) {
                Human human = new Human();
                human.setId(resultSet.getInt("id"));
                human.setFio(resultSet.getString("fio"));
                human.setBirthYear(resultSet.getInt("year"));
                humans.add(human);
            }
        }catch (SQLException e) {
            throw new RuntimeException("Mistake in getting all humans + ", e);
        }
        return humans;
    }

    public Human getById(int id) {
        Human human = new Human();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM human WHERE id = ?");
            ){
            statement.setInt(1, id);
            try( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    human.setId(resultSet.getInt("id"));
                    human.setFio(resultSet.getString("fio"));
                    human.setBirthYear(resultSet.getInt("year"));
                } else {
                    throw  new RuntimeException("No human found with id: " + id);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Mistake in getting by id all humans + ", e);
        }
        return human;
    }

    public void addHuman(Human human) {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement("INSERT INTO  human (fio, year) VALUES (?, ?)")) {
                statement.setString(1, human.getFio());
                statement.setInt(2, human.getBirthYear());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            throw new RuntimeException("Mistake in adding human + ", e);
        }
    }

    public void updateHuman(int id ,Human human) {
        try {
            try (Connection connection = getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE human SET fio=?, year=? WHERE id=?")) {
                preparedStatement.setString(1, human.getFio());
                preparedStatement.setInt(2, human.getBirthYear());
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Mistake in updating human + ", e);
        }
    }

    public void deleteHuman(int id) {
        try {
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement("DELETE FROM human WHERE id =?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Deleting in updating human + ", e);
        }
    }
}

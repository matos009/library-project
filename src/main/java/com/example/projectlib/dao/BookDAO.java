package com.example.projectlib.dao;

import com.example.projectlib.models.Book;
import com.example.projectlib.models.Human;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class BookDAO {
    public Connection getConnection() throws  SQLException {

            return DriverManager.getConnection(UtilNames.URL.getValue(), UtilNames.USERNAME.getValue(), UtilNames.PASSWORD.getValue());


    }

    public List<Book> getBookList(){
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);){
            while(resultSet.next()){
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setYear(resultSet.getInt("year"));
                bookList.add(book);
            }
        } catch (SQLException e){
            throw new RuntimeException("Error while getting book list ", e);
        }
        return bookList;
    }

    public List<Book> getBookListOfUser(int id){
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE user_id = " + id;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);){
            while(resultSet.next()){
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setYear(resultSet.getInt("year"));
                bookList.add(book);
            }
        } catch (SQLException e){
            throw new RuntimeException("Error while getting book list ", e);
        }
        return bookList;
    }

    public Book getBookById(int id){
        Book book = new Book();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id = ?");
             ){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    book.setId(resultSet.getInt("id"));
                    book.setName(resultSet.getString("name"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setYear(resultSet.getInt("year"));
                    book.setUser_id(resultSet.getInt("user_id"));
                } else {
                    throw new RuntimeException("Error while getting book by id - no book found");
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Error while getting book by id ", e);

        }
        return book;
    }

    public void addBook(Book book){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  book (name, author, year) VALUES (?, ?, ?)");){

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getYear());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Error while adding book ", e);

        }
    }

    public void updateBook(int id, Book book){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET name=?, author=?, year=? WHERE id=?");){

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getYear());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error while updating book ", e);
        }
    }

    public void updateUserOfBook(int id, Book book){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET user_id=? WHERE id=?");){

            preparedStatement.setInt(1, book.getUser().getId());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error while updating book ", e);
        }
    }

    public void deleteBook(int id){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book WHERE id=?");){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error while updating book ", e);
        }
    }


}
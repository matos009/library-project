package com.example.projectlib.controllers;

import com.example.projectlib.dao.BookDAO;
import com.example.projectlib.dao.HumanDAO;
import com.example.projectlib.models.Book;
import com.example.projectlib.models.Human;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final HumanDAO humanDAO;


    @Autowired
    public BookController(BookDAO bookDAO, HumanDAO humanDAO) {
        this.bookDAO = bookDAO;
        this.humanDAO = humanDAO;
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookDAO.getBookList());
        return "book/books";
    }



    @GetMapping("/new")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());

        return "book/newBook";
    }

    @PostMapping
    public String addBook(@ModelAttribute @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/newBook";
        }
        bookDAO.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        if( bookDAO.getBookById(id) == null) {
            throw new RuntimeException("Book with id " + id + " not found");
        }
        model.addAttribute("person", bookDAO.getBookById(id));
        return "book/edit";
    }

    @PostMapping("/edit")
    public String editBook(@RequestParam("id") int id, @RequestParam("name")  @NotBlank(message = "Название книги не должно быть пустым")
    @Size(max = 255, message = "Название книги не должно превышать 255 символов") String title, @RequestParam("author")  @NotBlank(message = "Название книги не должно быть пустым")
    @Size(max = 255, message = "Название книги не должно превышать 255 символов") String author, @RequestParam("year")  @Min(value = 1950, message = "Год должен быть не раньше 1950")
    @Max(value = 2025, message = "Год должен быть не позже 2025") int year){
        Book book = bookDAO.getBookById(id);
        book.setYear(year);
        book.setAuthor(author);
        book.setName(title);
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable int id, Model model) {
        if (bookDAO.getBookById(id) == null) {
            throw new RuntimeException("Book with id " + id + " not found");
        }

        Book book = bookDAO.getBookById(id);
        if(humanDAO.getById(book.getUser_id())==null) {
            throw new RuntimeException("Human with id " + id + " not found");
        }
        Human human = humanDAO.getById(book.getUser_id());
        System.out.println(human);
        book.setUser(human);
        model.addAttribute("book", book);
        model.addAttribute("people", humanDAO.getAll());
        return "book/book";

    }

    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") @NotBlank int id, @RequestParam("userId") @NotBlank int userId) {
        try {
            Book book = bookDAO.getBookById(id);
            book.setUser(humanDAO.getById(userId));
            bookDAO.updateUserOfBook(id, book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
}

package com.example.projectlib.controllers;

import com.example.projectlib.dao.BookDAO;
import com.example.projectlib.dao.HumanDAO;
import com.example.projectlib.models.Human;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class HumanController {
    private final HumanDAO humanDAO;
    private final BookDAO bookDAO;

    @Autowired
    public HumanController(HumanDAO humanDAO, BookDAO bookDAO) {
        this.humanDAO = humanDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String human(Model model) {
        model.addAttribute("humans", humanDAO.getAll());
        return "human/people";
    }

    @GetMapping("/{id}")
    public String human(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", humanDAO.getById(id));
        model.addAttribute("books", bookDAO.getBookListOfUser(id));
        return "human/person";
    }

    @GetMapping("/new")
    public String addHuman() {
        return "human/newPerson";
    }

    @PostMapping
    public String addHuman(@RequestParam("fio") @NotBlank String fio,
                           @RequestParam("birthYear") @NotBlank int birthday) {
        Human human = new Human();
        human.setFio(fio);
        human.setBirthYear(birthday);
        humanDAO.addHuman(human);
        return  "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editHuman(@PathVariable("id") @NotBlank int id,  Model model) {
        model.addAttribute("person", humanDAO.getById(id));
        return "human/edit";
    }


    @PostMapping("/edit")
    public String editHuman(@RequestParam("id") @NotBlank  int id  ,@RequestParam("fio") @NotBlank String fio,
                            @RequestParam("birthYear")@NotBlank int birthday, Model model){
        Human human = humanDAO.getById(id);
        human.setFio(fio);
        human.setBirthYear(birthday);
        humanDAO.updateHuman(id ,human);
        return  "redirect:/people";

    }


}

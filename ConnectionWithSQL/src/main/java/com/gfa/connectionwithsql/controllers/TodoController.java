package com.gfa.connectionwithsql.controllers;

import com.gfa.connectionwithsql.models.Todo;
import com.gfa.connectionwithsql.repositories.AssigneeRepository;
import com.gfa.connectionwithsql.repositories.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {
    private final TodoRepository todoRepository;
    private final AssigneeRepository assigneeRepository;

    public TodoController(TodoRepository todoRepository, AssigneeRepository assigneeRepository) {
        this.todoRepository = todoRepository;
        this.assigneeRepository = assigneeRepository;
    }

    @GetMapping(value={"/todo", "/", "/list"})
    public String list(Model model) {
        model.addAttribute("todos", todoRepository.findByOrderByIdAsc());
        model.addAttribute("assignee", assigneeRepository.findAll());
        return "todolist";
    }

    @GetMapping("/todo/")
    public String isActive(@RequestParam boolean isActive, Model model) {
        model.addAttribute("todos", todoRepository.findAll().stream()
                .filter(task -> !task.isDone() == isActive).toList());
        return "todolist";
    }

    @GetMapping("/add")
    public String renderAddTodo() {
        return "add-todo";
    }

    @PostMapping("/save")
    public String addTodo(@RequestParam("newTodo") String newTodo) {
        todoRepository.save(new Todo(newTodo));
        return "redirect:/todo";
    }

    @GetMapping("/{id}/delete")
    public String deleteTodo(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        todoRepository.deleteById(id);
        return "redirect:/todo";
    }

    @GetMapping("/{id}/edit")
    public String renderEditTodo(@PathVariable long id, Model model) {
        model.addAttribute("todo", todoRepository.findById(id).get());
        model.addAttribute("assignees", assigneeRepository.findAll());
        return "edit-todo";
    }

    @PostMapping("/{id}/edit")
    public String editTodo(@ModelAttribute Todo todo) {
        todoRepository.save(todo);
        return "redirect:/todo";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "", name = "search") String searchText, Model model) {
        model.addAttribute("todos", todoRepository.findAll().stream()
                .filter(todo -> todo.getTitle().toLowerCase().contains(searchText.toLowerCase())).toList());
        return "todolist";
    }
}

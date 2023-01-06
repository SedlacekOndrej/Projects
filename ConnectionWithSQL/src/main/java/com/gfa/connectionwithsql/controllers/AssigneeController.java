package com.gfa.connectionwithsql.controllers;

import com.gfa.connectionwithsql.models.Assignee;
import com.gfa.connectionwithsql.models.Todo;
import com.gfa.connectionwithsql.repositories.AssigneeRepository;
import com.gfa.connectionwithsql.repositories.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class AssigneeController {
    private final AssigneeRepository assigneeRepository;
    private final TodoRepository todoRepository;

    public AssigneeController(AssigneeRepository assigneeRepository, TodoRepository todoRepository) {
        this.assigneeRepository = assigneeRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/assignees")
    public String renderAssignees(Model model) {
        model.addAttribute("assignees", assigneeRepository.findByOrderByIdAsc());
        return "assignees";
    }

    @GetMapping("/add-assignee")
    public String renderAddAssignee() {
        return "add-assignee";
    }

    @PostMapping("/save-assignee")
    public String addAssignee(@ModelAttribute(name = "name") String name, @ModelAttribute(name = "email") String email) {
        assigneeRepository.save(new Assignee(name, email));
        return "redirect:/assignees";
    }

    @GetMapping("/{id}/delete-assignee")
    public String deleteAssignee(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        if (assigneeRepository.findById(id).get().getTodoList().size() > 0) {
            List<Todo> todoList = todoRepository.findAll().stream()
                    .filter(todo -> todo.getAssignee().getId().equals(id)).toList();
            todoList.forEach(todo -> todo.setAssignee(null));
        }
        assigneeRepository.deleteById(id);
        return "redirect:/assignees";
    }

    @GetMapping("/{id}/edit-assignee")
    public String renderEditAssignee(@PathVariable Long id, Model model) {
        model.addAttribute("assignee", assigneeRepository.findById(id).get());
        return "edit-assignee";
    }

    @PostMapping("/{id}/edit-assignee")
    public String editAssignee(@PathVariable Long id, @ModelAttribute(name = "editedName") String editedName) {
        assigneeRepository.findById(id).get().setName(editedName);
        assigneeRepository.save(assigneeRepository.findById(id).get());
        return "redirect:/assignees";
    }

    @GetMapping("/{id}/detail")
    public String renderDetail(@PathVariable Long id, Model model) {
        model.addAttribute("todoList", assigneeRepository.findById(id).get().getTodoList());
        return "detail";
    }
}

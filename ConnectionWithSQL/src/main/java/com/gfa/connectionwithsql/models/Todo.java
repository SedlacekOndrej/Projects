package com.gfa.connectionwithsql.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "is_urgent")
    private boolean urgent;
    @Column(name = "is_done")
    private boolean done;
    @Column(name = "todo_created")
    private Date created;
    @Column(name = "deadline")
    private String dueDate;
    @ManyToOne
    private Assignee assignee;

    public Todo(String title) {
        this.title = title;
        this.urgent = false;
        this.done = false;
        this.created = new Date();
    }

    public Todo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return title;
    }
}

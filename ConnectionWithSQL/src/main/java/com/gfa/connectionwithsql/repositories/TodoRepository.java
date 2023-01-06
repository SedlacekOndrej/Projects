package com.gfa.connectionwithsql.repositories;

import com.gfa.connectionwithsql.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByOrderByIdAsc();

}

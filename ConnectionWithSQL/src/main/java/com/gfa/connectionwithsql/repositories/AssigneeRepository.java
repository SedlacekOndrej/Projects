package com.gfa.connectionwithsql.repositories;

import com.gfa.connectionwithsql.models.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigneeRepository extends JpaRepository<Assignee, Long> {
    List<Assignee> findByOrderByIdAsc ();
}

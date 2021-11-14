package com.example.yebelo.repo;

import com.example.yebelo.model.Number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberRepo extends JpaRepository<Number,Integer> {
}

package com.example.NeoflexTestTask.repository;

import com.example.NeoflexTestTask.entity.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends JpaRepository<Date, Integer> {

    public boolean existsByDate(java.util.Date date);

}

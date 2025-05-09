package com.example.project.repository.admin;

import com.example.project.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByFechaBetween(Date startDate, Date endDate);
}

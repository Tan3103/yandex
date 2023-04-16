package com.tannur.yandex.repositories;

import com.tannur.yandex.models.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
}

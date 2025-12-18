package com.example.demo.repository;

import com.example.demo.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfluencerRepository extends JpaRepository<Influencer, Long> {

    Optional<Influencer> findBySocialHandle(String socialHandle);
}

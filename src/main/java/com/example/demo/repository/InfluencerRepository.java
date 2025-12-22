// InfluencerRepository.java
package com.example.demo.repository;
import com.example.demo.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
    Influencer findBySocialHandle(String socialHandle);
}
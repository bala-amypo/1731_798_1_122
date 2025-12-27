// // InfluencerRepository.java
// package com.example.demo.repository;
// import com.example.demo.model.Influencer;
// import org.springframework.data.jpa.repository.JpaRepository;
// public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
//     Influencer findBySocialHandle(String socialHandle);
// }


package com.example.demo.repository;

import com.example.demo.model.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
    Optional<Influencer> findBySocialHandle(String socialHandle);
}
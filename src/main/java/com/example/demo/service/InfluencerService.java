package com.example.demo.service;

import com.example.demo.model.Influencer;
import java.util.List;

public interface InfluencerService {
    Influencer createInfluencer(Influencer influencer);
    Influencer updateInfluencer(Long id, Influencer influencer);
    Influencer getInfluencerById(Long id);
    List<Influencer> getAllInfluencers();
    void deactivateInfluencer(Long id);
}
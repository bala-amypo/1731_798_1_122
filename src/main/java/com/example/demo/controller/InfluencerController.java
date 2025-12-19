package com.example.demo.controller;

import com.example.demo.model.Influencer;
import com.example.demo.service.InfluencerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/influencers")
@Tag(name = "Influencers")
public class InfluencerController {

    private final InfluencerService influencerService;

    public InfluencerController(InfluencerService influencerService) {
        this.influencerService = influencerService;
    }

    @PostMapping("/")
    public ResponseEntity<Influencer> createInfluencer(@RequestBody Influencer influencer) {
    return ResponseEntity.ok(influencerService.createInfluencer(influencer));
}

    @PutMapping("/{id}")
    public ResponseEntity<Influencer> update(@PathVariable Long id, @RequestBody Influencer influencer) {
        return ResponseEntity.ok(influencerService.updateInfluencer(id, influencer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Influencer> getById(@PathVariable Long id) {
        return ResponseEntity.ok(influencerService.getInfluencerById(id));
    }
    @GetMapping("/")
    public ResponseEntity<List<Influencer>> getAllInfluencers() {
    return ResponseEntity.ok(influencerService.getAllInfluencers());
}
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        influencerService.deactivateInfluencer(id);
        return ResponseEntity.ok().build();
    }
}
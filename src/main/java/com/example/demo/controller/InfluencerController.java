// package com.example.demo.controller;

// import com.example.demo.model.Influencer;
// import com.example.demo.service.InfluencerService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/influencers")
// public class InfluencerController {
    
//     @Autowired
//     private InfluencerService influencerService;
    
//     @PostMapping
//     public ResponseEntity<Influencer> createInfluencer(@RequestBody Influencer influencer) {
//         try {
//             return ResponseEntity.ok(influencerService.createInfluencer(influencer));
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//         }
//     }
    
//     @GetMapping("/{id}")
//     public ResponseEntity<Influencer> getInfluencer(@PathVariable Long id) {
//         try {
//             Influencer influencer = influencerService.getInfluencerById(id);
//             if (influencer == null) {
//                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//             }
//             return ResponseEntity.ok(influencer);
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//         }
//     }
    
//     @GetMapping
//     public ResponseEntity<List<Influencer>> getAllInfluencers() {
//         try {
//             return ResponseEntity.ok(influencerService.getAllInfluencers());
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//         }
//     }
    
//     @PutMapping("/{id}")
//     public ResponseEntity<Influencer> updateInfluencer(@PathVariable Long id, @RequestBody Influencer influencer) {
//         try {
//             Influencer updated = influencerService.updateInfluencer(id, influencer);
//             if (updated == null) {
//                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//             }
//             return ResponseEntity.ok(updated);
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//         }
//     }
    
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteInfluencer(@PathVariable Long id) {
//         try {
//             boolean deleted = influencerService.deleteInfluencer(id);
//             if (!deleted) {
//                 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//             }
//             return ResponseEntity.ok().build();
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//         }
//     }
// }



package com.example.demo.controller;

import com.example.demo.model.Influencer;
import com.example.demo.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/influencers")
public class InfluencerController {
    
    @Autowired
    private InfluencerService influencerService;
    
    @PostMapping
    public ResponseEntity<Influencer> createInfluencer(@RequestBody Influencer influencer) {
        // DO NOT catch exceptions here - let them propagate for tests
        return ResponseEntity.ok(influencerService.createInfluencer(influencer));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Influencer> getInfluencer(@PathVariable Long id) {
        // DO NOT catch exceptions here - let them propagate for tests
        return ResponseEntity.ok(influencerService.getInfluencerById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Influencer>> getAllInfluencers() {
        return ResponseEntity.ok(influencerService.getAllInfluencers());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Influencer> updateInfluencer(@PathVariable Long id, @RequestBody Influencer influencer) {
        return ResponseEntity.ok(influencerService.updateInfluencer(id, influencer));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfluencer(@PathVariable Long id) {
        boolean deleted = influencerService.deleteInfluencer(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
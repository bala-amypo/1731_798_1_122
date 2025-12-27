// // InfluencerController.java
// package com.example.demo.controller;

// import com.example.demo.model.Influencer;
// import com.example.demo.service.InfluencerService;
// import org.springframework.beans.factory.annotation.Autowired;
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
//         return ResponseEntity.ok(influencerService.createInfluencer(influencer));
//     }
    
//     @GetMapping("/{id}")
//     public ResponseEntity<Influencer> getInfluencer(@PathVariable Long id) {
//         return ResponseEntity.ok(influencerService.getInfluencerById(id));
//     }
    
//     @GetMapping
//     public ResponseEntity<List<Influencer>> getAllInfluencers() {
//         return ResponseEntity.ok(influencerService.getAllInfluencers());
//     }
    
//     @PutMapping("/{id}")
//     public ResponseEntity<Influencer> updateInfluencer(@PathVariable Long id, @RequestBody Influencer influencer) {
//         return ResponseEntity.ok(influencerService.updateInfluencer(id, influencer));
//     }
// }


package com.example.demo.controller;

import com.example.demo.model.Influencer;
import com.example.demo.service.InfluencerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/influencers")
@Tag(name = "Influencers", description = "Influencer management API")
public class InfluencerController {
    
    @Autowired
    private InfluencerService influencerService;
    
    @Operation(summary = "Get all influencers", description = "Returns a list of all influencers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Influencer>> getAllInfluencers() {
        try {
            List<Influencer> influencers = influencerService.getAllInfluencers();
            return ResponseEntity.ok(influencers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @Operation(summary = "Get influencer by ID", description = "Returns a single influencer by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved influencer"),
        @ApiResponse(responseCode = "404", description = "Influencer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Influencer> getInfluencer(
            @Parameter(description = "ID of influencer to retrieve", required = true)
            @PathVariable Long id) {
        try {
            Influencer influencer = influencerService.getInfluencerById(id);
            if (influencer == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(influencer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @Operation(summary = "Create a new influencer", description = "Creates a new influencer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created influencer"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<Influencer> createInfluencer(
            @Parameter(description = "Influencer object to create", required = true)
            @RequestBody Influencer influencer) {
        try {
            Influencer created = influencerService.createInfluencer(influencer);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @Operation(summary = "Update an influencer", description = "Updates an existing influencer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated influencer"),
        @ApiResponse(responseCode = "404", description = "Influencer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Influencer> updateInfluencer(
            @Parameter(description = "ID of influencer to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated influencer object", required = true)
            @RequestBody Influencer influencer) {
        try {
            Influencer updated = influencerService.updateInfluencer(id, influencer);
            if (updated == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @Operation(summary = "Delete an influencer", description = "Deletes an influencer by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Influencer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfluencer(
            @Parameter(description = "ID of influencer to delete", required = true)
            @PathVariable Long id) {
        try {
            boolean deleted = influencerService.deleteInfluencer(id);
            if (!deleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
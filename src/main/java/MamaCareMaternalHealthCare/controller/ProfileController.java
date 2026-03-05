package MamaCareMaternalHealthCare.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import MamaCareMaternalHealthCare.model.Profile;
import MamaCareMaternalHealthCare.service.ProfileService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // Save Profile
    @PostMapping("/save")
    public ResponseEntity<?> saveProfile(@RequestBody Profile profile){
        Profile savedProfile = profileService.saveProfile(profile);
        return ResponseEntity.ok(savedProfile);
    }

    // Get Profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable UUID id){
        Profile profile = profileService.getProfileById(id);
        return ResponseEntity.ok(profile);
    }

    // Get All Profiles
    @GetMapping("/all")
    public ResponseEntity<?> getAllProfiles(){
        List<Profile> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    // Update Profile
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProfile(@RequestBody Profile profile, @PathVariable UUID id){
        String message = profileService.updateProfile(profile, id);
        return ResponseEntity.ok(message);
    }

    // Delete Profile
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable UUID id){
        String message = profileService.deleteProfile(id);
        return ResponseEntity.ok(message);
    }
}
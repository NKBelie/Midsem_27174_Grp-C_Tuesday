package MamaCareMaternalHealthCare.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MamaCareMaternalHealthCare.model.Profile;
import MamaCareMaternalHealthCare.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    // Save Profile
    public Profile saveProfile(Profile profile) {

        //if(profileRepository.existsByEmail(profile.getEmail())){
        //    throw new RuntimeException("Profile with email " + profile.getEmail() + " already exists.");
       // }

        return profileRepository.save(profile);
    }

    // Get Profile by ID
    public Profile getProfileById(UUID profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile with ID " + profileId + " not found."));
    }

    // Get All Profiles
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    // Update Profile
    public String updateProfile(Profile profile, UUID profileId) {

        Profile existingProfile = profileRepository.findById(profileId).orElse(null);

        if(existingProfile != null){
            existingProfile.setFullName(profile.getFullName());
            existingProfile.setEmail(profile.getEmail());
            existingProfile.setPhoneNumber(profile.getPhoneNumber());
            existingProfile.setAddress(profile.getAddress());

            profileRepository.save(existingProfile);
            return "Profile updated successfully.";
        }

        return "Profile with ID " + profileId + " not found.";
    }

    // Delete Profile
    public String deleteProfile(UUID profileId) {

        Profile existingProfile = profileRepository.findById(profileId).orElse(null);

        if(existingProfile != null){
            profileRepository.delete(existingProfile);
            return "Profile deleted successfully.";
        }

        return "Profile with ID " + profileId + " not found.";
    }
}
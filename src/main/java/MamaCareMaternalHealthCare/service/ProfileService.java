package MamaCareMaternalHealthCare.service;

import org.springframework.beans.factory.annotation.Autowired;

import MamaCareMaternalHealthCare.model.Profile;
import MamaCareMaternalHealthCare.repository.ProfileRepository;

public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile profileSave(Profile profile) {
        if (profile.getId() != null) {
            Profile existingProfile = profileRepository.findById(profile.getId()).orElse(null);
            if (existingProfile != null) {
                existingProfile.setFullName(profile.getFullName());
                existingProfile.setEmail(profile.getEmail());
                existingProfile.setPhoneNumber(profile.getPhoneNumber());
                existingProfile.setAddress(profile.getAddress());
                
                return profileRepository.save(existingProfile);
            } else {
                throw new RuntimeException("Profile with ID " + profile.getId() + " not found.");
            }
        } else {
            return profileRepository.save(profile);
    }
    }
}


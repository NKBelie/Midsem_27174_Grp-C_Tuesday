package MamaCareMaternalHealthCare.service;

import java.util.UUID;

import MamaCareMaternalHealthCare.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MamaCareMaternalHealthCare.model.Location;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public String savechildAndParentLocation(Location location, String parentId) {
        if(parentId !=null){
            Location parent = locationRepository.findById(UUID.fromString(parentId)).orElse(null);
            if(parent != null){
                location.setParent(parent);
            }
        }
             Boolean checkLocation = locationRepository.existsByCode(location.getCode());
             if(checkLocation){
                 return "Location with code " + location.getCode() + " already exists.";
             } else {
                 locationRepository.save(location);
                 return "Location saved successfully.";
             }
    }
    public String updateLocation(Location location, String locationId) {
        Location existingLocation = locationRepository.findById(UUID.fromString(locationId)).orElse(null);
        if (existingLocation != null) {
            existingLocation.setName(location.getName());
            existingLocation.setCode(location.getCode());
            existingLocation.setParent(location.getParent());
            locationRepository.save(existingLocation);
            return "Location updated successfully.";
        } else {
            return "Location with ID " + locationId + " not found.";
        }
    }
    public String deleteLocation(String locationId) {
        Location existingLocation = locationRepository.findById(UUID.fromString(locationId)).orElse(null);
        if (existingLocation != null) {
            locationRepository.delete(existingLocation);
            return "Location deleted successfully.";
        } else {
            return "Location with ID " + locationId + " not found.";
        }
    }
    public String displayAllLocations() {
        return locationRepository.findAll().toString();
    }
    public String displayById(String locationId){
        Location existingLocation = locationRepository.findById(UUID.fromString(locationId)).orElse(null);
        if (existingLocation != null) {
            return existingLocation.toString();
        } else {
            return "Location with ID " + locationId + " not found.";
        }
    }
}
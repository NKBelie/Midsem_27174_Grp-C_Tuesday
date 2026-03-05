package MamaCareMaternalHealthCare.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MamaCareMaternalHealthCare.model.ELocationType;
import MamaCareMaternalHealthCare.model.Location;
import MamaCareMaternalHealthCare.repository.LocationRepository;

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
    public String updateLocation(Location updateLocation, String locationId) {

    Optional<Location> existingLocation = locationRepository.findById(UUID.fromString(locationId));

    if (existingLocation.isPresent()) {

        Location location = existingLocation.get(); // extract entity

        location.setName(updateLocation.getName());
        location.setCode(updateLocation.getCode());
        location.setParent(updateLocation.getParent());
        location.setType(updateLocation.getType());

        locationRepository.save(location);

        return "Location updated successfully.";

    } else {
        return "Location with ID " + locationId + " not found.";
    }
}
    public String deleteLocation(UUID locationId) {
        Location existingLocation = locationRepository.findById((locationId)).orElse(null);
        if (existingLocation != null) {
            locationRepository.delete(existingLocation);
            return "Location deleted successfully.";
        } else {
            return "Location with ID " + locationId + " not found.";
        }
    }
    public List<Location> displayAllLocations() {
        return locationRepository.findAll();
    }
    public Location displayById(UUID locationId){
        Optional<Location> location = locationRepository.findById(locationId);
        return location.orElse(null);
    }
    public List<Location> searchByType(ELocationType type){
        List<Location> locations = locationRepository.findByType(type);
        if(locations!=null && locations.isEmpty()){
            return locations ;
        }else{
            return null;
        }
    }
    public List<Location> searchByName(String name){
        List<Location> locations = locationRepository.findByName(name);
        if(locations!=null && locations.isEmpty()){
            return locations;
        }else{
            return null;
        }
    }
    public List<Location> searchByCode(String code){
        Optional<Location> location = locationRepository.findByCode(code);
        if(location.isPresent()){
            return List.of(location.get());
        }else{
            return null;
        }
    }
    public List<Location> searchByTypeAndName(ELocationType type, String name){
       List<Location> locations = locationRepository.findByTypeAndName(type, name);
       if(locations!=null && locations.isEmpty()){
           return locations;
       }else{
           return null;
       }
    }
    public List<Location> searchByNameStartsWith(String name){
       List<Location> locations = locationRepository.findByNameStartsWith(name);
       if(locations!=null && locations.isEmpty()){
           return locations ;
       }else{
           return null;
       }
    }
}
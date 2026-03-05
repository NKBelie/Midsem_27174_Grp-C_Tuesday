package MamaCareMaternalHealthCare.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import MamaCareMaternalHealthCare.model.Location;
import MamaCareMaternalHealthCare.service.LocationService;

import java.util.List;


import MamaCareMaternalHealthCare.model.ELocationType;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping(value ="/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLocation(@RequestBody Location location,@RequestParam(required = false) String parentId){
        String savedLocation = locationService.savechildAndParentLocation(location,parentId);
        if(savedLocation.equals("Location saved successfully.")){
            return new ResponseEntity<>(savedLocation, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(savedLocation, HttpStatus.CONFLICT);
        }
    }
    @GetMapping(value = "/displayAllLocations", produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>getDisplayAllLocation(){
        return new ResponseEntity<>(locationService.displayAllLocations(),HttpStatus.OK);
    }
    @GetMapping(value = "/displayById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>getDisplayById(@PathVariable UUID id){
        Location location = locationService.displayById(id);
        if(location == null) {
        return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(location, HttpStatus.OK);
        }
    }
    @PutMapping(value="/updateLocation/{locationId}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLocation(@PathVariable String locationId, @RequestBody Location updatedLocation){

    String updateResponse = locationService.updateLocation(updatedLocation, locationId);
    if(updateResponse == null){
        return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
    } else{
        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }
}

    @DeleteMapping(value = "/deleteLocation/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable UUID id){
        String deletedResponse = locationService.deleteLocation(id);
        if(deletedResponse.equals("Location deleted successfully.")){
            return new ResponseEntity<>(deletedResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(deletedResponse, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByType(@RequestParam ELocationType type){
        List<Location> getLocations = locationService.searchByType(type);
        if(getLocations!= null){
            return new ResponseEntity<>("No locations found for the specified type.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(getLocations, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/searchByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByName(@RequestParam String name){
        List<Location> getLocations = locationService.searchByName(name);
        if(getLocations!= null){
            return new ResponseEntity<>("No locations found for the specified name.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(getLocations, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/searchByCode", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByCode(@RequestParam String code){
        List<Location> getLocations = locationService.searchByCode(code);
        if(getLocations!= null){
            return new ResponseEntity<>("No locations found for the specified code.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(getLocations, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/searchByTypeAndName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByTypeAndName(@RequestParam ELocationType type, @RequestParam String name){
        List<Location> getLocations = locationService.searchByTypeAndName(type, name);
        if(getLocations!= null){
            return new ResponseEntity<>("No locations found for the specified type and name.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(getLocations, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/searchByNameStartsWith", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByNameStartsWith(@RequestParam String name){
        List<Location> getLocations = locationService.searchByNameStartsWith(name);
        if(getLocations!= null){
            return new ResponseEntity<>(getLocations , HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No locations found for the specified name.", HttpStatus.NOT_FOUND);
        }
    }
}
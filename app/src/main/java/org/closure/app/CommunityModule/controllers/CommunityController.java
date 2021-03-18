package org.closure.app.CommunityModule.controllers;

import java.util.List;

import org.closure.app.CommunityModule.dto.CommunityResponse;
import org.closure.app.CommunityModule.models.CommunityModel;
import org.closure.app.CommunityModule.services.CommunityService;
import org.closure.app.UserModule.models.UserModel;
import org.closure.app.UserModule.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;




@RestController
@RequestMapping(path = "/v2/api/community")
public class CommunityController {

    @Autowired
    CommunityService service;

    @Autowired
    UserService userService;

    @GetMapping(value="/getbyuser")
    public ResponseEntity<CommunityResponse> findByUserId(@RequestParam(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUsers(userService.getById(userId)));
    }

    @GetMapping(value="/getbyid")
    public ResponseEntity<CommunityResponse> findById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping(value="/getbyname")
    public ResponseEntity<CommunityResponse> findByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
    }

    @PostMapping(value = "/addusertocommunity")
    public ResponseEntity<String> addUserToCommunity(@RequestParam(name = "cId") Long cId, @RequestParam(name = "uId") Long uId)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addUsreToCommunity(uId, cId));
    }
    
    @PostMapping(value="/addcommunity")
    public ResponseEntity<CommunityModel> addCommunity(@RequestBody CommunityModel entity) {
        return ResponseEntity.status(HttpStatus.OK).body(service.addCommunity(entity));
    }

    @RequestMapping(value="/delete", method=RequestMethod.DELETE)
    public String deleteCommunity(@RequestParam Long id) {
        return service.deleteCommunity(id);
    }

    @GetMapping(value="/search")
    public ResponseEntity<List<CommunityResponse>> search(@RequestParam(name = "value") String value) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.search(value));
    }

    @PostMapping(value="/edit")
    public ResponseEntity<CommunityModel> editCommunity(@RequestBody CommunityModel entity) {
        return ResponseEntity.status(HttpStatus.OK).body(service.edit(entity));
    }
    
    @GetMapping(value="/getall")
    public ResponseEntity<List<CommunityModel>> getMethodName() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping(value="/getusers")
    public ResponseEntity<List<UserModel>> getUsers(@RequestParam(name = "cId") Long cId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getUsers(cId));
    }
    
}

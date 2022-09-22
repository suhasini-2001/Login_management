package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entities.UserEntity;
import com.demo.exception.DuplicateRecordException;
import com.demo.exception.RecordNotFoundException;
import com.demo.service.UserService;

@RestController
@RequestMapping(value = "/userentity")
public class UserEntityController {

	@Autowired
	UserService userService;
 //http://localhost:8080/UserApp/userentity/show
	@GetMapping(value = "/show", produces = "application/json")
	List<UserEntity> showUserEntity() {
		System.out.println("User entity controller");
		List<UserEntity> userList = userService.showUserEntity();
		return userList;
	}
	//http://localhost:8080/UserApp/userentity
	@GetMapping(value = "/show3", produces = "application/json")
	public ResponseEntity<UserEntity> findById(@PathVariable("Id")long Id) {
	UserEntity user= userService.findById(Id);
	    return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
	}
   //http://localhost:8080/UserApp/userentity
	@PostMapping(consumes="application/json",produces="application/json")
    ResponseEntity<UserEntity> add(@RequestBody UserEntity user) throws DuplicateRecordException {
        UserEntity Id=userService.add(user);
        if(Id.getId()==0)
        {
            System.out.println("Before exception");
            throw new DuplicateRecordException("UserEntity with this id already Exist");
            
            
        }
    System.out.println("User Entity name in controller is "+Id);
    return ResponseEntity.ok(Id);
    }
	
	//http://localhost:8080/UserApp/userentity
    @PutMapping(consumes="application/json",produces="application/json")
   ResponseEntity<UserEntity> update(@RequestBody UserEntity user)throws RecordNotFoundException{
        UserEntity Id=userService.update(user);
        
        if (Id==null)
        {
            throw new RecordNotFoundException("UserEntity with this name "+user.getId()+"already Exist");
            
            
        }
        System.out.println("UserEntity name in controller is "+Id);
        return ResponseEntity.ok(Id);
    }
    
    //http://localhost:8080/UserApp/vehicleentity/show2/Id
	@DeleteMapping(value = "/show2/{UserId}", produces = "application/json")
	public ResponseEntity<UserEntity> deleteUserById(@PathVariable("UserId") long UserId) {
		userService.deleteUserById(UserId);
		return new ResponseEntity<UserEntity>(HttpStatus.OK);
	}
}
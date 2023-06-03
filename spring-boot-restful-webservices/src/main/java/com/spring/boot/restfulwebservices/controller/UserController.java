package com.spring.boot.restfulwebservices.controller;

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

import com.spring.boot.restfulwebservices.dto.UserDto;
import com.spring.boot.restfulwebservices.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

    // build create User REST API
	 @Operation(
	            summary = "Create User REST API",
	            description = "Create User REST API is used to save user in a database"
	    )
	    @ApiResponse(
	            responseCode = "201",
	            description = "HTTP Status 201 CREATED"
	    )
	@PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto){
    	UserDto savedUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
	 @Operation(
	            summary = "Get User By ID REST API",
	            description = "Get User By ID REST API is used to get a single user from the database"
	    )
	    @ApiResponse(
	            responseCode = "200",
	            description = "HTTP Status 200 SUCCESS"
	    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
    	UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    

    // build get user by first name & last name REST API
    // http://localhost:8080/api/users/1
	 
	 @Operation(
	            summary = "Get User By first name & last name REST API",
	            description = "Get User By first name & last name REST API is used to get a single user from the database"
	    )
	    @ApiResponse(
	            responseCode = "200",
	            description = "HTTP Status 200 SUCCESS"
	    )
    @GetMapping("{firstName}/{lastName}")
    public ResponseEntity<UserDto> getUserByFirstNameAndLastName(@PathVariable("firstName") String firstName,@PathVariable("lastName") String lastName){
    	UserDto userDto = userService.getUserByFirstNameAndLastName(firstName, lastName);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    // Build Get All Users REST API
    // http://localhost:8080/api/users
	 @Operation(
	            summary = "Get All Users REST API",
	            description = "Get All Users REST API is used to get a all the users from the database"
	    )
	    @ApiResponse(
	            responseCode = "200",
	            description = "HTTP Status 200 SUCCESS"
	    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> usersDto = userService.getAllUsers();
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    // Build Update User REST API
	 @Operation(
	            summary = "Update User REST API",
	            description = "Update User REST API is used to update a particular user in the database"
	    )
	    @ApiResponse(
	            responseCode = "200",
	            description = "HTTP Status 200 SUCCESS"
	    )
    @PutMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserDto userDto){
    	userDto.setId(userId);
        UserDto updatedUserDto = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    // Build Delete User REST API
	 @Operation(
	            summary = "Delete User REST API",
	            description = "Delete User REST API is used to delete a particular user from the database"
	    )
	    @ApiResponse(
	            responseCode = "200",
	            description = "HTTP Status 200 SUCCESS"
	    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
    
	/*
	 * @ExceptionHandler(ResourceNotFoundException.class) public
	 * ResponseEntity<ErrorDetails>
	 * handleResourceNotFoundException(ResourceNotFoundException exception,
	 * WebRequest webRequest){
	 * 
	 * ErrorDetails errorDetails = new ErrorDetails( LocalDateTime.now(),
	 * exception.getMessage(), webRequest.getDescription(false), "USER_NOT_FOUND" );
	 * 
	 * return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); }
	 */
}

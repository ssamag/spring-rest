package com.spring.boot.restfulwebservices.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.boot.restfulwebservices.dto.UserDto;
import com.spring.boot.restfulwebservices.entity.User;
import com.spring.boot.restfulwebservices.exception.EmailAlreadyExistsException;
import com.spring.boot.restfulwebservices.exception.ResourceNotFoundException;
import com.spring.boot.restfulwebservices.mapper.AutoUserMapper;
import com.spring.boot.restfulwebservices.repository.UserRepository;
import com.spring.boot.restfulwebservices.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	//private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
    	//User user = UserMapper.mapToUser(userDto);
    	//User user = modelMapper.map(userDto, User.class);
    	
    	 Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

         if(optionalUser.isPresent()){
             throw new EmailAlreadyExistsException("Email Already Exists for User");
         }
    	
    	User user = AutoUserMapper.MAPPER.mapToUser(userDto);
    	
        User savedUser = userRepository.save(user);
        //return UserMapper.mapToUserDto(savedUser);
        //return modelMapper.map(savedUser, UserDto.class);
        
        return  AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
    	 User user = userRepository.findById(userId).orElseThrow(
                 () -> new ResourceNotFoundException("User", "id", userId)
         );
        //return UserMapper.mapToUserDto(optionalUser.get());
        //return modelMapper.map(optionalUser.get(),UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        
    	List<User> usersList = userRepository.findAll();
    	//List<UserDto> usersDtoList = usersList.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
		/*
		 * List<UserDto> usersDtoList =
		 * usersList.stream().map((user)->modelMapper.map(user, UserDto.class))
		 * .collect(Collectors.toList());
		 */
    	List<UserDto> usersDtoList = usersList.stream().map((user)->AutoUserMapper.MAPPER.mapToUserDto(user))
    			.collect(Collectors.toList());
    	return usersDtoList; 
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
    	 User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                 () -> new ResourceNotFoundException("User", "id", userDto.getId())
         );
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);
       // return UserMapper.mapToUserDto(updatedUser);
       // return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
    	User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

    	userRepository.deleteById(userId);
    }

	@Override
	public UserDto getUserByFirstNameAndLastName(String firstName, String lastName) {
		Optional<User> optionalUser = userRepository.findByFirstNameAndLastNameIgnoreCase(firstName, lastName);
       // return UserMapper.mapToUserDto( optionalUser.get());
		//return modelMapper.map(optionalUser.get(),UserDto.class);
		 return AutoUserMapper.MAPPER.mapToUserDto(optionalUser.get());
	}
}

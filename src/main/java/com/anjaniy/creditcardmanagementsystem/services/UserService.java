package com.anjaniy.creditcardmanagementsystem.services;

import com.anjaniy.creditcardmanagementsystem.exceptions.InvalidArgumentException;
import com.anjaniy.creditcardmanagementsystem.models.dto.request.CreateUserRequest;
import com.anjaniy.creditcardmanagementsystem.models.dto.UserDto;
import com.anjaniy.creditcardmanagementsystem.models.dto.request.GetUsersRequest;
import com.anjaniy.creditcardmanagementsystem.models.dto.response.GetUsersResponse;
import com.anjaniy.creditcardmanagementsystem.models.entities.User;
import com.anjaniy.creditcardmanagementsystem.repositories.UserRepository;
import com.anjaniy.creditcardmanagementsystem.utilities.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto createUser(CreateUserRequest request) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(request.getGender());
        user.setAddress(Mapper.convert(request.getAddress(), user));
        user.setCreditCards(Mapper.convert(request.getCreditCards(), user));
        return Mapper.convert(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto getUser(String id) {
        if(id.isBlank()){
            throw InvalidArgumentException.from("id ", " id should not be blank!");
        }
        else {
            Optional<User> optionalUser = userRepository.findById(UUID.fromString(id));
            if(optionalUser.isPresent()){
                return Mapper.convert(optionalUser.get());
            } else {
                return null;
            }
        }
    }

    @Transactional
    public UserDto deleteUser(String id) {
        if(id.isBlank()){
            throw InvalidArgumentException.from("id ", " id should not be blank!");
        }
        else{
            Optional<User> optionalUser = userRepository.findById(UUID.fromString(id));
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                UserDto userDto = Mapper.convert(user);
                userRepository.delete(user);
                return userDto;
            } else {
                return null;
            }
        }
    }

    @Transactional(readOnly = true)
    public GetUsersResponse getUsers(GetUsersRequest request) {
        int pageSize = Math.min(Math.max(request.getPageSize(), 10), 100);
        Pageable pageable = PageRequest.of(request.getStartPage(), pageSize);
        Page<User> users = userRepository.findAll(pageable);
        GetUsersResponse getUsersResponse = new GetUsersResponse();
        getUsersResponse.setUser(users.stream().map(Mapper::convert).toList());
        getUsersResponse.setHasNext(users.hasNext());
        getUsersResponse.setHasPrevious(users.hasPrevious());
        getUsersResponse.setNextPage(users.getPageable().next().getPageNumber());
        getUsersResponse.setTotalPages(users.getTotalPages());
        getUsersResponse.setTotalElements((int)users.getTotalElements());
        return getUsersResponse;
    }

    @Transactional
    public UserDto updateUser(UserDto request) {
        if(request.getId().isBlank() || request.getId() == null) {
            throw InvalidArgumentException.from("id ", " id should not be null or empty");
        }
        Optional<User> optionalUser = userRepository.findById(UUID.fromString(request.getId()));
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (request.getName() != null) {
                user.setName(request.getName());
            }
            if (request.getEmail() != null) {
                user.setEmail(request.getEmail());
            }
            if (request.getPhoneNumber() != null) {
                user.setPhoneNumber(request.getPhoneNumber());
            }
            if (request.getGender() != null) {
                user.setGender(request.getGender());
            }
            if (request.getAddress() != null) {
                user.setAddress(null);
                user.setAddress(Mapper.convert(request.getAddress(), user));
            }
            if (request.getCreditCards() != null) {
                user.getCreditCards().clear();
                user.setCreditCards(Mapper.convert(request.getCreditCards(), user));
            }

            return Mapper.convert(user);
        }
        return null;
    }

}

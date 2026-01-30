package com.lucas.financemenager.service;

import com.lucas.financemenager.exception.BusinessException;
import com.lucas.financemenager.model.dto.UserRequest;
import com.lucas.financemenager.model.dto.UserResponse;
import com.lucas.financemenager.model.entity.User;
import com.lucas.financemenager.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    private void validateUser(User user){
        if(user.getName() == null || user.getName().isBlank()){
            throw new BusinessException("Nome é obrigatório.");
        }
        if(user.getEmail() == null || user.getEmail().isBlank()){
            throw new BusinessException("Email é obrigatório.");
        }
        if(user.getPassword() == null || user.getPassword().isBlank()){
            throw new BusinessException("Senha é obrigatória.");
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new BusinessException("E-mail já cadastrado.");
        }
    }

    public UserResponse createUser(UserRequest request){

        User user = new User(
                request.name(),
                request.email(),
                request.password()
        );

        validateUser(user);

        User saved = userRepository.save(user);

        return new UserResponse(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }
    public List<UserResponse> listUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .toList();
    }

}







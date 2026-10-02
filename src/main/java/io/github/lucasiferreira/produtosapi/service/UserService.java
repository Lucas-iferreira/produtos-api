package io.github.lucasiferreira.produtosapi.service;

import io.github.lucasiferreira.produtosapi.dto.UserRequest;
import io.github.lucasiferreira.produtosapi.dto.UserResponse;
import io.github.lucasiferreira.produtosapi.entity.User;
import io.github.lucasiferreira.produtosapi.exception.EntityAlreadyExistsException;
import io.github.lucasiferreira.produtosapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public UserResponse userResponse(UserRequest userRequest) {
        User user = new User();
        if (userRepository.findByUsername(userRequest.username()).isPresent()) {
            throw new EntityAlreadyExistsException("Usuário já existe no sistema!");
        }

        user.setUsername(userRequest.username());
        user.setPassword(encoder.encode(userRequest.password()));
        User userSaved = userRepository.save(user);

        return new UserResponse(userSaved);
    }
}

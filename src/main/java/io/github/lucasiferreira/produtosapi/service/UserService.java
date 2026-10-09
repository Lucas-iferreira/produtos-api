package io.github.lucasiferreira.produtosapi.service;

import io.github.lucasiferreira.produtosapi.dto.UserRequest;
import io.github.lucasiferreira.produtosapi.dto.UserResponse;
import io.github.lucasiferreira.produtosapi.entity.User;
import io.github.lucasiferreira.produtosapi.enums.Role;
import io.github.lucasiferreira.produtosapi.exception.EntityAlreadyExistsException;
import io.github.lucasiferreira.produtosapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Transactional
    public UserResponse register(UserRequest userRequest) {
        User user = new User();
        if (userRepository.findByUsername(userRequest.username()).isPresent()) {
            throw new EntityAlreadyExistsException("Usuário já existe no sistema!");
        }


        user.setUsername(userRequest.username());
        user.setPassword(encoder.encode(userRequest.password()));
        user.setRole(Role.USER);
        User userSaved = userRepository.save(user);

        return new UserResponse(userSaved);
    }

}

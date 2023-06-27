package com.grupo3a.ecommercefrutos2.service;


import com.grupo3a.ecommercefrutos2.entity.User;
import com.grupo3a.ecommercefrutos2.exception.EmailAlreadyRegisteredException;
import com.grupo3a.ecommercefrutos2.exception.EmailNotRegisteredException;
import com.grupo3a.ecommercefrutos2.exception.InvalidCredentialException;
import com.grupo3a.ecommercefrutos2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository repository;

    public User loginUser(User user) {
        User existingUser = repository.findByEmail(user.getEmail()).orElse(null);
        if (existingUser == null)
            throw new EmailNotRegisteredException();
        if (!existingUser.getPassword().equals(user.getPassword()))
            throw new InvalidCredentialException();
        return existingUser;
    }

    public User registerUser(User user) {
        User existingUser = repository.findByEmail(user.getEmail()).orElse(null);
        if (existingUser != null) throw new EmailAlreadyRegisteredException();
        return repository.save(user);
    }

}

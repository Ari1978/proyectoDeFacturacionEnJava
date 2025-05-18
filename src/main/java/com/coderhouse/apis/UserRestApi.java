package com.coderhouse.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.models.User;
import com.coderhouse.interfaces.UserRestInterface;

@Component
public class UserRestApi implements UserRestInterface {

    private final String BASE_URL = "https://6820233f72e59f922ef7d814.mockapi.io/users";

    @Autowired
    private RestTemplate rt;

    @Override
    public List<User> getAllUsers() {
        try {
            ResponseEntity<List<User>> response = rt.exchange(
                BASE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener usuarios: " + e.getMessage());
        }
    }

    @Override
    public User getUserById(String id) {
        try {
            return rt.getForObject(BASE_URL + "/" + id, User.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el usuario: " + e.getMessage());
        }
    }

    @Override
    public User addUser(User user) {
        try {
            return rt.postForObject(BASE_URL, user, User.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear usuario: " + e.getMessage());
        }
    }

    @Override
    public User updateUser(User user) {
        try {
            String url = BASE_URL + "/" + user.getId();  
            rt.put(url, user); 
            return user;       
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @Override
    public void deleteUserById(Long id) {
        try {
            rt.delete(BASE_URL + "/" + id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar usuario: " + e.getMessage());
        }
    }
}

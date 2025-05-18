package com.coderhouse.interfaces;

import java.util.List;
import com.coderhouse.models.User;

public interface UserRestInterface {

    List<User> getAllUsers();

    User getUserById(String id);

    User addUser(User user);

    User updateUser(User user);


	void deleteUserById(Long id);

}

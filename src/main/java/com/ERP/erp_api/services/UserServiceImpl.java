package com.ERP.erp_api.services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ERP.erp_api.domain.User;
import com.ERP.erp_api.exceptions.EtAuthException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;
import com.ERP.erp_api.repositories.UserRepository;

@Service
@Transactional
 public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if(email != null) email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public User registerUser(String username, String email, String password, Integer departementId, Integer roleId) throws EtAuthException {
  

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
            if (!pattern.matcher(email).matches())
                throw new EtAuthException("Invalid Format Email");

        Integer count =userRepository.getCountUsername(username);
        if (count > 0)
            throw new EtAuthException("User is Available");
        
        Integer userId = userRepository.create(username,email,password,departementId,roleId);
        return userRepository.findById(userId);
    }


    @Override
    public List<User> fetchAllUser() {
        return userRepository.findAll();
    }

    
    @Override
    public void removeUser(Integer userId) throws EtResourceNotFoundException {
        userRepository.removeById(userId); 
    }

}

package com.ERP.erp_api.services;

import java.util.List;

import com.ERP.erp_api.domain.User;
import com.ERP.erp_api.exceptions.EtAuthException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface UserService {

    List<User> fetchAllUser();

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String username, String email, String password, Integer departementId, Integer roleId) throws EtAuthException;

    void removeUser(Integer userId) throws EtResourceNotFoundException;
    
} 

package com.ERP.erp_api.repositories;

import java.util.List;

import com.ERP.erp_api.domain.User;
import com.ERP.erp_api.exceptions.EtAuthException;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface UserRepository {

    Integer create(String username, String email, String password, Integer departementId, Integer roleId)throws EtAuthException;

     List<User> findAll() throws EtResourceNotFoundException;

    User findByUsername(String username) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountUsername(String username);

    User findById(Integer userId);

     void removeById(Integer userId)  throws EtBadRequestException;
}

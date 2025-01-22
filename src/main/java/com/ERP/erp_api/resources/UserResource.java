package com.ERP.erp_api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

import com.ERP.erp_api.Constants;
import com.ERP.erp_api.domain.Role;
import com.ERP.erp_api.domain.User;
import com.ERP.erp_api.services.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>>  getAllUser(HttpServletRequest request){
        List<User> user = userService.fetchAllUser();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/Login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        Map<String, String> map = new HashMap<>();
        map.put("message","LoggedIn successfully");
         return new ResponseEntity<>(generateJWTToken(user),HttpStatus.OK);
    }

    @PostMapping("/Create")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String username = (String) userMap.get("username");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        Integer departementId = (Integer) userMap.get("departement_id");
        Integer roleId = (Integer) userMap.get("role_id");
        User user = userService.registerUser(username,email,password,departementId,roleId);
        Map<String, String> map = new HashMap<>();
        map.put("message", "registered succesfully");
        return new ResponseEntity<>(generateJWTToken(user),HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
            .setIssuedAt(new Date(timestamp))
            .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
            .claim("userId", user.getUserId())
            .claim("email", user.getEmail())
            .claim("username", user.getUsername())
            .claim("departementId", user.getDepartementId())
            .claim("roleId", user.getRoleId())
            .compact();
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            return map;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> removeUser(HttpServletRequest request, @PathVariable("userId") Integer userId){
        userService.removeUser(userId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Succes", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}

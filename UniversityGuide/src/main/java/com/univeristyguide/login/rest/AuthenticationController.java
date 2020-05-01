package com.univeristyguide.login.rest;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.univeristyguide.login.dto.AuthTokenDto;
import com.univeristyguide.login.dto.UserDto;
import com.univeristyguide.login.dto.dtoconverter.ToDtoConverter;
import com.univeristyguide.login.entity.AuthToken;
import com.univeristyguide.login.entity.LoginUser;
import com.univeristyguide.login.security.TokenProvider;
import com.univeristyguide.login.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    public String currentUsername;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUserName(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        currentUsername = jwtTokenUtil.getUsernameFromToken(token);
        AuthToken authToken = new AuthToken(token);
        UserDto userDto = ToDtoConverter.userToDtoConverter(userService.findOne(currentUsername));
        AuthTokenDto authTokenDto = new AuthTokenDto();
        authTokenDto.setToken(authToken.getToken());
        authTokenDto.setUser(userDto);
        return ResponseEntity.ok(authTokenDto);
    }
   

    //    @PreAuthorize("hasRole('USER')")
    @GetMapping("/current-user")
    public ResponseEntity<UserDto> getCurrentUser() {
        UserDto userDto = ToDtoConverter.userToDtoConverter(userService.findOne(currentUsername));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
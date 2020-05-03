package com.univeristyguide.login.rest;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.univeristyguide.login.dto.AuthTokenDto;
import com.univeristyguide.login.dto.PostsDto;
import com.univeristyguide.login.dto.UserDto;
import com.univeristyguide.login.dto.dtoconverter.ToDtoConverter;
import com.univeristyguide.login.entity.AuthToken;
import com.univeristyguide.login.entity.LoginUser;
import com.univeristyguide.login.security.TokenProvider;
import com.univeristyguide.login.service.PostsService;
import com.univeristyguide.login.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    public String currentUsername;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    //@Autowired
    private UserService userService;
    private PostsService postsService;
    
   
    
    
    @Autowired
    public AuthenticationController(UserService userService, PostsService postsService) {
		super();
		this.userService = userService;
		this.postsService = postsService;
	}


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
    
    

	  @PostMapping("/getposts")
	  public ResponseEntity<List<PostsDto>> findAllPosts(@RequestBody UserDto userDto)
	  { 
		  if(userDto.getId()>0) {
			  
			  List<PostsDto> posts = postsService.getAllPosts(userDto);
			  return new ResponseEntity<>(posts,HttpStatus.OK); 
			  
		  }
		  else {
			  List<PostsDto> posts = postsService.getAllPosts();
			  return new ResponseEntity<>(posts,HttpStatus.OK);
		  }
		  
	  }
    

}
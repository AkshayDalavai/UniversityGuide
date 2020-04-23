/*package com.univeristyguide.login.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.univeristyguide.login.entity.ConfirmationToken;
import com.univeristyguide.login.entity.User;
import com.univeristyguide.login.repository.ConfirmationTokenRepository;
import com.univeristyguide.login.repository.UserRepository;
import com.univeristyguide.login.service.EmailSenderService;

@RestController
public class UserAccountController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView displayRegistration(ModelAndView modelAndView, User user) {
		
	modelAndView.addObject("user", user);
	modelAndView.setViewName("register");
	return modelAndView;
		
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView registerUser(ModelAndView modelAndView , User user) {
		
		User existingUser = userRepository.findByEmailId(user.getEmail());
		if(existingUser != null) {
			
			modelAndView.addObject("message", "This email already exists!!");
			modelAndView.setViewName("Error");
			
		}
		else {
			
			userRepository.save(user);
			
			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			
			confirmationTokenRepository.saveAndFlush(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("CompleteRegistration");
			mailMessage.setFrom("yashuc2011@gmail.com");
			mailMessage.setText("To confirm your account , please click on the link: " +
			"http://localhost:8082/confirm-account?token=" + confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);
			
			
			modelAndView.addObject("emailId", user.getEmail());
			
			
			modelAndView.setViewName("successfully Registered");
		
		}
		
		return modelAndView;
		
	}
	
	
	public ModelAndView confirmUserAccount(ModelAndView modelAndView , @RequestParam("token") String confirmationToken)
	{
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		
		
		if(token != null) {
			
			User user = userRepository.findByEmailId(token.getUser().getEmail());
			user.setIs_enabled(true);
			userRepository.save(user);
			modelAndView.setViewName("Account Verified");
			
		}
		
		else {
			
			modelAndView.addObject("message", "The link is invalid or broken");
			modelAndView.setViewName("error");
			
		}	
		
		return modelAndView;		
		
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ConfirmationTokenRepository getConfirmationTokenRepository() {
		return confirmationTokenRepository;
	}

	public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}

	public EmailSenderService getEmailSenderService() {
		return emailSenderService;
	}

	public void setEmailSenderService(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}
	
	
	
	
}*/




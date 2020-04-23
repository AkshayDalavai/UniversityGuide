package com.univeristyguide.login.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univeristyguide.login.dto.CommentsDto;

import com.univeristyguide.login.entity.Comments;
import com.univeristyguide.login.service.CommentsService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CommentsRestController {
	
	private CommentsService commentsService ;
	
	@Autowired
	public CommentsRestController(CommentsService theCommentsService)
	{
		commentsService = theCommentsService;
	}
	
	@PostMapping("/comments")
	public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto commentDto)
	{  
		commentDto.setId(0);
		CommentsDto commentsDto = commentsService.createComments(commentDto);
		return new ResponseEntity<>(commentsDto, HttpStatus.OK);
	}
	
	@GetMapping("/comments/{postId}")
	public ResponseEntity<List<CommentsDto>> getCommentByPostId(@PathVariable int postId)
	{
		List<CommentsDto> comments = commentsService.getAllCommentsByPostId(postId);
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@PutMapping("/comments/update")
	public ResponseEntity<CommentsDto> updateComment(@RequestBody Comments comment)
	{
		commentsService.updateComments(comment);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<CommentsDto> deletePost(@PathVariable int commentId)
	{
		commentsService.deleteComments(commentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/comments/{commentId}/likes")
	public ResponseEntity<CommentsDto> likePost(@PathVariable int commentId,@RequestBody final int buttonState)
	{
		commentsService.likesComment(commentId, buttonState);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
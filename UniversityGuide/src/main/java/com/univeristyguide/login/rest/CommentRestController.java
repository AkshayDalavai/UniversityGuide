package com.univeristyguide.login.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api")
public class CommentRestController {
	
	private CommentsService commentsService ;
	
	@Autowired
	public CommentRestController(CommentsService theCommentsService)
	{
		commentsService = theCommentsService;
	}
	
	@PostMapping("/createcomment")
	public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto commentDto)
	{
		commentDto.setId(0);
		CommentsDto theCommentsDto = commentsService.createComments(commentDto);
		return new ResponseEntity<>(theCommentsDto,HttpStatus.OK);
	}
	
	@GetMapping("/getcomments/{postId}")
	public ResponseEntity<List<CommentsDto>> getCommentByPostId(@PathVariable int postId)
	{
		List<CommentsDto> comments = commentsService.getAllCommentsByPostId(postId);
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@PutMapping("/updatecomment")
	public ResponseEntity<CommentsDto> updateComment(@RequestBody Comments comment)
	{
		commentsService.updateComments(comment);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecomment/{commentId}")
	public ResponseEntity<CommentsDto> deleteComment(@PathVariable int commentId)
	{
		commentsService.deleteComments(commentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/likecomment/{commentId}")
	public ResponseEntity<CommentsDto> likeComment(@PathVariable int commentId,@RequestBody final int buttonState)
	{
		commentsService.likesComment(commentId, buttonState);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

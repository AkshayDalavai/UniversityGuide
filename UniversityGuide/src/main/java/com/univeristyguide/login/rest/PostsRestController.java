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

import com.univeristyguide.login.dto.PostsDto;
import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.service.PostsService;

@RestController
@RequestMapping("/api")
public class PostsRestController {
	
	private PostsService postsService;
	
	@Autowired
	public PostsRestController(PostsService thePostsService)
	{
		postsService = thePostsService;
	}
	
	@GetMapping("/getposts")
	public ResponseEntity<List<PostsDto>> findAllPosts()
	{
		List<PostsDto> posts = postsService.getAllPosts();
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	
	@PostMapping("/createpost")
	public ResponseEntity<PostsDto> createPost(@RequestBody PostsDto postsDto)
	{
		postsDto.setId(0);
		
		PostsDto resultPost = postsService.createPost(postsDto);
		return new ResponseEntity<>(resultPost,HttpStatus.OK);
	}
	
	@PutMapping("/updatepost")
	public ResponseEntity<PostsDto> updatePost(@RequestBody Posts posts)
	{
		postsService.updatePosts(posts);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/getpost/{postsId}")
	public ResponseEntity<PostsDto> findPostById(@PathVariable int postsId)
	{
		PostsDto postsDto = postsService.getPostById(postsId);
		return new ResponseEntity<>(postsDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/deletepost/{postId}")
	public ResponseEntity<PostsDto> deletePost(@PathVariable int postsId)
	{
		postsService.deletePosts(postsId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/likepost")
	public ResponseEntity<PostsDto> likePost(@PathVariable int postsId,@RequestBody final int buttonState)
	{
		postsService.likes(postsId, buttonState);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/getposts-by-userid/{userId}")
	public ResponseEntity<List<PostsDto>> getPostsByUserId(@PathVariable int userId)
	{
		List<PostsDto> posts = postsService.getAllPostsByUserId(userId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/getposts-by-categoryid/{categoryId}")
	public ResponseEntity<List<PostsDto>> getPostsByCategoryId(@PathVariable int categoryId)
	{
		List<PostsDto> posts = postsService.getAllPostsByCategoryId(categoryId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
}

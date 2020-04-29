package com.univeristyguide.login.rest;

import java.util.List;

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

import com.univeristyguide.login.dto.CommentsDto;
import com.univeristyguide.login.dto.PostsDto;
import com.univeristyguide.login.dto.PostsandCommentsDto;
import com.univeristyguide.login.entity.Posts;
import com.univeristyguide.login.service.CommentsService;
import com.univeristyguide.login.service.PostsService;

@RestController
@RequestMapping("/api")
public class PostsRestController {
	
	private PostsService postsService;
	private CommentsService commentsService;
	
	@Autowired
	public PostsRestController(PostsService thePostsService, CommentsService theCommentsService)
	{
		postsService = thePostsService;
		commentsService = theCommentsService;
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
	
	@PostMapping("/updatepost")
	public ResponseEntity<PostsDto> updatePost(@RequestBody Posts posts)
	{
		PostsDto post = postsService.updatePosts(posts);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@GetMapping("/getpost/{postsId}")
	public ResponseEntity<?> findPostById(@PathVariable int postsId)

	//@GetMapping("/getpost/{postsId}")
	//public ResponseEntity<PostsDto> findPostById(@PathVariable int postsId)

	{
		PostsDto postsDto = postsService.getPostById(postsId);
	    //final String uri = "http://localhost:8080/api/getcomments/" + postsId;
	    //RestTemplate restTemplate = new RestTemplate();
		
		List<CommentsDto> comments = commentsService.getAllCommentsByPostId(postsId);
		  
		PostsandCommentsDto postsAndComments = new PostsandCommentsDto() ;
		postsAndComments.setComments(comments);
		postsAndComments.setPosts(postsDto);
	  // String result = restTemplate.getForObject(uri, String.class);

	    
	    //http://localhost:8080/api/
	    //http://192.168.1.56:8080/UniversityGuide-0.0.1-SNAPSHOT/api/posts
		return new ResponseEntity<>(postsAndComments, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletepost/{postId}")
	public ResponseEntity<PostsDto> deletePost(@PathVariable int postsId)
	{
		postsService.deletePosts(postsId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@PostMapping("/likeposts")
	public ResponseEntity<PostsDto> likePost(@RequestBody int userId,int postId, boolean likes)
	{
		//postsService.likes(userId, postId, likes);
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

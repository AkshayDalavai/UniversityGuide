package com.univeristyguide.login.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univeristyguide.login.dto.CategoryDto;
import com.univeristyguide.login.entity.Category;
import com.univeristyguide.login.service.CategoryService;


@RestController
@RequestMapping("/api")
public class CategoryRestController {

private CategoryService categoryService;


@Autowired
public CategoryRestController(CategoryService theCategoryService) {
categoryService = theCategoryService;
}

@GetMapping("/getcategories")
public ResponseEntity<List<CategoryDto>> findAllCategory(){
List<CategoryDto> categories = categoryService.getAllCategories();
return new ResponseEntity<>(categories,HttpStatus.OK);
}


@PostMapping("/createcategory")
public ResponseEntity<CategoryDto> createCategory(@RequestBody Category theCategory){

theCategory.setId(0);
CategoryDto resultCategory = categoryService.createCategory(theCategory);
return new ResponseEntity<>(resultCategory , HttpStatus.OK);

}




}

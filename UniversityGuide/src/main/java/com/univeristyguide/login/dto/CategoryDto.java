package com.univeristyguide.login.dto;

import java.util.List;

import com.univeristyguide.login.entity.Posts;

public class CategoryDto {

		private int id;
		
		private String CategoryName;
		
	
  
		public CategoryDto() {
			
		}

		public CategoryDto(int id, String categoryName) {
			
			this.id = id;
			CategoryName = categoryName;
		
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getCategoryName() {
			return CategoryName;
		}

		public void setCategoryName(String categoryName) {
			CategoryName = categoryName;
		}

		@Override
		public String toString() {
			return "CategoryDto [id=" + id + ", CategoryName=" + CategoryName + "]";
		}


	
		
		
		
}

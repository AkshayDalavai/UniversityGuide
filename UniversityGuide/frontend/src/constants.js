export const BASE_URI = 'http://192.168.1.56:8080/UniversityGuide-0.0.1-SNAPSHOT';

export const LOGIN = `${BASE_URI}/api/login`;

export const GET_CATEGORIES  = `${BASE_URI}/api/getcategories`;
export const GET_POSTS  = `${BASE_URI}/api/getposts`;
//postId to be added at the end of url for get_post
export const GET_POST = `${BASE_URI}/api/getpost/`; 
export const CREATE_POST = `${BASE_URI}/api/createpost`;
export const LIKE_POST = `${BASE_URI}/api/likepost/`;
export const UPDATE_POST = `${BASE_URI}/api/updatepost`;

export const CREATE_COMMENT = `${BASE_URI}/api/createcomment`;

export const CREATE_USER = `${BASE_URI}/api/createuser`;

export const getAuthStatus = () => {
    const token = localStorage.getItem("accessToken");
    return {token, isAuthenticated: !!token};
}
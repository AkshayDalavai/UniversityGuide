export const BASE_URI = 'http://192.168.1.56:8080/UniversityGuide-0.0.1-SNAPSHOT';

export const GET_CATEGORIES  = `${BASE_URI}/api/getcategories`;
export const GET_POSTS  = `${BASE_URI}/api/getposts`;
//postId to be added at the end of url for get_post
export const GET_POST = `${BASE_URI}/api/getpost/`;
export const CREATE_POST = `${BASE_URI}/api/createpost`;
export const LIKE_POST = `${BASE_URI}/api/likepost/`;

export const CREATE_USER = `${BASE_URI}/api/createuser`;
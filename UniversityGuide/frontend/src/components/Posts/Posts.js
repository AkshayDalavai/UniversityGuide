import React, { Component } from 'react';
import PropTypes from 'prop-types';
import PostCard from '../Post/PostCard';
import CreateContent from '../CreateContent/CreateContent';
import {Form, FormGroup, Input, Jumbotron} from 'reactstrap';
import Unauthenticated from '../Auth/Unauthenticated';
import axios from 'axios';
import {GET_POSTS, CREATE_POST, UPDATE_POST, LIKE_POST, SEARCH_POSTS, DELETE_POST} from '../../constants'; 
import Progress from 'react-progress-2';

class Posts extends Component {
    static propTypes = {
        categories: PropTypes.array.isRequired,
        isAuthenticated: PropTypes.bool.isRequired,
        loggedinUser: PropTypes.object.isRequired,
        accessToken: PropTypes.string.isRequired
    }

    state = {
        posts: [],
        editPost: {},
        modal: false,
        search: ''
    }
    
    componentDidMount(){
            this.getPosts();
    }

    toggle = () => {
        this.setState((prevState) => {
            return {
                modal: !prevState.modal
            }
        });
    }
    
    handleInputChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    getPosts = () => {
        Progress.show();
        const user =  {
            id: this.props.loggedinUser ? this.props.loggedinUser.id : 0
        }
        axios.post(GET_POSTS, user)
             .then(res => {
                Progress.hide();
                this.setState({
                    posts: res.data
                })
             })
             .catch(err => {
                console.log(err);
                Progress.hide();
             })
    }

    searchPosts = e => {
        e.preventDefault();
        // const user =  {
        //     id: this.props.loggedinUser ? this.props.loggedinUser.id : 0
        // }
        /**
         * @todo: get search results here
         */
        if(this.state.search != ''){
            Progress.show();
            axios.get(`${SEARCH_POSTS}${this.state.search}`)
                 .then(res => {
                    Progress.hide();
                     this.setState({
                         posts: res.data
                     })
                 })
                .catch(err => {
                    Progress.hide();
                    alert(err);
                });
        }else{
            this.getPosts();
        }
    }

    addNewPost = (event, postDetails) => {
        event.preventDefault();
        Progress.show();
        const post = {
            userId: postDetails.userId  ,
            isAnonymous: postDetails.isAnonymous || false,
            categoryId: +postDetails.categoryId,
            title: postDetails.postTitle,
            postContent: postDetails.postContent
        }
        if(postDetails.isEditPost){
            post.id = postDetails.id
            //Update Post
            axios.post(UPDATE_POST, post)
             .then(res =>{
                let posts = [...this.state.posts];
                let idx = posts.findIndex(post => post.id === res.data.id)
                if(idx !== -1)
                    posts[idx] = res.data;
                this.setState({
                    posts
                });
             })
             .catch(err => {
                console.log(err);
             })
             .finally(err => {
                 Progress.hide();
                 this.toggle();
             })

        }else{
            //Create Post
            axios.post(CREATE_POST, post)
             .then(res =>{
                const posts = this.state.posts;
                posts.unshift(res.data);
                this.setState({
                    posts
                });
             })
             .catch(err => {
                console.log(err);
             })
             .finally(err => {
                 Progress.hide();
                 this.toggle();
             })

        }
    }

    likePost = (postId, userId = 1, postslikes = true) => {
        const likeObj = {
            userId,
            postId,
            postslikes
        }
        axios.post(LIKE_POST, likeObj)
             .then(res => {
                let posts = [...this.state.posts];
                let idx = posts.findIndex(post => post.id === postId)
                if(idx !== -1){
                    posts[idx].likes = postslikes;
                    posts[idx].likesCount = res.data;
                }
                this.setState({
                    posts
                });
            })
              .catch(err =>{
                alert(err);
              })
    }

    editPost = (post) => {
        this.setState((prevState) => {
            return {
                editPost: post,
                modal: !prevState.modal
            }
        });
    }

    deletePost = (id) => {
        axios.delete(`${DELETE_POST}${id}`)
             .then(res => {
                let posts = [...this.state.posts];
                let idx = posts.findIndex(post => post.id === id)
                if(idx !== -1)
                    posts.splice(idx, 1)
                this.setState({
                    posts
                });
             })
             .catch(err => {
                 alert(err);
             });
    }

    render() {
        return (
            <div className="mt-2 mb-2">
                {/* <div className="border border-secondary rounded mb-3">
                    <Filter />
                </div> */}
                <div className="container pl-0 pr-0">
                            <Form onSubmit={this.searchPosts}>
                                <FormGroup>
                                    <Input  type="text" name="search" id="search" maxLength="45" className="mb-3 mt-3"
                                            placeholder="Search and Press Enter..." onChange={this.handleInputChange}
                                            value={this.state.search}/>
                                </FormGroup>
                              </Form>
                </div>
                <div className="container border border-secondary rounded" onClick={this.toggle}>
                    <div className="mt-2 float-left ">Create POST</div>
                    <Input  type="text" className="mb-3" readOnly={true}  
                            placeholder="Ask for advice, mentorship,  and more from the community . . ."/>
                </div>
                {this.state.modal ? this.props.isAuthenticated ? 
                <CreateContent categories={this.props.categories} toggle={this.toggle} modal={this.state.modal} 
                               addNewPost={this.addNewPost} editPostObj={this.state.editPost} loggedinUser={this.props.loggedinUser}/> : 
                               <Unauthenticated toggle={this.toggle} modal={this.state.modal}  /> : null}
                
                {this.state.posts && this.state.posts.length > 0 ? this.state.posts.map(indPost => 
                    <PostCard key={indPost.id} editPost={this.editPost} likePost={this.likePost} post={indPost}
                              category={indPost.category} isAuthenticated={this.props.isAuthenticated} accessToken={this.props.accessToken}
                              loggedinUser={this.props.loggedinUser} deletePost={this.deletePost}/>)
                : <Jumbotron className="mt-2 mb-2"><p className="lead">No Posts Yet. Signup/Login to add a post</p></Jumbotron>}
            </div>
        )
    }
}

export default Posts

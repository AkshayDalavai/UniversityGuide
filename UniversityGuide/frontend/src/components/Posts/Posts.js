import React, { Component } from 'react';
import PropTypes from 'prop-types';
import PostCard from '../Post/PostCard';
import CreateContent from '../CreateContent/CreateContent';
import Filter from '../Filter/Filter';
import {Form, FormGroup, Input} from 'reactstrap';

import axios from 'axios';
import {GET_POSTS, CREATE_POST, LIKE_POST} from '../../constants'; 

class Posts extends Component {
    static propTypes = {
        categories: PropTypes.array.isRequired
    }

    state = {
        posts: [],
        modal: false,
        search: ''
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

    componentDidMount(){
        axios.get(GET_POSTS)
            .then(res => {
                console.log(res.data);
                this.setState({
                    posts: res.data
                })
            })
            .catch(err => {
                console.log(err);
            })
        }

    handleSubmit = e => {
        e.preventDefault();
        /**
         * @todo: get search results here
         */
        let posts = this.state.posts;
        posts = posts.filter(post => post.postContent.toLowerCase().includes(this.state.search));
        this.setState({
            posts
        })
    }

    addNewPost = (event, postDetails) => {
        event.preventDefault();
        const post = {
            userId: 1,
            isAnonymous: postDetails.isAnonymous,
            categoryId: +postDetails.categoryId,
            title: postDetails.postTitle,
            postContent: postDetails.postContent
        }
        /**
         * @todo: DO a getPosts once a post is added in order to maintain synchronicity
         */
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
        const posts = this.state.posts;
        posts.unshift(post);
        this.setState({
            posts
        });
        //Close the Modal
        this.toggle();
    }

    likePost = (postId) => {
        
        // axios.post(`${LIKE_POST}`, postId)
        //      .then(res => {
                 
        //      })
    }

    render() {
        return (
            <div className="mt-2 mb-2">
                {/* <div className="border border-secondary rounded mb-3">
                    <Filter />
                </div> */}
                <div className="container border border-secondary rounded mb-3">
                            <Form onSubmit={this.handleSubmit}>
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
                {this.state.modal ? 
                <CreateContent categories={this.props.categories} toggle={this.toggle} modal={this.state.modal}
                               addNewPost={this.addNewPost}/> : null}
                
                {this.state.posts.map(indPost => <PostCard key={indPost.id} likePost={this.likePost} post={indPost} category={indPost.category} />)}
            </div>
        )
    }
}

export default Posts

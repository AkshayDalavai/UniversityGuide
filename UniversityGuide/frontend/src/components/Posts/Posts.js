import React, { Component } from 'react';
import PropTypes from 'prop-types';
import PostCard from '../Post/PostCard';
import CreateContent from '../CreateContent/CreateContent';
import Filter from '../Filter/Filter';
import {Form, FormGroup, Input} from 'reactstrap';
import axios from 'axios';

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
        /**
         * @todo: get posts here
         */
        axios.get('http://192.168.1.56:8080/UniversityGuide-0.0.1-SNAPSHOT/api/user')
            .then(res =>{
                console.log(res);
            })
            .catch(err => {
                console.log(err);
            })
        let posts = localStorage.getItem('posts');
        if(posts){
            this.setState({
                posts: JSON.parse(posts)
            })
        }
    }

    handleSubmit = e => {
        e.preventDefault();
        /**
         * @todo: get search results here
         */
        console.log(this.state.search)
    }
    
    addNewPost = (event, postDetails) => {
        event.preventDefault();
        const post = {
            id: Math.floor(Math.random() * 10000000),
            userID: 'nik@g.com',
            isAnonymous: postDetails.isAnonymous,
            categoryID: postDetails.categoryID,
            postTitle: postDetails.postTitle,
            postContent: postDetails.postContent,
            creationDate: new Date().toISOString(),
            updatedDate: new Date().toISOString(),
            likes: 10,
            comments: 2
        }
        console.log(post);
        const posts = this.state.posts;
        posts.unshift(post);
        localStorage.setItem('posts', JSON.stringify(posts));
        this.setState({
            posts
        });
        //Close the Modal
        this.toggle();
    }

    render() {
        return (
            <div className="mt-2 mb-2">
                <div className="border border-secondary rounded mb-3">
                    <Filter />
                </div>
                <div className="container border border-secondary rounded mb-3">
                            <Form onSubmit={this.handleSubmit}>
                                <FormGroup>
                                    <Input  type="text" name="search" id="search" maxLength="45" className="mb-3 mt-3"
                                            placeholder="Search and Press Enter..." onChange={this.handleInputChange}
                                            value={this.state.searcg}/>
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
                
                {this.state.posts.map(post => <PostCard key={post.id} post={post} category={this.props.categories.filter(cat => cat.id == post.categoryID)[0]} />)}
            </div>
        )
    }
}

export default Posts

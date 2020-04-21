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
        axios.get('http://192.168.1.56:8080/UniversityGuide-0.0.1-SNAPSHOT/api/posts')
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
        console.log(this.state.search)
    }
    
    addNewPost = (event, postDetails) => {
        event.preventDefault();
        const post = {
            userId: 1,
            isAnonymous: postDetails.isAnonymous,
            categoryId: +postDetails.categoryID,
            title: postDetails.postTitle,
            postContent: postDetails.postContent
        }
        console.log(post);
        axios.post('http://192.168.1.56:8080/UniversityGuide-0.0.1-SNAPSHOT/api/posts', post)
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
                
                {this.state.posts.map(indPost => <PostCard key={indPost.id} post={indPost} category={this.props.categories.filter(cat => cat.id == indPost.categoryId)[0]} />)}
            </div>
        )
    }
}

export default Posts

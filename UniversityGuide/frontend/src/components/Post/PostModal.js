import React, {Component} from 'react';
import PostCard from './PostCard';
import { Modal, ModalHeader, ModalBody, Form, FormGroup, Button, CustomInput, Label, Input, Jumbotron } from 'reactstrap';
import PropTypes from 'prop-types';
import {GET_POST, CREATE_COMMENT, LIKE_COMMENT, DELETE_COMMENT} from '../../constants';
import axios from 'axios';

class PostModal extends Component {
    constructor(props){
        super(props);
        this.state = {
            post: {},
            comments: [],
            commentContent: '',
            isAnonymous: false
        };
    }

    static propTypes = {
        postId: PropTypes.number,
    }
    componentDidMount(){
        /**
         * @todo: Make rest call here and get the comments for the post
         * 
         */
       if(this.props.postId){
           axios.get(`${GET_POST}${this.props.postId}`)
                .then(res => {
                    this.setState({
                      post: res.data,
                      comments: res.data.comments
                    });
                })
       }
    }
    handleInputChange = (event) => {
        const value = event.target.type === 'checkbox' ? event.target.checked: event.target.value;
        this.setState({
            [event.target.name]: value
        });
    }

    handleCommentSubmit = (event) => {
        event.preventDefault();
        const comment = {
            userId: this.props.loggedinUser.id,
            postsId: this.state.post.id,
            isAnonymous: this.state.isAnonymous,
            commentsContent: this.state.commentContent
        }
        axios.post(CREATE_COMMENT, comment)
             .then(res => {
                 let comments = [...this.state.comments];
                 comments.unshift(res.data);
                 this.setState({
                     comments,
                     commentContent: '',
                     isAnonymous: false
                 });
             })
             .catch(err => {
                 console.log(err);
             })
    }

    likeComment = (id, userId = 1, postslikes = true, isComments = false, postId) => {
        const likeObj = {
            userId,
        }
        if(isComments){
            likeObj.commentId = id;
            likeObj.postId = postId;
            likeObj.commentLike = postslikes;
            axios.post(LIKE_COMMENT, likeObj)
                 .then(res => {
                    let comments = [...this.state.comments];
                    let idx = comments.findIndex(comment => comment.id === id)
                    if(idx !== -1)
                    comments[idx].likesCount = res.data;
                    this.setState({
                        comments
                    });
                 })
                 .catch(err =>{
                    alert(err);
            })
        }
    }

    deleteComment = (commentId) => {
        axios.delete(`${DELETE_COMMENT}${commentId}`)
             .then(res => {
                let comments = [...this.state.comments];
                let idx = comments.findIndex(comment => comment.id === commentId)
                if(idx !== -1)
                    comments.splice(idx, 1)
                this.setState({
                    comments
                });
             })
             .catch(err => {
                 alert(err);
             })
    }
    render() {
        let comments;
        if(this.state.comments.length > 0){
            comments = <React.Fragment>
                            <div className="container border border-secondary rounded">
                            <div className="text-secondary">COMMENTS</div>
                                {this.state.comments ? this.state.comments.map(comment => {
                                    return <PostCard key={comment.id} post={comment} disable={true} loggedinUser={this.props.loggedinUser}
                                                     likePost={this.likeComment} isAuthenticated={this.props.isAuthenticated} accessToken={this.props.accessToken}
                                                     deletePost={this.deleteComment} isComment={true}>

                                    </PostCard>
                                }) : null}
                            </div>
                        </React.Fragment>
        }else{
            comments = <React.Fragment>
                <Jumbotron>
                    <p className="lead">There are no comments yet.</p>
                    <small>Be the first to share your thoughts</small>
                </Jumbotron>
            </React.Fragment>
        }
        return (
            <Modal isOpen={this.props.modal} toggle={this.props.toggle} centered={true} size="lg">
                <ModalHeader toggle={this.props.toggle}>{this.state.post.title}</ModalHeader>
                <ModalBody style={{'maxHeight': 'calc(100vh - 210px)', 'overflowY': 'auto'}}>
                    {this.state.post ?  
                        <PostCard post={this.state.post} category={this.state.post.category} disable={true} deletePost={this.props.deletePost}
                                 likePost={this.props.likePost} isAuthenticated={this.props.isAuthenticated} loggedinUser={this.props.loggedinUser}
                                 accessToken={this.props.accessToken}/> : null}
                    <Form onSubmit={this.handleCommentSubmit}> 
                        <FormGroup>
                            <Label for="postComment" hidden>Leave your comment here...</Label>
                            <Input  type="textarea" className="mb-3" name="commentContent" onChange={this.handleInputChange}
                                    id="postComment" placeholder="Leave your comment here..." value={this.state.commentContent}/>
                        </FormGroup>
                        <FormGroup className="col-sm-12" >
                            <Button color="danger" className="float-right" onClick={this.handleCommentSubmit}
                                    disabled={!this.state.commentContent || !this.props.isAuthenticated} >Comment</Button>
                            <CustomInput type="checkbox" id="postAnonymously" label="Post Anonymously" onChange={this.handleInputChange}/>
                        </FormGroup>
                    </Form>
                    { comments }
                </ModalBody>
            </Modal>
        )
    }
}

export default PostModal;
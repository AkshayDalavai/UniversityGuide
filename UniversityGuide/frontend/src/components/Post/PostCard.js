import React, { Component } from 'react'
import { Card, CardBody, CardTitle, CardFooter, CardHeader, CardText, Badge, Button} from 'reactstrap';
import {Switch, Route} from 'react-router-dom';
import PropTypes from 'prop-types';
import PostModal from './PostModal';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart as fasHeart, faComments, faPenSquare, faTrash } from '@fortawesome/free-solid-svg-icons';
import {faHeart as farHeart} from '@fortawesome/fontawesome-free-regular';

library.add(fasHeart, faComments, faPenSquare, faTrash);

class PostCard extends Component {
    static propTypes = {
        post: PropTypes.object.isRequired,
        category: PropTypes.object.isRequired,
        isAuthenticated: PropTypes.bool,
        likePost: PropTypes.func,
        loggedinUser: PropTypes.object
    }

    state= {
        modal: false
    }

    toggle = () => {
        if(!this.props.disable){
            this.setState((prevState) => {
                return {
                    modal: !prevState.modal
                }
            })
        }
    }
    render() {
        let postCard;
        if(this.props.post && !!this.props.post.id){
            const {id, isAnonymous, user, title, createdDate, likesCount, commentsCount} = this.props.post;
            postCard = <Card className="mt-2 mb-2" >
                                <CardHeader className="pb-0">
                                    <div className="row">
                                        <div className="col-xs-9 col-md-9 col-lg-9 lead text-left">
                                            {!this.props.disable ? <div className="text-left" > <Badge color="warning">{this.props.category.categoryName}</Badge></div> : null}
                                        </div>
                                        {!this.props.disable && this.props.loggedinUser && (this.props.loggedinUser.id === this.props.post.user.id) ? <div className="col-xs-3 col-lg-3 mb-1" onClick={() => {this.props.editPost(this.props.post)}}>
                                            <Button color="warning" outline className="float-right"> <FontAwesomeIcon icon="pen-square" /> </Button> 
                                        </div> : null}
                                    </div>
                                    <div className="row">
                                        <div className="col-xs-9 col-md-9 col-lg-9 small lead text-left">
                                            {isAnonymous ? "Anonymous" : `${user.lastName}, ${user.firstName}`} | &nbsp;
                                            {createdDate.split('T')[0]}
                                        </div>
                                        {this.props.loggedinUser && (this.props.loggedinUser.id === this.props.post.user.id) ?
                                        <div className="col-xs-3 col-lg-3 mt-1 pb-1" onClick={() => {this.props.deletePost(this.props.post.id)}}>
                                        <Button color="warning" outline className="float-right"><FontAwesomeIcon icon="trash" /></Button>
                                    </div> : null }
                                    </div>                                   
                                </CardHeader>
                                <CardBody onClick={this.toggle}>
                                    {title ? <CardTitle className="text-left font-weight-bold">{title}</CardTitle> : null}
                                    <CardText className="text-left">{this.props.post.postContent ? this.props.post.postContent: this.props.post.commentsContent}</CardText>
                                </CardBody>
                                <CardFooter>
                                    {/* @todo: Add likes and comments here */}
                                    <div className="float-left" >
                                    <Button color="warning" outline onClick={() => this.props.likePost(id, this.props.loggedinUser.id, !this.props.post.likes, this.props.isComment, this.props.post.postsId)} disabled={!this.props.isAuthenticated}>
                                         Likes: <FontAwesomeIcon icon={this.props.post.likes ? fasHeart : farHeart} /> <Badge color="secondary"> {likesCount} </Badge>
                                    </Button> &nbsp;
                                    {!this.props.disable ?<Button color="warning" onClick={this.toggle} outline>Comments: <FontAwesomeIcon icon="comments" /> <Badge color="secondary">{commentsCount}</Badge></Button> : null}
                                    </div>
                                </CardFooter>
                            </Card> 
        }else{
            postCard = null;
        }

        return (
            <React.Fragment>
                {postCard}
                {this.state.modal ? 
                    <PostModal  toggle={this.toggle} category={this.props.category} modal={this.state.modal}
                                postId={this.props.post.id} likePost={this.props.likePost} loggedinUser={this.props.loggedinUser}
                                isAuthenticated={this.props.isAuthenticated} accessToken={this.props.accessToken} deletePost={this.props.deletePost}/>: null}
            </React.Fragment>
        )
    }
}                   
export default PostCard;

import React, { Component } from 'react'
import { Card, CardBody, CardTitle, CardFooter, CardHeader, CardText, Badge, Button} from 'reactstrap';
import {Switch, Route} from 'react-router-dom';
import PropTypes from 'prop-types';
import PostModal from './PostModal';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faComments } from '@fortawesome/free-solid-svg-icons';
library.add(faHeart, faComments);

class PostCard extends Component {
    static propTypes = {
        post: PropTypes.object.isRequired,
        category: PropTypes.object.isRequired
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
            const {id, isAnonymous, user, title, postContent, createdDate, likesCount, commentsCount} = this.props.post;
            postCard = <Card className="mt-2 mb-2" >
                                <CardHeader onClick={this.toggle}>
                                    {!this.props.disable ? <div className="text-left" > <Badge color="warning">{this.props.category.categoryName}</Badge></div> : null}
                                    <div className="small lead text-left">
                                        {isAnonymous ? "Anonymous" : `${user.lastName}, ${user.firstName}`} | &nbsp;
                                        {createdDate.split('T')[0]}
                                    </div>
                                </CardHeader>
                                <CardBody onClick={this.toggle}>
                                    <CardTitle className="text-left">{title}</CardTitle>
                                    <CardText className="text-left">{postContent}</CardText>
                                </CardBody>
                                <CardFooter>
                                    {/* @todo: Add likes and comments here */}
                                    <div className="float-left" >
                                    <Button color="warning" outline onClick={this.props.likePost}> Likes: <FontAwesomeIcon icon="heart" /> <Badge color="secondary"> {likesCount} </Badge></Button> &nbsp;
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
                {this.state.modal ? <PostModal toggle={this.toggle} category={this.props.category} modal={this.state.modal} postId={this.props.post.id}/>: null}
            </React.Fragment>
        )
    }
}

export default PostCard;

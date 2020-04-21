import React, { Component } from 'react'
import { Card, CardBody, CardTitle, CardFooter, CardHeader, CardText, Badge, Button} from 'reactstrap';
import {Switch, Route} from 'react-router-dom';
import PropTypes from 'prop-types';
import PostModal from './PostModal';
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHeart, faComments } from '@fortawesome/free-solid-svg-icons'
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
        const {id, isAnonymous, user, title, postContent, createdDate, likesCount, commentsCount} = this.props.post;

        return (
            <React.Fragment>
               <Card className="mt-2 mb-2" onClick={this.toggle} >
                   <CardHeader>
                        <div className="text-left" > <Badge color="primary" outline>{this.props.category.categoryName}</Badge></div>
                        <div className="small lead text-left">
                            {isAnonymous ? "Anonymous" : `${user.lastName}, ${user.firstName}`} | &nbsp;
                             {createdDate.split('T')[0]}
                        </div>
                   </CardHeader>
                   <CardBody>
                        <CardTitle className="text-left">{title}</CardTitle>
                        <CardText className="text-left">{postContent}</CardText>
                   </CardBody>
                   <CardFooter>
                       {/* @todo: Add likes and comments here */}
                       <div className="float-left" >
                          <Button color="primary" outline> Like: <FontAwesomeIcon icon="heart" /> <Badge color="secondary"> {likesCount} </Badge></Button> &nbsp;
                          <Button color="primary" outline>Comments: <FontAwesomeIcon icon="comments" /> <Badge color="secondary">{commentsCount}</Badge></Button>
                           {/* <AiFillLike />
                           <AiOutlineLike />
                           <FaCommentAlt /> */}
                       </div>
                   </CardFooter>
                </Card> 
                {this.state.modal ? <PostModal toggle={this.toggle} category={this.props.category} modal={this.state.modal} postID={id}/>: null}
            </React.Fragment>
        )
    }
}

export default PostCard;

import React, { Component } from 'react'
import { Card, CardBody, CardTitle, CardFooter, CardHeader, CardText} from 'reactstrap';
import {Switch, Route} from 'react-router-dom';
import PropTypes from 'prop-types';
import PostModal from './PostModal';

import 'font-awesome/css/font-awesome.min.css';

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
        const {id, isAnonymous, userID, postTitle, postContent, creationDate} = this.props.post;

        return (
            <React.Fragment>
               <Card className="mt-2 mb-2" onClick={this.toggle} >
                   <CardHeader>
                        <div className="text-left">{this.props.category.name}</div>
                        <div className="small lead text-left">
                            {isAnonymous ? "Anonymous" : userID} | &nbsp;
                             {creationDate.split('T')[0]}
                        </div>
                   </CardHeader>
                   <CardBody>
                        <CardTitle className="text-left">{postTitle}</CardTitle>
                        <CardText className="text-left">{postContent}</CardText>
                   </CardBody>
                   <CardFooter>
                       {/* @todo: Add likes and comments here */}
                   </CardFooter>
                </Card> 
                {this.state.modal ? <PostModal toggle={this.toggle} category={this.props.category} modal={this.state.modal} postID={id}/>: null}
            </React.Fragment>
        )
    }
}

export default PostCard;

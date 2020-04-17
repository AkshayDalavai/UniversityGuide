import React, { Component } from 'react'
import { Card, CardBody, CardTitle, CardFooter, CardHeader, CardText} from 'reactstrap';
import PropTypes from 'prop-types';

import 'font-awesome/css/font-awesome.min.css';

class PostCard extends Component {
    static propTypes = {
        post: PropTypes.object.isRequired,
        category: PropTypes.object.isRequired
    }
    render() {
        const {isAnonymous, userID, title, postContent, creationDate} = this.props.post;
        return (
            <React.Fragment>
               <Card className="mt-2 mb-2">
                   <CardHeader>
                        <div className="text-left">{this.props.category.name}</div>
                        <div className="small lead text-left">
                            {isAnonymous ? "Anonymous" : userID} | &nbsp;
                            {creationDate.split('T')[0]}
                        </div>
                   </CardHeader>
                   <CardBody>
                        <CardTitle className="text-left">{title}</CardTitle>
                        <CardText className="text-left">{postContent}</CardText>
                   </CardBody>
                   <CardFooter>
                       {/* @todo: Add likes and comments here */}
                   </CardFooter>
                </Card> 
            </React.Fragment>
        )
    }
}

export default PostCard;

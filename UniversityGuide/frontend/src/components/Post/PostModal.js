import React, {Component} from 'react';
import PostCard from './PostCard';
import { Modal, ModalHeader, ModalBody, Form, FormGroup, Button, CustomInput } from 'reactstrap';
import PropTypes from 'prop-types';

class PostModal extends Component {
    constructor(props){
        super(props);
        this.state = {

        };
    }

    static propTypes = {
        post: PropTypes.object.isRequired,
        category: PropTypes.object.isRequired
    }
    componentDidMount(){
        /**
         * @todo: Make rest call here and get the comments for the post
         * 
         */
        console.log('Inside CDM of PostModal');
    }
    render() {
        return (
            <div className="container">
                <PostCard post={this.props.post} category={this.props.category}/>
                <Form > 
                {/* onSubmit={this.handleCommentSubmit} */}
                    <FormGroup>
                        <Label for="postComment" hidden>Leave your comment here...</Label>
                        <Input type="textarea" className="mb-3" name="postComment" id="postComment" placeholder="Leave your comment here..." />
                    </FormGroup>
                    <FormGroup className="float-right" inline>
                        <Button color="danger" inline>Comment</Button>
                        <CustomInput type="checkbox" id="postAnonymously" label="Post Anonymously" />
                    </FormGroup>
                </Form>
                <div className="container border border-secondary rounded">
                        <h3>COMMENTS</h3>
                        {this.state.comments.map(comment => {
                            return <PostCard key={comment.id} post={comment}></PostCard>
                        })}
                </div>
            </div>
        )
    }
}

export default PostModal;
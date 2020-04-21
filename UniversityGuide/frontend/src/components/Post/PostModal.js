import React, {Component} from 'react';
import PostCard from './PostCard';
import { Modal, ModalHeader, ModalBody, Form, FormGroup, Button, CustomInput, Label, Input } from 'reactstrap';
import PropTypes from 'prop-types';

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
        postID: PropTypes.number,
        category: PropTypes.object
    }
    componentDidMount(){
        /**
         * @todo: Make rest call here and get the comments for the post
         * 
         */
       if(this.props.postID){
           /**
            * @todo: Make axios call to get post details
            */
           this.setState({
               post : {
                id: 3,
                userID: 'xyz123@syr.edu',
                categoryID: 2,
                title: 'POST TITLE 3',
                creationDate: new Date().toISOString(),
                hasComments: true,
                postContent: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur in mi dui. Aliquam vestibulum posuere ex id lacinia. Donec ullamcorper, arcu vehicula pellentesque vulputate, metus nisl auctor odio, et ultricies ligula velit at mi. Quisque nulla velit, malesuada at diam et, dignissim facilisis ipsum. Curabitur a dolor at nisi vehicula interdum.',
                likes: 20,
                comments: 3,
                isAnonymous: false
            },
            
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
        // let comments  =  this.state.comments;
        // comments.unshift(this.state.commentContent);
        // localStorage.setItem('comments', comments)
        // this.setState({
        //     comments
        // });
        this.props.toggle();
    }
    render() {
        return (
            <Modal isOpen={this.props.modal} toggle={this.props.toggle} centered={true}>
                <ModalHeader toggle={this.props.toggle}>{this.state.post.title}</ModalHeader>
                <ModalBody>
                    <PostCard post={this.state.post} category={this.props.category} disable={true}/>
                    <Form onSubmit={this.handleCommentSubmit}> 
                        <FormGroup>
                            <Label for="postComment" hidden>Leave your comment here...</Label>
                            <Input  type="textarea" className="mb-3" name="commentContent" onChange={this.handleInputChange}
                                    id="postComment" placeholder="Leave your comment here..." value={this.state.commentContent}/>
                        </FormGroup>
                        <FormGroup className="col-sm-12" >
                            <Button color="danger" className="float-right"
                                    disabled={!this.state.commentContent} >Comment</Button>
                            <CustomInput type="checkbox" id="postAnonymously" label="Post Anonymously" onChange={this.handleInputChange}/>
                        </FormGroup>
                    </Form>
                    { <div className="container border border-secondary rounded">
                            <div className="text-secondary">COMMENTS</div>
                            {this.state.comments ? this.state.comments.map(comment => {
                                return <PostCard key={comment.id} post={comment}></PostCard>
                            }) : null}
                    </div> }
                </ModalBody>
            </Modal>
        )
    }
}

export default PostModal;
import React, { Component } from 'react'
import { Modal, Form, Input, FormGroup, ModalHeader, ModalBody, CustomInput, Col, Row, Label, Button } from 'reactstrap';

class CreateContent extends Component {
    constructor(props){
        super(props);
        this.state = {
            modal: false,
            id: '',
            id_category: '',
            postTitle: '',
            postsContent: ''
        }
    }

    toggle = () => {
        this.setState((prevState) => {
            return {
                modal: !prevState.modal
            }
        });
    }
    /**
     * @todo: userID, creation_date, updated_date, has_comments: false, to be added bfr sending it to server
     */
    render() {
        return (
            <React.Fragment>
                <div className="container border border-secondary rounded" onClick={this.toggle}>
                    <div className="mt-2 float-left ">Create POST</div>
                    <Input type="text" className="mb-3" readOnly={true}  placeholder="Ask for advice, mentorship,  and more from the community . . ."
                            />
                </div>
                <Modal isOpen={this.state.modal} toggle={this.toggle} size="lg" centered={true}>
                    <ModalHeader toggle={this.toggle}>Create POST</ModalHeader>
                    <ModalBody>
                        <Form onSubmit={this.handleSubmit}>
                            <FormGroup>
                                <Label for="postTitle" hidden>Post Title</Label>
                                <Input type="text" className="mb-3" name="postTitle" id="postTitle" placeholder="Post Title" />
                                <Label for="postContent" hidden>Add a description or link</Label>
                                <Input type="textarea" className="mb-3" name="postContent" id="postContent" placeholder="Add a description or link" />
                            </FormGroup>
                            <Row form>
                                <Col sm={4}>
                                    <FormGroup>
                                        <CustomInput type="checkbox" id="postAnonymously" label="Post Anonymously" />
                                    </FormGroup>
                                </Col>
                                <Col sm={8}>
                                    <FormGroup className="float-right ml-2">
                                        <Button color="danger">Post</Button>
                                    </FormGroup>
                                    <FormGroup className="float-right">
                                        <Input type="select" name="category" id="category">
                                            {this.props.categories.map(category => {
                                                return <option key={category.id}>{category.name}</option>
                                            })}
                                        </Input>
                                    </FormGroup>
                                </Col>
                            </Row>
                        </Form>
                    </ModalBody>
                </Modal>
            </React.Fragment>
        )
    }
}

export default CreateContent

import React, { Component } from 'react'
import { Modal, Form, Input, FormGroup, ModalHeader, ModalBody, CustomInput, Col, Row, Label, Button } from 'reactstrap';
import PropTypes from 'prop-types'
class CreateContent extends Component {
    static propTypes = {
        addNewPost: PropTypes.func.isRequired,
        categories: PropTypes.array.isRequired,
        toggle: PropTypes.func.isRequired,
        modal: PropTypes.bool.isRequired
    }
    constructor(props){
        super(props);
        this.state = {
            isAnonymous: true,
            categoryID: '',
            postTitle: '',
            postContent: ''
        }
    }

    /**
     * @todo: userID, creation_date, updated_date, has_comments: false, to be added bfr sending it to server
     */
    handleInputChange = event =>{
        const value = event.target.type === 'checkbox' ? event.target.checked: event.target.value;
        this.setState({
            [event.target.name]: value
        });
    }

    render() {
        return (
            <React.Fragment>
                <Modal isOpen={this.props.modal} toggle={this.props.toggle} size="lg" centered={true}>
                    <ModalHeader toggle={this.props.toggle}>Create POST</ModalHeader>
                    <ModalBody>
                        <Form onSubmit={(event) => {this.props.addNewPost(event, this.state)}}>
                            <FormGroup>
                                <Label for="postTitle" hidden>Post Title</Label>
                                <Input  type="text" className="mb-3" name="postTitle" id="postTitle" placeholder="Post Title"
                                        value={this.state.postTitle} onChange={this.handleInputChange} />
                                <Label for="postContent" hidden>Add a description or link</Label>
                                <Input type="textarea" className="mb-3" name="postContent" id="postContent" placeholder="Add a description or link"
                                        value={this.state.postContent} onChange={this.handleInputChange} rows={4}/>
                            </FormGroup>
                            <Row form>
                                <Col sm={4}>
                                    <FormGroup>
                                        <CustomInput type="checkbox" id="postAnonymously" label="Post Anonymously" name="isAnonymous"
                                                     value={this.state.isAnonymous} onChange={this.handleInputChange}/>
                                    </FormGroup>
                                </Col>
                                <Col sm={8}>
                                    <FormGroup className="float-right ml-2">
                                        <Button color="danger" disabled={!this.state.postTitle}>Post</Button>
                                    </FormGroup>
                                    <FormGroup className="float-right">
                                        <Input type="select" name="category" id="category" name="categoryID" value={this.state.categoryID} onChange={this.handleInputChange}>
                                            {this.props.categories.map(category => {
                                                return <option key={category.id} value={category.id}>{category.categoryName}</option>
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

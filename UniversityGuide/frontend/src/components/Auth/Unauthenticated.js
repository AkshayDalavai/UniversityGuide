import React, { Component } from 'react'
import {Modal, ModalHeader, ModalBody, Jumbotron} from 'reactstrap';
class Unauthenticated extends Component {
    render() {
        return (
            <React.Fragment>
                  <Modal isOpen={this.props.modal} toggle={this.props.toggle} centered={true}>
                    <ModalHeader toggle={this.props.toggle}>Signup/Login</ModalHeader>
                    <ModalBody>
                        <Jumbotron><p className="lead">Please Signup/Login to continue</p></Jumbotron>
                    </ModalBody>
                </Modal>
            </React.Fragment>
        )
    }
}

export default Unauthenticated


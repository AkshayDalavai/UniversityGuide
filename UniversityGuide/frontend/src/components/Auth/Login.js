import React, {Component} from 'react';
import {Button, Modal, ModalHeader, ModalBody, Form, FormGroup, Label, Input, FormFeedback} from 'reactstrap';
//import PropTypes from 'prop-types';

class Login extends Component{
    constructor(props){
        super(props);
        this.state = {
            modal: false,
            errorMessage: null,
            email: '',
            password: '',
            formErrors: {
                email: false,
                password: false,
                formValid: true
            }
        }
    }

    toggle = () => {
        this.setState((prevState) => {
          return  {
              modal: !prevState.modal
          }
        });
    }

    handleInputChange = event => {
        const {name, value} = event.target;
        //Validate Form Field
        let formErrors = this.validateFormField(name, value);
        if(!formErrors.email && !formErrors.password){
            //Check if fields are empty are not as by default all the error flags are set to false before they are touched
            const {email, password} = this.state;
            if(email && password)
                formErrors.formValid = false;
            else
                formErrors.formValid = true;
        }else
            formErrors.formValid = true;
        this.setState({
            [name]: value,
            formErrors
        });
    }

    validateFormField = (fieldName, value) => {
        let formErrors = this.state.formErrors;
        switch(fieldName){
            case 'email': {
                //Check for Valid email
                let regex = new RegExp(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/);
                if(regex.test(value))
                    formErrors.email = false
                else
                    formErrors.email = true;
            }
            break;
            case 'password': {
                if(value.length > 5)
                    formErrors.password = false
                else
                    formErrors.password = true
            }
            break;
        }
        return formErrors;
    }

    handleSubmit = event => {
        event.preventDefault();
        const user = {
            email: this.state.email,
            password: this.state.password,
            updatedAt: new Date().toISOString()
        }
        console.log(user);
        /**
         * @todo: REST Call here
         * @todo: Handle invalid credentials error once REST is implemented
         */
        this.toggle();
    }

    render(){
        return(
             <div>
                 <Button outline color="info" size="md" onClick={this.toggle} className="mr-3 mb-3">Log In</Button>
                 <Modal isOpen={this.state.modal} toggle={this.toggle} centered={true}>
                     <ModalHeader toggle={this.toggle}>Log In to your account</ModalHeader>
                     <ModalBody>
                         <Form onSubmit={this.handleSubmit}>
                              <FormGroup>
                                 <Label for="email">Email</Label>
                                <Input  type="email" name="email" id="email" maxLength="45" className="mb-3"
                                        placeholder="Enter Your University Email Address" onChange={this.handleInputChange}
                                        value={this.state.email} invalid={this.state.formErrors.email}/>
                                <FormFeedback>Valid Email is Required</FormFeedback>
                              </FormGroup>
                              <FormGroup>
                                 <Label for="password">Password</Label>
                                <Input  type="password" name="password" id="password" maxLength="30" className="mb-3"
                                        placeholder="Enter Password" onChange={this.handleInputChange}
                                        value={this.state.password} invalid={this.state.formErrors.password}/>
                                <FormFeedback>Your Password should be between 6 to 30 characters </FormFeedback>
                              </FormGroup>
                             <FormGroup>
                                <Button color="dark" style={{marginTop: '2rem'}} disabled={this.state.formErrors.formValid} block>Log In</Button>
                             </FormGroup>
                         </Form>
                     </ModalBody>
                 </Modal>
             </div>
        );
    }
}

export default Login;
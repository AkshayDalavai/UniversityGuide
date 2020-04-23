import React, {Component} from 'react';
import {Button, Modal, ModalHeader, ModalBody, Form, FormGroup, Label, Input, FormFeedback} from 'reactstrap';
//import PropTypes from 'prop-types';
import axios from 'axios';
import {CREATE_USER} from '../../constants';

class SignUp extends Component{
    constructor(props){
        super(props);
        this.state = {
            modal: false,
            errorMessage: null,
            firstName: '',
            lastName: '',
            email: '',
            password: '',
            confirmPassword: '',
            university: 'Syracuse University',
            formErrors: {
                firstName: false,
                email: false,
                password: false,
                confirmPassword: false,
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
        if(!formErrors.firstName && !formErrors.email && !formErrors.password && !formErrors.confirmPassword){
            //Check if fields are empty are not as by default all the error flags are set to false before they are touched
            const {firstName, email, password, confirmPassword} = this.state;
            if(firstName && email && password & confirmPassword)
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
            case 'firstName':{
                //Check for Valid name i.e. alphanumeric only
                let regex = new RegExp(/^[a-z0-9]+$/, 'i');
                if(regex.test(value))
                    formErrors.firstName = false
                else
                    formErrors.firstName = true;
            }
            break;
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
            case 'confirmPassword': {
                if(this.state.password === value)
                    formErrors.confirmPassword = false;
                else
                    formErrors.confirmPassword = true;
            }
            break;
        }
        return formErrors;
    }

    handleSubmit = event => {
        event.preventDefault();
        const user = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            password: this.state.password,
            university: this.state.university,
            roles: "USER"
        }
        console.log(user);
        /**
         * @todo: Make REST Call here
         * @todo: Handle User Already Exists error once REST is implemented
         */
        axios.post(CREATE_USER, user)
             .then(res => {
                 alert("User Added Successfully");
                 this.toggle();
             })
             .catch(err => {
                 alert(`Error Occurred ${err}`)
                 this.toggle();
             });
    }

    render(){
        return(
             <div>
                 <Button outline color="success" size="md" onClick={this.toggle}>Sign Up</Button>
                 <Modal isOpen={this.state.modal} toggle={this.toggle} centered={true}>
                     <ModalHeader toggle={this.toggle}>Let's get started</ModalHeader>
                     <ModalBody>
                         <Form onSubmit={this.handleSubmit}>
                             <FormGroup>
                                 <Label for="firstName">First Name</Label>
                                <Input  type="text" name="firstName" id="firstName" maxLength="45" className="mb-3"
                                        placeholder="Enter Your First Name" onChange={this.handleInputChange}
                                        value={this.state.firstName} invalid={this.state.formErrors.firstName}/>
                                <FormFeedback>Oh noes! that is invalid name format, please enter alphanumeric character only</FormFeedback>
                             </FormGroup>
                             <FormGroup>
                                 <Label for="lastName">Last Name</Label>
                                <Input  type="text" name="lastName" id="lastName" maxLength="45" className="mb-3"
                                        placeholder="Enter Your Last Name" onChange={this.handleInputChange}
                                        value={this.state.lastName}/>
                              </FormGroup>
                              <FormGroup>
                                 <Label for="university">University</Label>
                                <Input  type="text" name="university" id="university" maxLength="50" className="mb-3"
                                        placeholder="Enter Your University Name" onChange={this.handleInputChange}
                                        value={this.state.university} disabled/>
                              </FormGroup>
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
                                 <Label for="confirmPassword">Confirm Password</Label>
                                <Input  type="password" name="confirmPassword" id="confirmPassword" maxLength="30" className="mb-3"
                                        placeholder="Confirm Password" onChange={this.handleInputChange}
                                        value={this.state.confirmPassword} invalid={this.state.formErrors.confirmPassword}/>
                                <FormFeedback>Passwords don't match </FormFeedback>
                            </FormGroup>
                             <FormGroup>
                                <Button color="dark" style={{marginTop: '2rem'}} disabled={this.state.formErrors.formValid} block>Sign Up</Button>
                             </FormGroup>
                         </Form>
                     </ModalBody>
                 </Modal>
             </div>
        );
    }
}

export default SignUp;
import React, {Component} from 'react';
import {Button, Modal, ModalHeader, ModalBody, Form, FormGroup, Label, Input, FormFeedback} from 'reactstrap';

class Login extends Component{
    constructor(props){
        super(props);
        this.state = {
            email: '',
            password: '',
            formErrors: {
                email: false,
                password: false,
                formValid: true
            }
        }
    }

    // this.props.loginErrorMessage gives any error on login to be displayed to the user

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
            case 'password': 
                if(value.length > 5)
                    formErrors.password = false
                else
                    formErrors.password = true 
            break;
            default: 
        }
        return formErrors;
    }

    render(){
        return(
             <div>
                 <Button outline color="info" size="md" onClick={this.props.toggle} className="mr-3 mb-3">Log In</Button>
                 <Modal isOpen={this.props.modal} toggle={this.props.toggle} centered={true}>
                     <ModalHeader toggle={this.props.toggle}>Log In to your account</ModalHeader>
                     <ModalBody>
                         <Form onSubmit={(event) => this.props.login(event, this.state)}>
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
import React, {Component} from 'react';
import { Collapse, Navbar, NavbarToggler, NavbarBrand, Nav, NavItem, NavLink, ButtonDropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import SignUp from '../Auth/SignUp';
import Login from '../Auth/Login';
import {Link} from 'react-router-dom';

class Header extends Component{
    constructor(props){
        super(props);
        this.state = {
            isOpen: false,
            dropdownOpen: false
        }
    }

    toggle = () => {
        this.setState((prevState) => {
            return {
                isOpen: !prevState.isOpen
            }
        });
    }

    dropdownToggle = () => {
        this.setState((prevState) => {
            return {
                dropdownOpen: !prevState.dropdownOpen
            }
        });
    }

    render(){
        /**
         * @todo: Conditionally render Login, Logout and Signup buttons
         */
        let navLinks;
        if(this.props.isAuthenticated){
            navLinks = <React.Fragment>
                        <NavItem>
                            <ButtonDropdown isOpen={this.state.dropdownOpen} toggle={this.dropdownToggle}>
                            <DropdownToggle caret>
                                {this.props.loggedinUser ? this.props.loggedinUser.firstName : "User"}
                            </DropdownToggle>
                            <DropdownMenu>
                                <DropdownItem header disabled>My Profile</DropdownItem>
                                <DropdownItem onClick={this.props.logout}>Logout</DropdownItem>
                            </DropdownMenu>
                            </ButtonDropdown>
                        </NavItem>
                    </React.Fragment>
        }else{
            navLinks = <React.Fragment>
                            <NavItem>
                                <Login toggle={this.props.loginToggle} login={this.props.login} loginErrorMessage={this.props.loginErrorMessage} modal={this.props.showLogin}/>
                            </NavItem>
                            <NavItem>
                                <SignUp />
                            </NavItem>
                        </React.Fragment>
        }
        return(
            <div>
                <Navbar color="dark" dark className="pl-3 pr-4" expand="sm">
                        <NavbarBrand href="/" style={{fontFamily: 'monospace'}}>Univer[c]ity Guide</NavbarBrand>
                        <NavbarToggler onClick={this.toggle} />
                        <Collapse isOpen={this.state.isOpen} navbar>
                            <Nav className="ml-auto" navbar>
                                <NavItem>
                                    <NavLink tag={Link} to="/suforum" >SU Forum</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink tag={Link} to="/susports">SU Sports</NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink tag={Link} to="/suhousing" disabled>SU Housing</NavLink>
                                </NavItem>
                                {navLinks}
                            </Nav>
                        </Collapse>
                </Navbar>
            </div>
        );
    }
}



export default Header;
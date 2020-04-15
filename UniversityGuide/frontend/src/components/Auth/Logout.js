import React, { Component } from 'react';
import {NavLink} from 'reactstrap';

export class Logout extends Component {
    /**
     * @todo: Implement logout function here, if used as prop then convert to stateless component
     */
    render() {
        return (
            <React.Fragment>
                <NavLink href="#">Logout</NavLink>
            </React.Fragment>
        )
    }
}

export default Logout;

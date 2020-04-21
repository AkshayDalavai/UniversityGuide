import React, { Component } from 'react';
import {UncontrolledButtonDropdown, DropdownToggle, DropdownMenu, DropdownItem} from 'reactstrap';

class Filter extends Component {
    render() {
        return (
            <React.Fragment>
                <span className="blockquote float-left">Filters:</span>
                <UncontrolledButtonDropdown className="m-2">
                    <DropdownToggle caret>
                        View
                    </DropdownToggle>
                    <DropdownMenu>
                        <DropdownItem>All Posts</DropdownItem>
                        <DropdownItem>My Posts</DropdownItem>
                        <DropdownItem>My Liked Posts</DropdownItem>
                    </DropdownMenu>
                </UncontrolledButtonDropdown>
                <UncontrolledButtonDropdown className="m-2">
                    <DropdownToggle caret>
                        Sort By
                    </DropdownToggle>
                    <DropdownMenu>
                        <DropdownItem>Creation Date</DropdownItem>
                        <DropdownItem>Likes</DropdownItem>
                        <DropdownItem>Logged In User</DropdownItem>
                    </DropdownMenu>
                </UncontrolledButtonDropdown>
            </React.Fragment>
        )
    }
}

export default Filter

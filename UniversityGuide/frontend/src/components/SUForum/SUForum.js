import React, { Component } from 'react';
import Posts from '../Posts/Posts';
import PostModal from '../Post/PostModal';
import {Switch, Route} from 'react-router-dom';

class SUForum extends Component {
    render() {
        return (
            <div className="container">
                <Posts categories={this.props.categories} />
                <Switch>
                    <Route exact path={`SUForum/:postID`}>
                        <PostModal />
                    </Route>
                </Switch>
            </div>
        )
    }
}

export default SUForum;

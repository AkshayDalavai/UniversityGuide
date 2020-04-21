import React, { Component } from 'react';
import Posts from '../Posts/Posts';
import PostModal from '../Post/PostModal';
import {Switch, Route} from 'react-router-dom';

class SUForum extends Component {
    constructor(props){
        super(props);
        this.state = {
            posts: []
          }
    }

    render() {
        return (
            <div className="container">
                <Posts posts={this.state.posts} categories={this.props.categories} />
                <Switch>
                    <Route exact path={`SUForum/:postID`}>
                        <PostModal category={this.props.category}/>
                    </Route>
                </Switch>
            </div>
        )
    }
}

export default SUForum;

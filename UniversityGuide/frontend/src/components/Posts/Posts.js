import React, { Component } from 'react';
import PropTypes from 'prop-types';
import PostCard from '../Post/PostCard';

class Posts extends Component {
    static propTypes = {
        posts: PropTypes.array.isRequired,
        categories: PropTypes.array.isRequired
    }
    render() {
        return (
            <div className="mt-2 mb-2">
                {this.props.posts.map(post => <PostCard key={post.id} post={post} category={this.props.categories.filter(cat => cat.id == post.categoryID)[0]} />)}
            </div>
        )
    }
}

export default Posts

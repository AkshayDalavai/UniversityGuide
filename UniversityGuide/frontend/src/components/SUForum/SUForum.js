import React, { Component } from 'react'
import CreateContent from '../CreateContent/CreateContent';
import Posts from '../Posts/Posts';

class SUForum extends Component {
    constructor(props){
        super(props);
        this.state = {
            categories: [
              {id: '1', name:'Housing'},
              {id: '2', name:'On-Campus Jobs'},
              {id: '3', name:'SU Sports'},
              {id: '4', name:'Classes and Subjects'},
              {id: '5', name:'Random'},
            ],
            posts: [
                {
                id: 1,
                userID: 'nshanege@syr.edu',
                categoryID: 1,
                title: 'POST TITLE',
                creationDate: new Date().toISOString(),
                hasComments: true,
                postContent: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur in mi dui. Aliquam vestibulum posuere ex id lacinia. Donec ullamcorper, arcu vehicula pellentesque vulputate, metus nisl auctor odio, et ultricies ligula velit at mi. Quisque nulla velit, malesuada at diam et, dignissim facilisis ipsum. Curabitur a dolor at nisi vehicula interdum.',
                likes: 20,
                comments: 3,
                isAnonymous: false
            },
            {
                id: 2,
                userID: 'abc@syr.edu',
                categoryID: 4,
                title: 'POST TITLE 2',
                creationDate: new Date().toISOString(),
                hasComments: true,
                postContent: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur in mi dui. Aliquam vestibulum posuere ex id lacinia. Donec ullamcorper, arcu vehicula pellentesque vulputate, metus nisl auctor odio, et ultricies ligula velit at mi. Quisque nulla velit, malesuada at diam et, dignissim facilisis ipsum. Curabitur a dolor at nisi vehicula interdum.',
                likes: 20,
                comments: 3,
                isAnonymous: true
            },
            {
                id: 3,
                userID: 'xyz123@syr.edu',
                categoryID: 2,
                title: 'POST TITLE 3',
                creationDate: new Date().toISOString(),
                hasComments: true,
                postContent: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur in mi dui. Aliquam vestibulum posuere ex id lacinia. Donec ullamcorper, arcu vehicula pellentesque vulputate, metus nisl auctor odio, et ultricies ligula velit at mi. Quisque nulla velit, malesuada at diam et, dignissim facilisis ipsum. Curabitur a dolor at nisi vehicula interdum.',
                likes: 20,
                comments: 3,
                isAnonymous: false
            }
            ]
          }
    }
    render() {
        return (
            <div className="container">
                <CreateContent categories={this.state.categories}/>
                <Posts posts={this.state.posts} categories={this.state.categories} />
            </div>
        )
    }
}

export default SUForum;

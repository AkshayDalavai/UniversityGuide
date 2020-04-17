import React from 'react';
import renderer from 'react-test-renderer';
import Header from './Header';

describe('Test Suite for Header Component', () => {
    it("matches snapshot", () => {
        const tree = renderer.create(<Header/>).toJSON();
        expect(tree).toMatchSnapshot();
    })
})
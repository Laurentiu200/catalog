import React from 'react';
import './learning.css';
import {Link} from "react-router-dom";
import {Button} from "react-bootstrap";
const LearningPage = () => {
    const content = [
        {
            text: "This is a sample text for the learning page. It's a great place to put some informative content.",
            image: "https://via.placeholder.com/150"
        },
        {
            text: "Here is another piece of text. Adding more content helps to fill the page and make it more useful.",
            image: "https://via.placeholder.com/150"
        },
        {
            text: "Yet another text entry to showcase how this learning page can handle multiple items.",
            image: null
        },
        {
            text: null,
            image: "https://via.placeholder.com/150"
        }
    ];

    return (
        <div className="learning-page">
            <h1>Learning Page</h1>
            <div className="content-display">
                {content.map((item, index) => (
                    <div key={index} className="content-item">
                        {item.text && <p>{item.text}</p>}
                        {item.image && <img src={item.image} alt="content" />}
                    </div>
                ))}
            </div>
            <div style={{marginTop: 50}}>
            <Link to={`/`} >
                <Button>Home Page</Button>
            </Link>
            </div>
        </div>
    );
};

export default LearningPage;

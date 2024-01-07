import React from "react";


const ModalGrade = ({ toggleModal }) => {
    return (
        <div>
            <div className="modal">
                <div className="modal-content">
                    <header>
                        <div className="close">
                            <span className="close-btn" onClick={() =>    toggleModal(false)}>
                                &times;
                            </span>
                        </div>
                        <h1>Modal Title</h1>
                    </header>
                    <body>
                    <p>Modal content</p>
                    </body>
                    <footer>
                        <button onClick={() => toggleModal(false)}>Close</button>
                    </footer>
                </div>
            </div>
        </div>
    );
}


export default ModalGrade;
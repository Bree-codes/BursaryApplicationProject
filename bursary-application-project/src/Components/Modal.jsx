import React, { useState, useEffect } from 'react';
import './Modal.css'; // Import CSS file for modal styles

const Modal = ({ message }) => {
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        // Show modal when component mounts
        setShowModal(true);

        // Set timer to close modal after 40 seconds
        const timer = setTimeout(() => {
            setShowModal(false);
        }, 40000); // 40 seconds in milliseconds

        // Clean up timer when component unmounts or modal is closed manually
        return () => clearTimeout(timer);
    }, []);

    return (
        <div className={`modal ${showModal ? 'show' : ''}`}>
            <div className="modal-content">
                <span className="close" onClick={() => setShowModal(false)}>&times;</span>
                <p>{message}</p>
            </div>
        </div>
    );
};

export default Modal;

import { useState, useEffect } from 'react';
import './../ComponetStyles/Modal.css'; // Import CSS file for modal styles

// eslint-disable-next-line react/prop-types
const Modal = ({ message }) => {
    const [showModal, setShowModal] = useState(true);

    useEffect(() => {
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
                {message}
            </div>
        </div>
    );
};

export default Modal;

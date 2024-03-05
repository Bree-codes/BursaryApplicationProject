import { useState } from "react";
import { Button } from "react-bootstrap";

const MenuButton = () => {
    const [isHovered, setIsHovered] = useState(false);

    return (
        <div style={{width: "99%", margin: "10px auto"}}>
            <Button
                className={"p-2 m-2 mt-3"}
                style={{
                    width: "100%",
                    background: isHovered ? "lime" : "transparent",
                    color: "wheat",
                    border: "none",
                }}
                onMouseEnter={() => setIsHovered(true)}
                onMouseLeave={() => setIsHovered(false)}
            >
                Create Form
            </Button>
        </div>
    );
};

export default MenuButton;

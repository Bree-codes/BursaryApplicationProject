import { useState } from "react";
import { Button } from "react-bootstrap";

// eslint-disable-next-line react/prop-types
const MenuButton = ({title,onclick}) => {
    const [isHovered, setIsHovered] = useState(false);

    return (
        <div style={{width: "99%", margin: "2px auto"}}>
            <Button
                className={"p-2"}
                style={{
                    width: "100%",
                    background: isHovered ? "lime" : "transparent",
                    color: "wheat",
                    border: "none",
                }}
                onMouseEnter={() => setIsHovered(true)}
                onMouseLeave={() => setIsHovered(false)}
                onClick={onclick}>
                {title}
            </Button>
        </div>
    );
};

export default MenuButton;

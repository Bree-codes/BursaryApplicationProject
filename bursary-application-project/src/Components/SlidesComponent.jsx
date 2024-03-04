import { Carousel, Image } from "react-bootstrap";
import image3 from "./../Images/image3.jpeg";
import image4 from "./../Images/image4.jpeg";
import image5 from "./../Images/image5.jpeg";
import image6 from "./../Images/image6.jpeg";
import image7 from "./../Images/image7.jpeg";
import image2 from "./../Images/image2.jpeg";
import image1 from "./../Images/image1.jpeg";


const SlidesComponent = () => {
    const images = [image1,image2,image3,image4,image5,image6,image7];
    return (
        <Carousel data-bs-theme="dark"
                  style={{ height: 'calc(100vh - 56px)' }}>
            {images.map( (image, index) => (
                <Carousel.Item key={index}>
                    <div style={{ height: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                        <Image src={image} className="d-block w-100" alt={`Slide ${index}`} />
                    </div>
                </Carousel.Item>
            ))}
        </Carousel>
    );
};

export default SlidesComponent;

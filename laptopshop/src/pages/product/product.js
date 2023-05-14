import React from 'react'
import "./product.css"
import {Link} from "react-router-dom"

function Product() {

    return (
        /*
        <div>
            <div className={styles.main}>
                <div className={styles.card}>
                <h1 className={styles["project-name"]}>Booking App</h1>

                <button className={styles["start-booking-btn"]}>
                    <Link to={"/booking"}>Start Booking</Link>
                </button>
                </div>
            </div>
        </div>*/
        <div>
            <div className="homepage-main">
                <div className="homepage-card">
                    <h1 className="homepage-project-name">Booking App</h1>
                    <button className="homepage-start-booking-btn">
                        <Link to={"/booking"}>Start Booking</Link>
                    </button>
                </div>
            </div>
        </div>
    );
}

export default Product

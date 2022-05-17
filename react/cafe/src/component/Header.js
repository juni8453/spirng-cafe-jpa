import {Link} from "react-router-dom";

export default function Header(){
    return (
        <div className="header">
            <h1>
            <Link to={"/"}> JPA 게시판 </Link>
            </h1>
            <div className="menu">
                <a href="http://localhost:8080/login" className="link">Login</a>
            </div>
        </div>
    );
}
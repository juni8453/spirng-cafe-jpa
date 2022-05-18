import { Link } from "react-router-dom";
import '../component-css/Header.css';

export default function Header() {

    return (
        <div className="nav">
            <div className="nav-left">
                <h1><Link to={"/"}> JPA 게시판 </Link></h1>
            </div>

            <ul className="nav-right">
                <li className="nav-login-btn">
                    <a href="http://localhost:8080/login">로그인</a>
                </li>
                <li className="nav-logout-btn">
                    <a href="http://localhost:8080/">로그아웃</a>
                </li>
            </ul>

        </div>
    );
}

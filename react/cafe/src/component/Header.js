import { Link } from "react-router-dom";
import '../component-css/Header.css';
import { getCookie, removeCookie } from "../actions/Cookie";

export default function Header() {

    function logout() {
        fetch(`http://3.36.169.168:8080/logout`, {
            method: "GET",
            headers: {
                "Authorization": `session=${getCookie("session")}`
            },
        }).then(res => {
            if (res.ok) {
                removeCookie("username");
                removeCookie("session");
            } 
            window.location.reload();
        })
    }

    return (
        <div className="nav">
            <div className="nav-left">
                <h1><Link to={"/"}> JPA 게시판 </Link></h1>
            </div>

            <ul className="nav-right">
                <li className="nav-login-btn">
                    <button><a href="http://3.36.169.168:8080/login">로그인</a></button>
                </li>
                <li className="nav-logout-btn">
                <button onClick={logout}><a>로그아웃</a></button>
                </li>
            </ul>

        </div>
    );
}

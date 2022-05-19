import { useRef } from "react";
import '../component-css/CreateArticle.css';
import { getCookie, removeCookie } from "../actions/Cookie";

export default function CreateArticle() {
    const contentRef = useRef(null);

    function onSubmit(e) {
        e.preventDefault();

        fetch(`http://localhost:8080/articles`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `session=${getCookie("session")}`,
            },
            body: JSON.stringify({
                content: contentRef.current.value
            }),
        }).then(res => {
            if (!res.ok) {
                window.alert("로그인이 필요합니다");
                removeCookie("username");
            }
            window.location.reload();
        });
    }

    return (<>
        <ul>
            {getCookie("username") ? getCookie("username") : "로그인 해주세요!"}
        </ul>
        <form onSubmit={onSubmit} className="form">
            <div className="input_area">
                <input type="text" placeholder="글 입력" ref={contentRef} />
                <button>
                    작성
                </button>
            </div>
        </form>
    </>
    )
}

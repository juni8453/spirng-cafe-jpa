import { useNavigate } from "react-router-dom";
import useFetch from "../hook/useFetch";
import { useRef } from "react";

export default function CreateArticle() {
    const days = useFetch("http://localhost:8080/articles");
    const navigate = useNavigate();
    const contentRef = useRef(null);

    function onSubmit(e) {
        e.preventDefault();

        fetch(`http://localhost:8080/articles`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: "React Post 테스트 중",
                content: contentRef.current.value
            }),
        }).then(res => {
            if (res.ok) {
                window.location.reload();
                navigate("/");  
                // setIsLoading(false);
            }
        });
    }

    return (<>
        <ul>
            여기에 로그인된 멤버이름!
        </ul>
        <form onSubmit={onSubmit}>
            <div className="input_area">
                <input type="text" placeholder="글 입력" ref={contentRef} />
                <button>
                    저-장
                    {/* {isLoading ? "Saving..." : "저장"} */}
                </button>
            </div>
        </form>
    </>
    )
}
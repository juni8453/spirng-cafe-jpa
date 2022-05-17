import { useNavigate } from "react-router-dom";
import useFetch from "../hook/useFetch";

export default function CreateArticle() {
    const days = useFetch("http://localhost:8080/articles");
    const navigate = useNavigate();

    function addDay() {
        fetch(`http://localhost:8080/articles`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                day: days.length + 1,
            }),
        }).then(res => {
            if (res.ok) {
                alert("생성이 완료 되었습니다");
                navigate("/");
            }
        });
    }

    return (<>
        <ul>
            Author
        </ul>
        <form>
            <div className="input_area">
                <input type="text" placeholder="글 입력" ref={contentRef} />
            </div>
            <button
                style={{
                    opacity: isLoading ? 0.3 : 1,
                }}
            >
                {isLoading ? "Saving..." : "저장"}
            </button>
        </form>
    </>
    )
}
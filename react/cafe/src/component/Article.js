import { getCookie } from "../actions/Cookie";

export default function Article({ article }) {

    function del() {
        fetch(`http://localhost:8080/articles/${article.id}`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `session=${getCookie("session")}`
            },
            body: JSON.stringify({
                deleted: true
            })
        }).then(res => {
            if (!res.ok) {
                window.alert("본인의 게시글만 삭제할 수 있습니다");
            }
            window.location.reload();
        }) 
    }

    return (
        <tr>
            <td>{article.memberName}</td>
            <td>{article.content}</td>
            <td>{article.createdDateTime}</td>
            <td><button className="btn_del" onClick={del}>삭제</button></td>
        </tr>
    )
}

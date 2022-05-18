export default function Article({ article }) {

    function del() {
        fetch(`http://localhost:8080/articles/${article.id}`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                content: "삭제됨!"
            })
        }).then(res => {
            if (res.ok) {

            }
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
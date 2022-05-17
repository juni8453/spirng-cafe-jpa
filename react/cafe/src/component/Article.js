import { useState } from "react"

export default function Article({ article }) {

    return (
        <tr>
            <td>{article.memberName}</td>
            <td>{article.content}</td>
            <td>{article.createdDateTime}</td>
        </tr>
    )
}
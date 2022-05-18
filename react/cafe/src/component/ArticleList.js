import useFetch from "../hook/useFetch";
import Article from "./Article";
import CreateArticle from "./CreateArticle";
import '../component-css/CreateArticle.css';
import '../component-css/ArticleTable.css';


export default function ArticleList(props) {
    const data = useFetch("http://localhost:8080/articles");

    return (<>

            <CreateArticle JSESSIONID={props.JSESSIONID} username={props.username}/>

        <div>
            <table class="type03">
                <tr>
                    <td>{data.content && data.content.map(article => (
                    <Article article={article} key={article.id} JSESSIONID={props.JSESSIONID} username={props.username}/>
                        ))}
                    </td>
                </tr>
            </table>
        </div>
    </>
    )
}

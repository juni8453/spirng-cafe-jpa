import useFetch from "../hook/useFetch";
import Article from "./Article";
import CreateArticle from "./CreateArticle";

export default function ArticleList() {
    const data = useFetch("http://localhost:8080/articles");
    // const {day} = useParams();


    return (<>
        <ul className="list_day">
            {CreateArticle}
        </ul>
        <table>
            <tbody>
                {data.content && data.content.map(article => (
                    <Article article={article} key={article.id}/>
                ))}
            </tbody>
        </table>
    </>
    )
}



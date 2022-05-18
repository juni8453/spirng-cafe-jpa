import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from './component/Header';
import ArticleList from './component/ArticleList';
import './Layout.css';
import Cookies from 'universal-cookie';


function App() {
  const cookies = new Cookies();
  const JSESSIONID= cookies.get("session")
  const username= cookies.get("username")
  console.log("세션:"+JSESSIONID )
  console.log("유저이름:"+username )

  return (
    <div className="App">
      <BrowserRouter><div className='div-header'>
        <Header />
      </div>

      <main className='div-main'>
        <Routes>
          <Route path="/" element={<ArticleList JSESSIONID={JSESSIONID} username={username}/>} />
          {/* <Route path="/warning" element={window.confirm("로그인이 필요합니다")} /> */}
        </Routes>
</main>
      </BrowserRouter>

    </div>
  );

}

export default App;

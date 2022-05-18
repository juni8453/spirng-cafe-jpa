import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Header from './component/Header';
import ArticleList from './component/ArticleList';
import './Layout.css';

function App() {
  return (
    <div className="App">

      <BrowserRouter>
      <div className='div-header'>
        <Header />
      </div>

      <main className='div-main'>
        <Routes>
          <Route path="/" element={<ArticleList />} />
        </Routes>
      </main>  

      </BrowserRouter>

    </div>
  );

}

export default App;

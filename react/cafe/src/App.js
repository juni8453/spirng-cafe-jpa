import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Header from './component/Header';
import ArticleList from './component/ArticleList';

function App() {
  return (
    <div className="App">
        <BrowserRouter>
        <Header />
        {}
        <Routes>
          <Route path="/" element={<ArticleList />} />
          {/* <Route path="/day/:day" element={<Day />} /> */}
          {/* <Route path="*" element={<EmptyPage />} /> */}
        </Routes>
       
      </BrowserRouter>
    </div>
  );

}

export default App;

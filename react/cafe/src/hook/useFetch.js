import { useState, useEffect } from "react"

export default function useFetch(url){
    const [data, setData] = useState([]);

    // 두번째 인자를 두지 않으면 렌더링이 될 때 마다 호출된다.
    // 두번째 인자를 의존성 배열이라 한다 - 의존성 배열 값이 변경되는 경우에만 이펙트가 호출된다
    // 렌더링 직후 딱 한번만 호출되게하려면 빈 배열을 인자로 주면 된다 
    useEffect(() => {
        fetch(url)
        .then(response =>{
            return response.json();
        })
        .then(data=>{
            setData(data);
        })
    }, [url]);

    return data;
}
### `CodeSquad 3번쩨 프로젝트` ⚒
#### 
- `기간` : 2022.05.09 ~ 2022.05.20
- `팀 멤버` : BC, Tany (BE / 2)
- `기술 스택` : Spring Boot, MySQL, Spring Data JPA, Boot Strap, Git, AWS EC2, Docker
---

#### `Git Flow`
- 개발할 기능이 생기면 `issue` 를 작성한 뒤 feature(개발), fix(수정) 브랜치를 생성해 개발을 진행한다.
- 개발이 끝난 브랜치는 depoly 브랜치로 머지한 뒤 AWS 서버에 배포한다.
1. `feat` - 새 기능 추가
2. `fix` - 버그 수정
3. `docs` - 문서 수정
4. `test` - 테스트 코드 추가
5. `refact` - 코드 리팩터링

``` text
main
 | - deploy (배포)
 | - develop-BE (개발)
       |
       | - feature (issue 에 맞춰 생성)
       | - fix
      
```
---
#### `Template`
1. `issue Template`
```text
제목: [FEAT] (BE) 기능제목
---

## 💡 issue
CollectionView 구현

## 📝 todo
[ ] 작업1
[ ] 작업2
[ ] 작업3
```


2. `commit Template`
```text
feat: 컬렉션뷰 ViewController 에 적용 (#1)
///공백///
Todo list 를 보여줄 컬렉션뷰를 ViewController 에 적용했다.
```



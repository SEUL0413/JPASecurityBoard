# :computer:JPA Security Board Project



### 프레임워크
JAPA 11  
SpringBoot  
JPA(Spring Data JPA)  
Spring Security    


### DataBase
Oracle  


### 프론트엔드
Html/CSS  
BootStrap  
Thymeleaf    


### 프로젝트 기능
- 게시판 CRUD기능, 조회수, 페이징 기능
- Security 회원가입 및 로그인
  

### 실행화면
<details>
  <summary>화면</summary>
  
**메인화면(로그인 전)**
  
<img width="70%" src="https://user-images.githubusercontent.com/45254193/193804365-44f14639-cbe6-40ca-939a-f6607eeb94a0.PNG"/>

**회원가입**
  
<img width="70%" src="https://user-images.githubusercontent.com/45254193/193804432-d3fd3b4f-badb-4765-a928-94333b83730d.PNG"/>

**로그인**
  
<img width="70%" src="https://user-images.githubusercontent.com/45254193/193804441-c3caaa84-754c-42ce-b4a6-d9ffe5c0e8e8.PNG"/>

**메인화면(로그인 후)**
  
<img width="70%" src="https://user-images.githubusercontent.com/45254193/193804444-7dda1ae4-b5cc-4a7d-a11e-63ea28037209.PNG"/>

**게시판 글등록**
  
<img width="70%" src="https://user-images.githubusercontent.com/45254193/193804451-0a44054b-3cc9-4be9-902b-feaaf24e9805.PNG"/>

**게시판 글목록**
  
<img width="70%" src="https://user-images.githubusercontent.com/45254193/193804455-7dedd094-e60a-45d3-9da5-5c86826e928e.PNG"/>

**게시판 글상세**
  
<img width="70%" src="https://user-images.githubusercontent.com/45254193/193804462-3a078e56-8aae-4f90-89c6-a23632d85498.PNG"/>

**게시판 글수정**
  
<img width="70%" src="https://user-images.githubusercontent.com/45254193/193804467-84ecdd5a-ebd3-4231-b4eb-45f75c3bf76e.PNG"/>

</details>


  
### 추후 보완사항
- 회원가입시 이메일 중복체크, 비밀번호 Validate 필요
- 게시판 검색 및 검색결과 페이징 필요
- 게시판 Create 첨부파일 등록 기능 추가 필요
- js inline코드에서 파일로 빼내어 작성 필요

이번엔 Thymeleaf를 사용해 간단히 서버 사이드 렌더링으로 만들어봤지만
다음엔 API는 그대로 사용하고 vue.js로 바꾸어 클라이언트 사이드 렌더링으로 만들어 볼 예정이다.




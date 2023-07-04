# post-backend

# Use Case
![무제](https://github.com/ironprayer/post-backend/assets/40513725/66ca9885-973b-48e5-857a-530634774a64)


# API 명세
|기능| method	|url	|request	|response|
|---|---|---|---|---|
게시글 작성 |	POST	|/post	|{ “title”: “title”“content”:”content”}	|{”title” :”title”, “writer”: “writer”, “content”:”content”, ”creatdAt”, “createdAt”}
게시글 전체 조회|	GET	|/post|	|	[ {”title” :”title”, “username”: “username”, “content”:”content”, ”creatdAt”, “createdAt”}, … ]
게시글 단건 조회|	GET	|/post/{id}	|id|	{”title” :”title”, “username”: “username”, “content”:”content”, ”creatdAt”, “createdAt”}
게시글 업데이트	|PUT	|/post/{id}	|id, { “title”: “title”, “content”:”content”} |	{”title” :”title”, “username”: “username”, “content”:”content”, ”creatdAt”, “createdAt”}
게시글 삭제	|DELETE	|/post/{id}	|id|	{”Task” : “Delete” , “msg” : “성공”}
유저 회원가입	|POST	|api/auth/signup	|{“username”:“username”, “password”:“password”}|	{”Task” : “SignUp” , “msg” : “Success”}
유저 로그인	|POST	|/api/auth/login	|{“username”:“username”, “password”:“password”}|	{”Task” : “Login” , “msg” : “Success”}

# ERD
![image](https://github.com/ironprayer/post-backend/assets/40513725/745ad8f7-0a22-4ffa-9dd9-33fe7f7b81c6)

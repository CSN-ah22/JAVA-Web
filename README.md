# JAVA-Web
<자바 웹을 다루는 기술> 책을 공부하며 실습 내용을 올립니다 </br>
pro05~pro10 파일까지는 servlet을 다루고 </br>
pro11 부터는 JSP를 다룹니다 </br>
감사합니다 🌼</br>
</br>

---
### 3장

- POST요청에서는 매개변수를 메시지 본문에 넣어 전달한다
- POST를 대량의 정보 송수신에 사용할 수도 있다
- GET은 매개변수를 보존할 수 있는 이점이 있다
- HTML을 전송하기 위한 프로토콜이 HTTP
- HTTP 통신을 위한 포트번호 : 80
- IP주소는 점이 3개

### 요청

[HTTP 로 수신된 내용 구성(요청헤더)](https://www.notion.so/HTTP-13ed9964e37d4f638a63e3ad7a0af89b)

[HTTP 로 수신된 내용 구성(메시지 헤더)](https://www.notion.so/HTTP-e31efa1db8114ad0a8851ad83a0af59c)

[웹 서버에 요청 전달하기(GET/POST)](https://www.notion.so/GET-POST-87fdf55b229349b78072cfb6d46e11f5)

---

### 응답

[HTTP의 응답 구조(메시지 본문)](https://www.notion.so/HTTP-d5e924dec4fe46ae916f48a2d7f7a3b2)

[정보를 인터넷으로 전송하는 구조](https://www.notion.so/7adbe48901164fb5b3cdf3c10638a813)




---
### 2장
- CGI가 뭐야?

    Common Gateway Interface

    - 웹 서버에서 작동하는 프로그램을 만들어 프로그램이 사람 대신 콘텐츠 생성
        - 웹 서버와 콘텐츠 생성 프로그램 간의 연동 구조
        - 동적 콘텐츠 : 프로그램이 생성한 HTML 등의 콘텐츠

            (웹 서버상에서 작동하는 프로그램이 html을 생성하여 돌려준다)

        - 정적 콘텐츠 : 미리 준비되어 변경이 불가능한 콘텐츠

            (웹 서버상에서 미리 준비된 html을 돌려준다)

    - CGI의 구조 사진

        ![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e90f6e1f-1edd-4497-9365-a1c541c1df12/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e90f6e1f-1edd-4497-9365-a1c541c1df12/Untitled.png)

    CGI에 사용되는 언어: C언어 / Perl (텍스트 처리 강점 대신 요청이 많으면 빵꾸) 

    개인 웹 사이트 등의 게시판 등 시스템 설치가 필요한 경우 사용

    JSP가 CGI의 역할을 대신할 수 있음 (웹 컨테이너의 내부에서 서블릿을 실행)

    기업 등 대규모/안정적 가동이 필요한 경우 사용

---

- JSP와 서블릿은 다르다

    서블릿의 문법이 어려워서 (디자인 변경시 프로그램 전체를 수정해야함) JSP가 탄생 

    서블릿은 HTML을 JSP는 자바문법을 다룸

- (http스킴) (www. ~~호스트명)/ (~~.pdf 경로명)
- HTTP+S까지 붙어야 보안용임
- 인트 라넷 (내부) 🐾 인터넷 (상호) 네트를
- HTML의 프로토콜 = HTTP
- Servlet (let이 들어가면 조각이란 뜻) <이거 개발용 자바 JAVAEE
- CGI 의 단점 ◾메모리, 프로세스문제


        ⇓

- JSP 의 단점 ◾디자이너 & 개발자 불협

   - 서블릿이 뭐야? 자바로 만들어진 HTML 등의 웹 콘텐츠 생성 프로그램


        ⇓

- 프레임 워크 개발


---
### 1장
- 데스크톱 애플리케이션

    PC에서 사용하는 워드프로세서, 스프레드시트, 이메일 프로그램

    - 특징
    - 주된 처리는 자신의 컴퓨터(PC)에서 실행</br>
      화면은 운영체제의 기능을 통하여 표시</br>
      애플리케이션을 PC에 설치할 필요가 있음</br>
- 웹 애플리케이션
    - 특징
    - 주된 처리는 PC가 아니라 서버상에서 진행됨</br>
      화면은 HTML로 표현되며, 웹 브라우저에 표시됨</br>
      애플리케이션을 PC에 설치할 필요가 없음</br>
---

인터넷: 컴퓨터들을 상호간에 연결

WWW(World-Wide Web)

URL (Uniform Resource Locator) 고유한 주소 문자열

- 과거의 토대: 미국 국방성의 ARPANET(알파넷)이 현재 인터넷의 원형 (텍스트만)

    현대의 토대: NCSA 모자이크: 문자와 그림을 표현

- 클라이언트-서버 모델

    클라이언트=컴퓨터, 서버=컴퓨터 상에서 작동되는 소프트웨어

    요청(request)  ,  응답(response)

- 아파치 HTTP 서버

    웹 서버용 소프트웨어


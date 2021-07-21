# JAVA-Web
<자바 웹을 다루는 기술> 책을 공부하며 실습 내용을 올립니다 </br>
pro05~pro10 파일까지는 servlet을 다루고 </br>
pro11 부터는 JSP를 다룹니다 </br>
감사합니다 🌼</br>
</br>

---
## 12장
### 실습 도중 문제 발생 및 해결 과정

문제발생 1

- 원인

    web.xml에 
``` 
    <error-page>
    <error-code>404</error-code>
    <location>/err/error_404.jsp</location>
    </error-page>
```
위의 코드를 사용했지만 지정된 jsp로 실행되지 않음

- 해결 방법
    - UTF-8
    - utf-8

    대소문자를 구별하지 않아서 생긴 문제이다

    jsp 이든 html 이든 유티에프팔을 아무렇게나 적으면 안된다

    그렇지 않으면 action 속성이 멋대로 다른 경로를 잡기 때문에 원하던 jsp로 넘어갈 수 없다

    절대 경로를 써도 마찬가지다..

    반드시 통일 되게 쓰자

문제발생2

- 원인

    Several ports (8005, 8080, 8009) required by Tomcat v7.0 Server at localhost are already in use. The server may already be running in another process, or a system process may be using the port. To start this server you will need to stop the other process or change the port number(s).

- 해결 방법
    - cmd를 연다
    - netstat -a -n -o -p tcp    를 입력
    - 로컬 주소열 에서 8009,8090 를 찾는다
    - 그 줄의 pid값을 본다
    - taskkill /f /pid 9124
        - '9124' 부분을 찾은 pid 값으로 바꿔 넣으면 된다

    해결 방법을 찾은 곳:   [https://myblog.opendocs.co.kr/archives/1702](https://myblog.opendocs.co.kr/archives/1702)

문제발생3

- 원인

    [Oracle] ORA-01950: 테이블스페이스 'USERS'에 대한 권한이 없습니다.

- 해결방법
    - cmd 창에서 sqlplus를 치고
    - system 계정으로 접속
    - alter user [유저명] default tablespace users quota unlimited on users;
    - 유저명 부분에 c##scott      !테이블 명 앞에 c##을 붙이는거 잊지말자!

    이후에는 권한이 부여되어 오류가 나지 않는다

    해결 방법을 찾은곳:  [https://javacatcher.tistory.com/23](https://javacatcher.tistory.com/23)

문제 발생4

- 원인

    Cannot load JDBC driver class 'oracle.jdbc.OracleDriver'

- 해결방법

    JDBC 오라클 드라이버가 없어서 발생한 오류

    <%오라클 설치폴더%>\product\11.2.0\client_1\jdbc\lib

    이 위치로 이동하면 ojdbc6.jar 파일이 존재

    ojdbc6.jar 파일을 WEB-INF 파일의 lib 파일에 넣는다




---

## 5장

### 웹 애플리케이션 이란?  :  웹으로 부터 요청을 받아 실행되는 프로그램을 말함

- webShop의 구성 폴더 및 웹 애플리케이션의 구성 요소
- 컨텍스트란?
   - 1. 톰캣의 server.xml에 등록하는 웹 애플리케이션
   - 2. 톰캣 입장에서 인식하는 한 개의 웹 애플리케이션
   - 특징 🎃

    ➢ 웹 애플리케이션당 하나의 컨텍스트가 등록됨</br>
    ➢ 웹 애플리케이션 이름과 같을 수도 있고 다를 수도 있음</br>
    ➢ 컨텍스트 이름은 중복되면 안됨</br>
    ➢ 웹 애플리케이션의 의미를 가장 잘 나타낼 수 있는 명사형으로 지정</br>
    ➢ 대소문자 구분</br>
    ➢ server.xml에 등록</br>
    
- 컨텍스트 등록 과정 일부
<Context path="/webMal" docBase="c:\webShop" reloadable="true"/>

- 해설
    - path : 웹 애플리케이션의 컨텍스트 이름

        웹 브라우저에서 실제 웹 애플리케이션을 요청하는 이름입니다.

    - docBase :  컨텍스트에 대한 실제 웹 애플리케이션이 위치한 경로를 나타냄

         WEB-INF 상위 폴더까지의 경로를 나타냅니다.

    - reloadable : 실행 중 소스 코드가 수정될 경우 바로 갱신할지를 설정

        만약 false로 설정하면 톰캣을 다시 실행해야 추가한 소스 코드의 기능이 반영됩니다.

- 웹 애플리케이션의 동작 과정
    1. 웹 브라우저에서 컨텍스트 이름으로 요청
    2. 톰캣 컨테이너가 요청한 컨텍스트 이름이 server.xml에 있는지 확인
    3. 해당 컨텍스트 이름이 존재하면 실제 웹 애플리케이션 경로로 가서 요청한 리소스를 클라이언트에게 전송
    4. 클라이언트 웹브라우저는 응답받은 내용을 브라우저에 표시
- server.xml 이란?
   - 톰캣은 모든 설정 정보를 XML로 저장한 후 실행 시 정보를 읽어와 설정대로 실행함
- 주소 형식
   - http : [//IP주소](//ip주소) : 포트번호 / 컨텍스이름 / 요청파일이름

      - 웹브라우저에서 웹 애플리케이션으로 요청할때 주소형식 입네다
- 이클립스
   - 하는일

       - a. 특별히 설정을 바꾸지 않는 한 server.xml 에 등록되는 프로젝트 이름은 컨텍스트 이름과 동일하다.
      -  b. 프로젝트를 생성할 때 Dynamic Web Project 를 선택한다.
      -  c. HTML, CSS, JSP 파일 등은 프로젝트 폴더의 WebContent 폴더 밑에 둔다

   - 하지 않는 일

      -  web.xml 파일은 생성하지 않는다.


- 등록되지 않은 컨텍스트 요청시 404 오류 발생

- 개발이 완료된 웹 애플리케이션을 실제 서비스 하기 위한 작업 과정을 배치(Deploy) 라고 함

- 배포하기 위한 기본 확장자는 .war 이다




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


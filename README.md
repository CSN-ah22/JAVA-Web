# JAVA-Web
<자바 웹을 다루는 기술> 책을 공부하며 실습 내용을 올립니다 </br>
pro05~pro10 파일까지는 servlet을 다루고 </br>
pro11 부터는 JSP를 다룹니다 </br>
감사합니다 🌼</br>
</br>

---
## 13장

### 13.1인클루드 액션 태그 사용하기

- 화면이 복잡해지면서 HTML태그에 자바 코드를 같이 써야 하는 문제로 어려움
- 스크립트릿을 제거하고 태그 형태로 기능을 제공하게 됨

[JSP의 여러가지 액션 태그](https://www.notion.so/a64f119cc9e84ea0b3c6db8ccf0a6f23)


- 인클루드 디렉티브 태그처럼 화면을 분활해서 관리할때 사용
- 즉 공통적으로 사용하는 상단 부분이나 왼쪽 메뉴바를 재사용 할때 사용됨

**형식**

```jsx
<jsp:include page="jsp페이지" flush="true 또는 false">
	...
</jsp:include>
```

**분석**

- page는 포함할 JSP 페이지를 의미함
- flush는 지정한 JSP를 실행하기 전 출력 버퍼 비움 여부를 지정함

**인클루드 디렉티브 태그와 인클루드 액션태그의 차이점**

- 액션태그는 param 액션태그를 이용해 동적 처리 가능 / 디렉티브 태그는 정적 처리만 가능
- 액션태그는 포함되는 JSP 가 각각 자바 파일로 생성됨 / 디렉티브 태그는 포함하는 JSP 에 합쳐진 후 한개의 자바파일로 생성
- 액션태그는 요청시에 처리 / 디렉티브 태그는 JSP 를 자바 파일로 변환시 처리됨

### 13.2 포워드 액션 태그 사용하기
- 서블릿에서 다른 서블릿으로 이동하는걸 포워딩 이라 했음
- RequestDispatcher 를 이용하는 방법 대신에
- 액션태그의 포워딩 방법을 쓰면 훨씬 쉬움

**형식**

```jsx
<jsp:forward page="포워딩할 jsp 페이지">
	...
</jsp:include>
```

**분석**
- 문제가 있음. 
- 로그인 할때 아이디를 입력하지 않으면 주의를 주는 문구가
- 최초로 접속하면 나타남
- 해결방법으로 param 액션태그를 사용해서 처리해보았다

**Param + forwarding**
```jsx
//login.jsp 일부
<%
String msg=request.getParameter("msg"); //브라우저에서 접속 시에 msg값을 가져와서 표시, 
					//최초 접속시에는 null이므로 아무것도 표시하지 않음
if(msg!=null){
%>
<h1><%=msg %></h1>
<%
}
%>
```

```jsx
//result2.jsp 일부
<%
String msg="아이디를 입력하지 않았습니다 아이디를 입력해주세요";//login.jsp로 전달할 오류 메시지를 선언함
%>

<%
String userID = request.getParameter("userID");// ID입력값 가져오기
if(userID.length()==0){ //이름값의 길이가 0 이라면
%>
<jsp:forward page="login2.jsp"> //login2.jsp에 넘어가기 + 데이터 전송
<jsp:param value="<%= msg %>" name="msg"/> //msg란 이름으로 오류 메세지 배달
</jsp:forward>
<%
}
%>
<h1>환영합니다 <%=userID %>님!!</h1> //else문, ID를 썼을경우
```

### 13.3 useBean, setPropery, getProperty 액션 태그 사용하기

- 디자이너 입장에서는 자바의 getter나 setter를 사용하는 것보다는 태그를 사용하는게 쉬움
- useBean, setProperty, getProperty 액션 태그를 사용해 객체 생성부터 속성에 값을 저장하거나 가져오는 방법에 대해 알아보는것이 목표
- 자바 빈에 대해 우선적으로 알아보겠음

### 1.  **자바빈에 대해**

- 프로그래밍시 여러 객체를 거치면서 만들어지는 데이터를 저장하거나 전달하는데 사용함
- 서블릿의 VO클래스 또는 자바의 DTO 클래스 같은 개념이라고 할 수 있음
- 자바 빈을 만드는것은 VO클래스를 만드는 방법과 같음

**특징**

- 속성의 접근 제한자는 private 이다
- 각 속성은 각각의 getter/setter를 가진다
- 기본 생성자를 반드시 가지며 다른 생성자도 추가할 수 있다

### 2. 자바 빈을 이용한 회원 정보 조회 실습

🍑 🍋🍑 🍋 🍑

### 1. 밑바탕 준비

- sec01.ex01 패키지 생성
- MemberBean, MemberDAO 클래스를 추가 (서블릿X 자바O)
- member.jsp와 memberForm.html을 생성함

🍑 🍋🍑 🍋 🍑

### 2. MemberBean 작성

- MemberBean = MemberVO 똑같이 구성한다
- 아래의 생성자를 추가한다

```java
public MemberBean(String id, String pwd, String name, String email) {
    	this.id=id;
    	this.pwd=pwd;
    	this.name=name;
    	this.email=email;
    }
```

🍑 🍋🍑 🍋 🍑

### 3. html 작성

1. 회원 정보를 입력한 후 member.jsp로 전송하도록 memberForm.html 작성 (POST)

🍑 🍋🍑 🍋 🍑

### 4. jsp 작성

1. member.jsp 에서 getParameter() 메서드로 입력값을 가져온다
2. MemberBean 객체를 생성하여 입력된 정보를 생성자에 설정한다
3. DAO객체를 만들고 addMember() 메서드를 호출하여 입력정보를 매개변수로써 넣어준다
4. 성공적으로 끝나면 listMembers() 메서드 또한 호출한다
5. listMembers() 의 반환값을 받아 표현식으로 출력한다

### DAO 수정

**listMembers() 메서드 수정**

- MemberVO 객체 대신 MemberBean 객체 생성
- MemberBean 객체의 setter 를 호출해 회원 정보를 생성자에 저장한다
    - 회원 정보는 조회 query 문으로 읽어온 것을 사용
- List에 다시 MemberBean 객체를 저장함

**addMember() 메서드 수정**

- addMember() 메서드의 매개변수로 MemberBean 객체를 받는다 (회원정보가 들어있음)
- MemberBean 객체의 getter를 호출해 회원 정보를 가져온다
- query 문을 pstmt 를 이용해 데베에 전달한다
- 회원 추가 반영

### 완성 src

[JAVA-Web/pro13/src/sec01/ex01 at main · CSN-ah22/JAVA-Web](https://github.com/CSN-ah22/JAVA-Web/tree/main/pro13/src/sec01/ex01)

### 2.  유즈빈 액션 태그를 이용한 회원 정보 조회 실습

**설명**

- 자바 빈을 자주 사용할 경우 화면이 복잡해지는 단점이 있음
- 이러한 단점을 보완하기 위해 나온것이 유즈빈 액션 태그임

**형식**

<jsp:useBean id="빈 이름" class="패키지 이름을 포함한 자바 빈 클래스" [scope="접근범위"]/>

- id는 JSP 페이지에서 자바 빈 객체에 접근할 때 사용할 이름을 의미함
- class는 패키기 이름을 포함한 자바 빈 이름을 말함
- scope는 자바 빈에 대한 접근 범위를 지정하는 역할

**실습**

- WebContent에 member2.jsp 추가
- <jsp:useBean> 액션 태그를 이용하여 MemberBean 클래스에 대해 id가 m인 빈을 생성한다
- <jsp:useBean> 액션 태그는 직접 MemberBean 객체를 생성한다는것과 같은 역할을 함
- m 의 setter를 이용해 입력값을 생성자에 전달합니다

### 완성 src

[JAVA-Web/member2.jsp at main · CSN-ah22/JAVA-Web](https://github.com/CSN-ah22/JAVA-Web/blob/main/pro13/WebContent/member2.jsp)

### 3. setProperty/getProperty 액션 태그를 이용한 회원 정보 실습

- setter를 이용하지  않고 속성값을 설정하고 싶을때 사용 (빈의 생성자 필요 없어짐)
- setProperty : useBean의 속성에 값을 설정하는 태그
- getProperty : useBean의 속성 값을 얻는 태그

- **setProperty  형식**

<jsp:getProperty name="자바 빈 이름"  property="속성이름" value="값" />

- name : <jsp:useBean> 액션 태그의 id 이름
- property: 값을 설정할 속성 이름
- value: 속성에 설정할 속성값 (입력값)

- **getProperty  형식**

<jsp:getProperty name="자바 빈 이름"  property="속성이름" />

- name : <jsp:useBean> 액션 태그의 id 이름
- property: 값을 얻을  속성 이름

- **실습 - 자바의 setter를 사용하지 않고 빈 속성을 설정하기**
    - member3~7.jsp 생성
    - member3 에 <jsp:setProperty> 액션태그를 이용해 빈의 속성을 설정한다
    - 스크립 트릿이 필요 없음 ! (헷갈리지 말기)

    ```java
    <jsp:useBean id='m' class="sec01.ex01.MemberBean" scope="page"/>
    <jsp:setProperty name="m" property="id" value='<%= request.getParameter("id") %>' />
    <jsp:setProperty name="m" property="pwd" value='<%= request.getParameter("pwd") %>' />
    <jsp:setProperty name="m" property="name" value='<%= request.getParameter("name") %>' />
    <jsp:setProperty name="m" property="email" value='<%= request.getParameter("email") %>' />
    ```

    - member4 작성
        - html 의 input 태그의 name을 MemberBean의 속성 이름과 동일하게 설정한다
        - <jsp:setProperty> 액션태그의 param 속성을 이용해 html에서 전달된 매개변수 이름으로 useBean의 속성에 자동으로 값을 설정한다

            ```java
            <jsp:useBean id='m' class="sec01.ex01.MemberBean" scope="page"/>
            <jsp:setProperty name="m" property="id" param="id" />
            <jsp:setProperty name="m" property="pwd" param="pwd" />
            <jsp:setProperty name="m" property="name" param="name" />
            <jsp:setProperty name="m" property="email" param="email" />
            ```

        - getParameter 를 사용하지 않는것이 장점

    - member5 작성
        - <jsp:setProperty> 액션태그에 param 속성을 생략
        - property 속성 이름만 지정해도 html에서 전달받은 매개변수중 같은 매개변수 값을 자동으로 설정해준다

            ```java
            <jsp:useBean id='m' class="sec01.ex01.MemberBean" scope="page"/>
            <jsp:setProperty name="m" property="id"/>
            <jsp:setProperty name="m" property="pwd"/>
            <jsp:setProperty name="m" property="name" />
            <jsp:setProperty name="m" property="email" />
            ```

    - member6 작성
        - <jsp:setProperty> 액션태그의 property 속성에 * 을 지정한다
        - JSP 에서 자동으로 매개변수 이름과 속성 이름을 비교한 후 같은 이름의 속성 이름에 전달된 값을 알아서 설정해준다
        - 따라서 JSP 나 HTML 페이지에서 전달된 데이터를 처리할때 미리 매개변수 이름과 속성 이름을 동일하게 설정하여 편리하게 사용할 수 있음

        ```java
        <jsp:useBean id='m' class="sec01.ex01.MemberBean" scope="page"/>
        <jsp:setProperty name="m" property="*"/>
        ```

- member7 작성
    - html에서 전달받은 입력값을 setProperty 을 사용해서 저장
    - <jsp:getProperty> 액션태그를 이용해서 useBean의 속성에 접근하여 값을 출력한다

        ```java
        <jsp:useBean id='m' class="sec01.ex01.MemberBean" scope="page"/>
        <jsp:setProperty name="m" property="*"/>

        <tr align="center">
        <td>
        <jsp:getProperty name="m" property="id" />
        </td>
        <td>
        <jsp:getProperty name="m" property="pwd" />
        </td>
        <td>
        <jsp:getProperty name="m" property="name" />
        </td>
        <td>
        <jsp:getProperty name="m" property="email" />
        </td>
        </tr>
        ```

    ### 전체 src

    [JAVA-Web/pro13/WebContent at main · CSN-ah22/JAVA-Web](https://github.com/CSN-ah22/JAVA-Web/tree/main/pro13/WebContent)
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
## 11장

### 11.1 JSP 등장 배경

인터넷 사용자의 폭발적인 증가 > 복잡한 화면과 복잡한 코드 >디자이너는 자바코드로 화면기능을 구현하기 어려움 > 화면 기능을 디자이너가 쉽게 작업하기 위해 JSP가 등장함 >HTML&CSS&자바스크립트를 기반으로 JSP가 제공하는 구성 요소들을 입혀 웹페이지를 구현함>재사용성과 유지관리가 수월해짐

### 11.2 JSP의 3단계 작업 과정

JSP는 HTML CSS 자바스크립트 및 JSP구성요소 등 복잡하게 구성되어 있기에 

파일 자체를 브라우저에 바로 전송하면 브라우저가 인식하지 못함

때문에 톰켓 컨테이너에 의해 아래와 같은 실행 단계를 거쳐야 한다

1.변환단계 : JSP파일을 자바 파일로 변환함

2.컴파일 단계 : 자바 파일을 클래스 파일로 컴파일한다

3. 실행 단계 : 클래스 파일을 실행하여 HTML및 CSS 등을 브라우저로 전송해 출력한다

어떻게 클래스 파일이 HTML과 CSS를 전송할 수 있지? - JAVA의 클래스가 아님 서블릿 클래스를 말함

### 11.3 JSP 페이지 구성 요소

디렉티브 태그 : JSP 페이지에 대한 전반적인 설정 정보를 지정할때

스크립트 요소:주석문, 스크립트릿,표현식,선언식

표현 언어

내장 객체

액션 태그

커스텀 태그

### 11.4 디렉티브 태그

페이지 디렉티브 태그 : JSP 페이지의 전반적인 정보를 설정할때 사용합니다

인클루드 디렉티브 태그 : 공통으로 사용하는 JSP 페이지를 다른 JSP 페이지에 추가할때 사용함

태그라이브 디렉티브 태그 : 개발자나 프레임워크에서 제공하는 태그를 사용할때 사용합니다
---
## 8장

### 8.1 서블릿 포워드 기능 사용하기

- 포워드: 하나의 서블릿에서 다른 서블릿이나 JSP와 연동하는 방법을 포워드 라고 한다
    - 서블릿에서 다른 서블릿이나 JSP로 요청을 전달하는 역할임
    - 이 요청을 전달할때 추가 데이터를 포함시켜서 전달할 수도 있다
        - 요청에 대한 추가 작업을 다른 서블릿에게 수행하게 한다
        - 요청에 포함된 정보를 다른 서블릿이나 JSP와 공유할 수 있다
        - 요청에 정보를 포함시켜 다른 서블릿에 전달할 수 있다
        - 모델2 개발시 서블릿에서 JSP로 데이터를 전달하는데 사용됨

### 8.2 서블릿의 여러 가지 포워드 방법

- redirect 방법
    - HttpServletResponse 객체의 sendRedirect() 메서드를 이용함
    - 웹 브라우저에 재요청 하는 방식
    - 형식: sendRedirect("포워드할 서블릿 또는 JSP ");
- Refresh 방법
    - HttpServletResponse 객체의 addHeader() 메서드를 이용함
    - 웹 브라우저에 재요청 하는 방식
    - 형식: response.addHeader("Refresh",경과시간(초);url=요청할 서블릿 또는 JSP");
- location 방법
    - 자바스크립트 location 객체의 href속성을 사용함
    - 자바스크립트에서 재요청 하는 방식
    - 형식: location.href='요청할 서블릿 또는 JSP ';
- dispatch 방법

    🍋 다른 방식과의 차이점

    🍋 서블릿에서 클라이언트를 거치지 않고 바로 다른 서블릿에게 요청하는 방법임

    🥭따라서 주소창의 URL이 변경되지 않는다

    - RequestDispatcher 클래스의 forward() 메서드를 이용함
    - 형식: RequestDispatcher dis= request.getRequestDispatcher("포워드할 서블릿 또는 JSP");

                 dis.forward(request.response);

### 8.3 dispatch를 이용한 포워드 방법

1. 준비
- sec03.ex01 패키지에 두개의 서블릿(FirstServlet.java, SecondServlet.java) 클래스 추가

1. 실습
- FirstServlet 작성
    - RequestDispatcher 클래스를 이용해 두번째 서블릿인 second를 지정한 후 forward() 메서드를 이용해 포워드 한다

        ```jsx
        RequestDispatcher dis= request.getRequestDispatcher("second");
        dis.forward(request.response); //world아님 word아님 철자주의!
        ```

- SecondServlet에 화면 구성

    ```jsx
    response.setContentType("text/html; charset=utf-8");
    		PrintWriter out = response.getWriter();
    		out.print("<html>");
    		out.print("<body>");
    		out.print("dispatch를 이용한 forward 실습입니다");
    		out.print("</body>");
    		out.print("</html>");
    ```

1. 실행
    - 두번째  서블릿의 "dispatch를 이용한 forward 실습입니다" 가 출력됨
    - 웹 브라우저 주소창의 URL이 변경되지 않음
    - 이는 포워드가 서버 안에서만 실행되었기 때문임

2. forward 사용 + GET방식으로 데이터를 전송해보기
- FirstServlet 작성

```jsx
RequestDispatcher dis= request.getRequestDispatcher("second?name=lee");
dis.forward(request.response); //world아님 word아님 철자주의!
```

- SecondServlet에 화면 구성

```jsx
response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		out.print("<html>");
		out.print("<body>");
		out.print("name :"+name+"<br>");
		out.print("dispatch를 이용한 forward 실습입니다");
		out.print("</body>");
		out.print("</html>");
```

### 8.4 바인딩

- Dispatcher 로 값을 전달시 GET 방식으로 전달하는건 데이터 양이 적을때는 편리함
- 하지만 GET은 보안성과 대량의 데이터에는 불편하다는 단점이 있음
- 따라서  대량의 정보를 서블릿에서 다른 서블릿 또는 JSP로 전달할때는 바인딩을 사용
- 바인딩의 사전적 의미는 "두 개를 하나로 묶는다" 임

[바인딩 메서드](https://www.notion.so/cad74d5d50f74c0bbf5a4db4dbd1fe43)

### HttpServletRequest를 이용한 dispatch 포워딩 + 바인딩

### 설명

- 바인딩시 dispatch로만 제대로 가능함
- 이유는 First 서블릿에서 Second 서블릿으로 전달되는 request가 브라우저를 거치지 않고 바로 전달되기 때문에 첫번째 서블릿의 request 객체에 바인딩된 데이터가 두번째 서블릿에 그대로 전달된다
- 자바의 모든 자료형이 전달 가능하다

### 실습

- FirstServlet 작성

    ```jsx
    response.setContentType("text/html; charset=utf-8");
    		PrintWriter out = response.getWriter();
    		request.setAttribute("address", "서울시 성북구"); //바인딩
    		RequestDispatcher dispatch = request.getRequestDispatcher("second"); //전달
    		dispatch.forward(request,response);
    ```

- SecondServlet 작성

    ```jsx
    response.setContentType("text/html; charset=utf-8");
    		PrintWriter out = response.getWriter();
    		String address = (String)request.getAttribute("address"); //값 받아옴
    		out.print("<html>");
    		out.print("<body>");
    		out.print("address :"+address+"<br>");
    		out.print("sendredirect");
    		out.print("</body>");
    		out.print("</html>");
    ```

### 8.5 ServletContext와 ServletConfig 사용법

### ServletContex 설명

- 톰캣 컨테이너 실행 시 각 컨텍스트(웹애플리케이션)마다 한 개의 ServletContex객체를 생성한다
- 톰캣 컨테이너가 종료되면 ServletContex객체도 소멸된다
- ServletContex객체는  웹 애플리케이션이 실행되면서 애플리케이션 공통의 자원(데이터)를 미리 바인딩 해서 서블릿들이 공유하여 사용한다

    즉 서블릿끼리 자원(데이터)을 공유하는데 사용한다

- 서블릿과 컨테이너 간의 연동을 위해 사용한다
[getAttribute(String name)](https://www.notion.so/getAttribute-String-name-ab8f98523548429f90d37b8733ea4180)

[getInitParameter(String name)](https://www.notion.so/getInitParameter-String-name-8e064fe9b1cf49159c16329edf6be1a1)

[setAttribute(String name, Object object)](https://www.notion.so/setAttribute-String-name-Object-object-56ba5b1201b246f981d98a585ef93717)

[ServletContex context = getServeltContext();](https://www.notion.so/ServletContex-context-getServeltContext-af8c59e6c17c40ec8504afdc61b1b150)

[getResourceAsStream("/WEB-INF/bin/init.txt")](https://www.notion.so/getResourceAsStream-WEB-INF-bin-init-txt-224d4bdabc71489c99531e8c56d2c470)

### 8.6 load-on-startup 기능  사용하기

---
## 7장

### 7.1 서블릿의 비즈니스 로직 처리 방법

서블릿 비즈니스 처리 작업이란 서블릿이 클라이언트로부터 요청을 받으면 그 요청에 대해 작업을 수행하는 것을 의미한다

대부분의 비즈니스 처리 작업은 데이터 베이스 연동 관련 작업이다

그 외에는 다른 서버와 연동해서 데이터를 얻는 작업도 수행한다

### 7.2 서블릿의 데이터 베이스 연동하기

- 데이터 베이스에 접근하는 클래스 DAO
- 조회된 데이터를 설정하는 클래스 VO

**서블릿으로 회원 정보 테이블의 회원 정보 조회 단계**

- 웹 브라우저가 서블릿에게 회원 정보를 요청합니다
- MemberServlet 은 요청을 받은후 MemberDAO 객체를 생성하여 listMembers()메서드를 호출합니다
- listMembers()에서 다시 connDB()메서드를 호출하여 데이터베이스와 연결한 후 SQL문을 실행해 회원 정보를 조회합니다
- 조회된 회원 정보는 MemberVO 속성에 설정한 후 다시 ArrayList에 저장됩니다
- ArrayList를 호출됐던 MemberServlet으로 반환한 후 ArrayList의 MemberVO를 차례대로 가져와 회원 정보를 HTML 태그의 문자열로 만듭니다
- 만들어진 HTML 태그를 웹 브라우저로 전송해서 회원 정보를 출력합니다

**실습**

1. pro07 프로젝트 생성
2. 오라클 DB와 연동하는데 필요한 드라이버인 ojdbc6.jar를 프로젝트의 /WebContent/WEB-INF/lib 폴더에 복붙 (다운 [LINK](https://www.oracle.com/database/technologies/jdbcdriver-ucp-downloads.html))
3. sec01.ex01 패키지 생성후 MemberDAO, MemberServlet, MemberVO 생성

**분석**

- Class.forName() 으로 드라이버를 읽음
- getConnection() 으로 데이터 베이스에 접속후 Connection 객체를 반환
- createStatement() 로 statement 인터페이스 생성
- Statement 클래스의 메서드 중 executeQuery() 을 사용하여 SQL문을 질의함
- ResultSet 객체를 이용하여 질의 결과를 받음
- getString("id") 를 이용하여  컬럼 값을 받아옴
- vo 객체를 선언 후 setID(id) 이런식으로 세팅해준다
- 설정된 vo 객체를 list.add() 를 사용해 저장한다

    (조회한 레코드의 개수만큼 MemberVO 객체를 저장한다고 함)

- list 반환

---

- MemberServlet 클래스
    - 브라우저의 요청을 받음
    - 조회된 결과를 출력함
- MemberDAO 클래스
    - DB와 연결
    - 회원 정보 조회 SQL문을 실행하여 조회한 레코드의 컬럼값을 MemberVO 객체의 속성에 설정한 후 ArrayList에 저장
    - 조회한 레코드의 개수만큼 MemberVO 객체를 저장한 ArrayList를 반환함
- MemberVO 클래스
    - 값을 전달하는 데 사용하는 VO(Value Object) 클래스

    > TIP1. Getter Setter 만들어주는 거 : 우클릭 → Source → Generate Getters and Setters

    ---

### ***완성*** ***sorce***

[JAVA-Web/pro07/src/sec01/ex01 at main · CSN-ah22/JAVA-Web](https://github.com/CSN-ah22/JAVA-Web/tree/main/pro07/src/sec01/ex01)

**PrepareStatement 인터페이스를 이용한 회원 정보 실습**

**설명**

- Statement 인터페이스의 단점
    - 연동할 때마다 DBMS에서 다시 SQL문을 컴파일해야 하므로 속도가 느림
    - 단점 보완을 위해 PrepareStatement 를 사용
- PrepareStatement 인터페이스
    - SQL문을 미리 컴파일해서 재사용
    - Statement 인터페이스보다 훨씬 빠르게 DB작업 수행 가능
    - 데이터베이스와 연동할 때 / 빠른 반복 처리가 필요할 때 사용
- PrepareStatement 인터페이스 특징
    - Statement 인터페이스를 상속 → 메서드를 그대로 사용
    - Statement : DBMS에 전달하는 SQL문은 단순한 문자열임 → DBMS가 스스로 이해할 수 있도록 컴파일 후 실행
    - PrepareStatement : 컴파일된 SQL문을 DBMS에 전달 → 성능 향상
    - 실행하려는 SQL문에 “?”를 넣을 수 있음
    - ”?”값만 바꾸어 손쉽게 설정할 수 있음

**실습**

1. sec01.ex02 생성후 기존에 작성된 DAO,VO,Servlet 복붙
2. 매핑 이름 /member2로 변경
3. MemberDAO 클래스를 PrepareStatement를 이용하여 연동하도록 작성

**분석**

- 눈으로 보면 Statement를 사용했을 때와 PrepareStatement 사용 결과가 같다
- 하지만 DB와 연동할 경우 수행 속도가 좀 더 빠름
- Statement 클래스의 메서드 또는 PreparedStatement 클래스의 메서드 중 SQL문에 의한 질의 결과를 받기 위한 클래스형의 이름은?
    - ResultSet

### ***완성*** ***sorce***

[JAVA-Web/pro07/src/sec01/ex02 at main · CSN-ah22/JAVA-Web](https://github.com/CSN-ah22/JAVA-Web/tree/main/pro07/src/sec01/ex02)

### 7.3 DataSource 이용해 데이터 베이스 연동하기

- Statement, PrepareStatement 문제점 : DB 연결에 시간이 많이 걸림
- 문제점을 극복하기 위해 커넥션풀이 등장함

**커넥션풀 설명**

- 미리 데이터베이스와 연결시킨 상태를 유지하는 기술이라 함
- 웹 애플리케이션이 실행됨과 동시에 연동할 데이터베이스와의 연결을 미리 설정

**커넥션풀 동작 과정**

- 동작 과정
    - 톰캣 컨테이너를 실행한 후 응용 프로그램을 실행
    - 톰캣 컨테이너 실행시 ConenctionPool 객체 생성
    - 생성된 ConnectionPool 객체는 DBMS와 연결
    - DB와의 연동 작업이 필요할 경우 응용 프로그램은 ConnectionPool에서 제공하는 메서드를 호출하여 연동
- 톰캣 컨테이너는 자체적으로 ConnectionPool 기능을 제공
    - 실행시 톰캣은 설정 파일에 설정된 DB 정보를 이용해 미리 DB와 연결하여 ConnectionPool 객체를 생성
    - 애플리케이션이 DB와 연동할 일이 생기면 ConnectionPool 객체의 메서드를 호출해 빠르게 연동하며 작업

### 커넥션풀 사용시 필요한 [ JNDI ]

- 웹 애플리케이션에서 Connection Pool 객체를 구현할 때는
    - Java SE에서 제공하는 javax.sql.DataSource 클래스 이용
- 웹 애플리케이션 실행시 톰캣이 만들어놓은 ConnectionPool 객체 접근시에는
    - JNDI 이용
- **JNDI(Java Naming and Directory Interface)란?**
    - 필요한 자원을 키/값(key/value) 쌍으로 저장한 후 필요할 때 키를 이용해 값을 얻는 방법
    - 접근할 자원에 미리 키를 지정한 후, 애플리케이션이 실행중일 때 이 키를 이용해 자원에 접근하여 작업
- JNDI 사용 예
    - 웹 브라우저에서 name/value 쌍으로 전송한 후 서블릿에서 getParameter(name)으로 값을 가져올 때
    - 해시맵(HashMap)이나 해시테이블(HashTable)에 키/값으로 저장한 후 키를 이용해 값을 가져올 때
    - 웹 브라우저에서 도메인 네임으로 DNS 서버에 요청할 경우 도메인 네임에 대한 IP 주소를 가져올 때
- 톰캣 컨테이너가 ConnectionPool 객체를 생성하면?
    - 이 객체에 대한 JNDI 이름(key)을 미리 설정
    - 웹 애플리케이션에서 DB 연동 작업을 할 때 설정해놓은 JNDI 이름(key)으로 접근하여 작업

### **JNDI 설정**
- JDBC 드라이버(ojdbc6.jar)
    - pro07\WebContent\WEB-INF\lib\에 위치
- ConnectionPool 관련 jar파일(tomcat-dbcp.jar)
    - pro07\WebContent\WEB-INF\lib\에 ojdbc6.jar와 같이 위치
- context.xml
    - 이클립스에서 생성한 톰캣 서버의 설정 파일
    - Servers\Tomcat v9.0 Server at localhost-config\에 위치
- context.xml 파일 설정하기전 ConnectionPool로 연결할 DB 속성 알아보기
    - 다른 속성은 고정적으로 사용하며, 프로그래머는 주로 drvierClassName, user, password, url만 변경하여 설정

[ConnectionPool로 연결할 데이터베이스 속성](https://www.notion.so/254d26e7808a40fd99686345ae938e05)

**실습**

1. sec2.ex01 생성후 DAO,VO,Servlet 복붙
2. 매핑 이름을 /member3로 변경
3. connDB 주석처리
4. 생성자에서 톰캣 실행 시 톰캣에서 미리 생성한 DataSource를 name값인 jdbc/oracle을 이용해 미리 받아오기
5. 서블릿에서 listMembers() 메서드를 호출하면, getConnection() 메서드를 호출하여 DataSource에 접근 후 DB와의 연동 작업 수행

**context.xml 설정 예시**

```jsx
<Resource
    name="jdbc/oracle"
    auth="Container"
    type="javax.sql.DataSource"
    driverClassName="oracle.jdbc.OracleDriver"
    url="jdbc:oracle:thin:@localhost:1521:XE"
    username="scott"
    password="tiger"
    maxActive="50"
    maxWait="-1" />
```

**분석**

- InitialContext() 을 사용하여 컨텍스트의 생성자 객체 생성
- **JNDI**에 접근하기위해 기본경로 lookup("java:/comp/env")를 지정한다
- context.xml에 설정한 name값인 jdbc/oracle으로 미리 연결한 DataSource를 받아온다
    - lookup("jdbc/oracle")
- Context 클래스의 메서드 중 JDNI에 접근하기 위한 기본 경로를 지정하고 , context.xml 파일의 엘레먼트를 탐색하는 메서드는?
    - lookup
- DataSource 클래스의 메서드 중 데이터베이스에 접속한 후 Connection 객체를 받아오기 위한 메서드는?
    - getConnection()

### ***완성*** ***sorce***

[JAVA-Web/pro07/src/sec02/ex01 at main · CSN-ah22/JAVA-Web](https://github.com/CSN-ah22/JAVA-Web/tree/main/pro07/src/sec02/ex01)

### 7.4 DataSource 이용해 회원 정보 등록하기

**실습**

- WebContent에 memberForm.html이라는 회원 가입창 작성
- <hidden> 태그를 이용해 회원 가입창에서 새 회원 등록 요청을 서블릿에 전달
- command 값을 받아와 addMember이면 같이 전송된 회원 정보를 받아옴
- 회원 정보를 MemberVO 객체에 설정한 후 MemberDAO의 메서드로 전달해 SQL문을 이용해 테이블에 추가

**분석**

- PrepareStatement의 **insert**문은 회원 정보를 저장하기 위해 ?(물음표)를 사용
- ?는 id, pwd, name, age에 순서대로 대응
- 각 ?에 대응한느 값을 지정하기 위해 PrepareStatement의 setter를 이용
- setter() 메서드의 첫 번째 인자는 ‘?’의 순서 지정
- ’?’는 1부터 시작함
- PreparedStatement 클래스에서 insert, delete, update 문과 같은 SQL문을 질의하기 위해서는
    - executeUpdate() 메서드를 사용

```jsx
String query = "insert into t_member";			  // insert 문을 문자열로 생성
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			System.out.println("preparedStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);							  // insert문의 각 ?에 순서대로 회원정보를 대입
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();							  // 회원정보를 테이블에 추가
			pstmt.close();
```

### 7.5 회원 정보 삭제하기

**실습**

1. WebContent 하위의 기존 html 복붙후 이름과 매핑을 바꿈
2. sec02.ex03 패키지 생성후 DAO,VO,Servlet 복붙후 매핑 바꿈
3. 서블릿에 a태그를 이용하여 삭제기능을 구현할 수 있도록 추가
4. DAO에 삭제 기능 추가

**분석**

```jsx
con = dataFactory.getConnection();
			String query = "delete from t_member" + " where id=?";    // delete 쿼리문
			System.out.println("preparedStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,  id);								  
			pstmt.executeUpdate();
			pstmt.close();
```

***완성*** ***sorce***

[JAVA-Web/pro07/src/sec02/ex02 at main · CSN-ah22/JAVA-Web](https://github.com/CSN-ah22/JAVA-Web/tree/main/pro07/src/sec02/ex02)

---
## 6장

- 서블릿 기본 기능 수행 과정
    - 서블릿이 클라이언트로부터 요청을 받음
    - 데이터 베이스 연동과 같은 비즈니스 로직을 처리한다
    - 처리된 결과를 클라이언트에게 돌려줌
        - EX) 로그인시 ID와 비밀번호를 입력하고 버튼을 클릭하면 서버쪽의 서블릿에 값이 전달되고 서블릿에서는 여러 가지 메서드를 이용해 전송된 ID와 비밀번호를 받아온다

- 클라이언트가 서블릿에 요청을 하면 먼저 톰캣 컨테이너가 받는다

    그 다음 톰캣 컨테이너가 사용자의 요청이나 응답에 대한 HttpServletRequest 객체와 HttpServletResponse 객체를 만들고 서블릿의 doGet()이나 doPost() 메서드를 호출하면서 이 객체들을 전달한다

- JSP,PHP,ASP 가 나오기 전에는 HTML,CSS,자바스크립트를 이용해 웹 프로그램을 만들었다

    서블릿과 JSP는 여기에 자신의 기능을 추가하여 서로 연동이 되면서 동작하는 방식이다

    사용자의 요청은 HTML의 <form>태그나 자바스크립트로 부터 전송 받아서 처리함

- <form>태그와 관련된 여러 가지 속성
    - action ( 데이터를 전송할 서블릿의 매핑이름 이나 JSP를 지정하면 값을 전송한다 )
    - name ( 서블릿에서 name속성 값으로 전달된 입력 데이터를 받아온다 )
    - encType( form태그에서 전송할 데이터의 encoding 타입을 지정할때 사용 )
    - method ( 데이터 전송시 GET과 POST 방식중에 선택지정 )

- 서블릿에서 클라이언트 요청을 얻는 방법 ( doGet 이나 doPost에서 처리 )
    - getParameter(String name) 메서드 - name값으로 전송된 데이터들을 받아올때 사용
    - getParameterValues(String name) 메서드 - 하나의 name으로 여러개의 데이터들을 받아야 할때 사용
    - getParameterNames() 메서드 - name값을 모를때 사용
        - Enumeration 타입의 참조변수를 선언하고 .nextElement() 메서드를 이용한다 190p 참고


- 서블릿의 응답 처리 방법
    1. doGet() 이나 doPost() 메서드 안에서 처리한다
    2. HttpServletResponse 객체를 이용한다
    3. setContentType()을 이용해 클라이언트에게 전송할 데이터 종류(MEME-TYPE)를 지정한다
    4. 클라이언트와 서블릿의 통신은 자바 I/O스트림을 이용한다
    - 클라이언트에 해당하는 웹브라우저가 네트워크를 통해 서버에 해당하는 서블릿에 데이터를 보내는 경우 서블릿은 네트워크로부터 데이터를 입력받습니다
    - 반대로 서블릿이 웹 브라우저로 데이터를 전송하는 경우 네트워크로 데이터를 출력 합니다
    - 서블릿에서 웹브라우저로 데이터를 전송할때는 어떤 종류의 데이터를 전송하는지 웹브라우저에 알려줘야 합니다
    - 톰캣 컨테이너에서 제공하는 여러가지 전송 데이터 종류를 MIME-TYPE(마임 타입) 이라고 합니다

    - 서버에서 자바 I/O의 스트림 클래스를 이용하여 웹 브라우저로 데이터를 전송할 때는 MIME-TYPE을 설정해서 전송할 데이터의 종류를 지정합니다
        - HTML로 전송 시 : text/html
        - 일반 텍스트로 전송시 : text
        - xml로 데이터로 전송시 : application/xml
        - 새로운 종류의 데이터를 지정하고 싶다면 CATALINA_HOME\conf\web.xml에 추가하면 된다

    - 서블릿의 응답처리 과정
        - setContentType()을 이용해 MIME-TYPE을 지정한다
        - 데이터를 출력할 PrintWriter 객체를 생성한다
        - 출력 데이터를 HTML형식으로 만든다
        - PrintWriter 객체의 print() 또는 println() 메서드를 이용해 데이터를 출력한다


        - <input>태그에서 hidden 속성을 사용하면 화면에는 보이지 않지만 value를 통해 값을 저장할 수 있다

         

        - GET/POST 전송 방식
            - URL 주소에 데이터를 붙여서 전송하는 방식을 GET방식이라고 한다
                - doGet() 메서드를 이용하여 처리
            - TCP/IP 프로토콜 데이터의 HEADER영역에 숨겨서 전송하는 방식을 POST라고 한다
                - doPost() 메서드를 이용하여 처리

        - GET/POST 방식 동시에 처리하기
            - doGet()과 doPost()메서드에서 doHandle()메서드 호출
            - doHandle()메서드에서 한꺼번에 처리
           

        - 자바 스크립트로 값 입력 여부를 체크한뒤 서블릿에 요청하기
            - 자바스크립트 함수에서 <form>태그 객체를 받아온다
            - 객체를 이용하여 ID와 비밀번호를 받아온다
            - 값 입력 여부를 체크한다
            - <form>태그의 전송 방식을 method 속성에 지정한다
            - <form>태그의 action 속성에 전송할 서블릿 이름을 지정한다
            - submit()함수를 호출하여 서블릿으로 전송한다
            

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


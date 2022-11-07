<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <meta charset="UTF-8">
 <title>Title</title>
</head>
<body>

<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] : 재활용 하기위한 것, but 절대경로로하는 것이 가장좋다.
    /WEB-INF : 이 경로안에 JSP가 있으면 외부에서 직접, url 을 쳐서 호출 못한다.
    컨트롤러를 통해서 JSP를 호출한다!-->

  <form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
  <button type="submit">전송</button>
   </form>
</body>
</html>
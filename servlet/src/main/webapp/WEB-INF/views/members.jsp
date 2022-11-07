 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%-- taglib 선언 - <c:forEach> 기능을 사용할 수 있다.  --%>


 <html>
 <head>
  <meta charset="UTF-8">
  <title>Title</title>
 </head>
 <body>
 <a href="/index.html">메인</a>
 <table>
      <thead>
      <th>id</th>
      <th>username</th>
      <th>age</th>
      </thead>
  <tbody>

  <%-- 모델에 담아둔 members를 JSP가 제공하는 taglib기능을 사용해서 반복하면서 출력했다.
       members 리스트에서 member 를 순서대로 꺼내서 item 변수에 담고, 출력과정 반복 --%>

    <c:forEach var="item" items="${members}">
      <tr>
      <%-- jsp ${} 문법은 제공하는데 이 문법을 사용하면 request의 attribute에 담긴 데이터를 편리하게 조회할 수 있다. --%>
      <td>${item.id}</td>
      <td>${item.username}</td>
      <td>${item.age}</td>
      </tr>
    </c:forEach>
  </tbody>
 </table>
 </body>
 </html>
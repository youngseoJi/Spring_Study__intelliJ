 <%-- jsp 사용시, 타입, 언어 등 정하는 첫 줄 추가--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>

 <%-- 자바 로직 -> 회원 목록 --%>

<%
     MemberRepository memberRepository = MemberRepository.getInstance();
     List<Member> members = memberRepository.findAll();
%>


 <%-- html 로직 --%>

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

     <%-- 자바 로직 - for 문으로 회원 수만큼 목록생성되게함 --%>
     <%
     for (Member member : members) {
        // system.print.out 이랑 동일
         out.write(" <tr>\n");
         out.write(" <td>" + member.getId() + "</td>\n");
         out.write(" <td>" + member.getUsername() + "</td>\n");
         out.write(" <td>" + member.getAge() + "</td>\n");
         out.write(" </tr>");
    }
    %>

    </tbody>
  </table>

</body>
</html>
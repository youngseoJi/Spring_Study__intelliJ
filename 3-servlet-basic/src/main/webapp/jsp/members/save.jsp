<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>


<% // 자바 로직 : 회원 가입, 저장
    // request, response 사용 가능
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("save.jsp");

    // 유저이름, 나이 값 조회 -> 담기
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    // 회원 생성자
    Member member = new Member(username, age);
    System.out.println("member = " + member);

    // 회원가입
    memberRepository.save(member);
%>

<html>
<head>
 <meta charset="UTF-8">
</head>
<body>
성공
<ul>
 <li>id=<%=member.getId()%></li>
 <li>username=<%=member.getUsername()%></li>
 <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
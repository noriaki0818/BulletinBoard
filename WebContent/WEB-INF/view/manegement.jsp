<%@page import="Dao.BoardDao"%>
<%@page import="Bean.BoardBean"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>管理画面</h1>
	<a href="/BulletinBoard/index.jsp">掲示板へ</a>
	<table border="1">

		<%
			ArrayList<BoardBean> BookList = BoardDao.selectCommentList();
			for (int i = 0; i < BookList.size(); i++) {
				BoardBean com = BookList.get(i);
		%>
		<tr>
			<td>
			<%=com.getComment()%><br>
			投稿者：<%=com.getName()%>　メールアドレス：<%=com.getMail()%>　投稿時間：<%=com.getPost()%>編集時間：<%=com.getEdit()%>
			<button onclick="location.href='/BulletinBoard/Delete?post=<%=com.getPost() %>' ">削除</button>

			<td></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>
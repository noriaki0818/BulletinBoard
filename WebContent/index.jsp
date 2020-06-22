<%@page import="Dao.BoardDao"%>
<%@page import="Bean.BoardBean"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板</title>
</head>
<body>

	<h1>掲示板</h1>

	<form  method="POST" action="/BulletinBoard/Board" enctype="multipart/form-data">
		名前<br> <input type="text" name="name"><br>
		メールアドレス<br>
		<input type="email" name="mail"><br>
		ファイル<br>
		<input type="text" name="id"> <input type="file" name="file" /><br />
		コメント<br>
		<textarea rows="10" cols="50" name="comment"></textarea>
		<input type="submit" value="投稿">
	</form>

	<table border="1">
		<%
				ArrayList<BoardBean> BookList = BoardDao.selectCommentList();
				for (int i = 0; i < BookList.size(); i++) {
					BoardBean com = BookList.get(i);
			%>
		<tr>
			<td><%=com.getComment()%><br> 投稿者：<%=com.getName()%>
				メールアドレス：<%=com.getMail()%> 投稿時間：<%=com.getPost()%>編集時間：<%=com.getEdit()%>
			<td></td>
		</tr>
		<%
				}
			%>
	</table>
	<a href="/BulletinBoard/Manege">管理画面へ</a>
</body>
</html>
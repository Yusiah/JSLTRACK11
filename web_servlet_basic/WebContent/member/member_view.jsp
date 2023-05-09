<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dto.*, dao.*" %>
    
<%
	request.setCharacterEncoding("utf-8");
	MemberDao dao = new MemberDao();
	String id = request.getParameter("t_id");
	MemberDto dto = dao.getMemberView(id);

	
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">
		function goUpdateForm(){
			mem.t_gubun.value="memberUpdateForm";
			mem.method = "post";
			mem.action = "Member";
			mem.submit();
		}
		
		function goDelete(){
			mem.t_gubun.value="memberDelete";
			mem.method = "post";
			mem.action = "Member";
			mem.submit();
		}
	</script>
</head>
<body>
	<form name = "mem">
		<input name="t_gubun" type="hidden">
		<input name="t_id" type="hidden" value="<%=dto.getId()%>">
	</form>
	<div class="container">
	
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK 11 김진욱 회원관리</h1>
		</div>		
		<div class="write_wrap">
		
			<div class="board_list">
				<table class="board_table">
					<colgroup>
						<col width="12%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>ID</th>
							<td class="th_left">
								<%=dto.getId() %>
							</td>
						</tr>
						<tr>
							<th>성명</th>
							<td class="th_left">
								<%=dto.getName() %>
							</td>
						</tr>
						<tr>
							<th>나이</th>
							<td class="th_left">
								<%=dto.getAge() %>
							</td>
						</tr>
						<tr>
							<th>가입일</th>
							<td class="th_left">
								<%=dto.getReg_date() %>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="location.href='Member'" value="목록" class="btn_list">
				<input type="button" onClick="goUpdateForm()" value="수정" class="btn_list">
				<input type="button" onClick="goDelete()" value="삭제" class="btn_list">
				
<!--			<input type="button" onClick="location.href='MemberList'" value="목록" class="btn_list">
				<input type="button" onClick="location.href='MemberUpdate?t_id=<%=dto.getId()%>'" value="수정" class="btn_list">
				<input type="button" onClick="location.href='DBMemberDelete?t_id=<%=dto.getId()%>'" value="삭제" class="btn_list">   -->	
			</div>
		</div>
	</div>
</body>
</html>

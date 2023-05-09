<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "dao.*, dto.*" %>
<%
	request.setCharacterEncoding("utf-8");
	SnackDao dao = new SnackDao();
	String p_code = request.getParameter("t_p_code");
	SnackDto dto = dao.getSnackView(p_code);


%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>TRACK11 홍길동</title>
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet" >	
	<script type="text/javascript">

	</script>
</head>
<body>
	<div class="container">
	
		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 홍길동 SNACK</h1>
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
							<th>제품코드</th>
							<td class="th_left">
								<%=dto.getP_code()%>
							</td>
						</tr>
						<tr>
							<th>제품명</th>
							<td class="th_left">
								<%=dto.getP_name()%>
							</td>
						</tr>
						<tr>
							<th>가격</th>
							<td class="th_left">
								<%=dto.getStrPrice()%>
							</td>
						</tr>
						<tr>
							<th>제조사</th>
							<td class="th_left">
								<%=dto.getM_name() %>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<input type="button" onClick="location.href='SnackList'" value="목록" class="btn_list">
				<input type="button" onClick="location.href='SnackUpdate?t_p_code=<%=dto.getP_code()%>'" value="수정" class="btn_list">
				<input type="button" onClick="location.href='DBSnackDelete?t_p_code=<%=dto.getP_code()%>'" value="삭제" class="btn_list">
			</div>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../common_header.jsp" %>
<script type = "text/javascript">

	function goJoin(){
		if(checkValue(mem.t_id, "ID 입력")) return;
		
		if(mem.t_idCheckValue.value == ""){
			alert("사용가능한 ID가 아닙니다");
			mem.t_id.focus();
			return;
		}
		
		if(mem.t_id.value != mem.t_idCheckValue.value){
			alert("변경된 ID 중복검사 하시오");
			return;
		}
		
		if(checkValue(mem.t_name, "성명 입력")) return;
		if(checkValue(mem.t_pw_1, "비밀번호 입력")) return;
		if(checkValue(mem.t_pw_2, "비밀번호 확인 입력")) return;
		
		if(mem.t_pw_1.value != mem.t_pw_2.value){
			alert("비밀번호가 일치하지 않습니다")
			mem.t_pw_2_focus();
			return;
		}
		
		if(checkValue(mem.t_address, "주소 입력")) return;
		if(checkValue(mem.t_mobile_1, "번호 입력")) return;
		if(checkValue(mem.t_mobile_2, "번호 입력")) return;
		if(checkValue(mem.t_mobile_3, "번호 입력")) return;
		if(checkValue(mem.t_gender, "남녀구분 선택")) return;
		
		 
		
		mem.t_gubun.value="memberSave";
		mem.method="post";
		mem.action="Member";
		mem.submit();
		
/*		
		if(mem.t_id.value == ""){
			alert("id 입력")
			mem.t_id.focus();
			return;
		}
*/		

	}

	function checkID(){
		if(checkValue(mem.t_id,"ID 입력 후 중복검사 하시오")) return;
		
		$.ajax({
			type : "POST",
			url : "MemberCheckId",
			data: "t_id="+mem.t_id.value,
			dataType : "text",
			error : function(){
				alert('통신실패!!!!!');
			},
			success : function(data){
				var result = $.trim(data);
				mem.t_idCheck.value = result;
				
				if(result == "사용가능"){
					mem.t_idCheckValue.value = mem.t_id.value;
				}else{
					mem.t_idCheckValue.value = "";
				}
			
			}
		});				
	}
</script>	

		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="javascript:goWork('memberLogin')">LOGIN</a></li>
				<li><a href="javascript:goWork('memberPasswordFind')">ID / PASSWORD</a></li>
				<li><a href="javascript:goWork('memberJoin')"><span class="fnt"><i class="fas fa-apple-alt"></i></span> JOIN</a></li>
			</ul>
		</div>
		
		<div id="b_right">
			<p class="n_title">
				MEMBER JOIN
			</p>
			<form name = "mem">
			<input type = "hidden" name = "t_gubun">
			<table class="boardForm">
			  <colgroup>
				<col width="200" />
				<col width="500" />
			  </colgroup>
			  <tbody>
				<tr>
				  <th><label for="id">I D</label></th>
				  <td>
					<input name="t_id" type="text" size="10" id="id" title="id입력하세요">
					<input type="button" onclick="checkID()" value="ID중복검사" class="checkB">
					<input type = "text" name = "t_idCheck" style = "border:none" readonly>
					<input type = "hidden" name = "t_idCheckValue">
				  </td>
				</tr>
				<tr>
				  <th><label for="nana">성 명</label></th>
				  <td><input name = "t_name" type="text" size="8" id="nana"></td>
				</tr>
				<tr>
				  <th>비밀번호</th>
				  <td><input name ="t_pw_1" type="text" size="13"></td>
				</tr>
				<tr>
				  <th>비밀번호확인</th>
				  <td><input type="text" name ="t_pw_2" size="13"></td>
				</tr>
				<tr>
				  <th>지역</th>
				  <td>
					<select name="t_area">
						<option value="서울">서울</option>
						<option value="대전">대전</option>
						<option value="부산">부산</option>
						<option value="대구">대구</option>        
					</select>	  
				  </td>
				</tr>	
				
				<tr>
				  <th>주소</th>
				  <td><input type="text" name="t_address" size="40"></td>
				</tr>
				<tr>
				  <th>연락처</th>
				  <td>
					<input type="text" name="t_mobile_1" size="2"> -
					<input type="text" name="t_mobile_2" size="3"> -
					<input type="text" name="t_mobile_3" size="3">
				  </td>
				</tr>
				<tr>
				  <th>남여구분</th>
				  <td>
					  <input type="radio" value="f" name="t_gender" class="middleCheck" /> 여&nbsp;&nbsp;        
					  <input type="radio" value="m" name="t_gender" class="middleCheck" /> 남        
				  </td>
				</tr>
				<tr>
				  <th>취미</th>
				  <td>
					  <input type="checkbox" value="y" name="t_hobby_travel" class="middleCheck" /> 여행&nbsp;&nbsp; 
					  <input type="checkbox" value="y" name="t_hobby_reading" class="middleCheck" /> 독서&nbsp;&nbsp; 
					  <input type="checkbox" value="y" name="t_hobby_sports" class="middleCheck" /> 운동
				  </td>
				</tr>
			  </tbody>
			</table>
			</form>
			<div class="buttonGroup_center">
				<a href="javascript:goJoin()" class="butt">JOIN</a>
			</div>	
		</div>	
	</div>

<%@ include file = "../common_footer.jsp" %>

</body>
</html>


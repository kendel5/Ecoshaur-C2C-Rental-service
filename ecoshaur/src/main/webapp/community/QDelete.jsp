<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문 시작 QDelete.jsp-->
<body>
<div class="container">

	<form method="post" action="QDelete.do">
		<input type="hidden" name="postno" value="${dto.postno}">
		<div class="content">
			<p>정말 문의사항을 삭제하시겠습니까?</p>
			<p>삭제된 문의사항은 복구할 수 없습니다.</p>
		</div>

		<div class='bottom'>
			<input type="submit" value="삭제하기"> <input type='button'
				value='삭제취소'
				onclick="location.href='QRead.do?postno=${dto.postno}'">
		</div>
	</form>
</div>	
</body>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>
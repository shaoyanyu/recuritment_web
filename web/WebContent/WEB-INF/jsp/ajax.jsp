<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>ajax for springMVC</title>
		<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$("#click").click(function() {
					$.ajax( {
						type : "GET",
						url : "ajax.do",
						data : "name=zhangsan&age=20",
						dataType: "text",
						success : function(msg) {
							alert(msg);
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<input id="click" type="button" value="click to show person" />
	</body>
</html>
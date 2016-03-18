<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/favoriteFruit" method="POST">
		what's your favorite fruit? <#list fruits as fruit>
		<p>
			<input type="radio" name="fruit" value="${fruit}">${fruit}</input>
		</p>
		</#list> <input type="submit" value="Submit"/>
	</form>
</body>
</html>
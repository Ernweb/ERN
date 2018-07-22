<%@page session="false"%>
<html>
<body>
	<h1>Title : ${title}</h1>	
	<h1>Message : ${message}</h1>	
	
	<form action="/logout" method="post">
		<input value="Logout" type="submit">
	</form>
</body>
</html>
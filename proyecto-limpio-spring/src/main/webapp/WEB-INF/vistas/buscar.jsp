<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="saludar/{nombre}" method="GET" >
    	<h3 class="form-signin-heading">Taller Web I</h3>
		<hr class="colorgraph"><br>

		<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
		<input name="nombre" id="nombre" type="text" class="form-control" />
 		  		
		<button class="btn btn-lg btn-primary btn-block" Type="Submit">Login</button>
	</form>
</body>
</html>
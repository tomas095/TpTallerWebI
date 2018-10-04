<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Convertidor</title>
</head>
<body>
	<form action="resultadoConversion" method="GET">
		<label>Ingrese la frase:</label><br>
		<input type = "text" name="cadena" id="cadena"></input>
		<br>
		<br>
		<label>Seleccione un método:</label><br>
		
		<input type="radio" id="operacionPasarAMayuscula" name="nombreOperacion" value="pasarAMayuscula" checked> Pasar a Mayuscula<br>
  		<input type="radio" id="operacionPasarAMinuscula" name="nombreOperacion" value="pasarAMinuscula"> Pasar a Minuscula<br>
  		<input type="radio" id="operacionInvertirOrden" name="nombreOperacion" value="invertirOrden"> Invertir Orden <br>
		<input type="radio" id="operacionCantidadDeCaracteres" name="nombreOperacion" value="cantidadDeCaracteres"> Cantidad de Caracteres<br><br>
		
		<input type="button" onclick="postToController();" value="Convertir">
	</form>
	
	<script>
		function postToController(){
			if (isValid())
			{
				var cadena = document.getElementById("cadena").value;
				
				var operacion = document.querySelector('input[name="nombreOperacion"]:checked').value;
				
				location.href='./resultadoConversion/' + operacion + '/' + cadena;
			}
		}
		
		function isValid(){
			var cadena = document.getElementById("cadena").value;
			
			if (cadena.length == 0)
			{
				alert("Debe ingresar la cadena");
				return false;
			}
			
			return true;
		}
	</script>
</body>
</html>

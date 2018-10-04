package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorOperaciones {
	@RequestMapping(path = "/menuConversion", method = RequestMethod.GET)
	public ModelAndView irAMenuConversion() {
		return new ModelAndView("menuConversion");
	}
	
	@RequestMapping(path = "/resultadoConversion/{nombreOperacion}/{cadena}", method = RequestMethod.GET)
	public ModelAndView irAResultadoConversion(@PathVariable String nombreOperacion, @PathVariable String cadena) {
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("cadenaIngresada", cadena);
		
		switch(nombreOperacion) {
			case "pasarAMayuscula":
				modelo.put("resultado", cadena.toUpperCase());
				modelo.put("operacion", "pasar a mayúscula");
				break;
			case "pasarAMinuscula":
				modelo.put("resultado", cadena.toLowerCase());
				modelo.put("operacion", "pasar a minúscula");
				break;
			case "invertirOrden":
				modelo.put("resultado", new StringBuilder(cadena).reverse().toString());
				modelo.put("operacion", "invertir orden");
				break;
			case "cantidadDeCaracteres":
				modelo.put("resultado", cadena.length());
				modelo.put("operacion", "contar caracteres");
				break;
		}
		
		return new ModelAndView("resultadoConversion", modelo);
	}
}

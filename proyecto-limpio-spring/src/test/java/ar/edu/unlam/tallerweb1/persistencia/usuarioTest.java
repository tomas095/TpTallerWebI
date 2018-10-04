package ar.edu.unlam.tallerweb1.persistencia;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import static org.assertj.core.api.Assertions.*;


public class UsuarioTest extends SpringTest{

	// Ctrl + Shift + O - importar clases
	@Test @Transactional @Rollback
	public void TesQuePruebaGuardarUsuario() {
		
		//preparacion		
		Usuario baldoIgnacio = new Usuario();
		
		baldoIgnacio.setEmail("baldoignacio.u@gmail.com");
		baldoIgnacio.setPassword("38307024");
		baldoIgnacio.setRol("Lic. Programador");
		
		//Ejecucion 
		Session session = getSession();
		session.save(baldoIgnacio);
				
		//contrastacion
		Usuario usuarioBuscado = session.get(Usuario.class, baldoIgnacio.getId());
		assertThat(usuarioBuscado).isNotNull();		
	}
	
	@Test @Transactional @Rollback
	public void TesQuePruebaModificarUsuario() {
		
		//preparacion
				
		Usuario usuarioParaModificar = new Usuario();
		usuarioParaModificar.setEmail("baldoignacio.u@gmail.com");
		usuarioParaModificar.setPassword("38307024");
		usuarioParaModificar.setRol("Lic. Programador");
		
		Session session = getSession();
		session.save(usuarioParaModificar);
		
		Usuario usuarioModificado = session.get(Usuario.class, usuarioParaModificar.getId());
		
		//traerme el usuario
		
		usuarioModificado.setPassword("39272340");
	
		//Ejecucion 		
		session.update(usuarioModificado);
				
		//contrastacion
		assertThat(usuarioParaModificar.getPassword()).isEqualTo(usuarioModificado.getPassword());
		
	}
	
	@Test @Transactional @Rollback
	public void TesQuePruebaBorrarUsuario() {
		
		//preparacion
				
		Usuario usuarioParaBorrar = new Usuario();
		usuarioParaBorrar.setEmail("baldoignacio.u@gmail.com");
		usuarioParaBorrar.setPassword("38307024");
		usuarioParaBorrar.setRol("Programador");
		
		
		Session session = getSession();
		session.save(usuarioParaBorrar);
			
		//Ejecucion 		
		session.delete(usuarioParaBorrar);
				
		//contrastacion
		assertThat(usuarioParaBorrar).isNull();
	}
	
}

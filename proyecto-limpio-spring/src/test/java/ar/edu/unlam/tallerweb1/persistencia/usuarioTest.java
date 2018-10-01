package ar.edu.unlam.tallerweb1.persistencia;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class usuarioTest extends SpringTest{
	
	@Test @Transactional @Rollback
	public void guardarUsuarioTest(){
		//Preparación
		Usuario pepe = new Usuario();
		pepe.setEmail("email@gmail.com");
		pepe.setPassword("passw");
		pepe.setRol("Estudiante");
		
		//Ejecución
		Session session = getSession();
		session.save(pepe);
		
		//Contrastación
		Usuario usuarioBuscado = session.get(Usuario.class, pepe.getId());
		assertThat(usuarioBuscado).isNotNull();		
	}
	
	@Test @Transactional @Rollback
	public void modificarUsuarioTest(){
		//Preparación
		Usuario pepe = new Usuario();
		pepe.setEmail("email@gmail.com");
		pepe.setPassword("passw");
		pepe.setRol("Estudiante");
		
		//Ejecución
		Session session = getSession();
		session.save(pepe);
		
		//Traer usuario modificado
		Usuario usuarioModificado = session.get(Usuario.class, pepe.getId());
		
		usuarioModificado.setPassword("password");
		
		session.update(usuarioModificado);
		
		//Comparar
		Usuario usuarioBuscado = session.get(Usuario.class, pepe.getId());
		assertThat(usuarioBuscado.getPassword()).isEqualTo(usuarioModificado.getPassword());	
	}
	
	@Test @Transactional @Rollback
	public void eliminarUsuarioTest(){
		//Preparación
		Usuario pepe = new Usuario();
		pepe.setEmail("email@gmail.com");
		pepe.setPassword("passw");
		pepe.setRol("Estudiante");
		
		//Ejecución
		Session session = getSession();
		session.save(pepe);
		
		//Eliminar usuario modificado	
		session.delete(pepe);
		
		//Comparar
		Usuario usuarioBuscado = session.get(Usuario.class, pepe.getId());
		assertThat(usuarioBuscado).isNull();
	}
}

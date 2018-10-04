package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class TpCiudadesTest extends SpringTest {
	
	@Test @Transactional @Rollback
	public void TestQueBuscaPaisesDeHablaInglesa(){
		//preparacion 
		Pais pais1 = new Pais();
		pais1.setNombre("Argentina");
		pais1.setIdioma("Español");

		Pais pais2 = new Pais();
		pais2.setNombre("Brasil");
		pais2.setIdioma("Portugués");

		Pais pais3 = new Pais();
		pais2.setNombre("Estados Unidos");
		pais2.setIdioma("Ingles");
		
		//ejecucion 
		Session session = getSession();
		session.save(pais1);
		session.save(pais2);
		session.save(pais3);
				
		List<Pais> paisHablaInglesa = getSession().createCriteria(Pais.class)
				.add(Restrictions.eq("idioma", "Ingles"))
				.list();
		
		//contrastacion
		for (Pais pais : paisHablaInglesa){
			assertThat(pais.getIdioma()).isEqualTo("Ingles");
		}
	}
	
	@Test @Transactional @Rollback
	public void TestQueBuscaPaisesDelContinenteEuropeo() {
		//preparacion 
		Continente continenteE = new Continente();
		continenteE.setNombre("Europa");

		Continente continenteA = new Continente();
		continenteA.setNombre("America");

		Pais pais1 = new Pais();
		pais1.setNombre("España");
		pais1.setContinente(continenteE);

		Pais pais2 = new Pais();
		pais2.setNombre("Argentina");
		pais2.setContinente(continenteA);

		Pais pais3 = new Pais();
		pais3.setNombre("Alemania");
		pais3.setContinente(continenteE);
		
		//ejecucion
		Session session = getSession();
		session.save(pais1);
		session.save(pais2);
		session.save(pais3);

		List<Pais> paisEuropeo = getSession().createCriteria(Pais.class)
				.createAlias("continente", "cont")
				.add(Restrictions.eq("cont.nombre", "Europa"))
				.list();
		
		//contrastacion
		for (Pais pais : paisEuropeo){
			assertThat(pais.getContinente().getNombre()).isEqualTo("Europa");
		}		
	}
	
	@Test @Transactional @Rollback
	public void TestQueBuscaPaisesConCapitalAlNorteDelTropicoDeCancer2() {
		//preparacion 
		Ubicacion tropicoDeCancer = new Ubicacion();
		tropicoDeCancer.setLatitud(23.43722);
		
		Ubicacion ubicacion1 = new Ubicacion();
		ubicacion1.setLatitud(-34.60368);

		Ubicacion ubicacion2 = new Ubicacion();
		ubicacion2.setLatitud(41.38506);
		
		Ubicacion ubicacion3 = new Ubicacion();
		ubicacion3.setLatitud(-34.90111);
		
		Ciudad ciudad1 = new Ciudad();
		ciudad1.setNombre("Buenos Aires");
		ciudad1.setUbicacionGeografica(ubicacion1);

		Ciudad ciudad2 = new Ciudad();
		ciudad2.setNombre("Barcelona");
		ciudad2.setUbicacionGeografica(ubicacion2);

		Ciudad ciudad3 = new Ciudad();
		ciudad3.setNombre("Montevideo");
		ciudad3.setUbicacionGeografica(ubicacion3);
		
		Pais pais1 = new Pais();
		pais1.setNombre("Argentina");
		pais1.setCapital(ciudad1);

		Pais pais2 = new Pais();
		pais2.setNombre("España");
		pais2.setCapital(ciudad2);

		Pais pais3 = new Pais();
		pais3.setNombre("Uruguay");
		pais3.setCapital(ciudad3);
		
		//ejecucion
		Session session = getSession();
		session.save(pais1);
		session.save(pais2);
		session.save(pais3);
		
		List<Pais> paisesNorteTropicoCancer = getSession().createCriteria(Pais.class)
				.createAlias("capital", "capitalpais")
				.createAlias("capitalpais.ubicacionGeografica", "ubicacioncapitalpais")
				.add(Restrictions.gt("ubicacioncapitalpais.latitud", tropicoDeCancer.getLatitud()))
				.list();
		
		//contrastacion
		for (Pais pais : paisesNorteTropicoCancer){
			assertThat(pais.getCapital().getUbicacionGeografica().getLatitud()).isGreaterThan(tropicoDeCancer.getLatitud());
		}		
	}
	
	@Test @Transactional @Rollback
	public void TestQueBuscaTodasLasCiudadesDelHemisferioSur() {
		//preparacion 
		Ubicacion ubicacionLineaDelEcuador = new Ubicacion();
		ubicacionLineaDelEcuador.setLatitud(0.0);
		
		Ubicacion ubicacion1 = new Ubicacion();
		ubicacion1.setLatitud(-34.60368);

		Ubicacion ubicacion2 = new Ubicacion();
		ubicacion2.setLatitud(41.38506);
		
		Ciudad ciudad1 = new Ciudad();
		ciudad1.setNombre("Buenos Aires");
		ciudad1.setUbicacionGeografica(ubicacion1);

		Ciudad ciudad2 = new Ciudad();
		ciudad2.setNombre("Barcelona");
		ciudad2.setUbicacionGeografica(ubicacion2);
		
		//ejecucion
		Session session = getSession();
		session.save(ciudad1);
		session.save(ciudad2);
		
		List<Ciudad> ciudadesHemisferioSur = getSession().createCriteria(Ciudad.class)
				.createAlias("ubicacionGeografica", "ubicaciongeograficaciudad")
				.add(Restrictions.le("ubicaciongeograficaciudad.latitud", ubicacionLineaDelEcuador.getLatitud()))
				.list();
		
		//contrastacion
		for (Ciudad ciudad : ciudadesHemisferioSur){
			assertThat(ciudad.getUbicacionGeografica().getLatitud()).isLessThanOrEqualTo(ubicacionLineaDelEcuador.getLatitud());
		}	
	}
}


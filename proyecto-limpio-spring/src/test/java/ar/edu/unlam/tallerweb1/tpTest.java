package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class tpTest extends SpringTest {

	@Test
	@Transactional
	@Rollback
	public void TestQueBuscaPisesDeHablaInglesa() {

		Pais pais1 = new Pais();
		pais1.setNombre("Argentina");
		pais1.setIdioma("Español");

		Pais pais2 = new Pais();
		pais2.setNombre("Brasil");
		pais2.setIdioma("Portugués");

		Pais pais3 = new Pais();
		pais2.setNombre("Estados Unidos");
		pais2.setIdioma("Ingles");

		Session session = getSession();
		session.save(pais1);
		session.save(pais2);
		session.save(pais3);

		List<Pais> paisHablaInglesa = getSession().createCriteria(Pais.class).add(Restrictions.eq("idioma", "Ingles"))
				.list();

		assertThat(paisHablaInglesa).isNotNull();
		assertThat(paisHablaInglesa.size()).isEqualTo(1);
	}

	@Test
	@Transactional
	@Rollback
	public void TestQueBuscaPaisesDelContinenteEuropeo() {

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

		Session session = getSession();
		session.save(pais1);
		session.save(pais2);
		session.save(pais3);
		session.save(continenteE);
		session.save(continenteA);

		List<Pais> paisEuropeo = getSession().createCriteria(Pais.class).createAlias("continente", "cont")
				.add(Restrictions.eq("cont.nombre", "Europa")).list();

		assertThat(paisEuropeo).isNotNull();
		assertThat(paisEuropeo.size()).isEqualTo(2);
	}

	@Test
	@Transactional
	@Rollback
	public void TestQueBuscaCiudadesAlNorteDelTropicoDeCancer() {
		

	}

	@Test
	@Transactional
	@Rollback
	public void TestQueBuscaCiudadesDebajoDelHemisferioSur() {

		Ubicacion ubicacion1 = new Ubicacion();
		ubicacion1.setLatitud((float) -34.60368440);

		Ubicacion ubicacion2 = new Ubicacion();
		ubicacion2.setLatitud((float) 41.385063899);

		Ubicacion ubicacion3 = new Ubicacion();
		ubicacion3.setLatitud((float) -34.9011127);

		Ciudad ciudad1 = new Ciudad();
		ciudad1.setNombre("Buenos Aires");
		ciudad1.setUbicacionGeografica(ubicacion1);

		Ciudad ciudad2 = new Ciudad();
		ciudad2.setNombre("Barcelona");
		ciudad1.setUbicacionGeografica(ubicacion2);

		Ciudad ciudad3 = new Ciudad();
		ciudad3.setNombre("Montevideo");
		ciudad1.setUbicacionGeografica(ubicacion3);

		Session session = getSession();
		session.save(ubicacion1);
		session.save(ubicacion2);
		session.save(ubicacion3);
		session.save(ciudad1);
		session.save(ciudad2);
		session.save(ciudad3);

		List<Pais> paisesLatitudNegativa = getSession().createCriteria(Pais.class).createAlias("continente", "cont")
				.add(Restrictions.eq("cont.nombre", "Europa")).list();

		assertThat(paisesLatitudNegativa).isNotNull();
		assertThat(paisesLatitudNegativa.size()).isEqualTo(2);
	}

}

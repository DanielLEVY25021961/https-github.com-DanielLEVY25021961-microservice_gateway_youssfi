package levy.daniel.application.controllers.web.error;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CLASSE CountriesCircuitBreaker :<br/>
 * Controller coupe-circuit Hystrix chargé de gérer 
 * les défauts du Micro-Service public COUNTRIES de RapidAPI.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author Daniel Lévy
 * @version 1.0
 * @since 7 oct. 2020
 */
@RestController(value = "CountriesCircuitBreaker")
public class CountriesCircuitBreaker {
	
	// ************************ATTRIBUTS************************************/
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(CountriesCircuitBreaker.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public CountriesCircuitBreaker() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


		
	/**
	 * 
	 * @return Map<String, String>
	 */
	@GetMapping("/errorCountries")
	public Map<String, String> countries() {
		
		final Map<String, String> data 
			= new ConcurrentHashMap<String, String>();
		
		data.put("message", "Pays par défaut");
		data.put("countries", "France, Portugal, Algérie, Espagne, ...");
		
		return data;
		
	} // Fin de countries()._______________________________________________
	
	

} // FIN DE LA CLASSE CountriesCircuitBreaker.-------------------------------

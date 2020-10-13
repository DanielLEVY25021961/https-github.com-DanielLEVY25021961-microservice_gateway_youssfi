package levy.daniel.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

/**
 * CLASSE MicroServiceGatewayYoussfiBackendApplication :<br/>
 * .<br/>
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
 * @since 4 oct. 2020
 */
@SpringBootApplication
@EnableHystrix
public class MicroServiceGatewayYoussfiBackendApplication { // NOPMD by dan on 04/10/2020 19:00

	
		
	/**
	 * Point d'entrée de l'application (Micro-Service).
	 * 
	 * @param pArgs : String[] : paramètres de lancement du Micro-Service.
	 */
	public static void main(final String... pArgs) {
		
		SpringApplication
			.run(MicroServiceGatewayYoussfiBackendApplication.class, pArgs);
		
	} // Fin de main(...)._________________________________________________

	
	
	/**
	 * Configuration PROGRAMMATIQUE <strong>STATIQUE</strong> 
	 * des <strong>routes</strong> des divers 
	 * Micro-Services metier dans le PROXY GATEWAY.<br/>
	 * Les divers Micro-Services sont trouvés via leur NOM 
	 * par le LOAD-BALANCER (lb) et plus via leur URL.<br/>
	 * L'annotation Bean permettra d'informer SPRING que cette méthode 
	 * fait partie de la configuration et doit être exécutée 
	 * dès le démarrage des Micro-Services.<br/>
	 * <br/>
	 * Démarrer dans l'ordre :
	 * <ol>
	 * <li>l'annuaire EUREKA REGISTRY</li>
	 * <li>le premier service métier.</li>
	 * <li>le deuxième service métier.</li>
	 * <li>...</li>
	 * <li>le PROXY (GATEWAY).</li>
	 * </ol>
	 * 
	 * @param pRouteLocatorBuilder : RouteLocatorBuilder.
	 * 
	 * @return RouteLocator
	 */
	@Bean
	public RouteLocator getRoutes(
			final RouteLocatorBuilder pRouteLocatorBuilder) {
		
//		return pRouteLocatorBuilder.routes()
//				.route(route -> 
//					route.path("/customers/**")
//						.uri("lb://CUSTOMER-SERVICE")
//						.id("routeMicroService1_customer-service"))
//				.route(route -> 
//					route.path("/products/**")
//						.uri("lb://INVENTORY-SERVICE")
//						.id("routeMicroService2_inventory-service"))
//				.build();
		
		return pRouteLocatorBuilder.routes()
				.route(route -> 
					route.path("/countries/**")
						.filters(f -> f
								.addRequestHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
								.addRequestHeader("x-rapidapi-key", "7d690d356bmshdcf33e442a61abdp1226bejsn057e22788802")
								.addRequestHeader("useQueryString", "true")
								.rewritePath("/countries/(?<segment>.*)", "/${segment}")
								.hystrix(h -> h.setName("countries").setFallbackUri("forward:/errorCountries"))
								)
						.uri("https://restcountries-v1.p.rapidapi.com")
						.id("routeRestCountries"))
				.build();
		
	} // Fin de getRoutes(...).____________________________________________
	

	
	/**
	 * Configuration PROGRAMMATIQUE <strong>DYNAMIQUE</strong> 
	 * des <strong>routes</strong> des divers 
	 * Micro-Services metier dans le PROXY GATEWAY.<br/>
	 * L'annotation Bean permettra d'informer SPRING que cette méthode 
	 * fait partie de la configuration et doit être exécutée 
	 * dès le démarrage des Micro-Services.<br/>
	 * <br/>
	 * Démarrer dans l'ordre :
	 * <ol>
	 * <li>l'annuaire EUREKA REGISTRY</li>
	 * <li>le premier service métier.</li>
	 * <li>le deuxième service métier.</li>
	 * <li>...</li>
	 * <li>le PROXY (GATEWAY).</li>
	 * </ol>
	 * 
	 * @param pReactiveDiscoveryClient : ReactiveDiscoveryClient.
	 * @param pDiscoveryLocatorProperties : DiscoveryLocatorProperties.
	 * 
	 * @return DiscoveryClientRouteDefinitionLocator
	 */
	@Bean
	public DiscoveryClientRouteDefinitionLocator getDynamicRoutes(
			final ReactiveDiscoveryClient pReactiveDiscoveryClient
				, final DiscoveryLocatorProperties pDiscoveryLocatorProperties) {
		
		return new DiscoveryClientRouteDefinitionLocator(
				pReactiveDiscoveryClient
					, pDiscoveryLocatorProperties);
		
	} // Fin de getDynamicRoutes(...)._____________________________________
	
	

} // FIN DE LA CLASSE MicroServiceGatewayYoussfiBackendApplication.----------

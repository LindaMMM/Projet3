package fr.bicomat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Lanceur de l'application.
 * @author linda
 *
 */
@EnableJpaRepositories(basePackages = {"fr.bicomat.dao", "fr.bicomat.Auth.dao"})
@SpringBootApplication
@EnableScheduling
public class AgenceApplication {

    /**
     * Constructeur par défaut.
     */
    protected AgenceApplication() {	
    }

	/**
	 * Instance de log de l'application.
	 */
    private static final Logger LOG = LoggerFactory.getLogger(AgenceApplication.class) ;

    /**
     * Main de l'application. 
     * @param args argument de lancement.
     */
	public static void main(final String[] args) {
        LOG.debug("Chargement de l'application");
        SpringApplication.run(AgenceApplication.class, args) ;
    }
}

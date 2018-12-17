package fr.bicomat.dao;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.bicomat.entities.Alerte;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlerteRepositoryTest {
	
	@Autowired
	AlerteRepository alerteRepository;
	
	@Test
	public void testFindByDateAlerte() {
		Date now = new Date();
		Set<Alerte> list = alerteRepository.findByDateAlerte(now);
		Iterator<Alerte> it = list.iterator();
		while(it.hasNext()){

			Alerte v = it.next();
			System.out.println(v.toString());
			}
		assertNotNull(list);
	}

}


package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Customer;
import domain.HandyWorker;
import domain.Note;
import domain.Referee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	//Service----------------------------------------------
	@Autowired
	private NoteService		noteService;
	@Autowired
	private CustomerService	customerService;


	//Test----------------------------------------------

	@Test
	public void testCreate() {
		System.out.println("----testCreate()----");

		try {
			final Note note = this.noteService.create();
			final Note saved;
			note.setMoment(new Date());
			note.setMandatoryComment("comentario1");
			note.setRefereeComment("Comentario2");
			note.setHandyWorkerComment("Comentario3");
			note.setCustomer(new Customer());
			note.setReferee(new Referee());
			note.setHandyWorker(new HandyWorker());

			saved = this.noteService.save(note);
			Assert.isTrue(this.noteService.findAll().contains(saved));
			//Falta m�todo findAll.
			System.out.println("�xito");
		} catch (final Exception e) {
			System.out.println("Fallo, " + e.getMessage());
		}
	}
}

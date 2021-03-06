
package services;

import java.util.Arrays;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Application;
import domain.Box;
import domain.Category;
import domain.Complaint;
import domain.Customer;
import domain.Endorsement;
import domain.FixUpTask;
import domain.Money;
import domain.Phase;
import domain.SocialProfile;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CustomerServiceTest extends AbstractTest {

	//Service----------------------------------------------
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private FixUpTaskService	fixUpTaskService;


	//Test----------------------------------------------

	@Test
	public void testCreate() {
		System.out.println("----testCreate()----");

		try {
			final Customer cust = this.customerService.create();
			final Customer saved;
			//Actor
			cust.setName("johnny");
			cust.setEmail("eljohnny@jhonny.com");
			cust.setPhoneNumber("645783987");
			cust.setAddress("C/Minico");
			cust.setBan(false);
			cust.setMiddleName("Florencio");
			cust.setSurname("Liloni");
			cust.setPhotoURL("http://www.johnnypeke.com");
			cust.setSocialProfiles(Arrays.asList(new SocialProfile()));
			cust.setUserAccount(new UserAccount());
			cust.setBoxes(Arrays.asList(new Box()));
			//Endorser
			cust.setEndorsements(Arrays.asList(new Endorsement()));
			cust.setScore(98);
			//Customer
			cust.setFixUpTasks(Arrays.asList(new FixUpTask()));

			saved = this.customerService.save(cust);
			//Falta save
			Assert.isTrue(this.customerService.findAll().contains(saved));
			//Falta m�todo findAll.
			System.out.println("�xito");
		} catch (final Exception e) {
			System.out.println("Fallo, " + e.getMessage());
		}
	}
	@Test
	public void findByFixUpTask() {
		System.out.println("---testFindByFixUpTask---");

		try {
			final FixUpTask fixUp = this.fixUpTaskService.create();
			fixUp.setTicker(utilities.TickerGenerator.generateTicker());
			fixUp.setMoment(new Date());
			fixUp.setDescription("Esto es una descripci�n de fixUpTaskService");
			fixUp.setAddress("AddressHola");
			fixUp.setMaximumPrice(new Money());
			fixUp.setStartDate(new Date());
			fixUp.setEndDate(new Date());

			fixUp.setCategory(new Category());
			fixUp.setWarranty(Arrays.asList(new Warranty()));
			fixUp.setPhases(Arrays.asList(new Phase()));
			fixUp.setComplaints(Arrays.asList(new Complaint()));
			fixUp.setApplications(Arrays.asList(new Application()));

			final Customer custom = this.customerService.findByFixUpTask(fixUp);
			Assert.notNull(fixUp);
			Assert.notNull(custom);

			System.out.println("�xito");

		} catch (final Exception e) {
			System.out.println("Fallo");
		}

	}
	@Test
	public void fixUpTasksStatistics() {
		System.out.println("---testFixUpTasksStatistics---");

		final FixUpTask fixUp = this.fixUpTaskService.create();
		fixUp.setTicker(utilities.TickerGenerator.generateTicker());
		fixUp.setMoment(new Date());
		fixUp.setDescription("Esto es una descripci�n de fixUpTaskService");
		fixUp.setAddress("AddressHola");
		fixUp.setMaximumPrice(new Money());
		fixUp.setStartDate(new Date());
		fixUp.setEndDate(new Date());

		fixUp.setCategory(new Category());
		fixUp.setWarranty(Arrays.asList(new Warranty()));
		fixUp.setPhases(Arrays.asList(new Phase()));
		fixUp.setComplaints(Arrays.asList(new Complaint()));
		fixUp.setApplications(Arrays.asList(new Application()));

	}

	@Test
	public void customersWithMoreFixUpTasks() {
		System.out.println("---customersWithMoreFixUpTasks---");
		//final FixUpTask fixUp = this.fixUpTaskService.create();
		final Customer custo = this.customerService.create();

		//Customer res = Collections.max(custo.getFixUpTasks());
	}
	@Test
	public void topThreeCustomersbyComplaints() {
		System.out.println("---topThreeCustomersByComplaints---");
	}
}

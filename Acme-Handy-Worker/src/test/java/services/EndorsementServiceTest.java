
package services;

import java.util.ArrayList;
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
import domain.Endorsement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndorsementServiceTest extends AbstractTest {

	//Service
	@Autowired
	private EndorsementService	endorsementService;

	@Autowired
	private CustomerService		customerService;


	//Test
	@Test
	public void testEndorsement() {
		System.out.println("------Test Endorsement------");
		super.authenticate("customer");
		final Endorsement end, saved;
		end = this.endorsementService.create();
		try {
			super.authenticate("customer");
			end.setComment("comment1");
			end.setMoment(new Date());
			final ArrayList<Customer> custs = new ArrayList<>();
			custs.addAll(this.customerService.findAll());
			final Customer cust = custs.get(1);
			final Customer cust2 = custs.get(2);
			end.setSender(cust);
			end.setRecipient(cust2);
			saved = this.endorsementService.save(end);
			Assert.isTrue(this.endorsementService.findAll().contains(saved));

			//findBy, saveBy y delteBy de Customer y HadnyWorker

			super.unauthenticate();
			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

}

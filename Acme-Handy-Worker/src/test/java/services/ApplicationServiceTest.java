
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.Customer;
import domain.HandyWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	//Service
	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	//Test
	@Test
	public void testCreate() {
		System.out.println("------Test Create------");
		final Application app, saved, app2, app3;
		super.authenticate("handyWorker");
		app = this.applicationService.create();
		System.out.println(this.applicationService.findAll());
		try {

			//			app.setMoment(new Date());
			//			app.setStatus("pending");
			//			final Money mon = new Money();
			//			app.setOfferedPrice(mon);
			//			//			System.out.println(mon);
			//			//			System.out.println(mon.getAmount());
			//			//			System.out.println(mon.getCurrency());
			//			app.setComment("comment");
			//			app.setRejectedCause("rejectedCause");
			//			//			app.setCreditCard(new CreditCard());
			//			//			app.setFixUpTask(new FixUpTask());
			//			//			System.out.println(app.getFixUpTask());
			//			System.out.println(app);
			//			saved = this.applicationService.save(app);
			//			System.out.println(saved);
			//			System.out.println(this.applicationService.findAll());
			//			Assert.isTrue(this.applicationService.findAll().contains(saved));

			super.unauthenticate();

			//findByCustomer
			super.authenticate("customer");
			final ArrayList<Customer> custs = new ArrayList<>();
			custs.addAll(this.customerService.findAll());
			final Customer cust = custs.get(1);
			final Collection<Application> appsCust = this.applicationService.findByCustomer();
			System.out.println(appsCust);
			Assert.notNull(appsCust);

			//saveByCustomer
			final ArrayList<Application> sbc = new ArrayList<>();
			sbc.addAll(appsCust);
			final Application appCust = sbc.get(1);
			System.out.println(appCust.getComment());
			appCust.setComment("ejemplo");
			final Application appSavedByCust = this.applicationService.saveByCustomer(appCust);
			Assert.notNull(appSavedByCust);
			final Collection<Application> appsCust2 = this.applicationService.findByCustomer();
			final ArrayList<Application> sbc2 = new ArrayList<>();
			sbc2.addAll(appsCust2);
			final Application appCust2 = sbc2.get(1);
			System.out.println(appCust2.getComment());

			super.unauthenticate();

			//findByHandyWorker
			super.authenticate("handyWorker");
			final ArrayList<HandyWorker> hws = new ArrayList<>();
			hws.addAll(this.handyWorkerService.findAll());
			final HandyWorker hw = hws.get(1);
			System.out.println(hw);
			final Collection<Application> appsHw = this.applicationService.findByHandyWorker();
			System.out.println(appsHw);
			Assert.notNull(appsHw);

			//			//saveByHandyWorker
			//			final ArrayList<Application> sbc = new ArrayList<>();
			//			sbc.addAll(appsCust);
			//			final Application appCust = sbc.get(1);
			//			System.out.println(appCust.getComment());
			//			appCust.setComment("ejemplo");
			//			final Application appSavedByCust = this.applicationService.saveByCustomer(appCust);
			//			Assert.notNull(appSavedByCust);
			//			final Collection<Application> appsCust2 = this.applicationService.findByCustomer();
			//			final ArrayList<Application> sbc2 = new ArrayList<>();
			//			sbc2.addAll(appsCust2);
			//			final Application appCust2 = sbc2.get(1);
			//			System.out.println(appCust2.getComment());

			//
			//			super.authenticate("admin");
			//
			//			app2 = app;
			//			app2.setStatus("ACCEPTED");
			//			final Application newApp2 = this.applicationService.save(app2);
			//
			//			app3 = app;
			//			app3.setStatus("REJECTED");
			//			final Application newApp3 = this.applicationService.save(app3);
			//
			//			final double pendingApps = this.applicationService.pendingApplications();
			//			Assert.isTrue(pendingApps > 0);
			//
			//			final double acceptedApps = this.applicationService.pendingApplications();
			//			Assert.isTrue(acceptedApps > 0);
			//
			//			final double rejectedApps = this.applicationService.pendingApplications();
			//			Assert.isTrue(rejectedApps > 0);
			//			super.unauthenticate();
			//			super.authenticate("customer");
			//			final Date date = new GregorianCalendar(2012, 2, 2).getTime();
			//			newApp2.setMoment(date);
			//			final Application newApp4 = this.applicationService.saveByCustomer(app2);
			//			super.unauthenticate();
			//			super.authenticate("admin");
			//			final double elapsedApps = this.applicationService.elapsedApplications();
			//			Assert.isTrue(elapsedApps > 0);
			//
			//			final ArrayList<Object> offPStats = this.applicationService.offeredPriceStatisctics();
			//			Assert.notNull(offPStats);
			//
			//			super.unauthenticate();

			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

}

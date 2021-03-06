
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ReportServiceTest extends AbstractTest {

	//Service----------------------------------------------------------
	@Autowired
	private ReportService	reportService;


	//Test-------------------------------------------------------------

	@Test
	public void testReport() {
		System.out.println("------Test Report------");
		final Report report, saved;
		final Collection<Report> reports;
		try {
			report = this.reportService.create();
			report.setMoment(new Date());
			report.setDescription("This is a description.");
			report.setAttachments("http://www.attachments1.com");

			saved = this.reportService.save(report);
			Assert.notNull(saved);
			reports = this.reportService.findAll();

			Assert.isTrue(reports.contains(saved));
			System.out.println("�xito");
		} catch (final Exception e) {
			System.out.println("�Fallo," + e.getMessage() + "!");
		}
	}
}

package com.purna.stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.purna.libraries.TestContext;
import com.purna.libraries.Utilities;
import com.purna.pages.DashboardPage;
import com.purna.pages.LoginPage;
import com.purna.pages.SalesInvoicePage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SalesInvoice_stepdefinition extends Utilities{
	private WebDriver driver;
    private TestContext testContext;
	LoginPage onLoginPage;
	DashboardPage onDashBoardPage;
	SalesInvoicePage onNewSalesInvoicePage;
	
	public SalesInvoice_stepdefinition(TestContext context) {
		testContext = context;
    	driver = testContext.getDriver();
		onLoginPage = new LoginPage(driver);
		onDashBoardPage = new DashboardPage(driver);
		onNewSalesInvoicePage = new SalesInvoicePage(driver);
	}
	
      
	
	
	@When("user clicks on new sale invoice button")
	public void user_clicks_on_new_sale_invoice_button() {
		onDashBoardPage.btn_newSaleInvoice.click();
	}
	@When("user enters the sale invoice details")
	public void user_enters_the_sale_invoice_details() {
		onNewSalesInvoicePage.txtbox_powo.sendKeys(testContext.getMapTestData().get("powoNo"));
		onNewSalesInvoicePage.txtbox_powoDate.sendKeys(testContext.getMapTestData().get("powo_date"));
		onNewSalesInvoicePage.dropdown_billTo.click();
		
		//Select select = new Select(onNewSalesInvoicePage.dropdown_billTo);
		//select.selectByValue("Ashok");
		
		
		doDropDownUsingValue(onNewSalesInvoicePage.dropdown_billTo, "Ashok" );
		
		
	}
	@When("user save the sale invoice details")
	public void user_save_the_sale_invoice_details() {
		onNewSalesInvoicePage.btn_save.click();
		acceptAlert(driver);
	}
	
	@When("user clicks on sale invoice details button")
	public void user_clicks_on_sale_invoice_details_button() {
		onNewSalesInvoicePage.link_saveInvoiceDetail.click();
	}
	@Then("new sale invoice should be created")
	public void new_sale_invoice_should_be_created() {
	    Assert.assertEquals("Ashok", driver.findElement(By.xpath("//td[text()='Ashok']")).getText());
	}

}

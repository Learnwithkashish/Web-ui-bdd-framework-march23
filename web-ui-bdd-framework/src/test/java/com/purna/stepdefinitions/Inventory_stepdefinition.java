package com.purna.stepdefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.purna.libraries.TestContext;
import com.purna.libraries.Utilities;
import com.purna.pages.DashboardPage;
import com.purna.pages.LoginPage;
import com.purna.pages.PurchaseItemDashPage;
import com.purna.pages.PurchaseItemPage;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Inventory_stepdefinition extends Utilities{
	private WebDriver driver;
    private TestContext testContext;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	PurchaseItemDashPage purchaseItemDashPage;
	PurchaseItemPage purchaseItemPage;
	
	public Inventory_stepdefinition(TestContext context) {
		testContext = context;
    	driver = testContext.getDriver();
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		purchaseItemDashPage = new PurchaseItemDashPage(driver);
		purchaseItemPage = new PurchaseItemPage(driver);
	}
	
	@When("user clicks on Inventory link")
	public void user_clicks_on_inventory_link() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.link_inventory));
		
		dashboardPage.link_inventory.click();
	}
	
	@When("user clicks on Add new Item button")
	public void user_clicks_on_add_new_item_button() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(purchaseItemDashPage.btn_addNewItem));
		purchaseItemDashPage.btn_addNewItem.click();
	}
	
	@When("user enters inventory details")
	public void user_enters_inventory_details() {
		purchaseItemPage.textbox_itemcode.sendKeys(testContext.getMapTestData().get("itemCode"));
		purchaseItemPage.textbox_itemname.sendKeys(testContext.getMapTestData().get("itemname"));
		
		doDropDownUsingVisibleText(purchaseItemPage.dropdown_units, "Numbers");
		
		purchaseItemPage.textbox_suppCode.sendKeys(testContext.getMapTestData().get("supCode"));
		purchaseItemPage.textbox_weight.sendKeys(testContext.getMapTestData().get("weight"));
		purchaseItemPage.textbox_rate.sendKeys(testContext.getMapTestData().get("rate"));
		purchaseItemPage.textbox_quantity.sendKeys(testContext.getMapTestData().get("quantity"));
		purchaseItemPage.textbox_amount.click();	
	}
	
	@When("user clicks on Add Item button")
	public void user_clicks_on_add_item_button() {
		purchaseItemPage.btn_addItem.click();
	}
	
	@When("user clicks on Item details button")
	public void user_clicks_on_item_details_button() {
		purchaseItemPage.link_itemDetails.click();
	}
	
	@Then("Inventory should be created")
	public void inventory_should_be_created() {
	    Assert.assertEquals("Dairy_123", driver.findElement(By.xpath("//td[text()='Dairy_123']")).getText());
	}

}

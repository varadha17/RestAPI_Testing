package stepDefinition;

import io.cucumber.java.en.Given;
import pth.pageActions.ValidateContentPage;

public class contentPageTest {
	
	public ValidateContentPage vcp = new ValidateContentPage();

	@Given("run api")
	public void run_api() {
		vcp.setURI();
		vcp.getAPICalls();
	}




}

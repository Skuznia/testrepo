package org.caringbridge.uitests.testcases;

import java.util.Collections;
import java.util.List;
import org.caringbridge.uitests.automationsupport.GenerateData;
import org.caringbridge.uitests.automationsupport.ListUtility;
import org.caringbridge.uitests.base.BaseTestCase;
import org.caringbridge.uitests.base.TestCategory;
import org.caringbridge.uitests.base.TestCategoryOptions;
import org.caringbridge.uitests.browser.actions.NavigationActions;
import org.caringbridge.uitests.donation.actions.DonationFlows;
import org.caringbridge.uitests.donation.args.TributeDonationArgs;
import org.caringbridge.uitests.donations.structure.DonationPage;
import org.caringbridge.uitests.donations.structure.TributeDonationPage;
import org.caringbridge.uitests.homepage.structure.HomepagePage;
import org.caringbridge.uitests.infrastructure.interfaces.IElement;
import org.caringbridge.uitests.infrastructure.interfaces.options.SearchTypeOptions;
import org.caringbridge.uitests.profile.args.AddressArgs;
import org.caringbridge.uitests.profile.args.ProfileArgs;
import org.caringbridge.uitests.profile.args.options.EmailOptInOption;
import org.caringbridge.uitests.register.actions.RegisterFlows;
import org.caringbridge.uitests.sharedverification.SignInRegisterSharedVerification;
import org.caringbridge.uitests.signin.actions.SignInFlows;
import org.caringbridge.uitests.signout.structure.SignOutPage;
import org.caringbridge.uitests.site.args.SiteArgs;
import org.caringbridge.uitests.site.feeds.structure.TributesFeed;
import org.caringbridge.uitests.site.structure.SitePage;
import org.caringbridge.uitests.startasite.actions.StartASiteFlows;
import org.caringbridge.uitests.testmanagement.TestCaseVariables;
import org.caringbridge.uitests.tributes.actions.TributesFeedActions;
import org.junit.Test;

public class TributeDonationTests {
	
	@Test
	public void testTributeDonations(){
		//create a new site
        SiteArgs site = StartASiteFlows.startASiteComplete();
        SignOutPage.navigateTo();
        //create a new account	
        ProfileArgs profile = RegisterFlows.registerComplete();
        //navigate to site created
        TestCaseVariables.getBrowser().navigateTo(site.getSiteName());
        
        TributesFeed.getTributesFeedTab().click();
        TributesFeed.getTributeFeedPanel().waitForVisible();
        //verify tribute donation button & that it takes you to split #12022
        boolean present = verifyDonationButton();
        boolean present2 = false;
        //ask how to proceed if donationButton is not there.
        if(present){
            //click button to redirect to donation page with split #12022, maybe just redirect after verifying button is there?
        	TestCaseVariables.getBrowser().findElement("Donate in Honor of Patient", SearchTypeOptions.Attribute).click();    
        	//verify landing address has "split=12022"
        	String url = TestCaseVariables.getBrowser().getUrl();
        	TestCaseVariables.getVerify().contains("split=12022", url, "Verifying right split donation address.");
            //donate using predefined donation method
        	DonationFlows.tributeDonationFlow(profile, new TributeDonationArgs());
        	//donation success?
        	
        	//redirect back to site
        	TestCaseVariables.getBrowser().navigateTo(site.getSiteName());
            //verify that the donation button is still there
        	if(verifyDonationButton()){
        		present2 = true;
        	}	
        }    
        
        TestCaseVariables.getVerify().areEqual(true, present, "Tribute Donation Button Initially Located.");
        TestCaseVariables.getVerify().areEqual(true, present2, "Tribute Donation Button Located After Donation.");
        
        
	}
	@Test
	public boolean verifyDonationButton(){
        try{
        	TestCaseVariables.getBrowser().findElement("Donate in Honor of Patient", SearchTypeOptions.Attribute);
        }catch(Exception e){
        	return false;
        	//reminder: ask best way to verify an element
        }
		return true;
	}
	
	
}

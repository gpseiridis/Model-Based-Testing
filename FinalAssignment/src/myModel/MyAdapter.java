package myModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;

import static org.junit.Assert.*;



public class MyAdapter {

	
	 private StringBuffer verificationErrors = new StringBuffer(); // used to selenium IDE
	WebDriver driver;
	String key = "webdriver.chrome.driver";
	String value = "C:\\MBT\\drivers\\chromedriver.exe";
	int counter = 1;
	String username = "MbtUser";
	int randomNum = 0;
	
	
	 public MyAdapter() {
		 driver = new FirefoxDriver();
		 //System.setProperty(key, value);
		 //driver = new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 driver.get("http://localhost/opennote/#/");
		  
	    }
	
	    public void reset() {
	    	driver.quit();
	    	driver = new FirefoxDriver();
	    	randomNum++;
	    	username=username+randomNum;
	    	//driver = new ChromeDriver();
	    	driver.get("http://localhost/opennote/#/");
	    	
	      }
	  //waitSmall, waitMedium and waitLarge have been refactored to just pleaseWait
	    public void pleaseWait(int secs){
	    	try {
				Thread.sleep(secs);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    
	  //INPUT 2 clickRegister
		public void clickRegister(){
				//waitSmall();
				pleaseWait(500);
				driver.findElement(By.id("regBox")).click();
				//CHECK IF WORKS 
				try {
				      assertTrue(isElementPresent(By.xpath("(//input[@type='text'])[2]")));
				    } catch (Error e) {
				      verificationErrors.append(e.toString());
				    }
				
				
		}
		// INPUT 4 register
		public void register(){
			//waitMedium();
			pleaseWait(1000);
			driver.findElement(By.xpath("(//input[@type='text'])[2]")).click();
			driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(
					username+counter);
			driver.findElement(By.xpath("(//input[@type='password'])[2]")).clear();
			driver.findElement(By.xpath("(//input[@type='password'])[2]"))
					.sendKeys("george");
			driver.findElement(By.xpath("//input[@value='Register']")).click();
			//waitLarge();
			pleaseWait(2000);
			driver.findElement(By.id("alertify-ok")).click();
			clickNotes();
			driver.findElement(By.id("alertify-ok")).click();
			clickNotes();
			stopAlerts(); // <-- creates a folder just to stop getting a pop up window, we don't increment num of folders
			clickNotes();
			
			assertTrue(isElementPresent(By.cssSelector("button.customButton.ng-binding")));
			
			counter++;
				
		}   
	    
//INPUT: 1 clickLogin
	public void clickLogin(){
		
		//waitSmall();
		pleaseWait(500);
		
		driver.findElement(By.id("loginBox")).click();
		assertTrue(isElementPresent(By.xpath("//input[@type='text']")));
		//waitSmall();
		pleaseWait(500);
		}
	//INPUT 2 Login
	public void login(){
		
		
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(
				username+(counter-1));
		driver.findElement(By.xpath("//input[@type='password']")).click();
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(
				"george");
		driver.findElement(By.cssSelector("input.button")).click();
		assertTrue(isElementPresent(By.cssSelector("button.customButton.ng-binding")));
		//waitSmall();
		pleaseWait(500);
	}
	
	  //INPUT 20 clickNotes  BRINGS YOU TO MAIN MENU NO MATTER WHERE YOU ARE 
	public void clickNotes(){
		//waitSmall();
		pleaseWait(500);
		driver.findElement(By.id("home")).click();
		assertTrue(isElementPresent(By.cssSelector("button.customButton.ng-binding")));
		//waitSmall();
		pleaseWait(500);
	}
	
	//// INPUT 7 newFolder
	public void newFolder(){
		//Although it's supposed to create just a new folder, we actually create 2 folders
		//One is the main folder (automatedFolderForNotes) where we put the Notes 
		// the other is the folder where we put the child folders (ChildFoldersInside)
		// Reason is because Notes and Folder appear as boxes on screen and each of these have an ID
		// so it's easier to guide the Adapter to click the IDs on one folder that we know that it has only Notes or Child folders
		
		//waitSmall();
		pleaseWait(500);
		clickNotes();
		driver.findElement(By.cssSelector("button.customButton.ng-binding")).click();
		 
		//waitSmall();
		pleaseWait(500);
	    driver.findElement(By.id("alertify-text")).clear();
	    driver.findElement(By.id("alertify-text")).click();
	    
	    driver.findElement(By.id("alertify-text")).sendKeys("automatedFolderForNotes");
	   //waitSmall();
	    pleaseWait(500);
	    driver.findElement(By.id("alertify-ok")).click();
	  // waitSmall();
	    pleaseWait(500);
	    driver.findElement(By.id("home")).click();
	    driver.findElement(By.cssSelector("button.customButton.ng-binding")).click();
		 //driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		
//		waitSmall();
	    pleaseWait(500);
	    driver.findElement(By.id("alertify-text")).clear();
	    driver.findElement(By.id("alertify-text")).click();
	    
	    driver.findElement(By.id("alertify-text")).sendKeys("ChildFoldersInside");
	   // waitSmall();
	    pleaseWait(500);
	    driver.findElement(By.id("alertify-ok")).click();
	    try {
	        assertTrue(isElementPresent(By.id("folderTitle")));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	   // waitSmall();
	    pleaseWait(500);
	}
	
	//  INPUT 14 deleteFolder
	public void deleteFolder(){
		
				clickNotes();
				
				try {
				      assertEquals("automatedFolderForNotes", driver.findElement(By.cssSelector("h4.ng-binding")).getText());
				    } catch (Error e) {
				      verificationErrors.append(e.toString());
				    }
				driver.findElement(By.linkText("automatedFolderForNotes")).click();
			
				// end making sure
		
		//waitMedium();
				pleaseWait(1000);
		
		driver.findElement(By.id("folderTitle")).click();
	    driver.findElement(By.id("removeFolder")).click();
	    driver.findElement(By.id("alertify-ok")).click();
	  //  waitMedium();
	    pleaseWait(1000);
	    // NOW DELETE ChildFoldersInside
	    clickNotes();
		
		driver.findElement(By.linkText("ChildFoldersInside")).click();
	
		// end making sure

		//waitSmall();
		pleaseWait(500);

		driver.findElement(By.id("folderTitle")).click();
		driver.findElement(By.id("removeFolder")).click();
		driver.findElement(By.id("alertify-ok")).click();
		
		//waitSmall();
		pleaseWait(500);
// FOLDER WITH CHILDREN DELETED
// after we deleted the folders, we check if the remaining folder has the name 	stopAlerts
// which is the folder we create when we register a new user because if the user has no folder 
// an annoying pop up window will be displayed all the time until he creates a folder and it makes the guidance
// of the adapter way more difficult
		
		try {
		      assertEquals("stopAlerts", driver.findElement(By.cssSelector("h4.ng-binding")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
	}
	
	//INPUT 6 selectFolder
	
	public void selectFolder(){
		//waitSmall();
		pleaseWait(500);
		    driver.findElement(By.linkText("automatedFolderForNotes")).click();
		   // waitVerySmall();
		    pleaseWait(100);
		    try {
		        assertEquals("automatedFolderForNotes", driver.findElement(By.id("folderTitle")).getText());
		      } catch (Error e) {
		        verificationErrors.append(e.toString());
		      }
		   // waitMedium();
		    pleaseWait(1000);
	}
	
	////INPUT 13 renameFolder
	public void renameFolder(){
		// make sure you rename the correct folder
		// GO TO FOLDER WHERE NOTES ARE KEPT
		clickNotes();
		driver.findElement(By.linkText("automatedFolderForNotes")).click();
	// did some refactoring here
		rename("renamedFolder");
		//waitMedium();
		pleaseWait(1000);
		rename("automatedFolderForNotes");

	}
	
	// JUST A METHOD FOR REFACTORING, CALLED BY renameFolder
	public void rename(String FolderName){
		
		//waitMedium();
		pleaseWait(1000);
		driver.findElement(By.id("folderTitle")).click();
	    driver.findElement(By.id("renameFolder")).click();
	    driver.findElement(By.id("alertify-text")).clear();
	    //RENAME IT TO SOMETHING ELSE
	    driver.findElement(By.id("alertify-text")).sendKeys(FolderName);
	    driver.findElement(By.id("alertify-ok")).click();
		//waitSmall();
	    pleaseWait(500);
		try { 		      assertEquals(FolderName, driver.findElement(By.id("folderTitle")).getText());
	    } catch (Error e) {		      verificationErrors.append(e.toString());		    }
	}
	//INPUT 24 newChildFolder
	
	public void newChildFolder(){
		//we want the child folders to be created inside the folder ChildFoldersInside to avoid confusion
		// with the element IDs
		clickNotes();
		driver.findElement(By.linkText("ChildFoldersInside")).click();
	    //waitMedium();
		pleaseWait(1000);
		driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
	    driver.findElement(By.id("alertify-text")).clear();
	    driver.findElement(By.id("alertify-text")).click();
	    driver.findElement(By.id("alertify-text")).sendKeys("child folder");
	    driver.findElement(By.id("alertify-ok")).click();
	    //waitSmall();
	    pleaseWait(500);
	    try {
	        assertEquals("child folder", driver.findElement(By.id("folderTitle")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	    
// FOR SOME REASON, WHEN ITS INSIDE THE CHILD FOLDER SOMETIMES IT CRASHES... THEREFORE I WANT TO MOVE 
// AWAY FROM THAT FOLDER AND GO DO ACTIONS SOMEWHERE SAFER
// AND BY SAFE, I MEAN THE MAIN FOLDER I USE FOR TESTING 
	    clickNotes();
		driver.findElement(By.linkText("automatedFolderForNotes")).click();
	   // waitMedium();
		pleaseWait(1000);
	}
	
	// INPUT 22 selectChildFolder
	
	public void selectChildFolder(){
		clickNotes();
		
		driver.findElement(By.linkText("ChildFoldersInside")).click();
	    
		//waitLarge();
		pleaseWait(2000);
		//clicks the child folder
		 driver.findElement(By.cssSelector("h4.ng-binding")).click();
		 //waitSmall();
		 pleaseWait(500);
		 try {
		        assertEquals("child folder", driver.findElement(By.id("folderTitle")).getText());
		      } catch (Error e) {
		        verificationErrors.append(e.toString());
		      }
		  //waitSmall();
		 pleaseWait(500);
	}
	//INPUT 9 newNote and INPUT 10 saveNote SEQUENCE
	
	public void newNoteSaveNote(){
		// GO TO FOLDER WHERE NOTES ARE KEPT
		clickNotes();
		
		driver.findElement(By.linkText("automatedFolderForNotes")).click();
		//waitMedium();
		pleaseWait(1000);
		driver.findElement(By.cssSelector("button.customButton.ng-binding")).click();
		
		//waitMedium();
		pleaseWait(1000);
	    driver.findElement(By.id("noteName")).clear();
	    driver.findElement(By.id("noteName")).click();
	    //waitVerySmall();
	    pleaseWait(100);
	    driver.findElement(By.id("noteName")).sendKeys("40 KEKS");
	    //waitSmall();
	    pleaseWait(500);
	    driver.findElement(By.cssSelector("button.customButton.ng-binding")).click();
	   
	    //some wait for next page (singleNote)
		//waitMedium();
		pleaseWait(1000); 
	    assertEquals("40 KEKS", driver.findElement(By.id("noteName")).getAttribute("value"));
		// waitVerySmall();
	    pleaseWait(500);
	}
	// INPUT 8 Select Notes
	
	public void selectNote(){
		// to select notes you need to go first to the folder where the notes are kept
		// so it goes first to the Folder page (where folders are stored) and selects the automatedFolderForNotes folder
		clickNotes();
		
		driver.findElement(By.linkText("automatedFolderForNotes")).click();
		//waitMedium();
		 pleaseWait(1000);
		  driver.findElement(By.cssSelector("h4.ng-binding")).click();
		 //waitMedium();
		 pleaseWait(1337);
		 assertEquals("40 KEKS", driver.findElement(By.id("noteName")).getAttribute("value"));
		 //waitSmall();
		pleaseWait(500);
	}
	
	//INPUT 21 clickEdit
	public void clickEdit(){
//NOTE! the EDIT button sometimes disappears and we don't know why. Like it literally disappears
// we tried some trial and error but the code seems to work fine now
		//waitMedium();
		pleaseWait(1000);
		assertTrue(isElementPresent(By.cssSelector("button.customButton.ng-binding")));
	    try {
	      assertEquals("Edit", driver.findElement(By.cssSelector("button.customButton.ng-binding")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }	
	    
	    // we got many errors but now it seems fine
		//for some reason, now the click works! ¯\(°_o)/¯
	driver.findElement(By.cssSelector("button.customButton.ng-binding")).click(); 
	//waitMedium();    // commented in case we need to increase the wait
	//waitSmall();
	pleaseWait(500);
	}
	
	
	//INPUT 23, saveEdit
	
	public void saveEdit(){
		
		//waitSmall();
		//waitMedium();
	    pleaseWait(500); 
		driver.findElement(By.cssSelector("button.customButton.ng-binding")).click();
		   //some wait for next page (singleNote)
		//waitSmall();
		pleaseWait(500);
		//waitMedium();  // original was medium but I suspect it's not good idea
		assertEquals("40 KEKS", driver.findElement(By.id("noteName")).getAttribute("value"));
		//waitVerySmall();
		pleaseWait(100);
	}
	
	////INPUT 15 clickSearch combined with INPUT 6 selectFolder
	
	public void clickSearchSelectFolder(){
		clickNotes();
		driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
		
	    driver.findElement(By.xpath("//input[@type='text']")).clear();
	    driver.findElement(By.xpath("//input[@type='text']")).click();
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("automatedFolderForNotes");
	    driver.findElement(By.cssSelector("button.form-control.customButton")).click();
		//waitMedium();
	   pleaseWait(1000);
		driver.findElement(By.cssSelector("h4.ng-binding")).click();
		//waitLarge();
		pleaseWait(2000);
		try {
	        assertEquals("automatedFolderForNotes", driver.findElement(By.id("folderTitle")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
		//waitSmall();
		pleaseWait(500);
	}
	//clickSearchSelectNote
	
	public void clickSearchSelectNote(){
		
		
		clickNotes();
		driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
		driver.findElement(By.xpath("//input[@type='text']")).clear();
	    driver.findElement(By.xpath("//input[@type='text']")).click();
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("KEK");
	    driver.findElement(By.cssSelector("button.form-control.customButton")).click();
	  //  waitMedium();
	    pleaseWait(1000);
	    driver.findElement(By.cssSelector("h4.ng-binding")).click();
	 //   waitSmall();
	   pleaseWait(500);
	    assertEquals("40 KEKS", driver.findElement(By.id("noteName")).getAttribute("value"));
	    //waitVerySmall();
	    pleaseWait(100);
	}
	
	//INPUT 13 deleteNote
	
	public void deleteNote(){
		
		//waitMedium();
		pleaseWait(1000);
		driver.findElement(By.id("removeNote")).click();
		 driver.findElement(By.cssSelector("#removeNote")).click();
		//waitSmall();
	   pleaseWait(500);
		driver.findElement(By.id("alertify-ok")).click();
	    //waitVerySmall();
	    pleaseWait(100);
	    driver.findElement(By.id("alertify-ok")).click();// <- I click again because for some reason 
	    //the button is still there and doesn't disappear with the first click.. don't know why
		//waitMedium();
		pleaseWait(1000);
		try {
	        assertEquals("automatedFolderForNotes", driver.findElement(By.id("folderTitle")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
		//waitSmall();
		pleaseWait(500);
	}
	//INPUT 11 clearNote
	public void clearNote(){
		//another difficult case.. we had to use a Large wait to click the popup button!
		driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
		// TRY TO CLICK THE OK BUTTON..
		//waitLarge();
		pleaseWait(2000);
		//CLICK NOW
	    driver.findElement(By.id("alertify-ok")).click();
	    
	    //waitMedium();
	    pleaseWait(1000);
	    try {
	        assertEquals("automatedFolderForNotes", driver.findElement(By.id("folderTitle")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	   // waitMedium();
	    pleaseWait(1000);
	}
	
	
	//INPUT 5 logout
	public void logout(){
		
		clickNotes();
		//waitSmall();
		//waitMedium();//original was Medium
		pleaseWait(500);
		driver.findElement(By.xpath("//button[@ng-click='logOut();']")).click(); 
		//waitSmall();
		pleaseWait(500);
		// VERIFY IF THE FOLLOWING ELEMENTS ARE PRESENT ON THE Page = Home
		// 1. the Login Button, 2. the Register button...
		try {
		      assertTrue(isElementPresent(By.id("loginBox")));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		//waitSmall();
		pleaseWait(500);  
		try {
		      assertTrue(isElementPresent(By.id("regBox")));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		  //  waitVerySmall();
		pleaseWait(100);
	}
	
	
	//INPUT 15
		public void clickSearch(){
			clickNotes();
			driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
		//	waitSmall();
			pleaseWait(500);
		}
		
		

	// GRRRR NEED NEW FOLDER TO STOP SPAM 
////COMBINED WITH REGISTER TO STOP FLOATING MESSAGES "YOU HAVE NO NEW FOLDERS"
	public void stopAlerts(){
		//waitSmall();
		pleaseWait(500);
		driver.findElement(By.cssSelector("button.customButton.ng-binding")).click();
		 //driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
		//waitMedium();
	    pleaseWait(1000);
		driver.findElement(By.id("alertify-text")).clear();
	    driver.findElement(By.id("alertify-text")).click();
	    
	    driver.findElement(By.id("alertify-text")).sendKeys("stopAlerts");
	   // waitSmall();
	    pleaseWait(500);
	    driver.findElement(By.id("alertify-ok")).click();
	   // waitSmall();
	    pleaseWait(500);
	}
	
	
	
	
	
	
	
	
// FROM SELENIUM IDE AUTO-GENERATED METHOD
	private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
}


//just to display a bug


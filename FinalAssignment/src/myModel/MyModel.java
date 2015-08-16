package myModel;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.AllRoundTester;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.LookaheadTester;
import nz.ac.waikato.modeljunit.RandomTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.Tester;
import nz.ac.waikato.modeljunit.VerboseListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;

public class MyModel implements FsmModel {

	public enum Page {
		Home, Login, Register, Folder, Notes, SingleNote, Edit, Search
	}

	private Page mPage = Page.Home;
	int numberOfFolder = 0;
	int numberOfChildFolder = 0;
	int noNewFoldersPlease = 0; // flag for not having more than 1 automated
								// folders
	int numberOfNotes = 0;
	int noNewNotes = 0;
	int isRegistered = 0;
	private boolean live = true;
	private MyAdapter mAdapter = new MyAdapter();

	// INPUT 1 clickLogin
	@Action
	public void clickLogin() {

		if (live) 
		mAdapter.clickLogin();
		mPage = Page.Login;
		
	}

	public boolean clickLoginGuard() {
		return mPage == Page.Home && isRegistered > 0;
	}

	// INPUT 3 login
	@Action
	public void login() {
		if (live) 
		mAdapter.login();
		mPage = Page.Folder;

	}

	public boolean loginGuard() {
		return mPage == Page.Login;

	}

	// INPUT 2 clickRegister
	@Action
	public void clickRegister() {

		if (live) 
		mAdapter.clickRegister();
		mPage = Page.Register;
		
	}

	public boolean clickRegisterGuard() {
		return mPage == Page.Home;
	}

	// INPUT 4 register
	@Action
	public void register() {
		if (live) 
		mAdapter.register();
		mPage = Page.Folder;
		isRegistered++;
		numberOfFolder = 0;
		numberOfNotes = 0;
		numberOfChildFolder = 0;
		noNewFoldersPlease = 0;
	}

	public boolean registerGuard() {
		return mPage == Page.Register;
	}

	// INPUT 7 newFolder
	@Action
	public void newFolder() {
		
		if (live) 
		mAdapter.newFolder();
		mPage = Page.Notes;
		numberOfFolder++;
		noNewFoldersPlease++;
		

	}

	public boolean newFolderGuard() {

		return mPage == Page.Folder && noNewFoldersPlease <= 0;
	}

	// INPUT 15 clickSearch combined with INPUT 16 searchFolder and INPUT 6
	// selectFolder
	// first goes to Search Page, searches, goes to Notes as it selects and
	// opens the folder

	@Action
	public void clickSearchSelectFolder() {
		if (live) 
		mAdapter.clickSearchSelectFolder();
		mPage = Page.Notes;
		

	}

	public boolean clickSearchSelectFolderGuard() {
		return mPage == Page.Folder && numberOfFolder > 0;
	}

	// INPUT 15 clickSearch (from other Notes Page)
	// combined with INPUT 17 searchNote

	@Action
	public void clickSearchSelectNote() {
		if (live) 
		mAdapter.clickSearchSelectNote();
		mPage = Page.SingleNote;
		// }

	}

	public boolean clickSearchSelectNoteGuard() {
		return mPage == Page.Notes && numberOfNotes > 0;
	}

	// INPUT 14 deleteFolder
	@Action
	public void deleteFolder() {
		if (live) 
		mAdapter.deleteFolder();
		mPage = Page.Folder;
		//numberOfFolder = 0;  //// we make it 0 for the sake of not having duplicate folders
		numberOfFolder--;
		numberOfNotes = 0;
		numberOfChildFolder = 0;
		noNewFoldersPlease = 0;
	}

	public boolean deleteFolderGuard() {
		return mPage == Page.Notes;
	}

	// INPUT 6 selectFolder
	@Action
	public void selectFolder() {
		if (live) 
		mAdapter.selectFolder();
		mPage = Page.Notes;
		// }

	}

	public boolean selectFolderGuard() {
		return mPage == Page.Folder && numberOfFolder > 0;

	}

	// INPUT 13 renameFolder
	@Action
	public void renameFolder() {
		if (live) 
		mAdapter.renameFolder();
		mPage = Page.Notes;

	}

	public boolean renameFolderGuard() {
		return mPage == Page.Notes;

	}

	// INPUT 24 newChildFolder

	@Action
	public void newChildFolder() {
		if (live) 
		mAdapter.newChildFolder();
		mPage = Page.Notes;
		numberOfChildFolder++;

	}

	public boolean newChildFolderGuard() {

		return mPage == Page.Notes;
	}

	@Action
	public void selectChildFolder() {
		if (live) 
		mAdapter.selectChildFolder();
		mPage = Page.Notes;
		// }
	}

	public boolean selectChildFolderGuard() {

		return mPage == Page.Notes && numberOfChildFolder > 0;
	}

	// INPUT 9 newNote and INPUT 10 saveNote SEQUENCE
	@Action
	public void newNoteSaveNote() {
		if (live) 
		mAdapter.newNoteSaveNote();
		mPage = Page.SingleNote;
		numberOfNotes++;
		
	}

	public boolean newNoteSaveNoteGuard() {
		return mPage == Page.Notes;
	}

	// INPUT 8 selectNote
	@Action
	public void selectNote() {
		if (live) 
		mAdapter.selectNote();
		mPage = Page.SingleNote;
		// }
	}

	public boolean selectNoteGuard() {
		return mPage == Page.Notes && numberOfNotes > 0;

	}

	// INPUT 21, clickEdit
	@Action
	public void clickEdit() {
		if (live) 
		mAdapter.clickEdit();
		mPage = Page.Edit;

	}

	public boolean clickEditGuard() {
		return mPage == Page.SingleNote;

	}

	// INPUT 23 saveEdit
	@Action
	public void saveEdit() {
		if (live) 
		mAdapter.saveEdit();
		mPage = Page.Notes;

	}

	public boolean saveEditGuard() {
		return mPage == Page.Edit;

	}

	// INPUT 13 deleteNote

	@Action
	public void deleteNote() {
		if (live) 
		mAdapter.deleteNote();
		mPage = Page.Notes;
		numberOfNotes--;
	}

	public boolean deleteNoteGuard() {
		return mPage == Page.Edit;

	}

	// INPUT 11 clearNote
	// v1 we had the clickEdit but nothing happens, process stacked at Folder

	@Action
	public void clearNote() {
		if (live) 
		mAdapter.clearNote();
		mPage = Page.Notes;

	}

	public boolean clearNoteGuard() {
		return mPage == Page.Edit;

	}

	// INPUT 5 logout
	@Action
	public void logout() {
		if (live) 
		mAdapter.logout();
		mPage = Page.Home;
		
	}

	public boolean logoutGuard() {
		// return mPage != Page.Home;
		return mPage != Page.Home & mPage != Page.Login
				& mPage != Page.Register;
		// return mPage != Page.Home || mPage != Page.Login || mPage !=
		// Page.Register;
		/*
		 * return mPage == Page.Folder || mPage == Page.Notes || mPage ==
		 * Page.Edit || mPage == Page.SingleNote || mPage == Page.Search;
		 */
	}

	// INPUT 20 clickNotes BRINGS YOU TO MAIN MENU NO MATTER WHERE YOU ARE
	@Action
	public void clickNotes() {
		if (live) 
		mAdapter.clickNotes();
		mPage = Page.Folder;
		/*
		 * if(mPage != Page.Home || mPage!= Page.Register || mPage!=
		 * Page.Login){ mPage = Page.Home; }
		 */
	}

	public boolean clickNotesGuard() {
		// return mPage != Page.Home;
		// return mPage != Page.Home & mPage != Page.Login & mPage !=
		// Page.Register;
		// return mPage != Page.Home || mPage != Page.Login || mPage !=
		// Page.Register;
		return mPage == Page.Folder || mPage == Page.Notes
				|| mPage == Page.Edit || mPage == Page.SingleNote
				|| mPage == Page.Search;
	}

	@Action
	public void clickSearch() {
		if (live) 
		mAdapter.clickSearch();
		mPage = Page.Search;

	}

	public boolean clickSearchGuard() {
		return mPage == Page.Folder || mPage == Page.Notes;
	}

	@Override
	public Object getState() {

		return mPage;

	}

	@Override
	public void reset(boolean arg0) {
		// System.out.println(mPage);
		/*
		 * mPage = Page.Home; if (arg0) mAdapter.reset();
		 */
		isRegistered=0;
		/*numberOfChildFolder=0;
		numberOfFolder=0;
		numberOfNotes=0;*/
		this.live = arg0;
		mPage = Page.Home;
		if (arg0)
			mAdapter.reset();

	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		MyModel model = new MyModel();
		//Tester tester = new LookaheadTester(model);
		//RandomTester tester = new RandomTester(model);
		//Tester tester = new AllRoundTester(model);
		AllRoundTester tester = new AllRoundTester(model);
		tester.setLoopTolerance(1000);
		 
		//Tester tester = new GreedyTester(model);
		//tester.addListener(new VerboseListener());
		tester.buildGraph();
		tester.setLoopTolerance(3);
		
		tester.addListener(new VerboseListener()); //original Place
		tester.addListener(new StopOnFailureListener());
		tester.addCoverageMetric(new TransitionCoverage());
		tester.addCoverageMetric(new StateCoverage() {
			public String getName() {
				return "Total state coverage: ";
			}
		});
		 tester.addCoverageMetric(new StateCoverage());
		tester.addCoverageMetric(new ActionCoverage());
		tester.addCoverageMetric(new TransitionPairCoverage());

		tester.generate(100);
		tester.printCoverage();
		long estimatedTime = System.currentTimeMillis() - startTime;
		double elapsedSeconds = estimatedTime / 1000.0;
		double minutes =elapsedSeconds/60;
		System.out.println("tester took "+elapsedSeconds+" seconds to finish or "+ minutes+ " minutes!");
		
	}

}

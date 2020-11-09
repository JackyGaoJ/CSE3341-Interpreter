package edu.c3341;


/*Singleton Tab class, to help print tab*/
public class Tab {
	private static int tabNum = 1;
	private static Tab instance = null;
	
	public static Tab Instance() {
		if (instance == null) {
			instance = new Tab(); 
		}
		return instance;
	}
	
	/*Return the current number of tab needed*/
	public int Number(){
		return tabNum;
	}
	
	/*Increase the number of tab by 1*/
	void IncreaseTab() {
		tabNum +=1;
	}
	/*Decrease the number of tab by 1*/
	void DecreaseTab() {
		tabNum -=1;
	}

}

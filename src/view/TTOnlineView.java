package view;

import java.util.ArrayList;
import java.util.List;

import model.*;


public class TTOnlineView {
	
	private List<String> outputBuffer= new ArrayList<String>();
	private TTModel model;
	
	public TTOnlineView(TTModel model) {
		this.model = model;
	}
	
	public List<String> getOutputBuffer(){
		return this.outputBuffer;
	}
	
	public void drawMain() {
		this.outputBuffer.clear();
		this.outputBuffer.add("| Doggo Trumps, online test_ver. 0.1 |");
	}
	
	public void drawAIMenu(){
		this.outputBuffer.clear();
		this.outputBuffer.add("Select AI players (Max 1-4)...\n");
	}

	public void selectStat(){
		this.outputBuffer.clear();
		this.outputBuffer.add("Please select a stat to play between 1 and 5...\n");
	}

	public void notValid(){
		this.outputBuffer.clear();
		this.outputBuffer.add("Please select a valid menu option...\n");
	}


	
}

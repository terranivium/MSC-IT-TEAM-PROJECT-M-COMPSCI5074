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
		this.outputBuffer.add("--------------------------------------");
		this.outputBuffer.add("| Doggo Trumps, online test_ver. 0.1 |");
		this.outputBuffer.add("--------------------------------------");
		this.outputBuffer.add("Select mode...--------------");
		this.outputBuffer.add("	1. New Player Game");
		//System.out.println("	2. AI vs AI Autoplay"); // Bot class
		//System.out.println("	3. View runtime stats"); // DB
		this.outputBuffer.add("--------------------------------------");
	}
	
	public void drawAIMenu(){
		this.outputBuffer.clear();
		this.outputBuffer.add("Select AI players (Max 1-4)...");
	}
	
}

package controller;
import java.io.FileNotFoundException;

import model.TTModel;
import view.TTCLIView;

public class TTController{
		private TTModel dm;
		//insert view classes here too i.e
		//private CardView cardView
		//private PlayerView playerView
	public TTController(TTModel model, TTCLIView view){
		dm = new TTModel();
		
		dm.startGame();
		
	}
	
	
}
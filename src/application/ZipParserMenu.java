package application;

import com.zip.parser.CheckForFilesLoop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ZipParserMenu extends Application {
	
	private static Stage savedStage;

	@Override
	    public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("ZipParserMenu.fxml"));
			primaryStage.setTitle("ZIP Parser");			
			primaryStage.setScene(new Scene(root));
		    primaryStage.show();
		    
		    setSavedStage(primaryStage);
	    }

	    static Stage getSavedStage() {
	    	return savedStage;
	    }

	    private static void setSavedStage(Stage newSavedStage) {
	    	savedStage = newSavedStage;
	    }

		public static void main(String[] args) throws InterruptedException {
	    	new CheckForFilesLoop();
	    	launch(args);
	    }
	
}

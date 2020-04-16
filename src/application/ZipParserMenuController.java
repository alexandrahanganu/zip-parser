package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.zip.parser.CheckForFilesLoop;
import com.zip.parser.DataFromPropertiesFile;
import com.zip.parser.ManualZIP;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ZipParserMenuController{
    
    @FXML private javafx.scene.control.Button closeButton;
    @FXML private javafx.scene.control.Button manualZIPButton;
    
    @FXML private javafx.scene.control.Label labelSourcePath;
    @FXML private javafx.scene.control.Label labelDestinationPath;

    @FXML TextArea logBox;

    
    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        CheckForFilesLoop.checkFilesFromFolder.shutdown();
    }
    
    @FXML
    private void initialize() {
    	labelSourcePath.getText();
    	labelSourcePath.setText(DataFromPropertiesFile.getParserSourcePathValue());
    	labelDestinationPath.setText(DataFromPropertiesFile.getParserDestinationPathValue());
    	
		logBox.appendText("abc");
		 
    }
    
    @FXML
    private void propertiesButtonAction() {
    	
    	try {
    		  		
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditProperties.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			
			Stage stage = new Stage();
			
			stage.setTitle("Edit properties");
			stage.setAlwaysOnTop(true);
			stage.centerOnScreen();
			
			stage.setScene(new Scene(root));
			stage.show();
			stage.getScene();
			
			stage.setOnHidden(new EventHandler<WindowEvent>() {
			    public void handle(WindowEvent we) {
			    	labelSourcePath.setText(DataFromPropertiesFile.getParserSourcePathValue());
			    	labelDestinationPath.setText(DataFromPropertiesFile.getParserDestinationPathValue());
			    }
			});
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    private void manualZIPButtonAction() {
		
    	manualZIPButton.setOnAction(
    			new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Select files");
						fileChooser.setInitialDirectory(new File("C:/"));
						
						System.out.println(DataFromPropertiesFile.getParserAllowedFormatValue());
						
						//ExtensionFilter extF = new ExtensionFilter("Allowed Format", DataFromPropertiesFile.getParserAllowedFormatValueAsList());
						//fileChooser.getExtensionFilters().addAll(extF);
						
						List<File> selectedFiles = fileChooser.showOpenMultipleDialog(ZipParserMenu.getSavedStage());
						
						System.out.println(selectedFiles);
						
						ArrayList<String> filesPaths = new ArrayList<>();
						
						selectedFiles.forEach(f -> filesPaths.add(f.getPath()));
						
						if(!selectedFiles.isEmpty()) {
							ManualZIP manualZIP = new ManualZIP(filesPaths);
							manualZIP.run();
						}
					}
    				
    			}
    	);
    }
    
    private void newLogLine(String string) {
		
		logBox.appendText(string);
	}
	
	private String getDateTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		return "[" + dateTime + "]";
	}
	
	public void newFilesFound(int i) {
		newLogLine(getDateTime() + "Found " + i + " files to parse..." + "\n");
	}
	
	public void allFilesPArsed(String fileName) {
		newLogLine(getDateTime() + "Files succefully parsed and zipped. File name: " + fileName);
	}
    
}

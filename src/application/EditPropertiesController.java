package application;

import java.io.IOException;

import com.zip.parser.DataFromPropertiesFile;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EditPropertiesController {
	
	@FXML private javafx.scene.control.Button cancelButton;
	@FXML private javafx.scene.control.Button saveButton;

	@FXML private javafx.scene.control.TextField sourcePathEdit;
	@FXML private javafx.scene.control.TextField destPathEdit;
	@FXML private javafx.scene.control.TextField allowedEdit;
	@FXML private javafx.scene.control.TextField nameTempEdit;
	
	
	@FXML
    private void cancelButtonAction(){
		
        Stage stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();
    }
	
	@FXML
    public void initialize() {
		
		sourcePathEdit.setText(DataFromPropertiesFile.getParserSourcePathValue());
		destPathEdit.setText(DataFromPropertiesFile.getParserDestinationPathValue());

    	allowedEdit.setText(DataFromPropertiesFile.getParserAllowedFormatValue());
    	nameTempEdit.setText(DataFromPropertiesFile.getParserZipFolderTemplateNameValue());
    	


	}
	
	@FXML
	private void saveButtonAction() throws IOException {
		DataFromPropertiesFile.setParserSourcePathValue(sourcePathEdit.getText());
		DataFromPropertiesFile.setParserDestinationPathValue(destPathEdit.getText());
		DataFromPropertiesFile.setParserAllowedFormatValue(allowedEdit.getText());
		DataFromPropertiesFile.setParserZipFolderTemplateNameValue(nameTempEdit.getText());
		DataFromPropertiesFile.updatePropertiesFile();
		
		
		Stage stage = (Stage) saveButton.getScene().getWindow();
		
		saveButton.setOnAction(event -> stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST)));
		stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));

        stage.close();
        
		
		
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxworkshop_06;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

/**
 *
 * @author mhrimaz
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label status;
    @FXML
    private TextField urlField;
    @FXML
    private TextField fileField;
    @FXML
    private ProgressIndicator progress;

    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    @FXML
    private void handleDownloadAction(ActionEvent event) {
        try {
            URL url = new URL(urlField.getText());
            String filename = fileField.getText();
            Downloader downloader = new Downloader(url, filename);
            progress.progressProperty().bind(downloader.progressProperty());
            status.textProperty().bind(downloader.messageProperty());
            executor.submit(downloader);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fileField.clear();
            urlField.clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

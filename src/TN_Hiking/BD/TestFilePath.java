package TN_Hiking.BD;

import TN_Hiking.Gestionnaires.GestionnaireParcours;
import TN_Hiking.View.WelcomeView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFilePath extends Application {

    private TextArea textArea;
    private Button browse;


    private FileChooser fileChooser;
    private File file;
    private Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Tutorial get selected Filepath into text Area");

        fileChooser = new FileChooser();
        //fileChooser.setInitialDirectory(new File("C:\\Users\\Romain\\Desktop\\codingWeek\\oui\\project-grp12\\.gitignore"));

        browse = new Button("Browse");
        browse.setFont(Font.font("SanSerif",15));
        browse.setOnAction(e-> {
            //single File Selection
            file = fileChooser.showOpenDialog(primaryStage);
            // permet d'ouvrir le fichier
            if (file != null) {
                System.out.println(file.getPath());
                //try { desktop.open(file);} catch (IOException e1){e1.printStackTrace(); }
            }
        });
        Path path = Paths.get("src\\TN_Hiking\\BD\\INTEL");
        //Path path = Paths.get(".gitignore");
        System.out.println(path.getFileName());
        System.out.println(path.toString());
        System.out.println(path.getNameCount()+"");
        System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));
        textArea = new TextArea();
        textArea.setFont(Font.font("SanSerif",12));
        textArea.setPromptText("Path of Selected File Or Files");
        textArea.setPrefSize(300,50);
        textArea.setEditable(false);

        BorderPane layout = new BorderPane();



        VBox vbox = new VBox();
        vbox.getChildren().add(textArea);
        vbox.getChildren().add(browse);
        layout.setCenter(vbox);

        Scene newscene = new Scene(layout,1200,700, Color.rgb(0,0,0,0));

        primaryStage.setScene(newscene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

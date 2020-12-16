package TN_Hiking.BD;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileHandling {


    public FileHandling(){ }

    public String getFilePath(Window window){
        // how can i get window mister borat ?
        // SIMPLE ! you look at your .FXML file
        // you look at your first element (anchor/pane/borderpane..)
        // you give it an id let's say "mainPane" for instance
        // you import it into ecouteur class
        // Stage stage = (Stage) mainPane.getScene().getWindow();
        // stage is your window ;) enjoy
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        return file.getPath().toString();
    }

    public String getDirectoryPath(Window window)
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(window);
        return file.getPath().toString();
    }

    public void fileCopieColle(String source,String dest)
    {
        File sourceFile = new File(source);
        File desitnationFile = new File(dest);
        try{
            Files.copy(sourceFile.toPath(),desitnationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch(IOException e){ e.printStackTrace();}

    }


    public void copydir(File src, File dest) throws IOException {
        if (src.isDirectory()) {

            // if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
                System.out.println("Directory copied from " + src + "  to "
                        + dest);
            }

            // list all the directory contents
            String files[] = src.list();

            for (String fileName : files) {
                // construct the src and dest file structure
                File srcFile = new File(src, fileName);
                File destFile = new File(dest, fileName);
                // recursive copy
                copydir(srcFile, destFile);
            }

        }
        else {
            // If file, then copy it
            fileCopy(src, dest);
        }
    }

    public void fileCopy(File src, File dest) throws FileNotFoundException, IOException {
        InputStream in = null;
        OutputStream out = null;

        try {
            // If file, then copy it
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            // Copy the file content in bytes
            while ((length = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, length);
            }
        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
            if (out != null)
            {
                out.close();
            }
        }
        System.out.println("File copied from " + src + " to " + dest);
    }


}

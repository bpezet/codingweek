package TN_Hiking.BD;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FilePathController {

    public static void main(String[] args){
        FileHandling fl;
        fl = new FileHandling();

        String sourcePath="C:\\Users\\Romain\\Desktop\\codingWeek\\imageDirectory"; // \\borat.jpg";
        File srcDir = new File(sourcePath);
        System.out.println(srcDir.getName());


        String destinationPath=".\\BDD";//+ srcDir.getName();

        System.out.println(destinationPath);
        File destDir = new File(destinationPath);

        try {
            fl.copydir(srcDir, destDir.toPath().toRealPath().toFile());
        } catch(IOException e) { e.printStackTrace();}

        //fl.fileCopieColle(srcDir.toPath().toString(),destDir.toPath().toString());
        //fl.fileCopy(srcDir,destDir.toPath().toFile());
        //fl.fileCopy(srcDir,destDir);
        /*
        try {
            fl.copydir(srcDir, destDir);
        } catch(IOException e){ e.printStackTrace();}
        */

        //fl.fileCopy();
    }
}

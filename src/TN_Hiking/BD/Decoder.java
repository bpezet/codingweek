package TN_Hiking.BD;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Decoder {

    public void Decoder(){}
    public void decodeAction(String pathName){

        // pour ouvrir et lire un fichier ligne par ligne
        File file = new File(pathName);

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            String line = "j'aime les bannaes\n";
            bufferedWriter.write(line);
            //bufferedWriter.newLine();
            next(bufferedWriter);
            bufferedWriter.write(line);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        try {
            assert bufferedWriter != null;
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void next(BufferedWriter bufferedWriter){
        try {
            bufferedWriter.write("j'aime pas le chocolat\n");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}

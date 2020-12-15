package TN_Hiking.BD;


import java.io.*;

public class TestDec {

    public static void main(String[] args){

        //Writter wr = new Writter();
        //wr.WR();

        System.out.println("lol");

        // pour ouvrir et lire un fichier ligne par ligne
        File file = new File("src\\TN_HIKING\\BD\\INTEL" );
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        try {
            assert bufferedReader != null;
            bufferedReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }



    }


}

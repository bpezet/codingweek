package TN_Hiking.BD;

public class FrameWorkTest {
    public static void main(String[] args) {

        String test = "Mon Premier Parcours:3:12:Strasbourg:lapin.jpeg";
        String[] splitter = test.split(":");
        System.out.println("sizeofsplitter:"+splitter.length+"");
        System.out.print("/");
        for(int i = 0;i<splitter.length;i++){
            System.out.print(splitter[i]+ "/");
        }
        System.out.print("/");


    }
}

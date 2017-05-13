/**
 * Created by vajira on 5/12/17.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.swing.*;

class VirusAnalyzer {
    int count = 0;
    int size = 0;
    int[] occur;
    ArrayList<HashMap> al=new ArrayList<HashMap>();
    ArrayList<virus> viruses = new ArrayList<virus>();

    void readPattern(String filename) throws Exception {
        try
        {
            FileReader in = new FileReader(filename);
            BufferedReader br = new BufferedReader(in);
            String line;
            String def;
            String detail;
            String[] detailList;
            String[] defs;
            int i = 0;
            HashMap hashMap;
            virus myVirus;
            while ((line = br.readLine()) != null) {
                //hashMap.put(line.substring(0, line.indexOf("/")), line.substring(line.indexOf("/") + 1, line.length()));

                def = line.substring(0,line.indexOf("?"));

                detail = line.substring(line.indexOf("?")+1,line.length());

                detailList = detail.split("&");
                System.out.println(detailList.length);
                hashMap = new HashMap();
                myVirus = new virus();
                defs = def.split(" ");
                for (int j=0;j<defs.length;j++){
                    hashMap.put(defs[j].substring(0,defs[j].indexOf("/")),defs[j].substring(defs[j].indexOf("/")+1,defs[j].length()));

                }

                myVirus.setName(detailList[0]);
                myVirus.setType(detailList[1]);
                myVirus.setCorporation(detailList[2]);

                viruses.add(myVirus);
                al.add(hashMap);

                //++i;
            }
            //size = i;
            br.close();
        }
        catch(Exception e)
        {
            //System.out.println("Hello"+e);
        }
    }

    void updateDefinitions(){

    }

    void genReport(virus v){
        System.out.println("Selected file is infected with a virus!");
        System.out.println("Name : "+v.getName());
        System.out.println("Type : "+v.getType());
        System.out.println("Created by : "+v.getCorporation());
    }

    void searchVirus(String file) throws Exception {
        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);
        String line;
        occur = new int[al.size()];
        while ((line = br.readLine()) != null) {
            count++;
            for (int l=0;l<al.size();l++) {
                Set keys = (al.get(l)).keySet();

                boolean containsKey = keys.contains(String.valueOf(count));
                if (containsKey) {
                    String virus = (String) al.get(l).get(String.valueOf(count));
                    if (line.indexOf(virus) > -1) {
                        occur[l]++;
                    }
                }
            }
        }
        br.close();
        //System.out.println(al.size());

        for (int i=0;i<al.size();i++){
            if (occur[i]==al.get(i).size()) {
                JOptionPane.showMessageDialog(null, "Error", "Virus Detected ", JOptionPane.ERROR_MESSAGE);
                genReport(viruses.get(i));
                return;
            }
        }

        /*if (size == occur) {
            JOptionPane.showMessageDialog(null, "Error", "Virus Detected ", JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Clean File", "No Virus Found ", JOptionPane.INFORMATION_MESSAGE);
        }*/
    }
    public static void main(String []nix) {
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
        }*/
        /*try {
            VirusAnalyzer fr = new VirusAnalyzer();
            fr.readPattern("definitions.txt");
            fr.searchVirus("virus.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
/*

        FileReader in = null;
        String l = null;
        try {
            in = new FileReader("virus.exe");
            BufferedReader br = new BufferedReader(in);
            while ((l = br.readLine()) != null){
                System.out.println(l);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/      VirusAnalyzer fr = new VirusAnalyzer();
        try {
            fr.readPattern("definitions.txt");
            fr.searchVirus("virus.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

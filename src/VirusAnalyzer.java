/**
 * Created by vajira on 5/12/17.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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

                def = line.substring(0,line.indexOf("?"));

                detail = line.substring(line.indexOf("?")+1,line.length());

                detailList = detail.split("&");

                hashMap = new HashMap();
                myVirus = new virus();
                defs = def.split(" ");
                for (int j=0;j<defs.length;j++){
                    hashMap.put(defs[j].substring(0,defs[j].indexOf("/")),defs[j].substring(defs[j].indexOf("/")+1,defs[j].length()));

                }

                myVirus.setName(detailList[0]);
                myVirus.setType(detailList[1]);
                myVirus.setCorporation(detailList[2]);
                myVirus.setEffectiveness(detailList[3]);

                viruses.add(myVirus);
                al.add(hashMap);


            }

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
        System.out.println("=====================Details about malware=====================");
        System.out.println("===============================================================");
        System.out.println("Name : "+v.getName());
        System.out.println("Type : "+v.getType());
        System.out.println("Created by : "+v.getCorporation());
        System.out.println("Effectiveness :"+v.getEffectiveness());
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


        for (int i=0;i<al.size();i++){
            if (occur[i]==al.get(i).size()) {
                //JOptionPane.showMessageDialog(null, "Error", "Virus Detected ", JOptionPane.ERROR_MESSAGE);
                genReport(viruses.get(i));
                return;
            }
        }

        System.out.println("File has not infected viruses!");

        /*if (size == occur) {
            JOptionPane.showMessageDialog(null, "Error", "Virus Detected ", JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "Clean File", "No Virus Found ", JOptionPane.INFORMATION_MESSAGE);
        }*/
    }
    public static void main(String []nix) {


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path or name to be scanned : ");
        String filename = scanner.next();
        VirusAnalyzer fr = new VirusAnalyzer();
        try {
            fr.readPattern("definitions.txt");
            fr.searchVirus(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

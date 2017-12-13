package dw.dataimport;

import dw.dataimport.sfia.SFIAImport;

import java.io.IOException;

/**
 * Created by callumbarnes on 13/12/2017.
 */
public class DataImportApp {


    public static void main(String[] args) {

        SFIAImport sfiaImport = new SFIAImport("jdbc:h2:./database/cv-lib-db","org.h2.Driver","","");

        String pathToFile = "/Users/callumbarnes/Documents/GitHub/CVLib/sfiaskills.6.3.en.1.csv";
        try {
            sfiaImport.importDataFromCSV(pathToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

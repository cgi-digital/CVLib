package dw.dataimport.sfia;

import dw.dataimport.models.Skill;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by callumbarnes on 13/12/2017.
 */
public class SFIAImport {

    private final String DBURL;
    private final String JDBC_DRIVER;

    private final String USER;
    private final String PASS;

    public SFIAImport(String DBURL, String JDBC_DRIVER, String USER, String PASS) {
        this.DBURL = DBURL;
        this.JDBC_DRIVER = JDBC_DRIVER;
        this.USER = USER;
        this.PASS = PASS;
    }

    public void importDataFromCSV(String fileLocation) throws IOException {

        Map<String, Skill> skillsToWriteToDB = new HashMap<>();
        Reader in = new FileReader(fileLocation);
        Iterable<CSVRecord> listOfData = new ArrayList<>();

        //listOfData = CSVFormat.EXCEL.parse(in);
        listOfData = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);



        for(CSVRecord record : listOfData)
        {
            Skill currentSkill = skillsToWriteToDB.get(record.get("code"));

            if(currentSkill == null)
            {
                skillsToWriteToDB.put(record.get("code"), new Skill(record.get("Skill"), Integer.parseInt(record.get("level")), Integer.parseInt(record.get("level"))));
            }
            else {

                int currentRecordLevel = Integer.parseInt(record.get("level"));

                if(currentRecordLevel < currentSkill.getMinLevel())
                    currentSkill.setMinLevel(currentRecordLevel);
                else if(currentRecordLevel > currentSkill.getMaxLevel())
                    currentSkill.setMaxLevel(currentRecordLevel);


            }


        }

        insertIntoDB(skillsToWriteToDB);


        System.out.println();


    }

    private void insertIntoDB(Map<String, Skill> skillMap){


        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);
            System.out.println("Connected database successfully...");

            for(Map.Entry<String, Skill> skill : skillMap.entrySet()) {
                try {
                    //STEP 4: Execute a query
                    System.out.println("Inserting record into the table...");
                    stmt = conn.createStatement();

                    String sql = "INSERT INTO SKILLS (skill, minlevel, maxlevel, type, sfiacode)" + "VALUES ('" +
                            skill.getValue().getSkillName() + "', " +
                            skill.getValue().getMinLevel() + ", "+
                            skill.getValue().getMaxLevel() + ", " +
                            "'ICT', '" +
                            skill.getKey() + "'" +
                            ")";

                    stmt.executeUpdate(sql);

                    System.out.println("Inserted record into the table...");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt!=null)
                    conn.close();
            } catch (SQLException se) {
            } // do nothing
            try {
                if (conn!=null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        }

    }

}

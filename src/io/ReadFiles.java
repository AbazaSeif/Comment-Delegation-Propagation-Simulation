package io;

import constants.Constants;
import user.TimeBasedInformation;
import user.UserInformations;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class ReadFiles {
    /*
    * Read data files line by line
    * This method calls Parseline class and collect useful data in data structures
    * */
    public ArrayList<UserInformations> getUserList() {
        System.out.println("Collect all users information for once! ");
        String mainFolderPath = Constants.getDataPath();
        File fbUsersFolder = new File(mainFolderPath);
        File[] listOfUsers = fbUsersFolder.listFiles(); // we get all users path in a File lists
        File file;
        File folder;
        File[] listOfFiles;

        ArrayList<UserInformations> usersList = new ArrayList<>();

        for (int i = 0; i < listOfUsers.length; i++) {
            UserInformations user = new UserInformations();

            user.setUserId(Long.parseLong(listOfUsers[i].getName()));
            System.out.println(user.getUserId());
            folder = new File(listOfUsers[i].toString());
            listOfFiles = folder.listFiles();
            ArrayList<TimeBasedInformation> timeBasedInformationArrayList = new ArrayList<>();

            //in every folder
            for (int j = 0; j < listOfFiles.length; j++) {
                TimeBasedInformation timeBasedInformation = new TimeBasedInformation();
                ParseLines parseLines = new ParseLines();
                ArrayList<String> lines = new ArrayList<>();

                lines.clear();

                //pick iterated file in current folder
                //read all lines
                //pass these list to parser
                file = new File(listOfFiles[j].toString());
                try {
                    Scanner scanner = new Scanner(file);

                    while (scanner.hasNext()) {
                        lines.add(scanner.nextLine());
                    }
                    //control start date and end date -- START
                    // we need these dates for check simulation interval ie: 15 days
                    String startDate = Constants.getSimulationStartDate();
                    String endDate = Constants.getSimulationEndDate();
                    SimpleDateFormat simpleStartFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                    Date parsedStartDate = null;
                    Date parsedEndDate = null;
                    try {
                        parsedStartDate = simpleStartFormat.parse(startDate);
                        parsedEndDate = simpleStartFormat.parse(endDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //control start date and end date -- END

                    if (lines.size() > 1) {
                        timeBasedInformation = parseLines.parseLines(lines);
                        timeBasedInformation.setFileName(listOfFiles[j].toString());
                        //add only between two
//                        if (parsedStartDate != null && parsedEndDate != null &&
//                                timeBasedInformation.getCurrentTimestamp().after(parsedStartDate) &&
//                                timeBasedInformation.getCurrentTimestamp().before(parsedEndDate)) {
                            timeBasedInformationArrayList.add(timeBasedInformation);
//                        }
                    }
                    scanner.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            user.setUserActivites(timeBasedInformationArrayList);
            usersList.add(user);
        }
        return usersList;
    }
}

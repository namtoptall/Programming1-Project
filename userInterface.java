package finalProject;


// import the necessary libraries
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


public class userInterface {
    public static void Menu(){
        // create a Menu class, we will call it in the Main file, some below lines are variables for the Menu
        int mainOption;
        ArrayList<Date> dateList = new ArrayList<>();
        ArrayList<ArrayList<Date>> dateGroup = new ArrayList<>();
        String metricOptionString = null;
        String resultOptionString = null;
        long vaccinatedCalculation;
        long otherCalculation;
        ArrayList<Long> vaccinatedGroup = new ArrayList<>();
        ArrayList<Long> otherGroup = new ArrayList<>();
        ArrayList<Long> result = new ArrayList<>();
        SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
        int numberOfGroup;
        /* create an interface table, user can choose what option that they want
        (However, it should be started at the beginning) */
        do {
            System.out.println("==============================");
            System.out.println("|            Main            |");
            System.out.println("==============================");
            System.out.println("|   1. Option [1]: Data      |");
            System.out.println("|   2. Option [2]: Summary   |");
            System.out.println("|   3. Option [3]: Display   |");
            System.out.println("|   4. Option [4]: exit      |");
            System.out.println("==============================");
            Scanner sc = new Scanner(System.in);  // create a new scanner
            System.out.print("Choose your option: ");
            mainOption = sc.nextInt();
            switch (mainOption) { // switch cases that contains case 1, 2, 3 ,4
                case 1:
                    String dataOption;
                    data userInputData = new data();
                    System.out.println("You have chosen option 1: Data"); // case 1
                    // do loop, traverse from case 1,2,3,4
                    do {
                        System.out.println("===============================================================================");
                        System.out.println("|                                    Data                                     |");
                        System.out.println("===============================================================================");
                        System.out.println("|   1. Data -> Option [a]: A pair of start date and end date (inclusive)      |");
                        System.out.println("|   2. Data -> Option [b]: A number of days or weeks from a particular date   |");
                        System.out.println("|   3. Data -> Option [c]: A number of days or weeks to a particular date     |");
                        System.out.println("|   4. Data -> Option [d]: Back to Main                                       |");
                        System.out.println("===============================================================================");
                        System.out.print("Choose your option: ");
                        dataOption = sc.next();
                        // one more switch statement implement what happen after user input their choice
                        switch (dataOption){
                            case "a":
                                System.out.println("Display start date and end date");
                                System.out.print("Input start date: ");
                                String startDay = sc.next();
                                System.out.print("Input end date: ");
                                String endDay = sc.next();
                                try{
                                    obj.parse(startDay);
                                    obj.parse(endDay);
                                    System.out.println("Valid datetime");
                                    dateList = new ArrayList<>();
                                    dateList =  userInputData.timeRangeStartEnd(startDay, endDay);
                                    System.out.println(dateList);
                                }catch (ParseException except){
                                    System.out.println("Error reading datetime format!");
                                    Menu();
                                }
                                break;
                            case "b":
                                System.out.println("Display number of days or weeks from a particular date");
                                System.out.print("Input start date: ");
                                String beginDay = sc.next();
                                try{
                                    obj.parse(beginDay);
                                    System.out.println("Valid datetime");
                                    System.out.print("Input the number of day(s) or week(s): ");
                                    long numberOfDay = sc.nextLong();
                                    System.out.print("Choose range type: (\"day\" or \"week\"): ");
                                    String dayOrWeek = sc.next();
                                    dateList = new ArrayList<>();
                                    dateList = userInputData.timeRangeFromStart(numberOfDay, dayOrWeek, beginDay);
                                    System.out.println(dateList);
                                }catch (ParseException except){
                                    System.out.println("Error reading datetime format!");
                                    Menu();
                                }
                                break;
                            case "c":
                                System.out.println("Display number of days or weeks to a particular date");
                                System.out.print("Input end date: ");
                                String lastDay = sc.next();
                                try{
                                    obj.parse(lastDay);
                                    System.out.println("Valid datetime");
                                    System.out.print("Input the number of day(s) or week(s): ");
                                    long numberOfDay = sc.nextLong();
                                    System.out.print("Choose range type: (\"day\" or \"week\"): ");
                                    String dayOrWeek = sc.next();
                                    dateList = new ArrayList<>();
                                    dateList = userInputData.timeRangeToEnd(numberOfDay, dayOrWeek, lastDay);
                                    System.out.println(dateList);
                                }catch (ParseException except){
                                    System.out.println("Error reading datetime format!");
                                    Menu();
                                }
                                break;
                            case "d":
                                dataOption = "d";
                            default:
                                break;
                        }
                    }while (dataOption!="d");
                    break;
                case 2: // like case 1, the code continued to case 2
                    System.out.println("You have chosen option 2: Summary");
                    summary userInputSummary = new summary();
                    String summaryOption;
                    /* one more time, we implement exactly same logic with case 1.
                    However, there are some differences of 2 parts. */
                    do {
                        System.out.println("=================================================");
                        System.out.println("|                    Summary                    |");
                        System.out.println("=================================================");
                        System.out.println("|   1. Summary -> Option [a]: Group data        |");
                        System.out.println("|   2. Summary -> Option [b]: Metric            |");
                        System.out.println("|   3. Summary -> Option [c]: Result            |");
                        System.out.println("|   4. Summary -> Option [d]: Back to Main      |");
                        System.out.println("=================================================");
                        System.out.print("Choose your option: ");
                        summaryOption = sc.next();
                        switch (summaryOption){
                            case "a": // group data option
                                System.out.println("You have chosen Summary -> Group data");
                                String groupOption;
                                do {
                                    System.out.println("===========================================================================");
                                    System.out.println("|                          Summary -> Group data                          |");
                                    System.out.println("===========================================================================");
                                    System.out.println("|   1. Group -> Option [a1]: No grouping (each day is a separate group)   |");
                                    System.out.println("|   2. Group -> Option [a2]: Number of groups                             |");
                                    System.out.println("|   3. Group -> Option [a3]: Back to Summary(2)                           |");
                                    System.out.println("===========================================================================");
                                    System.out.print("Choose your option: ");
                                    groupOption = sc.next();
                                    switch (groupOption){     // one more switch statement that divide group data into 2 part
                                        case "a1":
                                            System.out.println("No grouping");
                                            dateGroup = new ArrayList<ArrayList<Date>>();
                                            dateGroup = userInputSummary.groupData(dateList);
                                            for (ArrayList<Date> item:  dateGroup){
                                                System.out.println(item);
                                            }
                                            break;
                                        case "a2":
                                            System.out.print("Enter the number of group(s): ");
                                            numberOfGroup = sc.nextInt();
                                            System.out.println("The data will be distributed into "+numberOfGroup +" groups:");
                                            System.out.println("...");
                                            dateGroup = new ArrayList<ArrayList<Date>>();
                                            dateGroup = userInputSummary.groupData(dateList, numberOfGroup);
                                            for (ArrayList<Date> item:  dateGroup){
                                                System.out.println(item);
                                            }
                                            break;
                                        case "a3": // return to the summary
                                            groupOption = "a3";
                                            break;
                                        default:
                                            System.out.println("invalid option!");
                                            break;
                                    }
                                }while (groupOption!="a3");
                                break;
                            case "b": // metric option from summary
                                System.out.println("You have chosen Summary -> Metric");
                                String metricOption;
                                do {
                                    System.out.println("===============================================================");
                                    System.out.println("|                       Summary -> Metric                     |");
                                    System.out.println("===============================================================");
                                    System.out.println("|   1. Summary -> Metric -> Option [b1]: Positive cases       |");
                                    System.out.println("|   2. Summary -> Metric -> Option [b2]: Deaths               |");
                                    System.out.println("|   3. Summary -> Metric -> Option [b3]: People vaccinated    |");
                                    System.out.println("|   4. Summary -> Metric -> Option [b4]: Back to Summary(2)   |");
                                    System.out.println("===============================================================");
                                    System.out.print("Choose your option: ");
                                    metricOption = sc.next();
                                    switch (metricOption){     // 4 more options
                                        case "b1":
                                            System.out.println("Positive cases");
                                            metricOptionString = "new_cases";
                                            break;
                                        case "b2":
                                            System.out.println("Deaths");
                                            metricOptionString = "new_deaths";
                                            break;
                                        case "b3":
                                            System.out.println("People vaccinated");
                                            metricOptionString = "people_vaccinated";
                                            break;
                                        case "b4":
                                            metricOption = "b4";
                                            break;
                                        default:
                                            System.out.println("invalid option!");
                                            break;
                                    }
                                }while (metricOption!="b4");
                                break;
                            case "c":    // Result of the summary from data grouping and metrics
                                System.out.println("You have chosen Summary -> Result");
                                String resultOption;
                                do {
                                    System.out.println("===============================================================");
                                    System.out.println("|                       Summary -> Result                     |");
                                    System.out.println("===============================================================");
                                    System.out.println("|   1. Summary -> Result -> Option [c1]: New Total            |");
                                    System.out.println("|   2. Summary -> Result -> Option [c2]: Up to                |");
                                    System.out.println("|   3. Summary -> Result -> Option [c3]: Back to summary(2)   |");
                                    System.out.println("===============================================================");
                                    System.out.print("Choose your option: ");
                                    resultOption = sc.next();
                                    switch (resultOption){ //
                                        case "c1":
                                            // calculate New total cases based on WHOdata and input data
                                            System.out.println("New total");
                                            resultOptionString = "new_total";
                                            vaccinatedGroup = new ArrayList<Long>();
                                            otherGroup = new ArrayList<Long>();
                                            otherCalculation = 0;
                                            readData resultCalculated = new readData("WHOdata.csv");
                                            String dateFormat = "";
                                            result = new ArrayList<>();
                                            for (int i = 0; i < dateGroup.size(); i++) {
                                                vaccinatedCalculation = 0;
                                                int itemIndex = 0;
                                                for (Date time: dateGroup.get(i)){
                                                    dateFormat = String.valueOf(obj.format(time));
                                                    ArrayList<String> calculatedInString = resultCalculated.getData("date", dateFormat, metricOptionString);
                                                    for (int j = 0; j < calculatedInString.size(); j++) {
                                                        otherCalculation+=Long.parseLong(calculatedInString.get(j));
                                                        if ((i==0&&itemIndex==0)||(i==dateGroup.size()-1&&itemIndex==dateGroup.get(i).size()-1)){
                                                            vaccinatedCalculation+=Long.parseLong(calculatedInString.get(j));
                                                        }
                                                    }
                                                    itemIndex++;
                                                }
                                                vaccinatedGroup.add(vaccinatedCalculation);
                                            }
                                            if (metricOptionString.equals("people_vaccinated")){
                                                result.add(vaccinatedGroup.get(1)-vaccinatedGroup.get(0));
                                                System.out.println(result.get(0));
                                            }else if (metricOptionString.equals("new_cases")||metricOptionString.equals("new_deaths")){
                                                result.add(otherCalculation);
                                                System.out.println(result.get(0));
                                            }
                                            System.out.println(result);
                                            break;
                                        case "c2":
                                            // calculate up-to
                                            System.out.println("Up to");
                                            resultOptionString = "up_to";
                                            vaccinatedGroup = new ArrayList<Long>();
                                            resultCalculated = new readData("WHOdata.csv");
                                            result = new ArrayList<>();
                                            for (int i = 0; i < dateGroup.size(); i++) {
                                                vaccinatedCalculation = 0;
                                                otherCalculation = 0;
                                                int itemIndex = 0;
                                                for (Date time: dateGroup.get(i)){
                                                    dateFormat = String.valueOf(obj.format(time));
                                                    ArrayList<String> calculatedInString = resultCalculated.getData("date", dateFormat, metricOptionString);
                                                    for (int j = 0; j < calculatedInString.size(); j++) {
                                                        otherCalculation+=Long.parseLong(calculatedInString.get(j));
                                                        if (itemIndex==0||itemIndex==dateGroup.get(i).size()-1){
                                                            vaccinatedCalculation+=Long.parseLong(calculatedInString.get(j));
                                                        }
                                                    }
                                                    itemIndex++;
                                                }
                                                vaccinatedGroup.add(vaccinatedCalculation);
                                                otherGroup.add(otherCalculation);
                                            }
                                            // calculate number of vaccinated people based on WHOdata and user input data
                                            if (metricOptionString.equals("people_vaccinated")){
                                                ArrayList<Long> vaccinatedResultStorage = new ArrayList<>();
                                                for (int i = 1; i < vaccinatedGroup.size(); i+=2) {
                                                    long vaccinatedResult = vaccinatedGroup.get(i)-vaccinatedGroup.get(i-1);
                                                    vaccinatedResultStorage.add(vaccinatedResult);
                                                }
                                                result = vaccinatedResultStorage;
                                                for (int i = 0; i < result.size(); i++) {
                                                    System.out.println(result.get(i)+" people vaccinated ---in--> "+dateGroup.get(i));
                                                }
                                            }else if (metricOptionString.equals("new_cases")||metricOptionString.equals("new_deaths")){
                                                result = otherGroup;
                                                for (int i = 0; i < result.size(); i++) {
                                                    System.out.println("total "+result.get(i)+" "+metricOptionString+ " --in--> "+dateGroup.get(i));
                                                }
                                            }
                                            break;
                                        case "c3":
                                            resultOption = "c3";
                                            break;
                                        default:
                                            System.out.println("invalid option!");
                                            break;
                                    }
                                }while (resultOption!="c3");
                                break;
                            case "d":
                                summaryOption = "d";
                            default:
                                System.out.println("invalid option!");
                                break;
                        }
                    }while (summaryOption!="d");
                    break;  // back to the main table

                /*  Display case that show the result after user input correctly in 2 previous part*/
                case 3:
                    String displayOption;
                    System.out.println("You have chosen option 4: Display");
                    do {
                        System.out.println("=====================================================");
                        System.out.println("|                      Display                      |");
                        System.out.println("=====================================================");
                        System.out.println("|   1. Display -> Option [a]: Tabular display       |");
                        System.out.println("|   2. Display -> Option [b]: Chart display         |");
                        System.out.println("|   3. Display -> Option [c]: Back to Main          |");
                        System.out.println("=====================================================");
                        System.out.print("Choose your option: ");
                        displayOption = sc.next();
                        switch (displayOption){
                            // tabular table
                            case "a":
                                System.out.println("Display table Range-Value");
                                display tableDisplay = new display();
                                // calculate the size of the table to show
                                long[] totalCalculationReformat = new long[result.size()];
                                for (int i = 0; i < totalCalculationReformat.length; i++) {
                                    totalCalculationReformat[i] = result.get(i);
                                }
                                System.out.println(Arrays.toString(totalCalculationReformat));
                                tableDisplay.tableDisplay(totalCalculationReformat,dateGroup,resultOptionString);
                                System.out.println();
                                break;
                            case "b": // indexes chart, scaling the table
                                System.out.println("Display chart (24 rows x 80 columns)");
                                display chartDisplay = new display();
                                totalCalculationReformat = new long[result.size()];
                                for (int i = 0; i < totalCalculationReformat.length; i++) {
                                    totalCalculationReformat[i] = result.get(i);
                                }
                                System.out.println(Arrays.toString(totalCalculationReformat));
                                chartDisplay.chartDisplay(totalCalculationReformat);
                                System.out.println();
                                System.out.println();
                                break;
                            case "c":
                                displayOption = "c";
                                break;
                            default:
                                break;
                        }
                    }while (displayOption!="c");
                    break;
                case 4:
                    System.out.println("Exiting ...");  // exit 
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }while(mainOption!=4);
    }



}


package finalProject;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class data {
    public ArrayList<Date> timeRangeStartEnd(String start_day, String end_day){
        SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yy");
        ArrayList<Date> result = new ArrayList<Date>();
        try{
            Date start = obj.parse(start_day);
            Date end = obj.parse(end_day);
            Calendar startCalendar = new GregorianCalendar();
            startCalendar.setTime(start);
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(end);
            while (startCalendar.before(endCalendar)){
                Date dateBetween = startCalendar.getTime();
                result.add(dateBetween);
                startCalendar.add(Calendar.DATE,1);
            }
            result.add(end);
            return result;
        }catch(ParseException except){
            System.out.println("Error reading datetime format!");
            except.printStackTrace();
            return result;
        }
    }
    public ArrayList<Date> timeRangeFromStart(long day, String weekOrDay, String start_day){
        SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yy");
        ArrayList<Date> result = new ArrayList<Date>();
        try{
            Date startDate = obj.parse(start_day);
            long end = -1;
            if (weekOrDay.equals("week")){
                end = startDate.getTime()+ day*7*24*60*60*1000;
            }else if (weekOrDay.equals("day")){
                end = startDate.getTime()+ day*24*60*60*1000;
            }
            Date endDate = new Date(end);
            Calendar startCalendar = new GregorianCalendar();
            startCalendar.setTime(startDate);
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(endDate);
            while (startCalendar.before(endCalendar)){
                Date dateBetween = startCalendar.getTime();
                result.add(dateBetween);
                startCalendar.add(Calendar.DATE,1);
            }
            result.add(endDate);
            return result;
        }catch(ParseException except){
            System.out.println("Error reading datetime format!");
            except.printStackTrace();
            return result;
        }
    }
    public ArrayList<Date> timeRangeToEnd(long day, String weekOrDay, String end_day){
        SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yy");
        ArrayList<Date> result = new ArrayList<Date>();
        try{
            Date endDate = obj.parse(end_day);
            long start = -1;
            if (weekOrDay.equals("week")){
                start = endDate.getTime()- day*7L*24L*60L*60L*1000L;
            }else if (weekOrDay.equals("day")){
                start = endDate.getTime()- day*24L*60L*60L*1000L;
            }
            Date startDate = new Date(start);
            Calendar startCalendar = new GregorianCalendar();
            startCalendar.setTime(startDate);
            Calendar endCalendar = new GregorianCalendar();
            endCalendar.setTime(endDate);
            while (startCalendar.before(endCalendar)){
                Date dateBetween = startCalendar.getTime();
                result.add(dateBetween);
                startCalendar.add(Calendar.DATE,1);
            }
            result.add(endDate);
            return result;
        }catch(ParseException except){
            System.out.println("Error reading datetime format!");
            except.printStackTrace();
            return result;
        }
    }

    public static void main(String[] args) {
        readData sample = new readData("WHOdata.csv");
        ArrayList<String> test =sample.getData("date","1/1/2021","new_deaths");
        long total = 0;
        for (int i = 0; i < test.size(); i++) {
            total+=Long.parseLong(test.get(i));
        }
        System.out.println(total);
    }
}
class readData extends data{
    File dataPath;
    public readData(String source){
        dataPath = new File(source);
    }
    public ArrayList<String> getData(String filter, String filterValue, String target){
        DateFormat obj = new SimpleDateFormat("MM/dd/yy");
        ArrayList<String> result = new ArrayList<String>();
        try {
            Scanner fileReader = new Scanner(dataPath);
            ArrayList<String> allRow = new ArrayList<String>();
            int countRow = 0;
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                allRow.add(data);
                countRow++;
            }
            String[][] allRowReformat = new String[countRow][8];
            int rowCount = 0;
            for (String row: allRow){
                String[] rowSplit = row.split(",",-1);
                int itemCount = 0;
                for (String item: rowSplit){
                    allRowReformat[rowCount][itemCount] = item;
                    itemCount++;
                }
                rowCount++;
            }
            String filterValueReformat;
            ArrayList<Character> filterValueFormat = new ArrayList<Character>();
            for (int i = 0;i<filterValue.length();i++){
                if (filterValue.charAt(i)=='0'){
                    if (i==0||i==filterValue.length()-7){
                        continue;
                    }else {
                        filterValueFormat.add(filterValue.charAt(i));
                    }
                }else{
                    filterValueFormat.add(filterValue.charAt(i));
                }
            }
            char[] filterCharArray = new char[filterValueFormat.size()];
            for (int i = 0; i < filterValueFormat.size(); i++) {
                filterCharArray[i] = filterValueFormat.get(i);
            }
            filterValueReformat = String.valueOf(filterCharArray);
            for (String[] row: allRowReformat) {
                int filterColumnIndex = getColumnIndex(filter);
                int targetColumnIndex = getColumnIndex(target);
                if (row[filterColumnIndex].equals(filterValueReformat)){
                    if (row[targetColumnIndex]==""){
                        result.add("0");
                    }else{
                        result.add(row[targetColumnIndex]);
                    }
                }
            }
            fileReader.close();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return result;
        }

    }
    public String getRow(int rowIndex){
        try {
            Scanner fileReader = new Scanner(dataPath);
            ArrayList<String> row = new ArrayList<String>();
            int i = 0;
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                data = String.join("", data.split("\""));
                row.add(data);
                if (i==rowIndex){
                    return row.get(rowIndex);
                }
                i++;
            }
            fileReader.close();
            return "error";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "error";
        }
    }
    public static int getColumnIndex(String columnName){
        switch (columnName){
            case "iso_code":
                return 0;
            case "continent":
                return 1;
            case "location":
                return 2;
            case "date":
                return 3;
            case "new_cases":
                return 4;
            case "new_deaths":
                return 5;
            case "people_vaccinated":
                return 6;
            case "population":
                return 7;
            default:
                return -1;
        }
    }
    public String getColumn(String columnName){
        try {
            Scanner fileReader = new Scanner(dataPath);
            ArrayList<String> column = new ArrayList<String>();
            int i = 0;
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();

                data = String.join("", data.split("\""));
                String[] columnData = data.split(",");
                column.add(columnData[getColumnIndex(columnName)]);
                System.out.println(column.get(i));
                i++;
            }
            fileReader.close();
            return "error";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "error";
        }
    }

}

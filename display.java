package finalProject;
import java.text.SimpleDateFormat;
import java.util.*;

public class display {
    //Display chart with the chosen metrics by various variables, and arrays.
    public void chartDisplay(long[] data){
        long[] sortedData = bubbleSort(data, "value", "descending");
        long[] sortedIndex = {3,2,4,5,1,6};
        long powerCounter = 1;
        long groupNumber = sortedData.length;
        while (true){
            //Loop until the powerCounter equal the data that we sorted.
            if (Math.pow(10,powerCounter)>=sortedData[0]){
                break;
            }else {
                powerCounter++;
            }
        }
        long groupIndex = 0;
        long labelSpaceCount = 0;
        long labelLength = 6;
        long totalLabelSpace = labelSpaceCount*groupNumber+labelLength*groupNumber;
        //Consider the space of label.
        while (totalLabelSpace<=80){
            labelSpaceCount++;
            totalLabelSpace = labelSpaceCount*groupNumber+labelLength*groupNumber;
        }
        labelSpaceCount--;
        // Start drawing chart.
        for (int i = 0; i < 23; i++) {
            System.out.print("|");
            long tab_count=0;
            ArrayList<Long> indexInARow = new ArrayList<Long>();
            // Use loop to scale the chart.
            for (int j = 0; j < sortedData.length; j++) {
                int rowScale = (int) Math.ceil((23 * sortedData[j]) / (sortedData[0]+Math.pow(10, powerCounter-1)));
                //Scale the row of the chart.
                if (rowScale==(23-i)){
                    indexInARow.add(bubbleSort(data, "index", "descending")[j]);
                }
            }
            //Create array to take the number of groups.
            long[] indexSort = new long[indexInARow.size()];
            for (int k = 0; k < indexInARow.size(); k++) {
                indexSort[k] = indexInARow.get(k);
            }
            indexSort = bubbleSort(indexSort, "value", "ascending");
            for (int k = 0; k < (labelSpaceCount+labelLength/2-1); k++) {
                System.out.print(" ");
            }
            int starInARow = 0;
            // Use loop to place the star which demonstrate the value for each group.
            for (int p = 0; p < indexSort.length; p++) {
                while (tab_count<(indexSort[p]-1)*(labelSpaceCount+labelLength)-starInARow){
                    System.out.print(" ");
                    tab_count++;
                }
                System.out.print("*");
                starInARow++;
            }
            System.out.println();
        }
        System.out.print("|");
        //Draw horizontal axis.
        for (int q = 0; q < 79; q++) {
            System.out.print("_");
        }
        System.out.println();
        //Write down the group number.
        for (int i = 1; i <= groupNumber; i+=1) {
            for (int j = 0; j < labelSpaceCount; j++) {
                System.out.print(" ");
            }
            System.out.print("group"+i);
        }
    }
    //Display table with the given metrics by various variables and arrays.
    public void tableDisplay(long[] data,ArrayList<ArrayList<Date>> label, String resultType){
        ArrayList<String> labelConversion = new ArrayList<>();
        //Create a date format.
        SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yy");
        //Display chart that base on the result type match any of these conditions.
        if (resultType.equals("up_to")){
            //Display labels.
            for (int i = 0; i < label.size(); i++) {
                //Initialize label format.
                String labelFormat = obj.format(label.get(i).get(0))+" - "+obj.format(label.get(i).get(label.get(i).size()-1));
                labelConversion.add(labelFormat);
            }
            System.out.println();
            long[] starCount = new long[labelConversion.size()];
            for (long stars : starCount){
                stars = 0;
            }
            //Draw table and display variable that depend on the result.
            for (int i = 0; i < labelConversion.size(); i++) {
                String line = "";
                line+="*  ";
                line+=labelConversion.get(i)+"  |  "+String.valueOf(data[i]);
                line+="  *";
                starCount[i]+=line.length();
            }
            long totalStar = bubbleSort(starCount, "value", "descending")[0];
            //Loop to add star until reach the result value.
            for (int i = 0;i<totalStar;i++){
                System.out.print("*");
            }
            System.out.println();
            //Draw table and display variables.
            for (int i = 0; i < labelConversion.size(); i++) {
                String line = "";
                System.out.print("*  ");
                line+="*\t";
                System.out.print(labelConversion.get(i)+"  |  "+String.valueOf(data[i]));
                line+=labelConversion.get(i)+"  |  "+String.valueOf(data[i]);
                line+="\t";
                if (line.length() < totalStar-1){
                    for (int j = 0;j<(totalStar-1-line.length());j++){
                        System.out.print(" ");
                    }
                }
                System.out.println("*");
            }
            //Loop to add star until reach the result value.
            for (int i = 0;i<totalStar;i++){
                System.out.print("*");
            }
            System.out.println();
        }
        //Similar steps to draw table and display variables with another condition.
        else if (resultType.equals("new_total")){
            String labelFormat = obj.format(label.get(0).get(0))+" - "+obj.format(label.get(label.size()-1).get(label.get(label.size()-1).size()-1));
            labelConversion.add(labelFormat);
            System.out.println();
            int totalStar = 0;
            for (int i = 0; i < labelConversion.size(); i++) {
                String line = "";
                line+="*  ";
                line+=labelConversion.get(i)+"  |  "+String.valueOf(data[i]);
                line+="  *";
                totalStar+=line.length();
            }
            for (int i = 0;i<totalStar;i++){
                System.out.print("*");
            }
            System.out.println();
            String currentStar = "";
            System.out.print("*  ");
            currentStar+= "*  ";
            System.out.print(labelFormat+"  |  "+data[0]);
            currentStar+=labelFormat+"  |  "+String.valueOf(data[0]);
            System.out.print("  ");
            currentStar+="  ";
            if (currentStar.length() <totalStar-1){
                for (int j = 0;j<(totalStar-1-currentStar.length());j++){
                    System.out.print(" ");
                }
            }
            System.out.println("*");
            for (int i = 0;i<totalStar;i++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
    static long[] bubbleSort(long[] originalArray, String returnType, String sortOrder) {
        long n = originalArray.length;
        long[] arrayIndex = new long[originalArray.length];
        for (int i = 0; i < arrayIndex.length; i++) {
            arrayIndex[i] = i+1;
        }
        long[] sortedArray = originalArray.clone();
        long tempValue = 0;
        long tempIndex = 0;
        if (sortOrder.equals("descending")){
            for(int i=0; i < n; i++){
                for(int j=1; j < (n-i); j++){
                    if(sortedArray[j-1] < sortedArray[j]){
                        //Swap elements and index.
                        tempValue = sortedArray[j-1];
                        tempIndex = arrayIndex[j-1];
                        sortedArray[j-1] = sortedArray[j];
                        arrayIndex[j-1] = arrayIndex[j];
                        sortedArray[j] = tempValue;
                        arrayIndex[j] = tempIndex;
                    }
                }
            }
        }else if (sortOrder.equals("ascending")){
            for(int i=0; i < n; i++){
                for(int j=1; j < (n-i); j++){
                    if(sortedArray[j-1] > sortedArray[j]){
                        //Swap elements and index.
                        tempValue = sortedArray[j-1];
                        tempIndex = arrayIndex[j-1];
                        sortedArray[j-1] = sortedArray[j];
                        arrayIndex[j-1] = arrayIndex[j];
                        sortedArray[j] = tempValue;
                        arrayIndex[j] = tempIndex;
                    }
                }
            }
        }
        if (returnType.equals("value")){
            return sortedArray;
        }else if (returnType.equals("index")){
            return arrayIndex;
        }else{
            long[] invalidResult = {0,0,0,0,0,0};
            return invalidResult;
        }
    }
}

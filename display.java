package finalProject;
import java.text.SimpleDateFormat;
import java.util.*;

public class display {
    public void chartDisplay(long[] data){
        long[] sortedData = bubbleSort(data, "value", "descending");
        long[] sortedIndex = {3,2,4,5,1,6};
        long powerCounter = 1;
        long groupNumber = sortedData.length;
        while (true){
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
        while (totalLabelSpace<=80){
            labelSpaceCount++;
            totalLabelSpace = labelSpaceCount*groupNumber+labelLength*groupNumber;
        }
        labelSpaceCount--;
        for (int i = 0; i < 23; i++) {
            System.out.print("|");
            long tab_count=0;
            ArrayList<Long> indexInARow = new ArrayList<Long>();
            for (int j = 0; j < sortedData.length; j++) {
                int rowScale = (int) Math.ceil((23 * sortedData[j]) / (sortedData[0]+Math.pow(10, powerCounter-1)));
                if (rowScale==(23-i)){
                    indexInARow.add(bubbleSort(data, "index", "descending")[j]);
                }
            }
            long[] indexSort = new long[indexInARow.size()];
            for (int k = 0; k < indexInARow.size(); k++) {
                indexSort[k] = indexInARow.get(k);
            }
            indexSort = bubbleSort(indexSort, "value", "ascending");
            for (int k = 0; k < (labelSpaceCount+labelLength/2-1); k++) {
                System.out.print(" ");
            }
            int starInARow = 0;
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
        for (int q = 0; q < 79; q++) {
            System.out.print("_");
        }
        System.out.println();
        for (int i = 1; i <= groupNumber; i+=1) {
            for (int j = 0; j < labelSpaceCount; j++) {
                System.out.print(" ");
            }
            System.out.print("group"+i);
        }
    }
    public void tableDisplay(long[] data,ArrayList<ArrayList<Date>> label, String resultType){
        ArrayList<String> labelConversion = new ArrayList<>();
        SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yy");
        if (resultType.equals("up_to")){
            for (int i = 0; i < label.size(); i++) {
                String labelFormat = obj.format(label.get(i).get(0))+" - "+obj.format(label.get(i).get(label.get(i).size()-1));
                labelConversion.add(labelFormat);
            }
            System.out.println();
            long[] starCount = new long[labelConversion.size()];
            for (long stars : starCount){
                stars = 0;
            }
            for (int i = 0; i < labelConversion.size(); i++) {
                String line = "";
                line+="*  ";
                line+=labelConversion.get(i)+"  |  "+String.valueOf(data[i]);
                line+="  *";
                starCount[i]+=line.length();
            }
            long totalStar = bubbleSort(starCount, "value", "descending")[0];
            for (int i = 0;i<totalStar;i++){
                System.out.print("*");
            }
            System.out.println();
            for (int i = 0; i < labelConversion.size(); i++) {
                String line = "";
                System.out.print("*  ");
                line+="*\t";
                System.out.print(labelConversion.get(i)+"  |  "+String.valueOf(data[i]));
                line+=labelConversion.get(i)+"  |  "+String.valueOf(data[i]);
                line+="\t";
                if (line.length() <totalStar-1){
                    for (int j = 0;j<(totalStar-1-line.length());j++){
                        System.out.print(" ");
                    }
                }
                System.out.println("*");
            }
            for (int i = 0;i<totalStar;i++){
                System.out.print("*");
            }
            System.out.println();
        }else if (resultType.equals("new_total")){
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
                        //swap elements and index
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
                        //swap elements and index
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

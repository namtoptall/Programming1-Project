package finalProject;

import java.util.ArrayList;
import java.util.Date;

public class summary {

    public summary(){

    }
    public ArrayList<ArrayList<Date>> groupData(ArrayList<Date> data){
        ArrayList<ArrayList<Date>> dataTransform = new ArrayList<ArrayList<Date>>();
        for (int i = 0; i < data.size(); i++) {
            ArrayList<Date> groupStorage = new ArrayList<Date>();
            groupStorage.add(data.get(i));
            dataTransform.add(groupStorage);
        }
        return dataTransform;
    }
    public ArrayList<ArrayList<Date>> groupData(ArrayList<Date> data, int numberOfGroup){
        ArrayList<ArrayList<Date>> dataTransform = new ArrayList<ArrayList<Date>>();
        if (numberOfGroup>data.size()){
            return dataTransform;
        }else{
            int[] groupSize = new int[numberOfGroup];
            int dataSize = data.size();
            int i = 0;
            while (dataSize!=0){
                groupSize[i]+=1;
                dataSize-=1;
                i++;
                if (i==numberOfGroup){
                    i = 0;
                }
            }
            for (int j = 0; j < groupSize.length; j++) {
                if (j-1>=0){
                    groupSize[j] += groupSize[j-1];
                }
            }
            int startIndex = 0;
            for (int j = 0; j < groupSize.length; j++) {
                ArrayList<Date> groupStorage = new ArrayList<Date>();
                for (int k = startIndex; k < groupSize[j]; k++) {
                    groupStorage.add(data.get(k));
                }
                dataTransform.add(groupStorage);
                if (j+1<=groupSize.length-1){
                    startIndex = groupSize[j];
                }
            }
        }
        return dataTransform;
    }

}


import joinery.DataFrame;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.util.List;

public class main
{
    public static void main(String[] args) throws IOException
    {
        Table titanic_data;
        titanic_data= Table.read().csv("titanic.csv");


        TitanicData.viewInfo(titanic_data);
        TitanicData.groupStatistics(titanic_data);
        DataFrame<Object> df= DataFrame.readCsv("titanic.csv");
        TitanicData.viewInfoJoiner(df);
        TitanicData.jgroupSatistics(df);
        // visualize data by histograms and pie charts
        TitanicDao obj=new TitanicDao();
        List<TitanicPassenger> allpassengers= obj.readFromJson();
        obj.readData(allpassengers,false);
        obj.graphPassengerAges(allpassengers);
        obj.graphPassengerClass(allpassengers);
        obj.graphPassengerSurvived(allpassengers);
        obj.graphPassengerSurvivedGender(allpassengers);
    }
}


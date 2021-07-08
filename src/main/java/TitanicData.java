import joinery.DataFrame;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.util.List;

public class TitanicData
{
    public TitanicData()
    {
    }
    public static void viewInfo(Table titanic_data)
    {
        System.out.println(titanic_data.columns());
        System.out.println("Structure of data");
        System.out.println(titanic_data.structure());
        System.out.println("shape of data");
        System.out.println(titanic_data.shape());
        System.out.println("first 4 rows");
        System.out.println(titanic_data.first(4));
        System.out.println("last four rowss");
        System.out.println(titanic_data.last(4));
        System.out.println("random 3 rows");
        List<String> columnNames= titanic_data.columnNames();
        System.out.println("column Names");
        System.out.println(columnNames);
        // System.out.println(titanic_data.print(3));  //to print a number of rows randomly
        //  titanic_data.printAll();   print all rows
        //titanic_data.sampleN(10); //selects at random 10 rows
    }
    public static void groupStatistics(Table titanic_data)
    {
        IntColumn select=titanic_data.intColumn("survived");
        System.out.println("count of survived in each category");
        System.out.println(select.countByCategory());
        System.out.println("-----------------------------------");

        StringColumn sex=titanic_data.stringColumn("sex");
        System.out.println("count of survived in each sex");
        System.out.println(sex.countByCategory());
        System.out.println("-----------------------------------");

        IntColumn pclass=titanic_data.intColumn("pclass");
        System.out.println("count of survived in each class");
        System.out.println(pclass.countByCategory());
        System.out.println("-----------------------------------");
        //   System.out.println(titanic_data.summary());
    }
    public static void viewInfoJoiner(DataFrame<Object> df)
    {
        System.out.println("first four rows");
        System.out.println(df.head(4));
        System.out.println("last three rows");
        System.out.println(df.tail(3));
        System.out.println("names columns that ernot numeric");
        System.out.println(df.nonnumeric().columns());
        System.out.println("number of samples");
        System.out.println(df.length());
        System.out.println();
        System.out.println("Number of columns");
        System.out.println(df.size());
        System.out.println();
        // System.out.println(df.types());
    }
    public static void jgroupSatistics(DataFrame<Object> df)
    {
        DataFrame<Object> survclass=df.retain("pclass","survived").groupBy("pclass").sum().resetIndex();
        System.out.println("Number of Survived in each class");
        System.out.println(survclass);
        DataFrame<Object> survsex=df.retain("sex","survived").groupBy("sex").sum().resetIndex();
        System.out.println("Number of Survived in each sex");
        System.out.println(survsex);

        DataFrame<Object> dfage=df.retain("age").describe();
        System.out.println("description of age");
        System.out.println(dfage);
        DataFrame<Object> dffare=df.retain("fare").describe();
        System.out.println("description of fare");
        System.out.println(dffare);
        /*DataFrame<Object> survhome=df.retain("home.dest","survived").groupBy("home.dest").sum().resetIndex();
        System.out.println(survhome);*/
    }















    /*public static void summarizeData(Table titanic_data)
    {
        Table summary=titanic_data.summarize("survived",count).by("pclass");
        System.out.println(summary);
        DoubleColumn age=titanic_data.doubleColumn("age");
        titanic_data.summarize(age,max,mean,min);
        System.out.println(age.max());
        Table results=titanic_data.summarize(age,mean,max,min).apply();
        System.out.println(results);
        DoubleColumn fare=titanic_data.doubleColumn("fare");
        Table results_fare=titanic_data.summarize(fare,mean,max,min).apply();
        System.out.println(results_fare);
        Table countSurvived = titanic_data.summarize("survived", count).by("pclass");
        System.out.println(countSurvived);
        Table countSurvived_dest = titanic_data.summarize("survived", count).by("home.dest");
        System.out.println(countSurvived_dest);
    }*/
}

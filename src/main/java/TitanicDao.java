import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TitanicDao
{
    public TitanicDao() {
    }

    public List<TitanicPassenger> readFromJson()
    {
        List<TitanicPassenger> allPassengers = new ArrayList<TitanicPassenger>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            InputStream input = new FileInputStream("titanic_csv.json");
            allPassengers= objectMapper.readValue(input, new TypeReference <List<TitanicPassenger>>(){});
        } catch (IOException Ex){
            Ex.printStackTrace();
        }

        return allPassengers;
    }
    public void readData(List<TitanicPassenger> allpassengers,boolean x)
    {   if(x){
        for(TitanicPassenger element:allpassengers)
        {
            System.out.println(element);
        }
    }
    else
    {
        System.out.println("No Data Printed");
    }
    }
    public void graphPassengerAges(List<TitanicPassenger> passengerList) {//filter to get an array of passenger ages
        List<Float> pAges= passengerList.stream().map (TitanicPassenger::getAge).limit (8).collect (Collectors.toList());
        List<String> pNames= passengerList.stream().map (TitanicPassenger::getName).limit (8).collect (Collectors.toList());
        CategoryChart chart = new CategoryChartBuilder().width (1024).height (768).title ("Age Histogram").xAxisTitle("Names").yAxisTitle("Age").build ();
        // 2.Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setStacked(true);
        // 3.Series
        chart.addSeries("Passenger's Ages", pNames, pAges);
        // 4.Show it
        new SwingWrapper(chart).displayChart();
    }
    public void graphPassengerClass(List<TitanicPassenger> passengerList)
    {//filter to get a map of passenger class and total number of passengers in each class
        Map<String, Long> result =passengerList.stream().collect(Collectors.groupingBy(TitanicPassenger::getPclass, Collectors.counting()));
        PieChart chart = new PieChartBuilder().width (800).height (600).title (getClass().getSimpleName()).build ();
        //Customize Chart
        Color[] sliceColors=new Color[]{new Color(180,68,50),new Color(130,105,120),new Color(80,143,160)};
        chart.getStyler().setSeriesColors(sliceColors);
        // Series
        chart.addSeries("First Class", result.get("1"));
        chart.addSeries("Second Class", result.get("2"));
        chart.addSeries("Third Class", result.get("3"));
        // Show it
        new SwingWrapper(chart).displayChart();


    }

    public void graphPassengerSurvived(List<TitanicPassenger> passengerList)
    {//filter to get a map of passenger class and total number of passengers in each class
        Map<String, Long> result =passengerList.stream().collect(Collectors.groupingBy(TitanicPassenger::getSurvived, Collectors.counting()));
        PieChart chart = new PieChartBuilder().width (800).height (600).title (getClass().getSimpleName()).build ();
        //Customize Chart
        Color[] sliceColors=new Color[]{new Color(180,68,50),new Color(130,105,120)};

        chart.getStyler().setSeriesColors(sliceColors);
        // Series
        chart.addSeries("Survived", result.get("1"));
        chart.addSeries("Not survived", result.get("0"));
        //chart.addSeries("Third Class", result.get("3"));
        // Show it
        new SwingWrapper(chart).displayChart();
    }
    public void graphPassengerSurvivedGender(List<TitanicPassenger> passengerList)
    {
        Map<String, Long> result =passengerList.stream().filter(c->c.getSurvived().trim().equals("1")).collect (Collectors.groupingBy(TitanicPassenger::getSex, Collectors.counting() ) );
        PieChart chart = new PieChartBuilder().width (800).height (600).title (getClass().getSimpleName()).build ();

        Color[] sliceColors= new Color[]{new Color (180, 68, 50), new Color (130, 105, 120)};
        chart.getStyler().setSeriesColors(sliceColors);

        chart.addSeries("Survived female", result.get("female"));
        chart.addSeries("Survived male", result.get("male"));

        new SwingWrapper(chart).displayChart();
    }




}


















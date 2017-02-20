package javaapplication1;

/**
 *
 * @author Myra
 */


import com.google.gson.Gson;
import java.net.*;
import java.io.*;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class JavaApplication1 {
    public static String endpoint;
    public static String[] xml;
    public static String[] json;
    public static int year;
    public static int month;
    public static int day;
    public static int i;
    public static int nrOfDays; 
    private static WritableCellFormat timesBoldUnderline;
    private static WritableCellFormat times;
    
// Read endpoint from config.properties file
    public static void getEndpoint(){
        FileInputStream fis;
        Properties property = new Properties();
 
        try {
            fis = new FileInputStream("C:\\Users\\Myra\\Desktop\\internship\\project 00\\JavaApplication1\\src\\javaapplication1\\config.properties");
            property.load(fis);
            endpoint = property.getProperty("db.dir");
            System.out.println("dir: " + endpoint);
            
        } catch (IOException e) {System.err.println("Error, File not found");}
    
    }

    // create excel file 
    public void createExcelFile() throws IOException, WriteException {
        File file = new File(endpoint + "BNM.xls");
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);

        workbook.write();
        workbook.close();
    }
           
    private static void createLabel(WritableSheet sheet) throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);
        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                                WritableFont.TIMES, 10, WritableFont.BOLD, false,
                                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);
        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        // Write headers for columns
        Label date = new Label(0, 0, "Date", timesBoldUnderline);
        sheet.addCell(date);
        Label x = new Label(1, 0, "XML", timesBoldUnderline);
        sheet.addCell(x);
        Label j = new Label(2, 0, "JSON", timesBoldUnderline);
        sheet.addCell(j);
        
        int h = day;
        for(i=0;i<nrOfDays;i++)
            
            {
        String g;
        if(h<10 && month <10){
        if(h<1 && (month == 5 || month == 7)){
        h=30;
        month--;
        } 
        if(h<1){
        h=31;
        month--;
        }
        if(month == 0){
            month=12;
            year--;
        }
        g = "0"+h+".0"+month+"."+year;
        Label a = new Label(0,i+1, g, times);
        sheet.addCell(a);
        }
        
        
        if(h<10 && month >=10){
        if(h<1 &&  (month == 12 || month == 10)){
        h=30;
        month--;
        } 
        if(h<1){
        h=31;
        month--;
        }
        g = "0"+h+"."+month+"."+year;
        Label a = new Label(0,i+1, g, times);
        sheet.addCell(a);
        }
        
        
        if(h>10 && month <10){
        g = ""+h+".0"+month+"."+year;
        Label a = new Label(0,i+1, g, times);
        sheet.addCell(a);
        }
        
        if(h>=10 && month >=10){
        g = ""+h+"."+month+"."+year;
        Label a = new Label(0,i+1, g, times);
        sheet.addCell(a);
        }
            
            Label b = new Label(1,i+1,xml[i], times);
            sheet.addCell(b);     
            Label c = new Label(2,i+1,json[i], times);
            sheet.addCell(c);
            h--;
            }
        }
    
   
  //get today date
    public static void date(){
        LocalDateTime now = LocalDateTime.now();
        year = now.getYear();
        month = now.getMonthValue();
        day = now.getDayOfMonth();
   }
    public static String instring()
        {
        BufferedReader box=new BufferedReader(new InputStreamReader(System.in));
        String str="";
        try
        {str=box.readLine();}
        catch(Exception e){};
        return str;
        }

    public static int inint()
        {return(Integer.valueOf(instring())).intValue();}

   
    public static void main(String[] args) throws WriteException, IOException, JAXBException {
        // TODO code application logic here
   
    date();
    getEndpoint();
    i=0;      
  
    System.out.println("How many days you want to extract from BNM?:");
    nrOfDays=inint();
    xml =  new String[nrOfDays];
    json =  new String[nrOfDays];
  

               int d=day; 
               int m=month;
               int y=year;
               for(i=0;i<nrOfDays;i++){
    if(d<10 && m <10){
        if(d<1 && (m == 5 || m == 7)){
        d=30;
        m--;
        } 
        if(d<1){
        d=31;
        m--;
        }
        if(m == 0){
            m=12;
            y--;
        }
        
// create objects from url
    JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    URL bnmCursUrl = new URL("https://www.bnm.md/en/official_exchange_rates?get_xml=1&date="+"0"+d+"."+"0"+m+"."+y);
    ValCurs cursulValutar = (ValCurs) jaxbUnmarshaller.unmarshal(bnmCursUrl);
    
//create xml string from objects 
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
    StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(cursulValutar, sw);
    xml[i] = sw.toString();
    
// convert java objects into json
    Gson gson=new Gson();
    json[i]=gson.toJson(cursulValutar);        
                }
    if(d<10 && m >=10){
        if(d<1 &&  (m == 12 || m == 10)){
        d=30;
        m--;
        } 
        if(d<1){
        d=31;
        m--;
        }
// create objects from url
    JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    URL bnmCursUrl = new URL("https://www.bnm.md/en/official_exchange_rates?get_xml=1&date="+"0"+d+"."+m+"."+y);
    ValCurs cursulValutar = (ValCurs) jaxbUnmarshaller.unmarshal(bnmCursUrl);
   
//create xml string from objects 
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
    StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(cursulValutar, sw);
    xml[i] = sw.toString();
 
// convert java objects into json
    Gson gson=new Gson();
    json[i]=gson.toJson(cursulValutar);
    }  
    if(d>10 && m <10){
// create objects from url
    JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    URL bnmCursUrl = new URL("https://www.bnm.md/en/official_exchange_rates?get_xml=1&date="+d+"."+"0"+m+"."+y);
    ValCurs cursulValutar = (ValCurs) jaxbUnmarshaller.unmarshal(bnmCursUrl);
   
//create xml string from objects 
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
    StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(cursulValutar, sw);
    xml[i] = sw.toString();
 
// convert java objects into json
    Gson gson=new Gson();
    json[i]=gson.toJson(cursulValutar);
    } 
    if(d>=10 && m >=10){
// create objects from url
    JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    URL bnmCursUrl = new URL("https://www.bnm.md/en/official_exchange_rates?get_xml=1&date="+d+"."+m+"."+y);
    ValCurs cursulValutar = (ValCurs) jaxbUnmarshaller.unmarshal(bnmCursUrl);
   
//create xml string from objects 
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
    StringWriter sw = new StringWriter();
    jaxbMarshaller.marshal(cursulValutar, sw);
    xml[i] = sw.toString();
 
// convert java objects into json
    Gson gson=new Gson();
    json[i]=gson.toJson(cursulValutar);
    } 
    
                d--;
                }
 
                JavaApplication1 test = new JavaApplication1();
                test.createExcelFile();
                System.out.println("Please check the result file");

              
 }
}
    


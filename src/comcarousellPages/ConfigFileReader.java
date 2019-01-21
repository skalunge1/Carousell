package comcarousellPages;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.io.File;
public class ConfigFileReader {
 
 private Properties properties;
 private final String propertyFilePath= "src/carousell.properties";
 
 
 public ConfigFileReader(){
 BufferedReader reader;
 try {
 reader = new BufferedReader(new FileReader(propertyFilePath));
 properties = new Properties();
 try {
 properties.load(reader);
 reader.close();
 } catch (IOException e) {
 e.printStackTrace();
 }
 } catch (FileNotFoundException e) {
 e.printStackTrace();
 throw new RuntimeException("Carousell.properties not found at " + propertyFilePath);
 } 
 }
 
 public String getCarouselluser(){
// try {
	 String user = properties.getProperty("user");
// }catch (Exception e)
// {
//	 System.out.println("Exception is "+e);
// }
return user;
 }
 
 public String getCarousellPass(){
		 String pass = properties.getProperty("pass");
	return pass;
	 }
 
 public String getCarousellitemname(){
		 String item_name = properties.getProperty("item_name");
	return item_name;
	 }
 
 public String getCarousellSearchUser(){
	 String search_user = properties.getProperty("search_user");
return search_user;
 }
 
 public long getImplicitlyWait() { 
 String implicitlyWait = properties.getProperty("implicitlyWait");
 if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
 else throw new RuntimeException("implicitlyWait not specified in the Carousell.properties file."); 
 }
 
 
}
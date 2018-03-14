import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * @author zhaoyy E-mail:zhaoyuanyuan@sinovatech.com
 * @version 创建时间：2018年1月25日 下午4:22:18
 * 类说明
 */
public class CreateDoc {

	public static void main(String[] args) {
		try {  
	        String inString = "";
	        String tmpString = "";
	        File inFile = new File("D://in4.csv"); // 读取的CSV文件
	        File outFile = new File("D://outtest4.csv");//输出的CSV文
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
            
            
            CsvReader creader = new CsvReader(reader, ',');
            CsvWriter cwriter = new CsvWriter(writer,',');
            while(creader.readRecord()){
			    inString = creader.getRawRecord();//读取一行数据
			    if(inString.indexOf("|") > 0){
			    	System.out.println(inString);
			    	inString = inString.replaceAll("＊", "").trim();
			    	String []ss = inString.split("\\|");
				    String idcd = ss[2].trim();
				    System.out.println(idcd);
				    if(idcd.length() == 9){
				    	tmpString = "8a739e02610c1b01016126f212ca3b7d|3|"+idcd+"|JVM9B3J|ahyd|";
					    //第一个参数表示要写入的字符串数组，每一个元素占一个单元格，第二个参数为true时表示写完数据后自动换行
						cwriter.writeRecord(tmpString.split(","), true);
				    }
			    }
				//注意，此时再用cwriter.write(inString)方法写入数据将会看到只往第一个单元格写入了数据，“，”没起到调到下一个单元格的作用
				//如果用cwriter.write(String str)方法来写数据，则要用cwriter.endRecord()方法来实现换行
				//cwriter.endRecord();//换行
				cwriter.flush();//刷新数据
			}    
				
            creader.close();
            cwriter.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }

}

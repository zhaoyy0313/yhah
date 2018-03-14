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
 * @version ����ʱ�䣺2018��1��25�� ����4:22:18
 * ��˵��
 */
public class CreateDoc {

	public static void main(String[] args) {
		try {  
	        String inString = "";
	        String tmpString = "";
	        File inFile = new File("D://in4.csv"); // ��ȡ��CSV�ļ�
	        File outFile = new File("D://outtest4.csv");//�����CSV��
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
            
            
            CsvReader creader = new CsvReader(reader, ',');
            CsvWriter cwriter = new CsvWriter(writer,',');
            while(creader.readRecord()){
			    inString = creader.getRawRecord();//��ȡһ������
			    if(inString.indexOf("|") > 0){
			    	System.out.println(inString);
			    	inString = inString.replaceAll("��", "").trim();
			    	String []ss = inString.split("\\|");
				    String idcd = ss[2].trim();
				    System.out.println(idcd);
				    if(idcd.length() == 9){
				    	tmpString = "8a739e02610c1b01016126f212ca3b7d|3|"+idcd+"|JVM9B3J|ahyd|";
					    //��һ��������ʾҪд����ַ������飬ÿһ��Ԫ��ռһ����Ԫ�񣬵ڶ�������Ϊtrueʱ��ʾд�����ݺ��Զ�����
						cwriter.writeRecord(tmpString.split(","), true);
				    }
			    }
				//ע�⣬��ʱ����cwriter.write(inString)����д�����ݽ��ῴ��ֻ����һ����Ԫ��д�������ݣ�������û�𵽵�����һ����Ԫ�������
				//�����cwriter.write(String str)������д���ݣ���Ҫ��cwriter.endRecord()������ʵ�ֻ���
				//cwriter.endRecord();//����
				cwriter.flush();//ˢ������
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

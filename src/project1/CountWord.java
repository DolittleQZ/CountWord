package abc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class CountWords {
	
	int linecount ;
	int wordcount ;
	int charcount ;
	
	
	public CountWords(int a,int b,int c)
	{
		linecount = a;
		wordcount = b;
		charcount = c;
	}
	
	public CountWords CountWd(String sFilename) {
		CountWords cw1 = new CountWords(0,0,0);
	    File file = new File(sFilename);
	    if(file.exists()) {
	        try {
	        	
	        	
	        	
	            FileInputStream fis = new FileInputStream(file);
	            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
	            BufferedReader br = new BufferedReader(isr);

	            String line = "";
	            StringBuffer sb  = new StringBuffer();

	            while ((line = br.readLine()) != null)
	            {
	                cw1.linecount++;
	                sb.append(line);
	                cw1.charcount += line.length();
	                
	            }
	            cw1.wordcount = sb.toString().split("\\s+").length;//
	                            
	            br.close();
	            isr.close();
	            fis.close();
	                                            
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	    return cw1;
	}

}

package def;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.print.DocFlavor.URL;


public class CountWords {
	
	int linecount ;
	int wordcount ;
	int charcount ;
	int nullLinecount;
	int codeLinecount;
	int noteLinecount;
	
	public CountWords(int a,int b,int c,int d,int e,int f)
	{
		linecount = a;
		wordcount = b;
		charcount = c;
		nullLinecount=d;
		codeLinecount=e;
		noteLinecount=f;
	}
	
	//遍历目录下所有文件
	public void traverseFolder2(String path,String []sParameter) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                       // System.out.println("文件夹:" + files[i].getAbsolutePath());
                        traverseFolder2(files[i].getAbsolutePath(),sParameter);
                    } else {
                    	//System.out.println("文件:" + files[i].getAbsolutePath());
                    	CountWords wc = new CountWords(0,0,0,0,0,0);
                    	wc=wc.CountWd(files[i].getAbsolutePath());
                    	wc.PrintResult(sParameter,files[i].getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
	//将在控制台程序中打印的结果输入OutputFile文件中
	public void PrintFile(String content,String OutputFile) {
		FileOutputStream fop = null;  
        File file;  
        try {  
            file = new File(OutputFile);  
            fop = new FileOutputStream(file);  
            // if file doesnt exists, then create it  
            if (!file.exists()) {  
                file.createNewFile();  
            }  
            // get the content in bytes  
            
            byte[] contentInBytes = content.getBytes();  
  
            fop.write(contentInBytes);  
            fop.flush();  
            fop.close();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (fop != null) {  
                    fop.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	}
    //在控制台程序中打印结果
	public void PrintResult(String []sParameter,String filename) {
		//String str="";
		for (int i = 0; i < sParameter.length; i++) {
            if (sParameter[i].equals("-l")) {
            //	str =str+ filename+",行数："+String.valueOf(linecount)+"\r";
                System.out.print(filename+",行数：");
                System.out.println(linecount);
            } else if (sParameter[i].equals("-w")) {
            	//str =str+ filename+",单词数："+String.valueOf(wordcount)+"\r";
                System.out.print(filename+",单词数：");
                System.out.println(wordcount);
            } else if (sParameter[i].equals("-c")) {
            	//str =str+ filename+",字符数："+String.valueOf(charcount)+"\r";
                System.out.print(filename+",字符数：");
                System.out.println(charcount);
            } else if (sParameter[i].equals("-a")) {
            	//str =str+ filename+", 代码行/空行/注释行："+String.valueOf(nullLinecount)+
            			//"/"+String.valueOf(codeLinecount)+
            		//	"/"+String.valueOf(noteLinecount)+"\n";
            	//atest.c, 代码行/空行/注释行: 10/3/9
                System.out.print(filename+", 代码行/空行/注释行：");
                System.out.print(nullLinecount);
                System.out.print("/"+codeLinecount);
                System.out.println("/"+noteLinecount);
            }
		}
		//return str;
	}
	//写入文件
	public String Writefile(String []sParameter,String filename,String filename2) {
		String str="";
		for (int i = 0; i < sParameter.length; i++) {
            if (sParameter[i].equals("-l")) 
            	str =str+ filename+",行数："+String.valueOf(linecount)+"\r";
            else if (sParameter[i].equals("-w")) 
            	str =str+ filename+",单词数："+String.valueOf(wordcount)+"\r";
                else if (sParameter[i].equals("-c")) 
            	str =str+ filename+",字符数："+String.valueOf(charcount)+"\r";
                else if (sParameter[i].equals("-a")) 
            	str =str+ filename+", 代码行/空行/注释行："+String.valueOf(nullLinecount)+
            			"/"+String.valueOf(codeLinecount)+
            			"/"+String.valueOf(noteLinecount)+"\n";
            	else if(sParameter[i].equals("-o")) {
            	PrintFile(str, filename2);
            }
		}
		return str;
	}
	//读取文件中字符数、单词数、行数以及代码行、空行和注释行数的统计
	public CountWords CountWd(String sFilename) {		
		CountWords cw1 = new CountWords(0,0,0,0,0,0);
	    File file = new File(sFilename);	    
	    if(file.exists()) {
	        try {	        	
	            FileInputStream fis = new FileInputStream(file);
	            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
	            BufferedReader br = new BufferedReader(isr);
	            String line = "";
	            StringBuffer sb  = new StringBuffer();
	            while ((line = br.readLine()) != null) {
	            	int space = 0,xiegang = 0;
	                cw1.linecount++;
	                sb.append(line);
	                cw1.charcount += line.length();
	                cw1.charcount++;
	                for(int i =0;i!=line.length();++i) {
	                	if(line.charAt(i)=='/') 
	                		if(line.charAt(i+1)=='/')
	                			{
	                				xiegang = 2;
	                				break;
	                			}
	                	if(line.charAt(i)!=' ')
	                		++space;	
	                }
	                cw1.wordcount = br.toString().split("\40+|,+").length;
	                if(xiegang == 2 && space <=1)
	                	cw1.noteLinecount++;
	                else if(space >= 2)
	                	cw1.codeLinecount++;
	                else cw1.nullLinecount++;
	            }
	            cw1.wordcount = sb.toString().split("\40+|,+").length;
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

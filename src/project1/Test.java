package abc;
import java.util.Scanner;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountWords cw = new CountWords(0,0,0);
		
		do{
			System.out.print("wc.exe ");
			Scanner scan = new Scanner(System.in);
			String []sParameter= new String[10];
			int j=0;
			String str=scan.next();
			if(str.equals("-c")||(str.equals("-w")||str.equals("-l"))){
				while(str.equals("-c")||(str.equals("-w")||str.equals("-l"))) {
					sParameter[j++] = str;
					str = scan.next();
				}
		
				String filename = Test.class.getResource("/")+str;
				cw = cw.CountWd("C:/Users/admin/Desktop/C++ Primer/primer C++/stdafx.txt");
				
				System.out.println(1234 + " "+ filename );
				
				for (int i = 0; i < sParameter.length; i++) {
	                if (sParameter[i].equals("-l")) {
	                    System.out.print("line count：");
	                    System.out.println(cw.linecount);
	                } else if (sParameter[i].equals("-w")) {
	                    System.out.print("word count：");
	                    System.out.println(cw.wordcount);
	                } else if (sParameter[i].equals("-c")) {
	                    System.out.print("char count：");
	                    System.out.println(cw.charcount);
	                } 
				}
			}else {
				System.out.println("格式错误！");
			}
			
			//System.out.println(1234);
		}while(true) ;
		
	}
}

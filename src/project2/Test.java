package def;
import java.io.File;
import java.net.URL;
import java.util.Scanner;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CountWords cw = new CountWords(0,0,0,0,0,0);
		
		do{
			Scanner scan = new Scanner(System.in);
			String []sParameter= new String[10];
			for(int i=0;i!=sParameter.length;++i)
				sParameter[i]="";
			int j=0;
			String str=scan.next();
			if(str.equals("wc.exe")) {
				str=scan.next();
				if(((str.equals("-c")||str.equals("-a"))||str.equals("-s"))||(str.equals("-w")||str.equals("-l"))){
					while(((str.equals("-c")||str.equals("-a"))||str.equals("-s"))||(str.equals("-w")||str.equals("-l"))) {
						sParameter[j++] = str;
						str = scan.next();
					}
					String filename = Class.class.getClass().getResource("/").getPath();
					String filename1 = "";
					if(str.charAt(str.length()-5)=='*') {
						if(str.charAt(0)=='*')
							cw.traverseFolder2(Class.class.getClass().getResource("/").getPath(), sParameter);
						else
							{
								str =str.substring(0, str.length()-5);
								
								cw.traverseFolder2(str, sParameter);
							}
					}
					else {
						filename = filename + str;     
						filename1 = str;
						cw = cw.CountWd(filename);
						cw.PrintResult(sParameter, filename1);
					}														
					str = scan.next();
					if(str.equals("-o")) {
						sParameter[j++] = str;
						str = scan.next();
						String filename2 = Class.class.getClass().getResource("/").getPath() + str;
						cw.Writefile(sParameter, filename1,filename2);
					}
				}else {
					System.out.println("格式错误！");
				}
			}else {
				System.out.println("格式错误！");
			}
		}while(true) ;
		
	}
}

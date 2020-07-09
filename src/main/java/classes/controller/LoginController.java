package classes.controller;

import java.io.IOException;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.*;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@Autowired
	ResourceLoader resourceLoader;

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		boolean blicenseValid = false;
		try {
			blicenseValid = validateLicense();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return "plain-login";
		if (blicenseValid) {
			return "fancy-login";
		} else {
			return "licenseError";
		}
	}

	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "access-denied";

	}

	private boolean validateLicense() throws ParseException, IOException {
		boolean bValid = false;
		Date date1 = new java.util.Date();

		Resource resource = resourceLoader.getResource("resources/data.txt");
		// Resource resource = new ClassPathResource("data.txt");
		InputStream inputStream = resource.getInputStream();
		String data = "";
		try {
			byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
			data = new String(bdata, StandardCharsets.UTF_8);

		} catch (IOException e) {
			e.printStackTrace();
		}
		/* StringBuffer sb = new StringBuffer();
	      //Converting string to character array
	      char ch[] = data.toCharArray();
	      for(int i = 0; i < ch.length; i++) {
	         String hexString = Integer.toHexString(ch[i]);
	         sb.append(hexString);
	      }
	      String hexResult = sb.toString();
        String enStr = encryptStr(hexResult);*/
        String deStr = decryptStr(data);
        
        String result = new String();
        char[] charArray = deStr.toCharArray();
        for(int i = 0; i < charArray.length; i=i+2) {
           String st = ""+charArray[i]+""+charArray[i+1];
           char ch2 = (char)Integer.parseInt(st, 16);
           result = result + ch2;
        }
        System.out.println(result);
        
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = sdformat.parse(result);
		if (date1.compareTo(date2) < 0) {
			bValid = true;
		}
		return bValid;
	}

	private String encryptStr(String str) {
		String enStr = "";
		try {
			for (int i = 0; i < str.length(); i++) {
				char ch = Character.toLowerCase(str.charAt(i));
				switch (ch)  
	            {  
	                case 'a':  
	                    enStr=enStr+"{";  
	                    break;  
	                case 'b':  
	                    enStr=enStr+"}";  
	                    break;  
	                case 'c':  
	                    enStr=enStr+"#";  
	                    break;  
	                case 'd':  
	                    enStr=enStr+"~";  
	                    break;  
	                case 'e':  
	                    enStr=enStr+"+";  
	                    break;  
	                case 'f':  
	                    enStr=enStr+"-";  
	                    break;  
	                case 'g':  
	                    enStr=enStr+"*";  
	                    break;  
	                case 'h':  
	                    enStr=enStr+"@";  
	                    break;  
	                case 'i':  
	                    enStr=enStr+"/";  
	                    break;  
	                case 'j':  
	                    enStr=enStr+"\\";  
	                    break;  
	                case 'k':  
	                    enStr=enStr+"?";  
	                    break;  
	                case 'l':  
	                    enStr=enStr+"$";  
	                    break;  
	                case 'm':  
	                    enStr=enStr+"!";  
	                    break;  
	                case 'n':  
	                    enStr=enStr+"^";  
	                    break;  
	                case 'o':  
	                    enStr=enStr+"(";  
	                    break;  
	                case 'p':  
	                    enStr=enStr+")";  
	                    break;  
	                case 'q':  
	                    enStr=enStr+"<";  
	                    break;  
	                case 'r':  
	                    enStr=enStr+">";  
	                    break;  
	                case 's' :  
	                    enStr=enStr+"=";  
	                    break;  
	                case 't':  
	                    enStr=enStr+";";  
	                    break;  
	                case 'u':  
	                    enStr=enStr+",";  
	                    break;  
	                case 'v' :  
	                    enStr=enStr+"_";  
	                    break;  
	                case 'w':  
	                    enStr=enStr+"[";  
	                    break;  
	                case 'x' :  
	                    enStr=enStr+"]";  
	                    break;  
	                case 'y':  
	                    enStr=enStr+":";  
	                    break;  
	                case 'z' :  
	                    enStr=enStr+"\"";  
	                    break;  
	                case ' ' :  
	                    enStr=enStr+" ";  
	                    break;  
	                case '.':  
	                    enStr=enStr+'3';  
	                    break;  
	                case ',':  
	                    enStr=enStr+"1";  
	                    break;  
	                case '(':  
	                    enStr=enStr+'4';  
	                    break;  
	                case '\"':  
	                    enStr=enStr+'5';  
	                    break;  
	                case ')' :  
	                    enStr=enStr+"7";  
	                    break;  
	                case '?' :  
	                    enStr= enStr+"2";  
	                    break;  
	                case '!':  
	                    enStr= enStr+"8";  
	                    break;  
	                case '-' :  
	                    enStr= enStr+"6";  
	                    break;  
	                case '%' :  
	                    enStr = enStr+"9";  
	                    break;  
	                case '1':  
	                    enStr=enStr+"r";  
	                    break;  
	                case '2':  
	                    enStr=enStr+"k";  
	                    break;  
	                case '3':  
	                    enStr=enStr+"b";  
	                    break;  
	                case '4':  
	                    enStr = enStr+"e";  
	                    break;  
	                case '5':  
	                    enStr = enStr+"q";  
	                    break;  
	                case '6':  
	                    enStr = enStr+"h";  
	                    break;  
	                case '7':  
	                    enStr = enStr+"u";  
	                    break;  
	                case '8' :  
	                    enStr= enStr+"y";  
	                    break;  
	                case '9':  
	                    enStr = enStr+"w";  
	                    break;  
	                case '0':  
	                    enStr = enStr+"z";  
	                    break;  
	                 default:  
	                    enStr=enStr+"0";  
	                    break;  
	            }  
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return enStr;
	}
	
	private String decryptStr(String str) {
		String deStr = "";
		try {
			for (int i = 0; i < str.length(); i++) {
				char ch=Character.toLowerCase(str.charAt(i));  
				switch (ch)  
	            {  
	            case '{':  
                    deStr=deStr+"a";  
                    break;  
                case '}':  
                    deStr=deStr+"b";  
                    break;  
                case '#':  
                    deStr=deStr+"c";  
                    break;  
                case '~':  
                    deStr=deStr+"d";  
                    break;  
                case '+':  
                    deStr=deStr+"e";  
                    break;  
                case '-':  
                    deStr=deStr+"f";  
                    break;  
                case '*':  
                    deStr=deStr+"g";  
                    break;  
                case '@':  
                    deStr=deStr+"h";  
                    break;  
                case '/':  
                    deStr=deStr+"i";  
                    break;  
                case '\\':  
                    deStr=deStr+"j";  
                    break;  
                case '?':  
                    deStr=deStr+"k";  
                    break;  
                case '$':  
                    deStr=deStr+"l";  
                    break;  
                case '!':  
                    deStr=deStr+"m";  
                    break;  
                case 'n':  
                    deStr=deStr+"n";  
                    break;  
                case '(':  
                    deStr=deStr+"o";  
                    break;  
                case ')':  
                    deStr=deStr+"p";  
                    break;  
                case '<':  
                    deStr=deStr+"q";  
                    break;  
                case '>':  
                    deStr=deStr+"r";  
                    break;  
                case '=' :  
                    deStr=deStr+"s";  
                    break;  
                case ';':  
                    deStr=deStr+"t";  
                    break;  
                case ',':  
                    deStr=deStr+"u";  
                    break;  
                case '_' :  
                    deStr=deStr+"v";  
                    break;  
                case '[':  
                    deStr=deStr+"w";  
                    break;  
                case ']' :  
                    deStr=deStr+"x";  
                    break;  
                case ':':  
                    deStr=deStr+"y";  
                    break;  
                case '\"' :  
                    deStr=deStr+"z";  
                    break;  
                case ' ' :  
                    deStr=deStr+" ";  
                    break;  
                case '3':  
                    deStr=deStr+'.';  
                    break;  
                case '1':  
                    deStr=deStr+",";  
                    break;  
                case '4':  
                    deStr=deStr+'(';  
                    break;  
                case '5':  
                    deStr=deStr+'\"';  
                    break;  
                case '7' :  
                    deStr=deStr+")";  
                    break;  
                case '2' :  
                    deStr= deStr+"?";  
                    break;  
                case '8':  
                    deStr= deStr+"!";  
                    break;  
                case '6' :  
                    deStr= deStr+"-";  
                    break;  
                case '9' :  
                    deStr = deStr+"%";  
                    break;  
                case 'r':  
                    deStr=deStr+"1";  
                    break;  
                case 'k':  
                    deStr=deStr+"2";  
                    break;  
                case 'b':  
                    deStr=deStr+"3";  
                    break;  
                case 'e':  
                    deStr = deStr+"4";  
                    break;  
                case 'q':  
                    deStr = deStr+"5";  
                    break;  
                case 'h':  
                    deStr = deStr+"6";  
                    break;  
                case 'u':  
                    deStr = deStr+"7";  
                    break;  
                case 'y' :  
                    deStr= deStr+"8";  
                    break;  
                case 'w':  
                    deStr = deStr+"9";  
                    break;  
                case 'z':  
                    deStr = deStr+"0";  
                    break;  
                 default:  
                    deStr=deStr+"0";  
                    break;  
	            }  
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return deStr;
	}
}

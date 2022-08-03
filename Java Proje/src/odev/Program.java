/**
*
* @author Aslýhan Akbulut  aslihan.akbulut1@ogr.sakarya.edu.tr
* @since 11.03.2022
* <p>
* Sýnýf: 2C  no:G201210080 
* </p>
*/

package odev;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {

	public static void main(String[] args) throws IOException {
		String dosyaAdi;
		int not = 0;
		int not2 =0;
		if (args.length > 0) {//komut argümaný olarak alýnan dosya adý stringe atandý ve file oluþturuldu.
			
			dosyaAdi = args[0];
            File dosya = new File(dosyaAdi);
            
            if(dosya.exists()) {//dosya bulunuyorsa okuma iþlemleri gerçekleþtirildi ve satýr satýr yedek ArrayListe atandý.
            	
            	FileReader okuyucu = new FileReader(dosya);
            	String x;
            	String line;
                BufferedReader bReader = new BufferedReader(okuyucu);
                ArrayList<String> satirlar = new ArrayList<String>();
                ArrayList<String> satirlaryedek = new ArrayList<String>();
                
                while((line = bReader.readLine()) != null)
                {
                	satirlaryedek.add(line);
                	
                }
                bReader.close();
                
                //--------- Satýrlar ArrayListe Eklendi.
                
                for(int i = 0; i<satirlaryedek.size();i++)//yorum satýrlarý regex ile bulundu.
                {
                	Pattern yorum = Pattern.compile("//");
                	Matcher yorumm = yorum.matcher(satirlaryedek.get(i));
                	
                	Pattern yorum2 = Pattern.compile("/[*]");
                	Matcher yorumm2 = yorum2.matcher(satirlaryedek.get(i));
                	if(yorumm.find())
                	{
                		
                	}
                	else if(yorumm2.find())
                	{
                		for( int j=i ;j<satirlaryedek.size();j++,i++)
                		{
                			Pattern yorum3 = Pattern.compile("[*]/");
                    		Matcher yorumm3 = yorum3.matcher(satirlaryedek.get(j));
                			if(yorumm3.find())
                				break;
                		}
                	}
                	else
                		satirlar.add(satirlaryedek.get(i));//yorum satýrý dýþýndakiler asýl ArrayListe eklendi.
                }
                //--------- Yorum Satýrlarý Silindi.
                
                Lexical lexical = new Lexical();//Lexical sýnýfýndan nesne oluþturuldu ve opertatör okumalarýna baþlandý.
                for (String satir : satirlar)
                {
                	//MANTIKSAL OPERATORLER
                    Pattern mantik1 = Pattern.compile("&&");
                    Matcher m1 = mantik1.matcher(satir);
                    
                    while(m1.find())
                    {
                    	lexical.mantiksalArttir();//bulunduðu zaman lexical nesnesinin ilgili arttýr fonksiyonu çaðýrýlýr.
                    	lexical.setIkili(lexical.getIkili()-2);//ikili operatör &'e sahip olduðundan ve burada 2 & okuduðundan ondan çýkarttýk.
                    }
                    
                    Pattern mantik3 = Pattern.compile("!");
                    Matcher m12 = mantik3.matcher(satir);
                    
                    while(m12.find())
                    {
                    	lexical.mantiksalArttir();
                    	not2++;
                    }
                    
                    Pattern mantik2 = Pattern.compile("\\|{2}");
                    Matcher m2 = mantik2.matcher(satir);
                    
                    while(m2.find())
                    {
                    	lexical.mantiksalArttir();
                    	lexical.setIkili(lexical.getIkili()-2);
                    }
                    //---------------------------------------------------
                    
                    //ÝLÝÞKÝSEL OPERATÖRLER
                    Pattern iliskisel1 = Pattern.compile("<|>|<=|>=");
                    Matcher m3 = iliskisel1.matcher(satir);
                    
                    while(m3.find())
                    {
                    	lexical.iliskiselArttir();
                    }
                    
                    Pattern iliskisel2 = Pattern.compile("={2}");
                    Matcher m4 = iliskisel2.matcher(satir);
                    
                    while(m4.find())
                    {
                    	lexical.iliskiselArttir();
                    	lexical.setIkili(lexical.getIkili()-2);
                    }
                    
                    Pattern iliskisel0 = Pattern.compile("!=");
                    Matcher m0 = iliskisel0.matcher(satir);
                    
                    while(m0.find())
                    {
                    	not++;
                    	lexical.iliskiselArttir();
                    	lexical.setMantiksal(lexical.getMantiksal()-1);// ! mantýksalýn içinde de var o yüzden çýkarttýk
                    	lexical.setIkili(lexical.getIkili()-1);// = ikilinin içinde var o yüzden çýkarttýk
                    }
                    
                    //----------------------------------------------------
                    
                    //TEKLÝ OPERATÖRLER
                    
                    Pattern tekli1 = Pattern.compile("\\+{2}");
                    Matcher m5 = tekli1.matcher(satir);
                    
                    while(m5.find())
                    {
                    	lexical.tekliArttir();
                    	lexical.setIkili(lexical.getIkili()-2);// + ikilinin içinde de var o yüzden çýkarttýk
                    }
                    Pattern tekli2 = Pattern.compile("\\-{2}");
                    Matcher m6 = tekli2.matcher(satir);
                    
                    while(m6.find())
                    {
                    	lexical.tekliArttir();
                    	lexical.setIkili(lexical.getIkili()-2);// - ikilinin içinde de var o yüzden çýkarttýk
                    }
                    //----------------------------------------------------
                    
                    //ÝKÝLÝ OPERATÖRLER
                    Pattern ikili1 = Pattern.compile("[-+/*]=");
                    Pattern ikili2 = Pattern.compile("[%&|^]=");
                    Pattern ikili3 = Pattern.compile("[+|-|*|/|^|%|&]");
                    Pattern ikili4 = Pattern.compile("\\|");
                    Pattern ikili5 = Pattern.compile("=");
                    
                    Matcher m7 = ikili1.matcher(satir);
                    Matcher m8 = ikili2.matcher(satir);
                    Matcher m9 = ikili3.matcher(satir);
                    Matcher m10 = ikili4.matcher(satir);
                    Matcher m11 = ikili5.matcher(satir);
                    
                    while(m7.find())
                    {
                    	lexical.ikiliArttir();
                    	lexical.setIkili(lexical.getIkili()-2);// hem ='i hem (+-/*)'i hem de ikisini birlikte sayýyor bu yüzden çýkarttýk.
                    }
                    while(m8.find())
                    {
                    	lexical.ikiliArttir();
                    	lexical.setIkili(lexical.getIkili()-2);
                    }
                    while(m9.find())
                    {
                    	lexical.ikiliArttir();
                    }
                    while(m10.find())
                    { 
                    	lexical.ikiliArttir();
                    }
                    while(m11.find())
                    {
                    	lexical.ikiliArttir();
                    }
                }
                lexical.operandHesapla(not2-not);// Operand sayýsý bulundu
            	lexical.setSayisal(lexical.getIkili()+lexical.getTekli());//sayýsal operatörun deðeri hesaplandý
                System.out.println(lexical);//nesne istenen formatta ekrana yazdýrýldý.
            }
            else
            	System.out.println("Dosya Bulunamadý.");
            
		}
		else
			System.out.println("Komut Satýrý Parametresi Bulunamadý.");
		
	}

}

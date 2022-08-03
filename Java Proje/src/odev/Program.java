/**
*
* @author Asl�han Akbulut  aslihan.akbulut1@ogr.sakarya.edu.tr
* @since 11.03.2022
* <p>
* S�n�f: 2C  no:G201210080 
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
		if (args.length > 0) {//komut arg�man� olarak al�nan dosya ad� stringe atand� ve file olu�turuldu.
			
			dosyaAdi = args[0];
            File dosya = new File(dosyaAdi);
            
            if(dosya.exists()) {//dosya bulunuyorsa okuma i�lemleri ger�ekle�tirildi ve sat�r sat�r yedek ArrayListe atand�.
            	
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
                
                //--------- Sat�rlar ArrayListe Eklendi.
                
                for(int i = 0; i<satirlaryedek.size();i++)//yorum sat�rlar� regex ile bulundu.
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
                		satirlar.add(satirlaryedek.get(i));//yorum sat�r� d���ndakiler as�l ArrayListe eklendi.
                }
                //--------- Yorum Sat�rlar� Silindi.
                
                Lexical lexical = new Lexical();//Lexical s�n�f�ndan nesne olu�turuldu ve opertat�r okumalar�na ba�land�.
                for (String satir : satirlar)
                {
                	//MANTIKSAL OPERATORLER
                    Pattern mantik1 = Pattern.compile("&&");
                    Matcher m1 = mantik1.matcher(satir);
                    
                    while(m1.find())
                    {
                    	lexical.mantiksalArttir();//bulundu�u zaman lexical nesnesinin ilgili artt�r fonksiyonu �a��r�l�r.
                    	lexical.setIkili(lexical.getIkili()-2);//ikili operat�r &'e sahip oldu�undan ve burada 2 & okudu�undan ondan ��kartt�k.
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
                    
                    //�L��K�SEL OPERAT�RLER
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
                    	lexical.setMantiksal(lexical.getMantiksal()-1);// ! mant�ksal�n i�inde de var o y�zden ��kartt�k
                    	lexical.setIkili(lexical.getIkili()-1);// = ikilinin i�inde var o y�zden ��kartt�k
                    }
                    
                    //----------------------------------------------------
                    
                    //TEKL� OPERAT�RLER
                    
                    Pattern tekli1 = Pattern.compile("\\+{2}");
                    Matcher m5 = tekli1.matcher(satir);
                    
                    while(m5.find())
                    {
                    	lexical.tekliArttir();
                    	lexical.setIkili(lexical.getIkili()-2);// + ikilinin i�inde de var o y�zden ��kartt�k
                    }
                    Pattern tekli2 = Pattern.compile("\\-{2}");
                    Matcher m6 = tekli2.matcher(satir);
                    
                    while(m6.find())
                    {
                    	lexical.tekliArttir();
                    	lexical.setIkili(lexical.getIkili()-2);// - ikilinin i�inde de var o y�zden ��kartt�k
                    }
                    //----------------------------------------------------
                    
                    //�K�L� OPERAT�RLER
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
                    	lexical.setIkili(lexical.getIkili()-2);// hem ='i hem (+-/*)'i hem de ikisini birlikte say�yor bu y�zden ��kartt�k.
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
                lexical.operandHesapla(not2-not);// Operand say�s� bulundu
            	lexical.setSayisal(lexical.getIkili()+lexical.getTekli());//say�sal operat�run de�eri hesapland�
                System.out.println(lexical);//nesne istenen formatta ekrana yazd�r�ld�.
            }
            else
            	System.out.println("Dosya Bulunamad�.");
            
		}
		else
			System.out.println("Komut Sat�r� Parametresi Bulunamad�.");
		
	}

}

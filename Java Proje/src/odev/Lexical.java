/**
*
* @author Asl�han Akbulut  aslihan.akbulut1@ogr.sakarya.edu.tr
* @since 11.03.2022
* <p>
* S�n�f: 2C  no:G201210080 
* </p>
*/
package odev;

public class Lexical {
	private int tekli;
	private int ikili;
	private int sayisal;
	private int iliskisel;
	private int mantiksal;
	private int operand;
	
	public Lexical()//de�i�kenlere ba�lang�� de�eri atayan kurucu fonksiyon
	{
		this.tekli = 0;
		this.ikili = 0;
		this.sayisal = 0;
		this.iliskisel = 0;
		this.mantiksal = 0;
	}
	//De�i�kenlerin get set fonksiyonlar�.
	public int getTekli() {
		return tekli;
	}
	public void setTekli(int tekli) {
		this.tekli = tekli;
	}
	public int getIkili() {
		return ikili;
	}
	public void setIkili(int ikili) {
		this.ikili = ikili;
	}
	public int getIliskisel() {
		return iliskisel;
	}
	public void setIliskisel(int iliskisel) {
		this.iliskisel = iliskisel;
	}
	public int getSayisal() {
		return sayisal;
	}
	public void setSayisal(int sayisal) {
		this.sayisal = sayisal;
	}
	public int getMantiksal() {
		return mantiksal;
	}
	public void setMantiksal(int mantiksal) {
		this.mantiksal = mantiksal;
	}
	public int getOperand() {
		return operand;
	}
	public void setOperand(int operand) {
		this.operand = operand;
	}
	//De�i�kenlerin artt�rma fonksiyonlar�
	public void tekliArttir()
	{
		int adet = this.tekli;
		adet++;
		setTekli(adet);
		
		
	}
	public void ikiliArttir()
	{
		int adet = this.ikili;
		adet++;
		setIkili(adet);
		
		
	}
	public void iliskiselArttir()
	{
		int adet = this.iliskisel;
		adet++;
		setIliskisel(adet);
	}
	public void mantiksalArttir()
	{
		int adet = this.mantiksal;
		adet++;
		setMantiksal(adet);
	}
	public void operandHesapla(int notSayisi)//operand hesaplama fonksiyonu
	{
		this.operand= 2*(ikili+mantiksal+iliskisel)+tekli-notSayisi;//not'�n 1 opernad� var bu yuzden ��kart�ld�
	}
	public String toString()//nesneyi ekrana yazd�rma fonksiyonu
	{
		return("Operat�r Bilgisi:"+"\n"
	            +"        "+"Tekli Operat�r Say�s�: "+tekli+"\n"
	            +"        "+"�kili Operat�r Say�s�: "+ikili+"\n"
	            +"        "+"Say�sal Operat�r Say�s�: "+sayisal+"\n"
	            +"        "+"�li�kisel Operat�r Say�s�: "+iliskisel+"\n"
	            +"        "+"Mant�ksal Operat�r Say�s�: "+mantiksal+"\n"
	            +"Operand Bilgisi:"+"\n"
	            +"        "+"Toplam Operand Say�s�: "+operand+"\n");
	}

}

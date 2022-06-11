package dijikstra;


/*
Questa classe rappresenta un menu testuale generico a piu' voci
Si suppone che la voce per uscire sia sempre associata alla scelta 0 
e sia presentata in fondo al menu
MYLIB SERINA
*/
public class MyMenu
{

  final private static String VOCE_USCITA = "0\tEsci";
  private static String richiesta_inserimento;

  private String titolo;
  private String [] voci;

	
  public MyMenu (String titolo, String [] voci, String richiestaInserimento)
  {
	  richiesta_inserimento = richiestaInserimento;
	this.titolo = titolo;
	this.voci = voci;
  }

  public int scegli ()
  {
	stampaMenu();
	return InputDati.leggiIntero(richiesta_inserimento, 0, voci.length);	 
  }
		
  public void stampaMenu ()
  {

    for (int i=0; i<voci.length; i++)
	 {
	  System.out.println( (i+1) + "\t" + voci[i]);
	 }
    System.out.println();
	System.out.println(VOCE_USCITA);
    System.out.println();
  }
  
  public void stampaMenuScelteSuMappa ()
  {

    for (int i=0; i<voci.length; i++)
    	/*
    	 * 1) Apri inventario
    	 * 2) Vai avanti
    	 * 3) Vai indietro
    	 * 4) Vai a sinistra
    	 * 5) Vai a destra
    	 */
	 {
	  System.out.println( (i+1) + "\t" + voci[i]);
	  if (i+1> 1) {
		  if (i+1 == 2)  System.out.println( "W) \t" + voci[i]);
		  if (i+1 == 3)  System.out.println( "S) \t" + voci[i]);
		  if (i+1 == 4)  System.out.println( "A) \t" + voci[i]);
		  if (i+1 == 5)  System.out.println( "D) \t" + voci[i]);
	  }
	 }
    System.out.println();
	System.out.println(VOCE_USCITA);
    System.out.println();
  }
  
  public void stampaInventarioEScegliCosaUsare ()
  {

    for (int i=0; i<voci.length; i++)
    	/*
    	 * 1) Usa item
    	 * 0) Esci
    	 */
	 {
	  System.out.println( (i+1) + "\t" + voci[i]);
	 }
    System.out.println();
	System.out.println(VOCE_USCITA);
    System.out.println();
  }
		
}


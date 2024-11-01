import java.io.*;
import java.util.*;

public class Main
{
    public static void main (String args[])
    {
    	try
    	{
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream ("TP1_traces.txt");
            Scanner scanner = new Scanner (file);

            int numero, nbReseau;
            String ligne, type, dest, orig, bonus;
            ArrayList<String> hexa;

            char[] code = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

            // La boucle va passer toutes les lignes du .txt
    		while (scanner.hasNextLine ())
    		{
    			ligne = scanner.nextLine ();
                hexa = lignes.split();

                bonus = "";

                // On détermine le numéro de la trame en enlevant le "0" à gauche
                numero = hexa.get(1)
                if(numero.charAt(0) == '0')
                {
                    numero = numero.charAt(1);
                }

                // Puis on regarde le type de la trame
                if(hexa.get(0) == "02")
                {
                    type = "acquittement";

                    System.out.println("trame " + type + " no " + numero);
                }
                else
                {
                    if(hexa.get(0) == "00")
                    {
                        type = "balise      ";
                        dest = "TOUS";
                    }
                    else if(hexa.get(0) == "01")
                    {
                        type = "message     ";
                        bonus += "     message";

                        // Là on cherche le destinataire du message en supprimant les "0" à gauche
                        dest = hexa.get(4) + hexa.get(5);
                        while(dest.charAt(0) == '0')
                        {
                            dest = dest.substring(1) + " ";
                        }
                    }

                    // Ici nous déterminons le numéro du réseau avec les 3e et 4e blocs en décodant le nombre hexadécimal
                    nbReseau = 0;
                    for(int i = 0; i < 4; i++)
                    {
                        nbReseau += 16 ** (3 - i) * code.indexOf(hexa.get(2 + i / 2).charAt(i % 2));
                    }

                    // Enfin on détermine l'envoyeur de la trame en supprimant les "0" à gauche
                    orig = hexa.get(6) + hexa.get(7);
                    while(orig.charAt(0) == '0')
                    {
                        orig = orig.substring(1) + " ";
                    }

                    System.out.println("trame " + type + " no " + numero + " réseau no " + nbReseau + " - dest : " + dest + " - origine : " + orig + bonus);
                }
    		}
    		scanner.close ();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace ();
    	}
    }
}

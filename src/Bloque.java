import java.io.Serializable;
import java.util.Date;

// Bloque doit implementer l'interface Serializable pour que les instances Bloque peuvent etre communiquées via RMI
public class Bloque implements Serializable {
    private String hash;
    private String hashPrecedent;
    private String data;
    private long timeStamp;
    private int nonce;
    private boolean encore;

    public static String GENESIS_BLOQUE="Genesis Bloque";
    public Bloque(String hashPrecedent, String data) {
        this.hashPrecedent = hashPrecedent;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash=calculerHash();
    }


    public String calculerHash(){
        // calculer le hash des données contenu dans le bloque
        return  OutilHash.hasherEnSha(this.hashPrecedent+Long.toString(this.timeStamp)+Integer.toString(nonce) + this.data);
    }

    public void minerBlock(int difficulte) {

        String target = new String(new char[difficulte]).replace('\0', '0'); //Creer une chaine avec difficulte * "0"
        while(!hash.substring( 0, difficulte).equals(target)) {
            nonce ++;
            hash = calculerHash();
        }
        System.out.println("Bloque Resolu!!! : " + hash);
    }

    public void minerBlockaDistance(int difficulte) {

        encore=true;
        String target = new String(new char[difficulte]).replace('\0', '0'); //Creer une chaine avec difficulte * "0"
        //Tanque ce bloque n'a pas reçu un signal disant qu'un autre bloque a terminé de miner le nouveau bloque, il continue à miner
        while(encore && !hash.substring( 0, difficulte).equals(target)) {
            nonce ++;
            hash = calculerHash();
        }
        if(encore)    System.out.println("Bloque Resolu!!! : " + hash);
        else System.out.println("Lqaweha chatryn ");
    }

    // Verifie si le bloque est valide ou pas
    public boolean estBloqueValide(String hashPrecedent,int difficulte){

        if(!this.hashPrecedent.equals(hashPrecedent)) return false;
        if(!this.calculerHash().equals(hash)) return false;
        String hashTarget = new String(new char[difficulte]).replace('\0', '0');
        if(!this.hash.substring(0,difficulte).equals(hashTarget)) return false;
        return true;
    }
    public void arreterMining(){
        encore=false;
    }
    public String getHash() {
        return hash;
    }


    public String getHashPrecedent() {
        return hashPrecedent;
    }

    public String getData() {
        return data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getNonce() {
        return nonce;
    }
}

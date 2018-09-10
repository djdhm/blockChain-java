import java.security.MessageDigest;
import java.util.Date;

public class Bloque {
    private String hash;
    private String hashPrecedent;
    private String data;
    private long timeStamp;
    private int nonce;

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
        System.out.println("Bloque Mining ");
        String target = new String(new char[difficulte]).replace('\0', '0'); //Creer une chaine avec difficulte * "0"
        while(!hash.substring( 0, difficulte).equals(target)) {
            nonce ++;
            hash = calculerHash();
        }
        System.out.println("Bloque Resolu!!! : " + hash);
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

package Structure;

import java.security.MessageDigest;

public class OutilHash {


    public static String hasherEnSha(String data){
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(data.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        }catch (Exception e){
            System.out.println("Probleme de hashage");
        }

        return  null;
    }
}

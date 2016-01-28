package covellj;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Justin on 7/9/2015.
 */
public class Encrypt {
    public String blowfish(String secret, String salt) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] kbytes = salt.getBytes();
        SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(1, key);
        byte[] encoding = cipher.doFinal(secret.getBytes());
        BigInteger n = new BigInteger(encoding);
        return n.toString(16);
    }
    public String sha256(String secret) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(secret.getBytes("UTF-8"));
        byte[] encryptedBtyes = messageDigest.digest();
        String encrypted = String.format("%0" + (encryptedBtyes.length*2) + "X", new BigInteger(1, encryptedBtyes));
        return encrypted;
    }
    public String aes(String secret, String salt){
        try {
            salt = sha256(salt);

        }
        catch (Exception e){
            return e.toString();
        }
        return "";
    }
}

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypt{
    private Cipher cipher;
    private transient SecretKeySpec key;
    private transient IvParameterSpec myiv;
    
    public  AESEncrypt(byte[] password,byte[] iv) throws InvalidAlgorithmParameterException{
        try {           
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            this.key = new SecretKeySpec(enCodeFormat, "AES");
            this.myiv = new IvParameterSpec(iv);
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            //System.out.println("key="+new String(base64.encode(enCodeFormat))+"\nmessage="+new String(byteContent));
        } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
        } catch (NoSuchPaddingException e) {
                e.printStackTrace();
        }
    }
    
    public byte[] Encrypt(byte[] content) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException{
    	this.cipher.init(Cipher.ENCRYPT_MODE, this.key,this.myiv);
    	return this.cipher.doFinal(content);
    }
    
    public byte[] Decrypt(byte[] content) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException{
    	this.cipher.init(Cipher. DECRYPT_MODE, this.key,this.myiv);
        return this.cipher.doFinal(content);
    }
    

}

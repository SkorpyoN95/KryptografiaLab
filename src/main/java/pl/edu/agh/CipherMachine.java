package pl.edu.agh;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.*;

import java.security.spec.InvalidKeySpecException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;


class CipherMachine {
    private final SecretKey privateKey;
    private final Cipher cipher;
    private final IvParameterSpec vector;
    private final byte[] paramsSeed = {
            'c', 'r', 'y', 'p',
            't', 'o', 'g', 'r',
            'a', 'p', 'h', 'y',
            'w', 'o', 'r', 'd'};
    
    CipherMachine()
	throws InvalidKeySpecException, NoSuchAlgorithmException,NoSuchPaddingException
    {
        Security.addProvider(new BouncyCastleProvider());
        privateKey = SecretKeyFactory
            .getInstance("AES")
            .generateSecret(new SecretKeySpec(generateKey("tajne"), "AES"));
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        vector = new IvParameterSpec(paramsSeed);
    }


    byte[] encrypt(byte[] message)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        cipher.init(Cipher.ENCRYPT_MODE, privateKey, vector);
        return cipher.doFinal(message);
    }
    
    byte[] decrypt(byte[] message)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        cipher.init(Cipher.DECRYPT_MODE, privateKey, vector);
	    return cipher.doFinal(message);
    }

    private byte[] generateKey(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(str.getBytes());
    }
}

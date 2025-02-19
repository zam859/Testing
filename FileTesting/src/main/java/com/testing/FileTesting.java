/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.testing;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class FileTesting {

    private static final String AES
            = "AES";

    public static void main(String[] args) throws ParseException {
        String txt = "";
       /* try {
            txt = "Keterangan : - nomor Badan Hukum AHU{9365.50.10.2014 tanggal 20 November 2AM;\n" +
                       "telp 081 2-937$1 696 / CEZ{64S4009;\n" +
                       "Media sosial:\n" +
                       "a) Twitten @vorldhumarparel ;\n" +
                       "b) lnstagram: umrldhumarcare;\n" +
                       "c) Faebook WorH Human Cae.";

        // Define the regex pattern
        // (?s) enables dot-all mode, allowing . to match newlines
        String regex = "(?s)Keterangan\\s*:\\s*([\\s\\S]*?)(?=\\r?\\n\\d+\\s*Nama\\s*:|\\r?\\n\\d+\\s*Nama\\s*:|\\r?\\n\\Z)";


            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(txt);
            while (matcher.find()) {
                System.out.println("text :"+matcher.group(1).trim());
            }
        } catch (NumberFormatException e) {
            System.out.println("Error converting formatted value to BigDecimal: " + e.getMessage());
        }
        
        String a =" mas abudul ";
        System.out.println( "aa:"+a.trim());
        */
      
          String original = "{\"Country Code\":null,\"Country Name\":null,\"Result\":\"ERROR\",\"Message\":[\"Field type not match in field Country Code\",\"Field type not match in field Country Name\"],\"Broken Field\":{\"Country Name\":\"Indonesia\",\"Country Code\":\"ID\"}}";

        // Use regex to find keys and wrap them in quotes
        String json = original.
        replaceAll("(\\w+)", "\"$1\"");
        Double a = Math.abs(2.1);
        
       double value = 0.15666666; // Example value

        // Create a DecimalFormatSymbols instance to set the decimal separator
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(','); // Set decimal separator to comma
        symbols.setGroupingSeparator('.'); // Set grouping separator to dot

        // Create a DecimalFormat instance for 6 decimal places
        DecimalFormat df = new DecimalFormat("0.000000", symbols);

        // Format the number
        String formattedValue = df.format(value);
        // Print the valid JSON
        //System.out.println(formattedValue);
        String json1 ="{\"customer\":null}";
        
        JSONObject js = new JSONObject(json1);
        if(js.optJSONObject("customer") != null){
            System.out.println(" masuk tidak null");
        }else{
            System.out.println(" masuk null");
        }
        
    }

    public static Map<String, String> doubleEncrypt(String message, String absolutePath) {
        String encryptMessage = "";
        String encryptKeyRandom = "";
        Map<String, String> map = new HashMap<>();
        try {
            String keyRandom = createAESKeyString();
            encryptMessage = encrypt(message, keyRandom);
            encryptKeyRandom = getEncryptRandomKeyWithRSA(keyRandom, loadPublicKeyFromFile(absolutePath));
            map.put("message", encryptMessage);
            map.put("key", encryptKeyRandom);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    public static String encrypt(String plainText, String key) {
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance("AES");

            byte[] decoded = Base64.getDecoder().decode(key);
            SecretKeySpec aesKey = new SecretKeySpec(decoded, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            result = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String createAESKeyString() {
        String result = "";
        try {
            SecureRandom securerandom = new SecureRandom();
            KeyGenerator keygenerator = KeyGenerator.getInstance(AES);

            keygenerator.init(256, securerandom);
            SecretKey key = keygenerator.generateKey();
            result = Base64.getMimeEncoder().encodeToString(key.getEncoded());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static PublicKey loadPublicKeyFromFile(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        String publicKeyPEM = sb.toString();
        publicKeyPEM = publicKeyPEM.replace("\n", "");
        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePublic(keySpec);
    }

    public static String encryptRandomKeyWithRSA(String keyRandom, PublicKey publicKey) {
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = Base64.getMimeEncoder().encodeToString(cipher.doFinal(keyRandom.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String getEncryptRandomKeyWithRSA(String keyRandom, PublicKey publicKeyEnc) {
        String result = encryptRandomKeyWithRSA(keyRandom, publicKeyEnc);
        return result;
    }

    public static PrivateKey loadPrivateKeyFromFile(String filename) throws Exception {
        try {
            FileInputStream fis = new FileInputStream(filename);
            byte[] privateKeyBytes = new byte[fis.available()];
            fis.read(privateKeyBytes);
            fis.close();

            String privateKeyPEM = new String(privateKeyBytes);
            privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "");
            privateKeyPEM = privateKeyPEM.replace("-----END PRIVATE KEY-----", "");
            privateKeyPEM = privateKeyPEM.replaceAll("\\s+", "");
            byte[] decodedBytes = Base64.getDecoder().decode(privateKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedBytes);
            return keyFactory.generatePrivate(keySpec);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid Base64 encoding: " + e.getMessage());
            throw e;
        } catch (IOException e) {
            System.err.println("Error reading private key file: " + e.getMessage());
            throw e;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Error generating private key: " + e.getMessage());
            throw e;
        }
    }

    public static String doubleDecrypt(String message, String key, String absolutePath) {
        String result = "";
        try {
            //ini buat decode key random dulu
            Cipher decryptCipher = Cipher.getInstance("RSA");
            decryptCipher.init(Cipher.DECRYPT_MODE, loadPrivateKeyFromFile(absolutePath));
            String keys = key.replaceAll("\\s+", "");
            byte[] decoded = Base64.getDecoder().decode(keys);
            byte[] decryptedBytes = decryptCipher.doFinal(decoded);
            String keyRandom = new String(decryptedBytes);
            //
            //ini buat decrypt message pakai key random yang udah di decrypt
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, stringToSecretKey(keyRandom));
            byte[] decodedMessage = Base64.getDecoder().decode(message);
            byte[] decryptedBytesMessage = cipher.doFinal(decodedMessage);
            result = new String(decryptedBytesMessage);
            //
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static SecretKey stringToSecretKey(String keyString) {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
}

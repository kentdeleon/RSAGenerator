package generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;
import java.util.Objects;


public class RSAKeyPairGenerator {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSAKeyPairGenerator(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keySize);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        int keySize = 1024;
        if (Objects.nonNull(args) && args.length != 0) {
            keySize = Integer.parseInt(args[0]);
        }

        RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator(keySize);
        keyPairGenerator.writeToFile("RSA/publicKey", keyPairGenerator.getPublicKey().getEncoded());
        keyPairGenerator.writeToFile("RSA/privateKey", keyPairGenerator.getPrivateKey().getEncoded());
        String publicKey = String.format("public key: %s", Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
        String privateKey = String.format("private key: %s", Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded()));

        System.out.println("---------------------------------------- \n");
        System.out.println(publicKey);
        System.out.println("\n---------------------------------------- \n");
        System.out.println(privateKey);
        System.out.println("\n---------------------------------------- \n");

    }

}

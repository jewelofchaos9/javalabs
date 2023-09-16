import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.Objects;

abstract class HashSummer {
    public abstract String hexDigest(byte [] fileData);
    HashSummer() {}
}
class MD5HashSummer extends HashSummer {
    private static String digestToHex(byte[] bytes) {
        BigInteger bytesAsNumber = new BigInteger(1, bytes);
        String hexRepresentation = bytesAsNumber.toString(16);
        while (hexRepresentation.length() < 32) {
            hexRepresentation = "0" + hexRepresentation;
        }
        return hexRepresentation;
    }
    public String hexDigest(byte [] fileData) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch(Exception e) {
            return "";
        }
        byte[] digest = md.digest(fileData);
        return digestToHex(digest);
    }
    MD5HashSummer(){
        super();
    }
}

class SHA256HashSummer extends HashSummer {
    private static String digestToHex(byte[] bytes) {
        BigInteger bytesAsNumber = new BigInteger(1, bytes);
        String hexRepresentation = bytesAsNumber.toString(16);
        while (hexRepresentation.length() < 64) {
            hexRepresentation = "0" + hexRepresentation;
        }
        return hexRepresentation;
    }
    public String hexDigest(byte [] fileData) {
        MessageDigest sha = null;
        try{
            sha = MessageDigest.getInstance("SHA-256");
        } catch(Exception e) {
            return "";
        }
        byte[] digest = sha.digest(fileData);
        return digestToHex(digest);
    }
    SHA256HashSummer(){
        super();
    }
}

public class HashSummerFactory {
    public static HashSummer getInstance(String type) throws Exception {
        if (Objects.equals(type, "md5")) {
            return new MD5HashSummer();
        }
        if (Objects.equals(type, "sha256")) {
            return new SHA256HashSummer();
        }
        throw new Exception("Algorithm not supported");
    }
}
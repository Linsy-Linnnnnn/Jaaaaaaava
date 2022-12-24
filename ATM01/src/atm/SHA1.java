package atm;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
SHA(Secure Hash Algorithm，安全散列算法），数字签名等密码学应用中重要的工具，
被广泛地应用于电子商务等信息安全领域。虽然，SHA与MD5通过碰撞法都被破解了，
但是SHA仍然是公认的安全加密算法，较之MD5更为安全*/
public class SHA1 {
    //SHAPsw存放加密后的密钥，Psw存放加密前的密码。
    private String SHAPsw;
    private String Psw;

    public void setSHAPsw(String SHAPsw) {
        this.SHAPsw = SHAPsw;
    }

    public String getSHAPsw() {
        return SHAPsw;
    }

    public void setPsw(String psw) {
        Psw = psw;
    }

    public String getPsw() {
        return Psw;
    }
    //////////////////////////////////////////////////////////////

    public static final String KEY_SHA = "SHA";

    public String getResult(String inputStr) {
        BigInteger sha = null;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
            SHAPsw = sha.toString(32);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha.toString(32);
    }

    public void main() {
        try {
            String inputStr = Psw;
            getResult(inputStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



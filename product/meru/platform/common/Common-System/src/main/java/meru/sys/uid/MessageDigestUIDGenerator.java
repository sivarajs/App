package meru.sys.uid;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MessageDigestUIDGenerator implements UIDGenerator {

  private SecureRandom mSecureRandom;
  private MessageDigest mMessageDigest;

  private static char[] DIGITS = { '0',
                            '1',
                            '2',
                            '3',
                            '4',
                            '5',
                            '6',
                            '7',
                            '8',
                            '9',
                            'a',
                            'b',
                            'c',
                            'd',
                            'e',
                            'f' };
  
  public MessageDigestUIDGenerator() throws NoSuchAlgorithmException {
    mSecureRandom = SecureRandom.getInstance("SHA1PRNG");
    mMessageDigest = MessageDigest.getInstance("SHA-1");
  }

  @Override
  public String getUId() {

    String randomNum = new Integer(mSecureRandom.nextInt()).toString();
    byte[] result =  mMessageDigest.digest( randomNum.getBytes() );

    return toHex(result);
  }

  private String toHex(byte[] data) {
    StringBuilder result = new StringBuilder();
    
    for (int idx = 0; idx < data.length; ++idx) {
      byte b = data[idx];
      result.append(DIGITS[(b & 0xf0) >> 4]);
      result.append(DIGITS[b & 0x0f]);
    }
    return result.toString();
  }
}

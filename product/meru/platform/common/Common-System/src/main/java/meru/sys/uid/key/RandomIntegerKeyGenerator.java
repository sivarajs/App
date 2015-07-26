package meru.sys.uid.key;


public class RandomIntegerKeyGenerator extends RandomKeyGenerator {

  private static final char[] CHARS = {'1',
                                       '2',
                                       '3',
                                       '4',
                                       '5',
                                       '6',
                                       '7',
                                       '8',
                                       '9',
                                       '0' };

  public RandomIntegerKeyGenerator() {
   super(CHARS);
  }
  
  public RandomIntegerKeyGenerator(byte length) {
      super(CHARS, length);
     }

}

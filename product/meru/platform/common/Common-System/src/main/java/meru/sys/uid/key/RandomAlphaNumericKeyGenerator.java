package meru.sys.uid.key;

public class RandomAlphaNumericKeyGenerator extends RandomKeyGenerator {

    private static final char[] CHARS = { 'A',
                                         'B',
                                         'C',
                                         'D',
                                         'E',
                                         'F',
                                         'G',
                                         'H',
                                         'I',
                                         'J',
                                         'K',
                                         'L',
                                         'M',
                                         'N',
                                         'O',
                                         'P',
                                         'Q',
                                         'R',
                                         'S',
                                         'T',
                                         'U',
                                         'V',
                                         'W',
                                         'X',
                                         'Y',
                                         'Z',
                                         '1',
                                         '2',
                                         '3',
                                         '4',
                                         '5',
                                         '6',
                                         '7',
                                         '8',
                                         '9',
                                         '0' };

    public RandomAlphaNumericKeyGenerator() {
        super(CHARS);
    }

    public static void main(String[] args) {
        RandomAlphaNumericKeyGenerator keyGen = new RandomAlphaNumericKeyGenerator();
        for (int i = 0; i < 10; i++) {
            System.out.println(keyGen.getKey());
        }
    }
}

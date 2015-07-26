package meru.sys.uid.key;

import java.util.Random;

public abstract class RandomKeyGenerator {

    private char[] mChars;
    private byte mLength;
    private Random mRandom;

    public RandomKeyGenerator(char[] chars) {
        this(chars,
             (byte) 6);

    }

    public RandomKeyGenerator(char[] chars,
                              byte length) {
        mChars = chars;
        mLength = length;
        mRandom = new Random();
    }

    public String getKey() {

        StringBuilder strBuilder = new StringBuilder(mLength);
        for (int i = 0; i < mLength; i++) {
            int index = Math.abs(mRandom.nextInt()) % mChars.length;
            strBuilder.append(mChars[index]);
        }

        return strBuilder.toString();
    }

}

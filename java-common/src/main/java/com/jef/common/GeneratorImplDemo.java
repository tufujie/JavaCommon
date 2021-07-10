package com.jef.common;

import com.jef.util.Generator;

/**
 * @author Jef
 * @date 20180728
 */
public class GeneratorImplDemo implements Generator<String>  {
    private static String[] stringArray = {"hello", "world"};
    private int index;
    public static int arrayLength = stringArray.length;
    @Override
    public String next() {
        return stringArray[index++];
    }
}

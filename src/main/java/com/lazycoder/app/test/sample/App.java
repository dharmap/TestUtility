package com.lazycoder.app.test.sample;

import com.lazycoder.app.test.utility.TestUtility;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println(TestUtility.loadFile("person.json"));
    }
}

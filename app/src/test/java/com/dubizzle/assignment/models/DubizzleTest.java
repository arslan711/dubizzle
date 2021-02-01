package com.dubizzle.assignment.models;

import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DubizzleTest {

    public static final String TIMESTAMP_1 = "2019-02-23 07:56:26";
    public static final String TIMESTAMP_2 = "2019-02-23 08:56:26";

    public String[] images = {"http://www.google.com"};


    @Test
    void isDubizzlesEqual_identicalProperties_returnTrue() {

        Dubizzle dubizzle = new Dubizzle("1","Coat", "AED 5", TIMESTAMP_1, Arrays.asList(images));
        Dubizzle dubizzleIdentitcal = new Dubizzle("1", "Coat","AED 5", TIMESTAMP_1, Arrays.asList(images));

        assertEquals(dubizzle, dubizzleIdentitcal);
    }



    @Test
    void isDubizzlesEqual_differentIds_returnFalse() {

        Dubizzle dubizzle = new Dubizzle("1","Coat", "AED 5", TIMESTAMP_1,Arrays.asList(images));
        Dubizzle dubizzleNotIdentitcal = new Dubizzle("2", "Coat","AED 5", TIMESTAMP_1,Arrays.asList(images));

        assertNotEquals(dubizzle, dubizzleNotIdentitcal);
    }



    @Test
    void isDubizzlesEqual_differentTimestamps_returnFalse() {

        Dubizzle dubizzle = new Dubizzle("1","Coat", "AED 5", TIMESTAMP_1,Arrays.asList(images));
        Dubizzle dubizzleNotIdentitcal = new Dubizzle("2", "Coat","AED 5", TIMESTAMP_2,Arrays.asList(images));

        assertNotEquals(dubizzle, dubizzleNotIdentitcal);
    }


    @Test
    void isDubizzleEqual_differentTitle_returnFalse() {

        Dubizzle dubizzle = new Dubizzle("1","Coat", "AED 5", TIMESTAMP_1,Arrays.asList(images));
        Dubizzle dubizzleNotIdentitcal = new Dubizzle("2", "Shirt","AED 5", TIMESTAMP_1,Arrays.asList(images));



        // Assert
        assertNotEquals(dubizzle, dubizzleNotIdentitcal);
    }



}



















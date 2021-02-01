package com.dubizzle.assignment.util;

import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtil {

    public static final String TIMESTAMP_1 = "2019-02-23 07:56:26";
    public static final Dubizzle TEST_DUBIZZLE_1 = new Dubizzle("11",
            "Glasses","AED 10", TIMESTAMP_1,new ArrayList<>());

    public static final String TIMESTAMP_2 = "2019-02-24 07:56:26";
    public static final Dubizzle TEST_DUBIZZLE_2 = new Dubizzle("12",
            "Car","AED 1000", TIMESTAMP_1,new ArrayList<>());
    public static final List<Dubizzle> DUBIZZLE_LIST = Collections.unmodifiableList(
            new ArrayList<Dubizzle>(){{
                add( new Dubizzle("11",
                        "Glasses","AED 10", TIMESTAMP_1,new ArrayList<>()));
                add(new Dubizzle("12",
                        "Car","AED 1000", TIMESTAMP_1,new ArrayList<>()));
            }}
    );
}

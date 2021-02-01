package com.dubizzle.assignment.util;

import com.dubizzle.assignment.utils.auxilary.DateUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class DateUtilTest {

    private static final String yesterday = "1 day ago";
    @Test
    public void testGetYesterdayTimeShouldReturn1DayAgo(){
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                assertEquals(yesterday, DateUtil.INSTANCE.timeSince("2021-01-31 00:00:00.000"));
                System.out.println("Timestamp is generated correctly");
            }
        });
    }

}














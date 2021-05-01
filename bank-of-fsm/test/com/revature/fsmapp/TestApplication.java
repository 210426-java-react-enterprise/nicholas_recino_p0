package com.revature.fsmapp;

import com.revature.fsmapp.util.LinkedListTest;

public class TestApplication {
    public static void main(String[] args) {
        LinkedListTest tester = new LinkedListTest();
        tester.test_add_withNonNullValue();
        tester.test_add_withNull();
    }
}

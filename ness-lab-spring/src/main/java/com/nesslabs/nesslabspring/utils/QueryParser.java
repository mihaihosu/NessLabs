package com.nesslabs.nesslabspring.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryParser {

    public static List<String> parse(String query) {
        return new ArrayList<>(Arrays.asList(query.split(",")));
    }

}

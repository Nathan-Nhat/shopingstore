package com.ttnhat.shop.Sercurity.Entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListRoles {
    private static List<String> roles = new LinkedList<String>(Arrays.asList("ADMIN", "CUSTOMER"));

    public static List<String> getAllRoles(){
        return roles;
    }
}

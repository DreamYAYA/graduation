package com.lql.graduation.util;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.UUID;

public class UidUtils {



public static String getUid(){

    return UUID.randomUUID().toString().replace("-","");
}

public static void main(String args[]){

    System.out.println(getUid().replace("-",""));
}




}

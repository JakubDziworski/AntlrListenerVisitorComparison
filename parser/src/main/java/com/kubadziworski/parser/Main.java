package com.kubadziworski.parser;

import com.google.gson.Gson;
import com.kubadziworski.parser.domain.Class;
import com.kubadziworski.parser.parser.VisitorOrientedParser;

/**
 * Created by jdziworski on 30.03.16.
 */
public class Main {
    private static final String someLangSourceCode =
            "class SomeClass {\n"+
                    "    fun1 {\n"+
                    "        instruction11\n"+
                    "        instruction12\n"+
                    "    }\n"+
                    "    fun2 {\n"+
                    "        instruction21\n"+
                    "        instruction22\n"+
                    "    }\n"+
                    "}";

    public static void main(String[] args) {
        final Class result = new VisitorOrientedParser().parse(someLangSourceCode);
        Gson gson = new Gson();
        final String json = gson.toJson(result);
        System.out.printf("code below: %n '%s' %n has been parsed to object: %n '%s'%n",someLangSourceCode,json);
    }
}

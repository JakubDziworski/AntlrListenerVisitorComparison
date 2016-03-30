package com.kubadziworski.parser.domain;

import java.util.Collection;

/**
 * Created by jdziworski on 30.03.16.
 */
public class Class {
    private String name;
    private Collection<Method> methods;

    public Class(String name, Collection<Method> methods) {
        this.name = name;
        this.methods = methods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Class aClass = (Class) o;

        if (name != null ? !name.equals(aClass.name) : aClass.name != null) return false;
        return methods != null ? methods.equals(aClass.methods) : aClass.methods == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (methods != null ? methods.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Class{" +
                "\nname='" + name + '\'' +
                "\nmethods=" + methods +
                '}';
    }
}

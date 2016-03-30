package com.kubadziworski.parser.domain;

import java.util.Collection;

/**
 * Created by jdziworski on 30.03.16.
 */
public class Method {
    private String name;
    private Collection<Instruction> instructions;

    public Method(String name, Collection<Instruction> instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method method = (Method) o;

        if (name != null ? !name.equals(method.name) : method.name != null) return false;
        return instructions != null ? instructions.equals(method.instructions) : method.instructions == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (instructions != null ? instructions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Method{" +
                "\nname='" + name + '\'' +
                "\ninstructions=" + instructions +
                '}';
    }
}

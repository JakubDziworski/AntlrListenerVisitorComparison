package com.kubadziworski.parser.domain;

/**
 * Created by jdziworski on 30.03.16.
 */
public class Instruction {
    private String name;

    public Instruction(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instruction that = (Instruction) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "\nname='" + name + '\'' +
                '}';
    }
}

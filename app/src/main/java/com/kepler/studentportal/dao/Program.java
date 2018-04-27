package com.kepler.studentportal.dao;

public class Program {
    private int id;
    private String program;
    private int program_category_id;

    @Override
    public String toString() {
        return program;
    }

    public Program(int id, String program, int program_category_id) {
        this.id = id;
        this.program = program;
        this.program_category_id = program_category_id;
    }

    public int getId() {
        return id;
    }
}

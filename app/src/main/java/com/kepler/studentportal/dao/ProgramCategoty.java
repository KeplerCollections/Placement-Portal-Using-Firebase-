package com.kepler.studentportal.dao;

public class ProgramCategoty {
    private int id;
    private String program;
    private int is_technical;

    @Override
    public String toString() {
        return program;
    }

    public ProgramCategoty(int id, String program, int is_technical) {
        this.id = id;
        this.program = program;
        this.is_technical = is_technical;
    }

    public int getId() {
        return id;
    }
}

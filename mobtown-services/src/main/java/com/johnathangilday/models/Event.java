package com.johnathangilday.models;

import java.time.LocalDateTime;
import java.util.List;

public class Event {

    public String ID;
    public String name;
    public String type;
    public LocalDateTime end;
    public LocalDateTime start;
    public List<Citation> citations;
}

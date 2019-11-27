package com.ym.ssm.chapter2.reflect;

public class BuilderDemo {
    public static void main(String[] args) {
        TicketHelper helper = new TicketHelper();
        helper.buildAdult("成人票");
        helper.buildChildrenNoSeat("有座儿童");
        helper.buildChildrenNoSeat("无座儿童");
        helper.buildElderly("老人票");
        helper.buildSoldier("军人票");
        Object ticket = TicketBuilder.builder(helper);
    }
}

package com.ym.ssm.chapter2.reflect;

import java.util.Observable;

public class ObserverTest {
    public static void main(String[] args) {
        ProductList instance = ProductList.getInstance();
        TaoBaoObserver taoBaoObserver = new TaoBaoObserver();
        JingDongObserver jingDongObserver = new JingDongObserver();
        instance.addObserver(taoBaoObserver);
        instance.addObserver(jingDongObserver);
        instance.addProduct("新增产品1");
    }
}

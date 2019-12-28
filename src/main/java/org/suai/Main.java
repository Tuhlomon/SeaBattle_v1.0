package org.suai;

import org.suai.view.GUI;
import org.suai.view.MusicThread;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        MusicThread mt = new MusicThread();
        mt.start();
        GUI start = new GUI();
    }
}
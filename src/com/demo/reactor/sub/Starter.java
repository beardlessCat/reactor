package com.demo.reactor.sub;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        new Thread(new Reactor(2333)).start();
    }
}

package br.com.ciceroednilson;

import io.helidon.microprofile.server.Server;

public final class Main {

    private Main() { }

    public static void main(final String[] args) {
        Server server = startServer();
        System.out.println("http://localhost:" + server.port() + "/order");
    }

    static Server startServer() {
        return Server.create().start();
    }

}

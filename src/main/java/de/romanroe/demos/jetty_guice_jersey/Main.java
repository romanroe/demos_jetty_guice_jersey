package de.romanroe.demos.jetty_guice_jersey;

import de.romanroe.demos.jetty_guice_jersey.guice.InitializeGuiceModulesContextListener;
import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {
    
    private static Server server;
    
    public static void main(String[] args) throws Exception {
        new Main().run();
    }
    
    private void run() throws Exception{
        createServer();
        bindGuiceContextToServer();
        startServer();
        waitForServerToFinnish();
    }

    private void bindGuiceContextToServer() {
        ServletContextHandler context = createRootContext();
        serveGuiceContext(context);
    }

    private void serveGuiceContext(ServletContextHandler context) {
        bindGuiceContextAndFilter(context);
        addDefaultServletToContext(context);
    }

    private void addDefaultServletToContext(ServletContextHandler context) {
        context.addServlet(DefaultServlet.class, "/");
    }

    private void bindGuiceContextAndFilter(ServletContextHandler context) {
        context.addEventListener(new InitializeGuiceModulesContextListener());
        context.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
    }

    private ServletContextHandler createRootContext() {
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("");
        server.setHandler(context);
        return context;
    }

    private void waitForServerToFinnish() throws InterruptedException {
        server.join();
    }

    private void startServer() throws Exception {
        server.start();
    }

    private void createServer() {
        server = new Server(8080);
    }
}

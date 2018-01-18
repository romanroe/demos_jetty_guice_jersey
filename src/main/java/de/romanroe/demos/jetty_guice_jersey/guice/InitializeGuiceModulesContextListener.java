package de.romanroe.demos.jetty_guice_jersey.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class InitializeGuiceModulesContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ApplicationModule());
    }
}

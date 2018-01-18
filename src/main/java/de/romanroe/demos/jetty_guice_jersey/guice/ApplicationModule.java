package de.romanroe.demos.jetty_guice_jersey.guice;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import de.romanroe.demos.jetty_guice_jersey.Main;
import de.romanroe.demos.jetty_guice_jersey.service.ValueService;

import java.util.Set;

public class ApplicationModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bindResources();
        serveBoundResources();

        bind(ValueService.class).asEagerSingleton();
    }

    private void bindResources() {
        for (Class<?> resource : lookupResources()) {
            bind(resource);
        }
    }

    private Set<Class<?>> lookupResources() {
        PackagesResourceConfig resourceConfig = new PackagesResourceConfig(Main.class.getPackage().getName());
        return resourceConfig.getClasses();
    }

    private void serveBoundResources() {
        serve("/*").with(GuiceContainer.class);
    }
}

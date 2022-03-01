package com.jamaya.applaudo;

import com.jamaya.applaudo.beans.ItemEJB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@SpringBootApplication
public class ApplaudoApplication {

    @Bean
    public Context context() throws NamingException {
        Properties jndiProps = new Properties();
        jndiProps.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put("jboss.naming.client.ejb.context", true);
        jndiProps.put("java.naming.provider.url", "http-remoting://localhost:8080");
        return new InitialContext(jndiProps);
    }

    @Bean
    public ItemEJB itemService(Context context) throws NamingException {
        return (ItemEJB) context.lookup(this.getFullName(ItemEJB.class));
    }

    @SuppressWarnings("rawtypes")
    private String getFullName(Class classType) {
        return "applaudo-be-1.0-SNAPSHOT/ItemEJBImpl!com.jamaya.applaudo.beans.ItemEJB";
    }
    public static void main(String[] args) {
        SpringApplication.run(ApplaudoApplication.class, args);
    }

}

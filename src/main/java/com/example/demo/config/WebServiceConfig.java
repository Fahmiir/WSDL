package com.example.demo.config;

import com.example.demo.service.CalculatorEndpoint;
import com.example.demo.service.SubEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.mapping.PayloadRootAnnotationMethodEndpointMapping;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.context.ApplicationContext;
//
//@Autowired
//ApplicationContext applicationContext;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

//    @Bean
//    public MessageDispatcherServlet messageDispatcherServlet() {
//        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//        servlet.setTransformWsdlLocations(true);
//        return servlet;
//    }



    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);  // Pastikan WSDL dapat dihasilkan
        servlet.setApplicationContext(applicationContext);
        ServletRegistrationBean<MessageDispatcherServlet> registrationBean = new ServletRegistrationBean<>(servlet, "/ws/*");
        registrationBean.setName("messageDispatcherServlet");
        return registrationBean;
    }

    // Endpoint untuk menangani request SOAP
    @Bean
    public PayloadRootAnnotationMethodEndpointMapping endpointMapping() {
        return new PayloadRootAnnotationMethodEndpointMapping();
    }


    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
 //       marshaller.setPackagesToScan("com.example.demo.dto");
        marshaller.setContextPath("com.example.demo.dto");
        return marshaller;
    }

    @Bean
    public CalculatorEndpoint calculatorEndpoint() {
        CalculatorEndpoint endpoint = new CalculatorEndpoint();
        return endpoint;
    }

    @Bean
    public SubEndpoint subEndpoint() {
        return new SubEndpoint();
    }

    @Bean
    public XsdSchema sumSchema() {
        return new SimpleXsdSchema(new org.springframework.core.io.ClassPathResource("sum.xsd"));
    }

    @Bean(name = "sumService")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema sumSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SumPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://example.com/soapws");
        wsdl11Definition.setSchema(sumSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema subtractionSchema() {
        return new SimpleXsdSchema(new org.springframework.core.io.ClassPathResource("sub.xsd"));
    }

    @Bean(name = "subService")
    public DefaultWsdl11Definition subServiceWsdl(XsdSchema subtractionSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("SubPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("http://example.com/calculator2");
        wsdl.setSchema(subtractionSchema);
        return wsdl;
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet2(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean<>(servlet, "/ws2/*");
    }

}

package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import preprocessor.PreProcessor;
import preprocessor.PreProcessorToUpperImpl;
import printer.Printer;
import printer.PrinterWithPrefixImpl;
import render.Render;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean(PrinterWithPrefixImpl.class);
        printer.print("Hello!");
    }
}

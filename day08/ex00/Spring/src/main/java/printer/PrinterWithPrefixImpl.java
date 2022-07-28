package printer;

import render.Render;

public class PrinterWithPrefixImpl implements Printer <String>{
    private Render render;
    private String prefix;

    public PrinterWithPrefixImpl(Render render) {
        this.render = render;
        prefix = "Prefix";
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void print(String prefix){
        render.printPrefix(this.prefix + " " + prefix);
    }
}

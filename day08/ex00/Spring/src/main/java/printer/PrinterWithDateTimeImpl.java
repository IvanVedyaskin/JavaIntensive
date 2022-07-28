package printer;

import render.Render;

import java.time.LocalDate;

public class PrinterWithDateTimeImpl implements Printer <LocalDate>{
    private Render render;

    public PrinterWithDateTimeImpl(Render render) {
        this.render = render;
    }

    @Override
    public void print(LocalDate ld) {
        render.printDate(ld);
    }

}

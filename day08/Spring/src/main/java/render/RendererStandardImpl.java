package render;

import preprocessor.PreProcessor;

import java.time.LocalDate;

public class RendererStandardImpl implements Render{
    private PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void printPrefix(String prefix) {
        System.out.println(preProcessor.process(prefix));
    }

    @Override
    public void printDate(LocalDate ld) {
        System.out.println(ld);
    }
}

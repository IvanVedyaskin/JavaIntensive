package render;

import preprocessor.PreProcessor;

import java.time.LocalDate;

public class RendererErrImpl implements Render{
    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void printPrefix(String prefix) {
        System.err.println(preProcessor.process(prefix));
    }

    @Override
    public void printDate(LocalDate ld) {
        System.err.println(ld);
    }
}

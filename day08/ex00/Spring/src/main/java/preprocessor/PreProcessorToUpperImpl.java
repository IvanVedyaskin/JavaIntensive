package preprocessor;

import java.util.Locale;

public class PreProcessorToUpperImpl implements PreProcessor{
    public PreProcessorToUpperImpl() {
    }

    @Override
    public String process(String prefix) {
        return prefix.toUpperCase(Locale.ROOT);
    }
}

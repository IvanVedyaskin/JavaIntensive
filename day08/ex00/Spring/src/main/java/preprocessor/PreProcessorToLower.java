package preprocessor;

import java.util.Locale;

public class PreProcessorToLower implements PreProcessor{
    public PreProcessorToLower() {
    }

    @Override
    public String process(String prefix) {
        return prefix.toLowerCase(Locale.ROOT);
    }
}

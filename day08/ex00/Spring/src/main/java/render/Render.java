package render;

import java.time.LocalDate;

public interface Render {
    void printPrefix(String prefix);
    void printDate(LocalDate ld);
}

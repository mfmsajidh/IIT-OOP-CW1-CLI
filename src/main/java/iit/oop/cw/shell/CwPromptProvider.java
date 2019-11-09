package iit.oop.cw.shell;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CwPromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("RENTAL-SYSTEM:>",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE)
        );
    }
}

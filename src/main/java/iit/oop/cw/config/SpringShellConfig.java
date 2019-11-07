package iit.oop.cw.config;

import iit.oop.cw.shell.InputReader;
import iit.oop.cw.enums.PromptColor;
import iit.oop.cw.shell.ShellHelper;
import org.jline.reader.History;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.Parser;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.jline.JLineShellAutoConfiguration;

@Configuration
public class SpringShellConfig {

    @Bean
    public ShellHelper shellHelper(@Lazy Terminal terminal) {
        return new ShellHelper(terminal);
    }

    @Bean
    public InputReader inputReader(
            @Lazy Terminal terminal,
            @Lazy Parser parser,
            JLineShellAutoConfiguration.CompleterAdapter completerAdapter,
            @Lazy History history,
            ShellHelper shellHelper) {
        LineReaderBuilder lineReaderBuilder = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(completerAdapter)
                .history(history)
                .highlighter(
                        (LineReader lineReader, String buffer) -> new AttributedString(
                                buffer, AttributedStyle.BOLD.foreground(PromptColor.WHITE.toJlineAttributedStyle())
                        )
                ).parser(parser);

        LineReader lineReader = lineReaderBuilder.build();
        lineReader.unsetOpt(LineReader.Option.INSERT_TAB);
        return new InputReader(lineReader, shellHelper);
    }
}

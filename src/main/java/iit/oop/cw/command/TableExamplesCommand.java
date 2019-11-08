package iit.oop.cw.command;

import iit.oop.cw.shell.ShellHelper;
import iit.oop.cw.util.LocalDateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.*;

import java.time.LocalDate;

@ShellComponent
public class TableExamplesCommand {

    @Autowired
    ShellHelper shellHelper;

    private String[] CONTINENTS = {"Europe", "North America", "South America", "Africa", "Asia", "Australia and Oceania"};
    private String[] COUNTRIES1 = {"Germany", "USA", "Brasil", "Nigeria", "China", "Australia"};
    private String[] COUNTRIES2 = {"France", "Canada", "Argentina", "Egypt", "India", "New Zealand"};

    @ShellMethod("Display sample tables")
    public void sampleTables() {

        Object[][] sampleData = new String[][] {
                CONTINENTS,
                COUNTRIES1,
                COUNTRIES2
        };

        TableModel model = new ArrayTableModel(sampleData);
        TableBuilder tableBuilder = new TableBuilder(model);

        shellHelper.printInfo("Air border style");
        tableBuilder.addFullBorder(BorderStyle.air);
        shellHelper.print(tableBuilder.build().render(80));

        shellHelper.printInfo("oldschool border style");
        tableBuilder.addFullBorder(BorderStyle.oldschool);
        shellHelper.print(tableBuilder.build().render(80));

        shellHelper.printInfo("fancy_light border style");
        tableBuilder.addFullBorder(BorderStyle.fancy_light);
        shellHelper.print(tableBuilder.build().render(80));

        shellHelper.printInfo("fancy_double border style");
        tableBuilder.addFullBorder(BorderStyle.fancy_double);
        shellHelper.print(tableBuilder.build().render(80));

        shellHelper.printInfo("mixed border style");
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        shellHelper.print(tableBuilder.build().render(80));

        shellHelper.printInfo("different border for the header row");
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        shellHelper.print(tableBuilder.build().render(80));

    }

    @ShellMethod("Table formatter demo")
    public void tableFormatterDemo() {

        LocalDateFormatter dateFormatter = new LocalDateFormatter("dd.MM.YYYY");

        LocalDate now = LocalDate.now();
        Object[][] sampleData = new Object[][] {
                {"Date", "Value"},
                {"Today", now},
                {"Today minus 1", now.minusDays(1)},
                {"Today minus 2", now.minusDays(2)},
                {"Today minus 3", now.minusDays(3)}
        };

        TableModel model = new ArrayTableModel(sampleData);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.on(CellMatchers.ofType(LocalDate.class)).addFormatter(dateFormatter);
        tableBuilder.addFullBorder(BorderStyle.fancy_light);
        shellHelper.print(tableBuilder.build().render(30));
    }

}

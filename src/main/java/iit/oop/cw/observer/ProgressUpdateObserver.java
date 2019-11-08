package iit.oop.cw.observer;

import iit.oop.cw.shell.ProgressBar;
import iit.oop.cw.shell.ShellHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Observable;
import java.util.Observer;

@Service
public class ProgressUpdateObserver implements Observer {

    @Autowired
    private ProgressBar progressBar;
    @Autowired
    private ShellHelper shellHelper;

    @Override
    public void update(Observable observable, Object event) {
        ProgressUpdateEvent upe = (ProgressUpdateEvent) event;
        int currentRecord = upe.getCurrentCount().intValue();
        int totalRecords = upe.getTotalCount().intValue();

        if (currentRecord == 0) {
            // Just in case the previous progress bar was interrupted
            progressBar.reset();
        }

        String message = null;
        int percentage = currentRecord * 100 / totalRecords;
        if (StringUtils.hasText(upe.getMessage())) {
            message = shellHelper.getWarningMessage(upe.getMessage());
            progressBar.display(percentage, message);
        }

        progressBar.display(percentage, message);
        if (percentage == 100) {
            progressBar.reset();
        }
    }
}

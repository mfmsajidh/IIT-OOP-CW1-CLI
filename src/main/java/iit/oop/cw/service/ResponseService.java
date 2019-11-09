package iit.oop.cw.service;

import iit.oop.cw.constant.ResponseConstant;
import iit.oop.cw.shell.ShellHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    @Autowired
    private ShellHelper shellHelper;

    private String getStatusType (int statusCode) {
        String statusType;
        switch (statusCode) {
            case 1:
                statusType = ResponseConstant.SUCCESS_TYPE;
                break;
            case 2:
                statusType = ResponseConstant.INFO_TYPE;
                break;
            case 3:
                statusType = ResponseConstant.WARNING_TYPE;
                break;
            case 4:
                statusType = ResponseConstant.ERROR_TYPE;
                break;
            default:
                statusType = ResponseConstant.NO_RESPONSE_TYPE;
        }
        return statusType;
    }

    public void respond(int statusCode, String statusMessage) {
        String statusType = getStatusType(statusCode);
        switch (statusCode) {
            case 1:
                shellHelper.printSuccess(String.format("%s: %s", statusType, statusMessage));
                break;
            case 2:
                shellHelper.printInfo(String.format("%s: %s", statusType, statusMessage));
                break;
            case 3:
                shellHelper.printWarning(String.format("%s: %s", statusType, statusMessage));
                break;
            case 4:
                shellHelper.printError(String.format("%s: %s", statusType, statusMessage));
                break;
            default:
                shellHelper.print(String.format("%s: %s", statusType, statusMessage));
        }
    }

}

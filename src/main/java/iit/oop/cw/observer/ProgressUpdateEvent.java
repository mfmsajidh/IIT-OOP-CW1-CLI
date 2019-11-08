package iit.oop.cw.observer;

public class ProgressUpdateEvent {

    private Long currentCount;
    private Long totalCount;
    private String message;

    public ProgressUpdateEvent(Long currentRecord, Long totalRecords) {
        this(currentRecord, totalRecords, null);
    }

    public ProgressUpdateEvent(Long currentRecord, Long totalRecords, String message) {
        this.currentCount = currentRecord;
        this.totalCount = totalRecords;
        this.message = message;
    }

    public Long getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Long currentCount) {
        this.currentCount = currentCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

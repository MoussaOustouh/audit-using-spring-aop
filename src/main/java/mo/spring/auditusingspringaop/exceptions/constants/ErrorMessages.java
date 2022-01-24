package mo.spring.auditusingspringaop.exceptions.constants;

public enum ErrorMessages {
    NO_RECORD_FOUND("No record found."),
    RECORD_ALREADY_EXISTS("Record already exists."),
    INTERNAL_SERVER_ERROR("Internal server error."),
    MISSING_REQUIRED_FIELD("Missing required field.");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

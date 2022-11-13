package lotto.domain;

public enum ErrorMessage {
    AMOUNT_ERROR("[ERROR] 금액은 1,000의 배수여야 합니다."),
    LOTTO_NUMBER_ERROR("[ERROR] 올바르지 않은 입력입니다."),
    OUT_OF_SIZE("[ERROR] 로또의 길이는 6이여야 합니다.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

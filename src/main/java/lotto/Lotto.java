package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = new ArrayList<>();
        isValidLotto(numbers);
    }

    public Lotto(String num) {
        numbers = new ArrayList<>();
        userLottoNumbers(num);
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }


    public void isValidLotto(List<Integer> numbers){
        if(!isDuplicatedNumberAndInRange(numbers)){
            throw new IllegalArgumentException(Message.OUT_OF_SIZE.getMessage());
        }
    }

    public void userLottoNumbers(String num) {
        if (!isValidUserLottoNumber(num)) {
            throw new IllegalArgumentException(Message.LOTTO_NUMBER_ERROR.getMessage());
        }
    }

    private boolean isValidUserLottoNumber(String numbers) {
        String[] numberSplit = splitUserInput(numbers);
        isOnlyNumber(numberSplit);

        List<Integer> onlyNumbers = StringArrayToIntegerList(numberSplit);

        return isDuplicatedNumberAndInRange(onlyNumbers);
    }

    private boolean isDuplicatedNumberAndInRange(List<Integer> onlyNumbers){
        for (int number : onlyNumbers) {
            int num = isNumberAndInRange(number);
            isDuplicated(num);
        }
        return isMaxSize();
    }

    private List<Integer> StringArrayToIntegerList(String[] stringArray) {
        int[] StringArrayToIntArray = Arrays.stream(stringArray)
                .mapToInt(Integer::valueOf)
                .toArray();
        return Arrays.stream(StringArrayToIntArray)
                .boxed()
                .collect(Collectors.toList());
    }

    private void isOnlyNumber(String[] numberSplit) {
        for (String number : numberSplit) {
            isNumber(number);
            isNumberAndInRange(Integer.parseInt(number));
        }
    }

    private String[] splitUserInput(String numbers) {
        numbers = numbers.replaceAll(" ", "");
        return numbers.split(",");
    }

    public int isNumberAndInRange(int number) {
        if (!isInNumberRange(number)) {
            throw new IllegalArgumentException(Message.LOTTO_NUMBER_ERROR.getMessage());
        }
        return number;
    }

    private void isNumber(String number) {
        String regex = "[0-9]*$";
        if (!number.matches(regex)) {
            throw new IllegalArgumentException(Message.LOTTO_NUMBER_ERROR.getMessage());
        }
    }

    private void isDuplicated(int number) {
        if (numbers.contains(number)) {
            throw new IllegalArgumentException(Message.LOTTO_NUMBER_ERROR.getMessage());
        }
        numbers.add(number);
    }

    private boolean isInNumberRange(int number) {
        return (number >= LottoValues.START_LOTTO_VALUE.getValue()
                && number <= LottoValues.MAX_LOTTO_VALUE.getValue()
        );
    }

    private boolean isMaxSize() {
        return (this.numbers.size() == 6);
    }
}

package data;

public enum ActionsDataEnum {
    ADD, LIST, EXIT, SEARCH, UPDATE;

    public static ActionsDataEnum toEnumActionsFunc(String userInputData) {

        userInputData = userInputData.toUpperCase().trim();
        return ActionsDataEnum.valueOf(userInputData);
    }
}

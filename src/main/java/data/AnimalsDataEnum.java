package data;

public enum AnimalsDataEnum {
    CAT, DOG, DUCK;
    public static AnimalsDataEnum toEnumAnimalsData(String userAnimalInputData) {

        userAnimalInputData = userAnimalInputData.toUpperCase().trim();
        return AnimalsDataEnum.valueOf(userAnimalInputData);

    }
}

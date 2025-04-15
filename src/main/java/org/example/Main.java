package org.example;

import animals.Animal;
import data.ActionsDataEnum;
import data.AnimalsDataEnum;
import tables.AnimalTable;
import tools.AnimalsFactory;
import tools.IsNumericCheck;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws SQLException, IOException {
        AnimalTable table = new AnimalTable();

        while (true) {
            ActionsDataEnum inputData = null;
            while (inputData == null) {
                System.out.println("Введите команду " + Arrays.toString(ActionsDataEnum.values()));
                String userData = scanner.nextLine();
                try {
                    inputData = ActionsDataEnum.toEnumActionsFunc(userData);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Введена неверная команда, попробуйте снова");
                }
            }
            switch (inputData) {
                case ADD:

                    Animal animal = getAnimal();

                    table.saveQuery(animal);

                    break;

                case LIST:

                    table.findAll().forEach(System.out::println);

                    break;

                case SEARCH:
                    AnimalsDataEnum inputEnum = null;
                    while (inputEnum == null) {
                        try {
                            System.out.println("Введите тип животного для поиска: ");
                            String userInputData = scanner.nextLine();
                            if (userInputData.equals("q")){
                                break;
                            }
                            inputEnum = AnimalsDataEnum.toEnumAnimalsData(userInputData);
                            table.findByType(inputEnum).forEach(System.out::println);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Введена неверная команда, попробуйте снова");
                        }
                    }
                    break;

                case UPDATE:

                    List<Animal> animals = table.findAll();
                    List<Long> ids = new ArrayList<>();
                    for (Animal a: animals){
                        ids.add(a.getId());
                    }
                    System.out.println("Введите id животного для редактирования: ");
                    long inputId = 0L;
                    while (!ids.contains(inputId)){
                        try {
                            inputId = Long.parseLong(scanner.nextLine());
                        }
                        catch (NumberFormatException e){
                            System.out.println("Некорректный тип данных ");
                        }
                        if (!ids.contains(inputId)){
                            System.out.println("Передан несущестивующий id ");
                        }
                    }
                    Animal animalForUpdate = table.findById(inputId);
                    System.out.println(animalForUpdate);


                    System.out.println("Введите новое имя: ");
                    String newName = scanner.nextLine();
                    if (newName != null && !newName.isEmpty()) {
                        animalForUpdate.setName(newName);
                    }

                    while (true) {
                        System.out.println("Введите новый возраст: ");
                        String newAge = scanner.nextLine();
                        if (newAge != null && !newAge.isEmpty() && IsNumericCheck.isNumeric(newAge)) {
                            animalForUpdate.setAge(Integer.parseInt(newAge));
                            break;
                        }
                    }

                    while (true) {
                        System.out.println("Введите новый вес: ");
                        String newWeight = scanner.nextLine();
                        if (newWeight != null && !newWeight.isEmpty() && IsNumericCheck.isNumeric(newWeight)) {
                            animalForUpdate.setWeight(Integer.parseInt(newWeight));
                            break;
                        }
                    }


                    System.out.println("Введите новый цвет: ");
                    String newColor = scanner.nextLine();
                    if (newColor != null && !newColor.isEmpty()) {
                        animalForUpdate.setColor(newColor);
                    }

                    table.updateQuery(animalForUpdate);
                    System.out.println("Данные успешно обновлены ");

                    break;


                case EXIT:
                    System.exit(0);
            }
        }
    }


    private static Animal getAnimal() {
        AnimalsDataEnum animalType = null;
        while (animalType == null) {
            System.out.println("Введите тип животного, которое хотите добавить " + Arrays.toString(AnimalsDataEnum.values()));
            String scannerUserData = scanner.nextLine();
            try {
                animalType = AnimalsDataEnum.toEnumAnimalsData(scannerUserData);
            } catch (IllegalArgumentException ex) {
                System.out.println("Невозможно создать животное такого типа, повторите попытку!");
            }

        }

        System.out.println("Введите имя: ");
        String name = scanner.nextLine();

        int age;
        while (true) {
            System.out.println("Введите возраст: ");
            String ageStr = scanner.nextLine();
            if (IsNumericCheck.isNumeric(ageStr)) {
                age = Integer.parseInt(ageStr);
                if (age > 0) {
                    break;
                }
            }
            System.out.println("Неподдерживаемый тип данных");
        }
        int weight;
        while (true) {
            System.out.println("Введите вес: ");
            String weightStr = scanner.nextLine();
            if (IsNumericCheck.isNumeric(weightStr)) {
                weight = Integer.parseInt(weightStr);
                if (weight > 0) {
                    break;
                }
            }
            System.out.println("Неподдерживаемый тип данных");
        }

        System.out.println("Введите цвет: ");
        String color = scanner.nextLine();


        return AnimalsFactory.animalsFactory(null, animalType, name, age, weight, color);

    }
}
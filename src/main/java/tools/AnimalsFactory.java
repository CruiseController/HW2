package tools;

import animals.Animal;
import animals.birds.Duck;
import animals.pets.Cat;
import animals.pets.Dog;
import data.AnimalsDataEnum;

public class AnimalsFactory {


    public static Animal animalsFactory(Long id, AnimalsDataEnum animalType, String name, Integer age, Integer weight, String color) {

        Animal animal = null;

        switch (animalType) {

            case CAT:
                animal = new Cat();
                break;

            case DOG:
                animal = new Dog();
                break;

            case DUCK:
                animal = new Duck();
                break;

        }
        animal.setType(animalType.name());
        animal.setId(id);
        animal.setName(name);
        animal.setColor(color);
        animal.setAge(age);
        animal.setWeight(weight);
        return animal;

    }
}

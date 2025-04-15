package animals.birds;

import animals.Animal;

public class Duck extends Animal implements IFlying{

    public void say() {
        System.out.println("Кря");
    }

    @Override
    public void fly() {
        IFlying.super.fly();
    }
}

package animals.birds;

public interface IFlying {
    default public void fly() {
        System.out.println("Я лечу");
    }
}

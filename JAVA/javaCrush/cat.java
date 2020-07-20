package JAVA.javaCrush;

// extends : cat inherit all from dog

public class Cat extends Dog {

    private int food;

    public Cat(String name, int age, int food) {
        super(name, age);  // super call the parent method
        this.food = food;
    }


    public Cat(String name, int age) {
        super(name, age);
        this.food = 1000; // asume the food to 500, if no input
    }


    public Cat(String name) {
        super(name, 1);  // asume the age to 0, if no input
        this.food = 1000; // asume the food to 500, if no input
    }


    public void speak() {
        System.out.println("meow! "+ this.name + " " + this.age + ". Food amount: " + this.food);
    }


    public void eat(int x) {
        this.food -= x;
        System.out.println("Fed: " + x + " Food amount: " + this.food);
    }

}
package JAVA.javaCrush;

public class Dog {

    // private : means only accessible within this class

    private String name;
    private int age;

    // this: look for the attribute

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
        add2();
        speak();
    }
    
    public void speak() {
        System.out.println("I am " + this.name + " age: "+ this.age);
    }


    public int getAge() {
        return this.age;
    }


    public void setAge(int age) {
        this.age = age;
        
    }


    private int add2() {
        return this.age + 2;
    }

}


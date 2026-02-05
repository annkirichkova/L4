import java.util.ArrayList;
import java.util.List;

abstract class Animal {
    protected String name;
    private static int animalCount = 0;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public abstract void run(int distance);
    public abstract void swim(int distance);

    public String getName() {
        return name;
    }

    public static int getAnimalCount() {
        return animalCount;
    }
}

class Dog extends Animal {
    private static final int MAX_RUN_DISTANCE = 500;
    private static final int MAX_SWIM_DISTANCE = 10;
    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= MAX_RUN_DISTANCE && distance > 0) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. Максимум: " + MAX_RUN_DISTANCE + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= MAX_SWIM_DISTANCE && distance > 0) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м. Максимум: " + MAX_SWIM_DISTANCE + " м.");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}

class Cat extends Animal {
    private static final int MAX_RUN_DISTANCE = 200;
    private static int catCount = 0;
    private boolean satiety;

    public Cat(String name) {
        super(name);
        this.satiety = false;
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= MAX_RUN_DISTANCE && distance > 0) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. Максимум: " + MAX_RUN_DISTANCE + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать!");
    }

    public void eatFromBowl(Bowl bowl, int foodAmount) {
        if (bowl.decreaseFood(foodAmount)) {
            satiety = true;
            System.out.println(name + " поел из миски и теперь сыт.");
        } else {
            System.out.println(name + " не стал есть, в миске недостаточно еды.");
        }
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void setSatiety(boolean satiety) {
        this.satiety = satiety;
    }

    public static int getCatCount() {
        return catCount;
    }
}

class Bowl {
    private int foodAmount;

    public Bowl(int initialFood) {
        if (initialFood < 0) {
            this.foodAmount = 0;
        } else {
            this.foodAmount = initialFood;
        }
    }

    public boolean decreaseFood(int amount) {
        if (amount <= 0) {
            return false;
        }

        if (foodAmount >= amount) {
            foodAmount -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("В миску добавлено " + amount + " еды. Теперь в миске: " + foodAmount);
        }
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        if (foodAmount >= 0) {
            this.foodAmount = foodAmount;
        } else {
            this.foodAmount = 0;
        }
    }
}

interface Colored {
    void setFillColor(String color);
    void setBorderColor(String color);
    String getFillColor();
    String getBorderColor();
}

interface Shape extends Colored {
    double calculatePerimeter();
    double calculateArea();

    default void printInfo() {
        System.out.println("Периметр: " + String.format("%.2f", calculatePerimeter()) +
                ", Площадь: " + String.format("%.2f", calculateArea()) +
                ", Цвет фона: " + getFillColor() +
                ", Цвет границ: " + getBorderColor());
    }
}

class Circle implements Shape {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius) {
        this.radius = radius;
        this.fillColor = "белый";
        this.borderColor = "черный";
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
        this.fillColor = "белый";
        this.borderColor = "черный";
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

class Triangle implements Shape {
    private double sideA;
    private double sideB;
    private double sideC;
    private String fillColor;
    private String borderColor;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.fillColor = "белый";
        this.borderColor = "черный";

        if (!isValidTriangle()) {
            System.out.println("Внимание: треугольник с такими сторонами не существует!");
        }
    }

    private boolean isValidTriangle() {
        return (sideA + sideB > sideC) &&
                (sideA + sideC > sideB) &&
                (sideB + sideC > sideA);
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double calculateArea() {
        double p = calculatePerimeter() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public void setFillColor(String color) {
        this.fillColor = color;
    }

    @Override
    public void setBorderColor(String color) {
        this.borderColor = color;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");

        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");
        Cat cat3 = new Cat("Васька");

        dog1.run(150);
        dog1.run(600);
        dog1.swim(5);
        dog1.swim(15);

        cat1.run(100);
        cat1.run(250);
        cat1.swim(10);


        Bowl bowl = new Bowl(30);
        System.out.println("В миске изначально: " + bowl.getFoodAmount() + " еды");

        Cat[] cats = {cat1, cat2, cat3};

        for (Cat cat : cats) {
            cat.eatFromBowl(bowl, 15);
        }

        System.out.println("\nИнформация о сытости котов:");
        for (Cat cat : cats) {
            System.out.println(cat.getName() + ": " + (cat.isSatiety() ? "сыт" : "голоден"));
        }
        System.out.println("В миске осталось: " + bowl.getFoodAmount() + " еды");

        bowl.addFood(50);

        System.out.println("\nПробуем покормить голодных котов снова:");
        for (Cat cat : cats) {
            if (!cat.isSatiety()) {
                cat.eatFromBowl(bowl, 15);
            }
        }

        System.out.println("\nСтатистика");
        System.out.println("Всего животных создано: " + Animal.getAnimalCount());
        System.out.println("Собак создано: " + Dog.getDogCount());
        System.out.println("Котов создано: " + Cat.getCatCount());

        System.out.println("\n\n=== Тестирование задания 2 ===");

        List<Shape> shapes = new ArrayList<>();

        Circle circle = new Circle(5);
        circle.setFillColor("красный");
        circle.setBorderColor("синий");
        shapes.add(circle);

        Rectangle rectangle = new Rectangle(4, 6);
        rectangle.setFillColor("зеленый");
        rectangle.setBorderColor("желтый");
        shapes.add(rectangle);

        Triangle triangle = new Triangle(3, 4, 5);
        triangle.setFillColor("оранжевый");
        triangle.setBorderColor("фиолетовый");
        shapes.add(triangle);

        Triangle invalidTriangle = new Triangle(1, 2, 10);
        invalidTriangle.setFillColor("серый");
        invalidTriangle.setBorderColor("черный");
        shapes.add(invalidTriangle);

        System.out.println("Характеристики геометрических фигур:");

        for (int i = 0; i < shapes.size(); i++) {
            System.out.print("\nФигура " + (i+1) + ": ");
            if (shapes.get(i) instanceof Circle) {
                System.out.println("Круг");
            } else if (shapes.get(i) instanceof Rectangle) {
                System.out.println("Прямоугольник");
            } else if (shapes.get(i) instanceof Triangle) {
                System.out.println("Треугольник");
            }
            shapes.get(i).printInfo();
        }

        System.out.println("\nДемонстрация дефолтного метода интерфейса");
        System.out.println("Для круга:");
        circle.printInfo();
    }
}
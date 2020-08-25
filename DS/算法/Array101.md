
# Arrays 101

[toc]

---

## intro

**Creating an Array**
If you make an Array with 1000000 spaces, the computer will reserve memory to hold 1000000 DVDs, even if you only put 15 DVDs into it. That memory can't be used for anything else in the meantime

```java
// The actual code for creating an Array to hold DVD's.
DVD[] dvdCollection = new DVD[15];

// A simple definition for a DVD.
public class DVD {
    public String name;
    public int releaseYear;
    public String director;

    public DVD(String name, String releaseYear, String director) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.director = director;
    }

    public String toString() {
        System.out.println(
            this.name + ", directed by " + this.director + ", released in " + this.releaseYear));
    }
}
```

**Writing Items into an Array**

```java
// create a DVD object for The Avengers.
DVD avengersDVD = new DVD("The Avengers", 2012, "Joss Whedon");
dvdCollection[7] = avengersDVD;  // put it into the 8th place of the Array. the index: 7.

DVD starWarsDVD = new DVD("Star Wars", 1977, "George Lucas");
dvdCollection[3] = starWarsDVD;
```

**Reading Items from an Array**



































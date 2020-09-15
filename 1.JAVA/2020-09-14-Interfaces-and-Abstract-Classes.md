---
title: Java - Interfaces and Abstract Classes
date: 2020-09-14 11:11:11 -0400
description:
categories: [Java, JavaPoint]
img: /assets/img/sample/rabbit.png
tags: [Java]
---


# Java - Interfaces and Abstract Classes

[toc]

---

## Commonalities in Code


```java

Method MarkovRunner.runMarkovN()

    FileResource fr = new FileResource("data/confucius.txt");
	String st = fr.asString();
	st = st.replace('\n', ' ');

public void runMarkovN() {
    // MarkovZero markov = new MarkovZero();
    // MarkovOne markov = new MarkovOne();
    // MarkovFour markov = new MarkovFour();
	MarkovModel markov = new MarkovModel();
    markov.setModel(8);
    markov.setTraining(st);
    markov.setRandom(365);

	for(int k=0; k < 2; k++){
        String text = markov.getRandomText(500);
    	printOut(text);
    }
}

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int n;
    public MarkovModel(){}

    public void setRandom(int seed){}

    public void setTraining(String s){}
    public void setModel(int N){}
    public String getRandomText(int numChars) {}

    public ArrayList<String> getFollows(String key) {}
}

```

## Developing an Interface

```java

public interface IMarkovModel {
    public void setTraining(String text);
    public String getRandomText(int numChars);
}

public class MarkovOne implements IMarkovModel{
    private String myText;
	private Random myRandom;
    public MarkovOne() {}
    public void setRandom(int seed){}
}
	
public class MarkovTwo implements IMarkovModel{}         // MarkovTwo is <IMarkovModel> objecy

public class MarkovFour implements IMarkovModel{}


```

## Interface: Utility and Flexibility


```java
public void runModel(IMarkovModel markov, String text, int size){
    markov.setTraining(text);
    System.out.println("running with "+markov);
    for(int k=0; k < 3; k++){
        String st = markov.getRandomText(size);
        printOut(st);
    }
}

MarkovZero mz = new MarkovZero();
runModel(mz,text,800);

MarkovTwo m2 = new MarkovTwo();
runModel(m2,text,800);
```


---

## Capture commonality in Abstract Base Class

`AbstractMarkovModel`

```java
// Class marked as abstract
public abstract class AbstractMarkovModel implements IMarkovModel {

    protected String myText;     // Shared state is protected, not private
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String text) {
        myText = text;
    }

    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key){
        // code not shown
    }
}



public class MarkovModel extends AbstractMarkovModel {

    private int myOrder;

    public MarkovModel(int order) {
        myOrder = order;
    }

    public String getRandomText(int length) {
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - myOrder);
        String current = myText.substring(index, index + myOrder);
        sb.append(current);
        
        for(int k=0; k < length-myOrder; k++){
            ArrayList<String> follows = getFollows(current);
        }
    }
}



```











.
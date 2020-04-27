
# Project


| ᐕ)⁾⁾ como estas~~~~ bien~~~~ y tu?~~~~


Index | Name | Date | Course material
---|---|---|---
1 | [LittleTurtlesAdventure](https://github.com/ocholuo/language/blob/master/PythonBasic/0.0.LittleTurtlesAdventure.md) | 03/21/2020 | University of Michigan in Coursera (Coursera) Chapter ?? : P
2 | [AreYourWordsHappy](https://github.com/ocholuo/language/blob/master/PythonBasic/0.0.AreYourWordsHappy.md) | 03/25/2020 | University of Michigan in Coursera (Coursera) Chapter 16.10
3 | [NestedPokemon](https://github.com/ocholuo/language/blob/master/PythonBasic/0.0.NestedPokemon.md) | 03/25/2020 | University of Michigan in Coursera (Coursera) Chapter 17.8
4 | [HiMyTamagotchiJiang](https://github.com/ocholuo/language/blob/master/PythonBasic/0.0.HiMyTamagotchiJiang.md) | 03/28/2020 | University of Michigan in Coursera (Coursera) Chapter 20.13
5 | [HiMyPokemon](https://github.com/ocholuo/language/blob/master/PythonBasic/0.0.HiMyPokemon.md) | 03/30/2020 | University of Michigan in Coursera (Coursera) Chapter 22.7
6 | [WheelOfPython](https://github.com/ocholuo/language/blob/master/PythonBasic/0.0.WheelOfPython.md) | 03/30/2020 | University of Michigan in Coursera (Coursera) Chapter 22.8
7 | [TasteDive](https://github.com/ocholuo/language/blob/master/PythonBasic/0.0.TasteDive.md) | 04/03/2020 | University of Michigan in Coursera (Coursera) Chapter 24.14

---

:purple_heart: some link:

:star: when the runestone error: use [this link](https://runestone.academy/runestone/books/published/fopp/AdvancedAccumulation/toctree.html) to access the text book

---



```py

# a list of numbers from 0 to 67:
for i in range(68)

# return values
list.sort()  =None
sorted(list) =list


# seperate
inventory = ["shoes, 12, 29.99", "shirts, 20, 9.99", "sweatpants, 25, 15.00", "scarves, 13, 7.75"]
for item in inventory:
    a=item.split(", ")
    name=a[0]
    num=a[1]
    price=a[2]
    print("The store has {} {}, each for {} USD.".format(num,name,price))



def get_related_titles(lst):
    titlelst=[]
    for name in lst:
        [titlelst.append(name) for name in extract_movie_titles(get_movies_from_tastedive(name)) if name not in titlelst]
    return titlelst


sum = lambda arg1, arg2: arg1 + arg2
print ("sum= ", sum( 10, 20 ))
sum=30

together= lambda num,abc,x=" ":x.join([str(num),abc])


```

.

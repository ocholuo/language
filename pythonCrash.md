


## String
str.count( sub, start= 0, end=len(string) )


## List
```py
1.	list.append(obj): 在列表末尾添加新的对象
2.	list.count(obj): 统计某个元素在列表中出现的次数
3.	list.extend(seq): 在列表末尾一次性追加另一个序列中的多个值（用新列表扩展原来的列表）
4.	list.index(obj): 从列表中找出某个值第一个匹配项的索引位置
5.	list.insert(index, obj): 将对象插入列表
6.	list.pop([index=-1]): 移除列表中的一个元素（默认最后一个元素），并且返回该元素的值
7.	list.remove(obj): 移除列表中某个值的第一个匹配项
8.	list.reverse(): 反向列表中元素
9.	list.sort( key=None, reverse=False): 对原列表进行排序
10.	list.clear(): 清空列表
11.	list.copy(): 复制列表

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
```


## dictonary

```py

############### dictonary mechanics ###############

sports = {'baseball': 9, 'basketball': 4, 'soccer': 4, 'cricket': 2}
sports['hockey']=3
sport=list(sports.keys())


############### dictonary accumulation ###############
credits=0
for i in sports:
    credits+= sports[i]

str1 = "peter piper picked a peck of pickled peppers"
freq = {}                 # check frequency
for i in str1:
    if i not in freq:
        freq[i]=0
    freq[i]+=1
best_char=freq.keys()[0]         # check bigest value & key
best_value=freq[freq.keys()[0]]
for j in freq:
    if freq[j]>freq[freq.keys()[0]]:
        best_char=j
        best_value=freq[j]
```



## Tuple

```py
tuples_lst = [('Beijing', 'China', 2008), ('London', 'England', 2012), ('Rio', 'Brazil', 2016, 'Current'), ('Tokyo', 'Japan', 2020, 'Future')]
country=[]
for i,j in enumerate(tuples_lst):
    country.append(j[1])

# Tuple Unpacking
(variable names) = (values)

julia = "Julia", "Roberts", 1967, "Duplicity", 2009, "Actress", "Atlanta, Georgia"

name, surname, birth_year, movie, movie_year, profession, birth_place = julia
name, surname, birth_year, movie, movie_year, profession, birth_place="Julia", "Roberts", 1967, "Duplicity", 2009, "Actress", "Atlanta, Georgia"

print(name)   # julia

# variable names on the left side!!
# "Julia", "Roberts", 1967, "Duplicity", 2009, "Actress", "Atlanta, Georgia" = name, surname, birth_year, movie, movie_year, profession, birth_place
# SyntaxError: can't assign to literal on line 7

(a, b, c, d) = (1, 2, 3)  # ValueError: need more than 3 values to unpack
(a, b, c, d) = (1, 2, 3, 4)

students = [('Tommy', 95), ('Linda', 63), ('Carl', 70), ('Bob', 100), ('Raymond', 50), ('Sue', 75)]
passed = [ name for (name,grade) in students if grade>=70 ]
```


## Sorted
```py
letters = "alwnfiwaksuezlaeiajsdl"
sorted_letters=sorted(letters, reverse=True)

# according to the dic value
medals = {'Japan':41, 'Russia':56, 'South Korea':21, 'United States':121, 'Germany':42, 'China':70}
top_three=sorted(medals,reverse=True,key=lambda key:medals[key])[:3]

# according second letter
ex_lst = ['hi', 'how are you', 'bye', 'apple', 'zebra', 'dance']
lambda_sort=sorted(ex_lst, key=lambda str: str[1])
```



## Functions
```py
def length(inlist):
    if len(inlist) >= 5:
        return "Longer than 5"
    else:
        return "Less than 5"

def test(x, abool = True, dict1 = {2:3, 4:5, 6:8}):
    return abool and dict1.get(x, False)
test(5, dict1 = {5:4, 2:1})


def strip_punctuation(string):   # move the punctuation
    for i in string:
        if i in ["'", '"', ",", ".", "!", ":", ";", '#', '@']:
            string=string.replace(i,'')
    return string
```



## lambda

```py
def fname(arguments):
    return value

fname = lambda arguments: value


mult = lambda int,x=6: int*x
greeting = lambda name, greeting="Hello ", excl="!": greeting + name + excl


lst_check = ['plums', 'watermelon', 'kiwi', 'strawberries', 'blueberries', 'peaches', 'apples', 'mangos', 'papaya']
map_testing= map( lambda s: 'Fruit: '+s, lst_check)

countries = ['Canada', 'Mexico', 'Brazil', 'Chile', 'Denmark', 'Botswana', 'Spain', 'Britain', 'Portugal', 'Russia', 'Thailand', 'Bangladesh', 'Nigeria', 'Argentina', 'Belarus', 'Laos', 'Australia', 'Panama', 'Egypt', 'Morocco', 'Switzerland', 'Belgium']
b_countries= filter( lambda s: s[0]=='B' , countries)

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


## zip, map filter

```py
l1 = ['left', 'up', 'front']
l2 = ['right', 'down', 'back']
zip(l1,l2) = [('left', 'right'), ('up', 'down'), ('front', 'back')]

opposites= [ (x1,x2) for (x1,x2) in zip(l1,l2) if len(x1)>3 and len(x2)>3 ]


def square(x) : return x ** 2
map(square, [1,2,3,4,5])
map(lambda x: x ** 2, [1, 2, 3, 4, 5])

filter(function, sequence)

lst_check = ['plums', 'watermelon', 'kiwi', 'strawberries', 'blueberries', 'peaches', 'apples', 'mangos', 'papaya']
# elements in lst_check that have a w
filter_testing=list( filter( lambda value: 'w' in value, lst_check) )
```

---

## test
```py
assert sorted([1, 7, 4]) == [1, 4, 7]
assert sorted([1, 7, 4], reverse=True) == [7, 4, 1]
```

---

## except

```py
numb = [6, 0, 36, 8, 2, 36, 0, 12, 60, 0, 45, 0, 3, 23]
remainder = []

for num in numb:
    try:
        36%num!=0
        remainder.append(36%num)
    except Exception, e:
        print(e)
        remainder.append("Error")


full_lst = ["ab", 'cde', 'fgh', 'i', 'jkml', 'nop', 'qr', 's', 'tv', 'wxy', 'z']
attempt = []

for elem in full_lst:
    try:
        attempt.append(elem[1])
    except:
        attempt.append("Error")
```


---

## RegularExpression
```py
# ^	Matches the beginning of a line
# $	Matches the end of the line
# .	Matches any character
# \s	Matches whitespace
# \S	Matches any non-whitespace character
# *	    Repeats a character zero or more times
# *?	Repeats a character zero or more times (non-greedy)
# +	    Repeats a character one or more times
# +?	Repeats a character one or more times (non-greedy)
# [aeiou]	Matches a single character in the listed set
# [^XYZ]	Matches a single character not in the listed set
# [a-z0-9]	The set of characters can include a range
# (	Indicates where string extraction is to start
# )	Indicates where string extraction is to end

import re

hand = open('file.txt')
    for line in hand:
        line = line.rstrip()
        if re.search('From:', line):
            print(line)


x='my 2 favoriate numbers are 19 and 42'
y=re.findall('[0-9]+',x)
print(y)     # ['2', '19', '42']


# greedy match
x='From: sing the : character.'
y=re.findall('^F.+:',x)   # greedy: it will give back the largest one.
print(y)                  # ['From: sing the : ']
y=re.findall('^F.+?:',x)  # dont be greedy: add '?'
print(y)                  # ['From:']
```



## Data collect
```py
import requests
import json

page = requests.get("https://api.datamuse.com/words?rel_rhy=funny")

print(type(page))       #<class 'requests.Response'>

print(page.text[:150])  # print the first 150 characters
#[{"word":"money","score":4417,"numSyllables":2},{"word":"honey","score":1208,"numSyllables":2},{"word":"sunny","score":720,"numSyllables":2},{"word":"

print(page.url)          # print the url that was fetched
#https://api.datamuse.com/words?rel_rhy=funny

x = page.json()            # turn page.text into a python object
x = jsno.loads(page, text) # list

------------------------------------

d = {'q': '"violins and guitars"', 'tbm': 'isch'}
results = requests.get("https://google.com/search", params=d)
results.url  # https://www.google.com/search?q=%22violins+and+guitars%22&tbm=isch
results.text

------------------------------------

import requests_with_caching
import json

parameters = {"term":"ann arbor", "entity": "podcast"}
response = requests_with_caching.get("https://api.datamuse.com/words?rel_rhy=happy", permanent_cache_file="data_cache.txt")

py_data = json.loads(data_cache.txt)
for r in py_data['results']:
    print(r['trackName'])

------------------------------------

import requests

def requestURL(baseurl, params = {}):
    # accepts a URL path and a params diction as inputs.
    # calls requests.get() with those inputs,
    # and returns the full URL of the data you want to get.
    req = requests.Request(method = 'GET', url = baseurl, params = params)
    prepped = req.prepare()
    return prepped.url

print(requestURL(some_base_url, some_params_dictionary))

https://api.datamuse.com/words?rel_rhy=funny

print(requestURL("https://api.datamuse.com/words", {"rel_rhy":"funny"}) )

```


## Network with PY
```py
# make a socket, a connection
import socket
mysock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
mysock.connect( ('data.pr4e.org', 80))   # host and port

# HTTP
# http:// www.goo.com/ index.html
# protocol+host+document
import socket
mysock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
mysock.connect( ('data.pr4e.org', 80))   # host and port
cmd = 'GET http://data.pr4e.org/romeo.txt HTTP/1.0\r\n\r\n'.encode()
mysock.send(cmd)

while True:
    data = mysock.recv(512)
    if (len(data) < 1):
        break
    print(data.decode())
mysock.close()
```


---


## class

```py

class Pokemon():
    attack = 12
    defense = 10
    health = 15
    p_type = "Normal"

    def __init__(self, name,level = 5):
        self.name = name
        self.level = level
        self.weak = "Normal"
        self.strong = "Normal"

    def train(self):
        self.update()
        self.attack_up()
        self.defense_up()
        self.health_up()
        self.level = self.level + 1
        if self.level%self.evolve == 0:
            return self.level, "Evolved!"
        else:
            return self.level

    def attack_up(self):
        self.attack = self.attack + self.attack_boost
        return self.attack

    def defense_up(self):
        self.defense = self.defense + self.defense_boost
        return self.defense

    def health_up(self):
        self.health = self.health + self.health_boost
        return self.health

    def update(self):
        self.health_boost = 5
        self.attack_boost = 3
        self.defense_boost = 2
        self.evolve = 10

    def opponent(self):
        if self.p_type=='Grass':
            opponent=('Fire', 'Water')
        elif self.p_type=="Ghost":
            opponent=('Dark', 'Psychic')
        elif self.p_type=="Fire":
            opponent=('Water', 'Grass')
        elif self.p_type=="Flying":
            opponent=('Electric', 'Fighting')
        else:
            opponent=('None', 'None')
        return opponent  

    def __str__(self):
        self.update()
        return "Pokemon name: {}, Type: {}, Level: {}".format(self.name, self.p_type, self.level)

class Grass_Pokemon(Pokemon):
    attack = 15
    defense = 14
    health = 12
    p_type = "Grass"

    def update(self):
        self.health_boost = 6
        self.attack_boost = 2
        self.defense_boost = 3
        self.evolve = 12


    attackadding=0

    def train(self):
        self.update()
        #print("boost: {} attack, {} defense, {} health, {} evolve".format(self.attack_boost, self.defense_boost, self.health_boost, self.evolve))
        self.defense_up()
        self.health_up()
        self.level = self.level + 1
        self.attack_up()
        if self.level % self.evolve == 0:
            print("Evolved!")
            return self.level, "Evolved!"
        else:
            return self.level

    def attack_up(self):
        self.attackadding = self.attackadding + self.attack_boost
        #print("attackadding:", self.attackadding)
        if self.level>=10:
            self.attack = self.attack + self.attackadding
            self.attackadding=0
        return self.attack

    def moves(self):
        self.p_moves = ["razor leaf", "synthesis", "petal dance"]

    def __str__(self):
        return "Pokemon name: {}, Type: {}, Level: {}".format(self.name, self.p_type, self.level)


class Ghost_Pokemon(Pokemon):
    p_type = "Ghost"

    def update(self):
        self.health_boost = 3
        self.attack_boost = 4
        self.defense_boost = 3

class Fire_Pokemon(Pokemon):
    p_type = "Fire"

class Flying_Pokemon(Pokemon):
    p_type = "Flying"

po1=Flying_Pokemon('nana')
a=po1.opponent()
print(po1)
print(a)
```
























.

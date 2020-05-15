


############### String ###############
str.count( sub, start= 0, end=len(string) )


############### List ###############
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




############### Tuple ###############
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



############### Sorted ###############
letters = "alwnfiwaksuezlaeiajsdl"
sorted_letters=sorted(letters, reverse=True)

# according to the dic value
medals = {'Japan':41, 'Russia':56, 'South Korea':21, 'United States':121, 'Germany':42, 'China':70}
top_three=sorted(medals,reverse=True,key=lambda key:medals[key])[:3]

# according second letter 
ex_lst = ['hi', 'how are you', 'bye', 'apple', 'zebra', 'dance']  
lambda_sort=sorted(ex_lst, key=lambda str: str[1])



############### Functions ###############
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




############### lambda ###############
mult = lambda int,x=6:int*x
greeting = lambda name, greeting="Hello ", excl="!": greeting + name + excl


def get_related_titles(lst):
    titlelst=[]
    for name in lst:
        [titlelst.append(name) for name in extract_movie_titles(get_movies_from_tastedive(name)) if name not in titlelst]
    return titlelst


sum = lambda arg1, arg2: arg1 + arg2
print ("sum= ", sum( 10, 20 ))
sum=30

together= lambda num,abc,x=" ":x.join([str(num),abc])


############### RegularExpression ###############

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



############### Network with PY ###############

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



# note


[toc]

## re

![Screen Shot 2020-04-12 at 22.44.42](https://i.imgur.com/57Q8Xev.png)


## serarch and return `True` or `False`.


```py

1. use it as find().

    import re

    hand = open('file.txt')
    for line in hand:
        line = line.rstrip()
        if re.search('From:', line):
     #  if line.find('From:')>= 0:
            print(line)


2. use it as startswith()

    import re

    hand = open('file.txt')
    for line in hand:
        line = line.rstrip()
        if re.search('^From:', line):  # ^ is beginning
      # if line.startswith('From:')
            print(line)


```

---

## match and extract data


```py

1. normal match

    import re

    x='my 2 favoriate numbers are 19 and 42'
    y=re.findall('[0-9]+',x)  # give back the string
    print(y)

    ['2', '19', '42']
    y=re.findall('AEI+',x)    # give back the string
    print(y)
    []


2. greedy match

    import re

    x='From: sing the : character.'
    y=re.findall('^F.+:',x)  # give back the string
    print(y)

  # ['From:']
    ['From: sing the : ']

    greedy: it will give back the largest one.



3. dont be greedy by '?'

    import re

    x='From: sing the : character.'
    y=re.findall('^F.+?:',x)  # give back the string
    print(y)

    ['From:']
  # ['From: sing the : ']


```

---

## search EMAIL

```py

1. by using RE.

    data='From admin@google.com Sat Jan 5 09:14:23'

    y=re.findall('\S+@\S+', data)

    # parentheses for the part, where to start and stop extract
    y=re.findall('^From (\S+@\S+)', data)

    y=re.findall('@([^ ]*)', data)
    # [^ ]: everything but a space
    y=re.findall('^From .*@([^ ]*)', data)



2. by using py.


    data='From admin@google.com Sat Jan 5 09:14:23'

    atpos=data.find('@')        # 21
    sppos=data.find('', atpos)  # 31
    host=data[atpos+1:sppos]    # google.com


    words=line.split()
    email==words[1]
    pieces=email.split(@)
    host=pieces[1]            # google.com

```

## assignment

```py

# find the number match this style
'X-DSPAM-Confidence: 0.8937'

import re

hand=open("file.txt")

for line in hand:
    line=line.strip()
    stuff=re.findall('^X-DSPAM-Confidence: 0.[0-9]+', line)



```




.

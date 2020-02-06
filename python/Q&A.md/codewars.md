
# solution

| No. | Puzzle Name             | From     | State                       | Difficulty | Link                                                                                                                                                                                                                                                                                                                                                                                                 |
|-----|-------------------------|----------|-----------------------------|------------|---------------------------------------------------------------------|
| 1   | Duplicate Encoder       | obnounce | :heavy_check_mark:Completed | Medium     | https://www.codewars.com/kata/54b42f9314d9229fd6000d9c/train/python                                                                                                                                                                                                                                              |
| 1   |                         | obnounce |:x: Not Completed  | Medium     |   https://www.codewars.com/kata/54b42f9314d9229fd6000d9c/train/python                                                                                                                                                                                                                                                                                             |



# words

## 1. Duplicate Encoder

```py
def duplicate_encode(word):
    import re
    answer=[]
    word=word.lower()
    for i in range(len(word)):
        filterlist=word[0:i]+word[i+1:]
#        print(filterlist)
        if word[i] in filterlist:
            answer.append(')')
        else:
            answer.append('(')
    answer=''.join(answer)
    return answer


def duplicate_encode(word):
    a = ["(" if word.lower().count(c) == 1 else ")" for c in word.lower()]
    return "".join(a)


def duplicate_encode(word):
    word = word.upper()
    result = ""
    for char in word:
        if word.count(char) > 1:
            result += ")"
        else:
            result += "("
    return result


def duplicate_encode(word):
    word = word.lower()
    return ''.join([')' if word.count(char) > 1 else '(' for char in word])
```

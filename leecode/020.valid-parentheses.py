
# 20. Valid Parentheses

# Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
# An input string is valid if:
# - Open brackets must be closed by the same type of brackets.
# - Open brackets must be closed in the correct order.
# - Note that an empty string is also considered valid.

# Example 1:
# Input: "()"
# Output: true

# Example 2:
# Input: "()[]{}"
# Output: true

# Example 3:
# Input: "(]"
# Output: false

# Example 4:
# Input: "([)]"
# Output: false

# Example 5:
# Input: "{[]}"
# Output: true 



# 1. 
# '(': left +=1 
# ')': left -=1



class Solution:
    def isValid(self, s: str) -> bool:
        newstr=''
        for i in s:
            if i in ['(',')','{','}','[',']']:
                newstr+=i
        while '()' in newstr or '{}' in newstr or '[]' in newstr:
            newstr=newstr.replace('()','').replace('{}','').replace('[]','')
        return newstr ==''
# Runtime: 40 ms, faster than 17.44% of Python3 online submissions for Valid Parentheses.
# Memory Usage: 13.7 MB, less than 6.09% of Python3 online submissions for Valid Parentheses.


class Solution:
    def isValid(self, s: str) -> bool:
        while '()' in s or '{}' in s or '[]' in s:
            s=s.replace('()','').replace('{}','').replace('[]','')
        return s ==''
# Runtime: 80 ms, faster than 6.27% of Python3 online submissions for Valid Parentheses.
# Memory Usage: 14 MB, less than 5.22% of Python3 online submissions for Valid Parentheses.


class Solution:
    def isValid(self, s):
        bracket_map = {"(": ")", "[": "]",  "{": "}"}
        open_par = set(["(", "[", "{"])
        stack = []
        for i in s:
            if i in open_par:                             # 放入开始的符号
                stack.append(i)
            elif stack and i == bracket_map[stack[-1]]:   # 若是闭合 但是前面有此开合
                stack.pop()
            else:
                return False
        return stack == []
# Runtime: 32 ms, faster than 46.59% of Python3 online submissions for Valid Parentheses.
# Memory Usage: 13.8 MB, less than 5.22% of Python3 online submissions for Valid Parentheses.


# courser:
class Stack:
    def __init__(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def peek(self):
        return self.items[len(self.items)-1]
        return self.items[-1]

    def size(self):
        return len(self.items)

#from pythonds.basic.stack import Stack

def matches(opener, closer):
    openers = "({["
    closers = ")}]"
    return openers.index(opener) == closers.index(closer)


def parChecker(symbolString):
    s = Stack()
    balanced = True
    index = 0
    while index < len(symbolString) and balanced:
        symbol = symbolString[index]
        if index == "({[":
            s.push(symbol)
        else:
            if s.isEmpty():
                balanced = False
#            else:
#                s.pop()
            else:
                top = s.pop()
                if not matches(top, symbol):
                    balanced = False
        index += 1
    if balanced and s.isEmpty():
        return True
    else:
        return False

print(parChecker("((())(()"))



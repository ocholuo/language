
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
            #print(newstr)
            newstr=newstr.replace('()','')
            #print(newstr)
            newstr=newstr.replace('{}','')
            #print(newstr)
            newstr=newstr.replace('[]','')
        print(newstr)
        if newstr =='':
            return 1
        return 0



class Solution:
    def isValid(self, s: str) -> bool:
        while '()' in s or '{}' in s or '[]' in s:
            s=s.replace('()','').replace('{}','').replace('[]','')
        return s ==''
# Runtime: 44 ms, faster than 13.11% of Python3 online submissions for Valid Parentheses.
# Memory Usage: 14.1 MB, less than 5.22% of Python3 online submissions for Valid Parentheses.


class Solution(object):
    def isValid(self, s):
        s="([)]"  # should be false
        bracket_map = {"(":")", "[":"]",  "{":"}"}
        open_par = ["(", "[", "{"]
        for i in s:
            if i in open_par:
                closech = bracket_map[i]
                s=s.replace(i, '').replace(closech, '')
        return 1 if s=='' else 0
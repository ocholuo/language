
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
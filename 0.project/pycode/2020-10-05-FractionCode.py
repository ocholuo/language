

def gcd(m,n):
    while m%n != 0:
        m,n = n, m%n
    return n

class Fraction:
    def __init__(self, top, bottom):
        # make sure that the numerator and denominator are both integers. 
        # If either is not an integer the constructor should raise an exception.
        if not isinstance(top, int):
            valErr = ValueError("{} is not integer".format(top))
            raise valErr
        if not isinstance(bottom, int):
            valErr = ValueError("{} is not integer".format(bottom))
            raise valErr
        common = gcd(abs(top), abs(bottom))
        self.num = top/common
        self.den = bottom/common

    def show(self):
        return print(f"{self.num} / {self.den}")

    def __str__(self):
        return f"{self.num}/{self.den}"
    
    def __repr__(self):
        return '%s(%r)' % (self.__class__, self.__str__())

    def getNum(self):
        return self.num

    def getDen(self):
        return self.den

    def __add__(self, other_fraction):
        new_num = self.num * other_fraction.den + self.den * other_fraction.num
        new_den = self.den * other_fraction.den
        # common = gcd(new_num, new_den)
        # return Fraction(new_num/common, new_den /common)
        return Fraction(new_num, new_den)

    def __eq__(self, other_fraction):
        first_num = self.num * other_fraction.den
        second_num = other_fraction.num * self.den
        return first_num == second_num

    def __sub__(self, other_fraction):
        new_num = self.num * other_fraction.den - self.den * other_fraction.num
        new_den = self.den * other_fraction.den
        # common = gcd(new_num, new_den)
        # return Fraction(new_num/common, new_den /common)
        return Fraction(new_num, new_den)

    def __mul__(self, other_fraction):
        new_num = self.num * other_fraction.num
        new_den = self.den * other_fraction.den
        # common = gcd(new_num, new_den)
        # return Fraction(new_num/common, new_den /common)
        return Fraction(new_num, new_den)

    def __truediv__(self, other):
        num = self.num * other.den
        den = self.den * other.num
        return Fraction(num, den)

    def __gt__(self, other_fraction):
        selfnum = self.num * other_fraction.den
        othernum = self.den * other_fraction.num
        return selfnum > othernum

    def __ge__(self, other_fraction):
        selfnum = self.num * other_fraction.den
        othernum = self.den * other_fraction.num
        return selfnum >= othernum

    def __lt__(self, other_fraction):
        selfnum = self.num * other_fraction.den
        othernum = self.den * other_fraction.num
        return selfnum < othernum

    def __le__(self, other_fraction):
        selfnum = self.num * other_fraction.den
        othernum = self.den * other_fraction.num
        return selfnum <= othernum

    def __ne__(self, other_fraction):
        selfnum = self.num * other_fraction.den
        othernum = self.den * other_fraction.num
        return selfnum != othernum

    def __radd__(self, other):
        if other == 0:
            return self
        else:
            other = Fraction(other, 1)
            return self.__add__(other)

    def __iadd__(self, other):
        if isinstance(other, int):
            other = Fraction(other, 1)
        return self.__add__(other)


f1 = Fraction(1, 4)
f2 = Fraction(1, 2)
f3 = f1 + f2
print(f3)
print(f1>f2)
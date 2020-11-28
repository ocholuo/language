#include <iostream>
using namespace std;

int main()  
{  
    cout << "type: \t\t" << "************size**************"<< endl;  

    cout << "bool: \t\t" << "size:" << sizeof(bool);  
    cout << "\tMax:" << (numeric_limits<bool>::max)();  
    cout << "\t\tMin:" << (numeric_limits<bool>::min)() << endl;  
    
    cout << "char: \t\t" << "size:" << sizeof(char);  
    cout << "\tMax:" << (numeric_limits<char>::max)();  
    cout << "\t\tMin:" << (numeric_limits<char>::min)() << endl;  

    cout << "signed char: \t" << "size:" << sizeof(signed char);  
    cout << "\tMax:" << (numeric_limits<signed char>::max)();  
    cout << "\t\tMin:" << (numeric_limits<signed char>::min)() << endl;  

    cout << "unsigned char: \t" << "size:" << sizeof(unsigned char);  
    cout << "\tMax:" << (numeric_limits<unsigned char>::max)();  
    cout << "\t\tMin:" << (numeric_limits<unsigned char>::min)() << endl;  

    cout << "wchar_t: \t" << "size:" << sizeof(wchar_t);  
    cout << "\tMax:" << (numeric_limits<wchar_t>::max)();  
    cout << "\t\tMin:" << (numeric_limits<wchar_t>::min)() << endl;  

    cout << "short: \t\t" << "size:" << sizeof(short);  
    cout << "\tMax:" << (numeric_limits<short>::max)();  
    cout << "\t\tMin:" << (numeric_limits<short>::min)() << endl;  

    cout << "int: \t\t" << "size:" << sizeof(int);  
    cout << "\tMax:" << (numeric_limits<int>::max)();  
    cout << "\tMin:" << (numeric_limits<int>::min)() << endl;  

    cout << "unsigned: \t" << "size:" << sizeof(unsigned);  
    cout << "\tMax:" << (numeric_limits<unsigned>::max)();  
    cout << "\tMin:" << (numeric_limits<unsigned>::min)() << endl;  

    cout << "long: \t\t" << "size:" << sizeof(long);  
    cout << "\tMax:" << (numeric_limits<long>::max)();  
    cout << "\tMin:" << (numeric_limits<long>::min)() << endl;  

    cout << "unsigned long: \t" << "size:" << sizeof(unsigned long);  
    cout << "\tMax:" << (numeric_limits<unsigned long>::max)();  
    cout << "\tMin:" << (numeric_limits<unsigned long>::min)() << endl;  

    cout << "double: \t" << "size:" << sizeof(double);  
    cout << "\tMax:" << (numeric_limits<double>::max)();  
    cout << "\tMin:" << (numeric_limits<double>::min)() << endl;  

    cout << "long double: \t" << "size:" << sizeof(long double);  
    cout << "\tMax:" << (numeric_limits<long double>::max)();  
    cout << "\tMin:" << (numeric_limits<long double>::min)() << endl;  

    cout << "float: \t\t" << "size:" << sizeof(float);  
    cout << "\tMax:" << (numeric_limits<float>::max)();  
    cout << "\tMin:" << (numeric_limits<float>::min)() << endl;  

    cout << "size_t: \t" << "size:" << sizeof(size_t);  
    cout << "\tMax:" << (numeric_limits<size_t>::max)();  
    cout << "\tMin:" << (numeric_limits<size_t>::min)() << endl;  

    cout << "string: \t" << "size:" << sizeof(string) << endl;  

    cout << "type: \t\t" << "************size**************"<< endl;  

    return 0;  
}
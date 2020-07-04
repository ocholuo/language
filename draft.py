import pytest

class Person:
    def greet():
        return "hello, there!"

@pytest.fixture
def person():
    return Person()

def test_greet(person):
    greeting = person.greet()
    assert greeting == "hi, there!"


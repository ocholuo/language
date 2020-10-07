
![circuit1](https://i.imgur.com/gNLPAqZ.png)

1. start to initial the Gate, all initial pin is None

![Screen Shot 2020-10-05 at 23.39.34](https://i.imgur.com/BUY0z5m.png) ![Screen Shot 2020-10-05 at 23.39.52](https://i.imgur.com/hlKoeeQ.png)

2. Connector,
    - get the from_gate, to_gate, link to gate.
    - to_gate: `if self.pin_a == None: self.pin_a = source (connector)`

![Screen Shot 2020-10-06 at 00.34.46](https://i.imgur.com/SUySGFb.png)


3. g4

```py
g4.get_output()

class LogicGate: get_output()

self.output = class NotGate(UnaryGate): perform_gate_logic(self)

class UnaryGate(LogicGate): self.get_pin():
    return self.pin.get_from().get_output()
    |
    V
return g3.get_output()

class LogicGate: get_output()

self.output = class OrGate(UnaryGate): perform_gate_logic(self):
a = self.get_pin_a()
b = self.get_pin_b()

class BinaryGate(LogicGate): self.get_pin():

def get_pin_a(self):
    return self.pin_a.get_from().get_output()
        |
        V
return g1.get_output()
a = self.get_pin_a()
b = self.get_pin_b()
request Enter


def get_pin_b(self):
    return self.pin_b.get_from().get_output()
       |
       V
return g2.get_output()
a = self.get_pin_a()
b = self.get_pin_b()
request Enter

```

![Screen Shot 2020-10-06 at 00.36.28](https://i.imgur.com/Ilfk3Pw.png)


```py
# -------------------------------------------------
class LogicGate:
    def __init__(self, lbl):
        self.name = lbl
        self.output = None

    def get_label(self):
        return self.name

    def get_output(self):
        self.output = self.perform_gate_logic()
        return self.output


# -------------------------------------------------
class Connector:
    def __init__(self, fgate, tgate):
        self.from_gate = fgate
        self.to_gate = tgate
        tgate.set_next_pin(self)

    def get_from(self):
        return self.from_gate

    def get_to(self):
        return self.to_gate


# -------------------------------------------------
class UnaryGate(LogicGate):
    def __init__(self, lbl):
        LogicGate.__init__(self, lbl)
        self.pin = None

    def get_pin(self):
        if self.pin == None:
            return int(input("Enter pin input for gate " + self.get_label() + ": "))
        else:
            return self.pin.get_from().get_output()

    def set_next_pin(self, source):
        if self.pin == None:
            self.pin = source
        else:
            print("Cannot Connect: NO EMPTY PINS on this gate")

# -------------------------------------------------
class BinaryGate(LogicGate):
    def __init__(self, lbl):
        super(BinaryGate, self).__init__(lbl)
        self.pin_a = None
        self.pin_b = None

    def get_pin_a(self):
        if self.pin_a == None:
            return int(input("Enter pin A input for gate " + self.get_label() + ": "))
        else:
            return self.pin_a.get_from().get_output()

    def get_pin_b(self):
        if self.pin_b == None:
            return int(input("Enter pin B input for gate " + self.get_label() + ": "))
        else:
            return self.pin_b.get_from().get_output()

    def set_next_pin(self, source):
        if self.pin_a == None:
            self.pin_a = source
        else:
            if self.pin_b == None:
                self.pin_b = source
            else:
                print("Cannot Connect: NO EMPTY PINS on this gate")


# -------------------------------------------------
class AndGate(BinaryGate):
    def __init__(self, lbl):
        BinaryGate.__init__(self, lbl)

    def perform_gate_logic(self):
        a = self.get_pin_a()
        b = self.get_pin_b()
        if a == 1 and b == 1:
            return 1
        else:
            return 0


class OrGate(BinaryGate):
    def __init__(self, lbl):
        BinaryGate.__init__(self, lbl)

    def perform_gate_logic(self):
        a = self.get_pin_a()
        b = self.get_pin_b()
        if a == 1 or b == 1:
            return 1
        else:
            return 0


class NotGate(UnaryGate):
    def __init__(self, nlbl):
        UnaryGate.__init__(self, lbl)

    def perform_gate_logic(self):
        if self.get_pin():
            return 0
        else:
            return 1




# -------------------------------------------------
def main():
    g1 = AndGate("G1")   
    g2 = AndGate("G2")
    g3 = OrGate("G3")
    g4 = NotGate("G4")       # initial all pin is None
    c1 = Connector(g1, g3)
    c2 = Connector(g2, g3)
    c3 = Connector(g3, g4)
    print(g4.get_output())

main()
```
















.

class Node:
    """A node of a linked list"""

    def __init__(self, node_data):
        self._data = node_data
        self._next = None

    def get_data(self):
        """Get node data"""
        return self._data

    def set_data(self, node_data):
        """Set node data"""
        self._data = node_data

    data = property(get_data, set_data)

    def get_next(self):
        """Get next node"""
        return self._next

    def set_next(self, node_next):
        """Set next node"""
        self._next = node_next

    next = property(get_next, set_next)

    def __str__(self):
        """String"""
        return str(self._data)


class OrderedList:
    def __init__(self):
        self.head = None
        self.count = 0

    def is_empty(self):
        return self.head == None

    def size(self):
        return self.count

    def remove(self, item):
        current = self.head
        previous = None
        while current is not None:
            if current.data == item:
                break
            previous = current
            current = current.next
        if current is None:
            raise ValueError("{} is not in the list".format(item))
        if previous is None:   # remove the frist item
            self.head = current.next
        else:
            previous.next = current.next

    def search(self, item):
        current = self.head
        while (current is not None):
            if current.data > item:
                return False
            if current.data == item:
                return True
            current = current.next
        return False

    def add(self, item):
        temp = Node(item)
        current = self.head
        previous = None
        self.count += 1
        while (current is not None) and current.data < item:
            previous = current
            current = current.next
        if previous is None:
            temp.next = self.head
            self.head = temp
        else:
            temp.next = current
            previous.next = temp





my_list = OrderedList()
my_list.add(31)
my_list.add(77)
my_list.add(17)
my_list.add(93)
my_list.add(26)
my_list.add(54)

print(my_list.size())
print(my_list.search(93))
print(my_list.search(100))
class Queue:
    def __init__(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def enqueue(self, item):
        self.items.insert(0,item)

    def dequeue(self):
        return self.items.pop()

    def size(self):
        return len(self.items)

import random

class Printer:
    def __init__(self, ppm):      # self define
        self.pagerate = ppm       # speed  pages/minute
        self.currentTask = None   # paint mission
        self.timeRemaining = 0    # current mission remain print time

    def tick(self):  # current status
        if self.currentTask != None:
            self.timeRemaining = self.timeRemaining -1
            if self.timeRemaining <=0:
                self.currentTask = None

    def busy(self):   # if busy?
        if self.currentTask != None:
            return True
        else:
            return False

    def startNext(self, newtask):
        self.currentTask = newtask
        self.timeRemaining = newtask.getpages() * 60 / self.pagerate  # (pages/speed)*60s


class Task:
    def __init__(self, time):
        self.timestamp = time                 # time created
        self.pages = random.randrange(1,21)   # pages

    def getStamp(self):
        return self.timestamp

    def getPages(self):
        return self.pages       

    def waitTime(self, currenttime):        # how long time it wait
         return currenttime - self.timestamp


def newPrintTask():    # whether if a new task creates
    num = random.randrange(1,181)    # 20task10student/hour -> 20task/hour -> 60*60/20 = 180/s
    if num == 181:
        return True
    else:
        return False


def simulation(numSeconds, pagesPerMinute):
    labprinter = Printer(pagesPerMinute)
    printQueue = Queue()
    waitingtimes = []

    for currentSecond in range(numSeconds):  # time period, add mission
        if newPrintTask():   
            task = Task(currentSecond)
            printQueue.enqueue(task)

    if (not labprinter.busy()) and (not printQueue.isEmpty()):
        nexttask = printQueue.dequeue()  # take the task out
        waitingtimes.append(nexttask.waitTime(currentSecond))
        labprinter.startNext(nexttask)
        labprinter.tick()  # keep working

        print(123)
    #averageWait = sum(waitingtimes)/len(waitingtimes)

    #print("Average wait %f secs %d tasks remaining." %(averageWait, printQueue.size()))
    print(printQueue.size())

for i in range(10):
    simulation(3600,1)    # 5ppm, 1 hour, run 10 times
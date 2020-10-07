class Sudoku(object):

    def cross(self, A, B):
        """ Cross product of elements in A and B """
        return [a+b for a in A for b in B]
        # cross('ABCDEFGHI', '123456789')
        # ['A1', 'B1', 'C1', 'D1', 'E1', 'F1', 'G1', 'H1', 'I1']
        # ['A2', 'B2', 'C2', 'D2', 'E2', 'F2', 'G2', 'H2', 'I2']
        # ['A3', 'B3', 'C3', 'D3', 'E3', 'F3', 'G3', 'H3', 'I3']
        # ['A4', 'B4', 'C4', 'D4', 'E4', 'F4', 'G4', 'H4', 'I4']
        # ['A5', 'B5', 'C5', 'D5', 'E5', 'F5', 'G5', 'H5', 'I5']
        # ['A6', 'B6', 'C6', 'D6', 'E6', 'F6', 'G6', 'H6', 'I6']
        # ['A7', 'B7', 'C7', 'D7', 'E7', 'F7', 'G7', 'H7', 'I7']
        # ['A8', 'B8', 'C8', 'D8', 'E8', 'F8', 'G8', 'H8', 'I8']
        # ['A9', 'B9', 'C9', 'D9', 'E9', 'F9', 'G9', 'H9', 'I9']

    def __init__(self):
        self.digits = '123456789'
        self.rows = 'ABCDEFGHI'
        self.cols = self.digits

        self.squares = self.cross(self.rows, self.cols)

        self.unitlist = ([self.cross(self.rows, c) for c in self.cols] + [self.cross(r, self.cols) for r in self.rows] +
                         [self.cross(rs, cs) for rs in ('ABC', 'DEF', 'GHI') for cs in ('123', '456', '789')] )
        self.units = dict((s, [u for u in self.unitlist if s in u])for s in self.squares)
        self.peers = dict((s, set(sum(self.units[s], [])) - set([s]))for s in self.squares)


    def getSquares(self):
        return self.squares

    def getUnitlist(self):
        return self.unitlist

    def getUnits(self):
        return self.units

    def getPeers(self):
        return self.peers
        

mysudo = Sudoku()

rows = '123456789'
cols = '123456789'
squares = mysudo.cross(rows, cols)
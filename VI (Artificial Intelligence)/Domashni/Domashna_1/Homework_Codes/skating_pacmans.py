from searching_framework import *


class SkatingPacman(Problem):

    def __init__(self, initial, M, N, yellow_goal, green_goal, goal=None):
        super().__init__(initial, goal)
        self.M = M
        self.N = N
        self.yellow_goal = yellow_goal
        self.green_goal = green_goal

    def successor(self, state):
        successors = dict()

        yellow_pacmans = list(state[0])
        green_pacmans = list(state[1])

        for yp in yellow_pacmans:
            x, y = yp
            # up
            i = 1
            while y + i < self.M:
                new_pacman = (x, y + i)
                if new_pacman in yellow_pacmans or new_pacman in green_pacmans:
                    break
                new_yellow_pacmans = list(state[0])
                new_yellow_pacmans.remove(yp)
                new_yellow_pacmans.append(new_pacman)
                new_state = (tuple(new_yellow_pacmans), tuple(green_pacmans))
                successors[
                    'Yellow pacman from (' + str(x) + ', ' + str(y) + ') - ' + str(i) + ' positions up'] = new_state
                i += 1

            # down
            i = 1
            while y - i >= 0:
                new_pacman = (x, y - i)
                if new_pacman in yellow_pacmans or new_pacman in green_pacmans:
                    break
                new_yellow_pacmans = list(state[0])
                new_yellow_pacmans.remove(yp)
                new_yellow_pacmans.append(new_pacman)
                new_state = (tuple(new_yellow_pacmans), tuple(green_pacmans))
                successors[
                    'Yellow pacman from (' + str(x) + ', ' + str(y) + ') - ' + str(i) + ' positions down'] = new_state
                i += 1

            # right
            i = 1
            while x + i < self.N:
                new_pacman = (x + i, y)
                if new_pacman in yellow_pacmans or new_pacman in green_pacmans:
                    break
                new_yellow_pacmans = list(state[0])
                new_yellow_pacmans.remove(yp)
                new_yellow_pacmans.append(new_pacman)
                new_state = (tuple(new_yellow_pacmans), tuple(green_pacmans))
                successors[
                    'Yellow pacman from (' + str(x) + ', ' + str(y) + ') - ' + str(i) + ' positions right'] = new_state
                i += 1

            # left
            i = 1
            while x - i >= 0:
                new_pacman = (x - i, y)
                if new_pacman in yellow_pacmans or new_pacman in green_pacmans:
                    break
                new_yellow_pacmans = list(state[0])
                new_yellow_pacmans.remove(yp)
                new_yellow_pacmans.append(new_pacman)
                new_state = (tuple(new_yellow_pacmans), tuple(green_pacmans))
                successors[
                    'Yellow pacman from (' + str(x) + ', ' + str(y) + ') - ' + str(i) + ' positions left'] = new_state
                i += 1

        for gp in green_pacmans:
            x, y = gp
            # up
            i = 1
            while y + i < self.M:
                new_pacman = (x, y + i)
                if new_pacman in yellow_pacmans or new_pacman in green_pacmans:
                    break
                new_green_pacmans = list(state[1])
                new_green_pacmans.remove(gp)
                new_green_pacmans.append(new_pacman)
                new_state = (tuple(yellow_pacmans), tuple(new_green_pacmans))
                successors[
                    'Green pacman from (' + str(x) + ', ' + str(y) + ') - ' + str(i) + ' positions up'] = new_state
                i += 1

            # down
            i = 1
            while y - i >= 0:
                new_pacman = (x, y - i)
                if new_pacman in yellow_pacmans or new_pacman in green_pacmans:
                    break
                new_green_pacmans = list(state[1])
                new_green_pacmans.remove(gp)
                new_green_pacmans.append(new_pacman)
                new_state = (tuple(yellow_pacmans), tuple(new_green_pacmans))
                successors[
                    'Green pacman from (' + str(x) + ', ' + str(y) + ') - ' + str(i) + ' positions down'] = new_state
                i += 1

            # right
            i = 1
            while x + i < self.N:
                new_pacman = (x + i, y)
                if new_pacman in yellow_pacmans or new_pacman in green_pacmans:
                    break
                new_green_pacmans = list(state[1])
                new_green_pacmans.remove(gp)
                new_green_pacmans.append(new_pacman)
                new_state = (tuple(yellow_pacmans), tuple(new_green_pacmans))
                successors[
                    'Green pacman from (' + str(x) + ', ' + str(y) + ') - ' + str(i) + ' positions right'] = new_state
                i += 1

            # left
            i = 1
            while x - i >= 0:
                new_pacman = (x - i, y)
                if new_pacman in yellow_pacmans or new_pacman in green_pacmans:
                    break
                new_green_pacmans = list(state[1])
                new_green_pacmans.remove(gp)
                new_green_pacmans.append(new_pacman)
                new_state = (tuple(yellow_pacmans), tuple(new_green_pacmans))
                successors[
                    'Green pacman from (' + str(x) + ', ' + str(y) + ') - ' + str(i) + ' positions left'] = new_state
                i += 1

        return successors

    def actions(self, state):
        return self.successor(state).keys()

    def result(self, state, action):
        return self.successor(state)[action]

    def value(self):
        return 1

    def goal_test(self, state):
        yellow_pacmans = state[0]
        green_pacmans = state[1]

        for y in yellow_pacmans:
            if y not in self.yellow_goal:
                return False

        for g in green_pacmans:
            if g not in self.green_goal:
                return False

        return True

    def h_pacman_yellow(self, pacman):
        if pacman in self.yellow_goal:
            return 0
        x, y = pacman
        if 0 <= y < self.M / 2 or x == self.N - 1:
            return 1
        else:
            return 2

    def h_pacman_green(self, pacman):
        if pacman in self.green_goal:
            return 0
        x, y = pacman
        if self.M / 2 <= y < self.M or x == 0:
            return 1
        else:
            return 2

    def h(self, node):
        suma = 0
        for pacman in node.state[0]:
            suma += self.h_pacman_yellow(pacman)
        for pacman in node.state[1]:
            suma += self.h_pacman_green(pacman)
        return suma
        # cost = 0
        #
        # for yellow_p in node.state[0]:
        #     min_dist = float('inf')
        #     for target in self.yellow_goal:
        #         dist = abs(yellow_p[0] - target[0]) + abs(yellow_p[1] - target[1])
        #         if dist < min_dist:
        #             min_dist = dist
        #             cost += min_dist
        #
        # for green_p in node.state[0]:
        #     min_dist = float('inf')
        #     for target in self.green_goal:
        #         dist = abs(green_p[0] - target[0]) + abs(green_p[1] - target[1])
        #         if dist < min_dist:
        #             min_dist = dist
        #             cost += min_dist
        # return cost


if __name__ == '__main__':
    M = int(input("Enter M side of the board: "))
    if M % 2 != 0:
        M = int(input("Try again, M must be even: "))
    N = int(input("Enter N side of the board: "))
    yellow = list()
    green = list()
    for j in range(int(M / 2), M):
        yellow.append((0, j))
    for j in range(int(M / 2)):
        green.append((N - 1, j))
    green = tuple(green)
    yellow = tuple(yellow)

    green_goal_set = set()
    yellow_goal_set = set()

    for i in range(int(M / 2), M):
        green_goal_set.add((0, i))
    for i in range(int(M / 2)):
        yellow_goal_set.add((N - 1, i))

    start_state = (yellow, green)
    skating_pacman_problem = SkatingPacman(start_state, M, N, yellow_goal_set, green_goal_set)

    result = astar_search(skating_pacman_problem)
    print(result.solution())


from constraint import *


def not_attacking(q1, q2):
    x1, y1 = q1
    x2, y2 = q2
    # horizontal and vertical
    if x1 == x2 or y1 == y2:
        return False
    # diagonal
    if x1 - x2 == y1 - y2 or x1 + y1 == x2 + y2:
        return False
    return True


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    num_queens = int(input())
    variables = range(1, num_queens + 1)
    domain = [(i, j) for i in range(0, num_queens) for j in range(0, num_queens)]
    problem.addVariables(variables, domain)

    # ---Tuka dodadete gi ogranichuvanjata----------------
    for queen1 in variables:
        for queen2 in variables:
            if queen1 < queen2:
                problem.addConstraint(not_attacking, (queen1, queen2))

    # ----------------------------------------------------
    if num_queens > 6:
        print(problem.getSolution())
    else:
        print(len(problem.getSolutions()))

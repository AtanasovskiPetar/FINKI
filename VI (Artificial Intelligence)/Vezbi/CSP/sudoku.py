from constraint import *

if __name__ == '__main__':
    solver = input()
    if solver == 'BacktrackingSolver':
        problem = Problem(BacktrackingSolver())
    elif solver == 'RecursiveBacktrackingSolver':
        problem = Problem(RecursiveBacktrackingSolver())

    # solver == 'MinConflictsSolver'
    else:
        problem = Problem(MinConflictsSolver())

    variables = range(0, 81)
    domains = range(1, 10)

    problem.addVariables(variables, domains)

    # ---Tuka dodadete gi ogranichuvanjata----------------
    for column in range(9):
        problem.addConstraint(AllDifferentConstraint(), [column + 9 * i for i in range(9)])

    for row in range(9):
        problem.addConstraint(AllDifferentConstraint(), [row * 9 + i for i in range(9)])

    square1 = (0, 1, 2, 9, 10, 11, 18, 19, 20)
    problem.addConstraint(AllDifferentConstraint(), square1)

    square2 = (3, 4, 5, 12, 13, 14, 21, 22, 23)
    problem.addConstraint(AllDifferentConstraint(), square2)

    square3 = (6, 7, 8, 15, 16, 17, 24, 25, 26)
    problem.addConstraint(AllDifferentConstraint(), square3)

    square4 = (27, 28, 29, 36, 37, 38, 45, 46, 47)
    problem.addConstraint(AllDifferentConstraint(), square4)

    square5 = (30, 31, 32, 39, 40, 41, 48, 49, 50)
    problem.addConstraint(AllDifferentConstraint(), square5)

    square6 = (33, 34, 35, 42, 43, 44, 51, 52, 53)
    problem.addConstraint(AllDifferentConstraint(), square6)

    square7 = (54, 55, 56, 63, 64, 65, 72, 73, 74)
    problem.addConstraint(AllDifferentConstraint(), square7)

    square8 = (57, 58, 59, 66, 67, 68, 75, 76, 77)
    problem.addConstraint(AllDifferentConstraint(), square8)

    square9 = (60, 61, 62, 69, 70, 71, 78, 79, 80)
    problem.addConstraint(AllDifferentConstraint(), square9)
    # ----------------------------------------------------

    solution = problem.getSolution()

    print(solution)
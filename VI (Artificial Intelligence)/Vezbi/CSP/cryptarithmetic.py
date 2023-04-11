from constraint import *


def function(*args):
    line1 = 1000 * args[0] + 100 * args[1] + 10 * args[2] + args[3]
    line2 = 1000 * args[4] + 100 * args[5] + 10 * args[6] + args[1]
    line3 = 10000 * args[4] + 1000 * args[5] + 100 * args[2] + 10 * args[1] + args[7]
    return line1 + line2 == line3


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    variables = ["S", "E", "N", "D", "M", "O", "R", "Y"]
    for variable in variables:
        problem.addVariable(variable, Domain(set(range(10))))

    # ---Tuka dodadete gi ogranichuvanjata----------------
    problem.addConstraint(AllDifferentConstraint())
    problem.addConstraint(function, variables)
    # ----------------------------------------------------

    print(problem.getSolution())

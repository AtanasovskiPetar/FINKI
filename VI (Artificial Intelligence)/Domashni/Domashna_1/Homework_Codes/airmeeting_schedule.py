from constraint import *


def fighterJetsConstraint(*args):
    for i in range(3):
        if str(args[i]).split("_")[1] == str(args[3]).split("_")[1]:
            return False
    return True


if __name__ == "__main__":
    problem = Problem(BacktrackingSolver())

    variables = ["president_jet", "new_helicopter", "ww2_jet", "fighter_jets"]
    domains = ["P1_1", "P1_2", "P1_3", "P1_4", "P2_1", "P2_2", "P2_3", "P2_4"]

    problem.addVariables(variables, domains)

    problem.addConstraint(AllDifferentConstraint())
    # Unary
    problem.addConstraint(lambda x: str(x).split("_")[0] == "P1", ['president_jet'])
    problem.addConstraint(lambda x: str(x).split("_")[0] == "P2", ['new_helicopter'])
    # Binary
    problem.addConstraint(lambda x, y: abs(int(str(x).split("_")[1]) - int(str(y).split("_")[1])) > 1,
                          ['president_jet', 'new_helicopter'])
    problem.addConstraint(lambda x, y: abs(int(str(x).split("_")[1]) - int(str(y).split("_")[1])) > 1,
                          ['president_jet', 'ww2_jet'])
    problem.addConstraint(fighterJetsConstraint, variables)
    problem.addConstraint(lambda x, y: int(str(x).split("_")[1]) < int(str(y).split("_")[1]),
                          ['new_helicopter', 'president_jet'])
    problem.addConstraint(lambda x, y: int(str(x).split("_")[1]) < int(str(y).split("_")[1]),
                          ['ww2_jet', 'new_helicopter'])

    solutions = problem.getSolutions()
    for solution in solutions:
        print(solution)

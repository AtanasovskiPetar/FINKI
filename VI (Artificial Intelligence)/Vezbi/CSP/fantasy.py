from constraint import *


class Participant:
    def __init__(self, weight, name, type):
        self.weight = weight
        self.name = name
        self.type = type

    def get_weight(self):
        return self.weight

    def get_name(self):
        return self.name

    def get_type(self):
        return self.type


def max_sum_constraint(*args):
    sum_weights = 0
    for arg in args:
        weight = arg.get_weight()
        sum_weights += weight
    if sum_weights > 100:
        return False
    return True


def get_total_weight(solution):
    curr_weight = 0
    for member in solution:
        curr_weight += solution[member].get_weight()
    return curr_weight


if __name__ == '__main__':
    fantasy_problem = Problem(BacktrackingSolver())
    num_members = int(input())
    variables = list()
    domains = list()

    for i in range(1, 6):
        variables.append('Participant ' + str(i))

    for i in range(num_members):
        info = input().split(' ')
        domains.append(Participant(float(info[0]), info[1], 'participant'))

    fantasy_problem.addVariables(variables, domains)

    leader_domain = list()
    num_leaders = int(input())
    for i in range(num_leaders):
        info = input().split(' ')
        leader_domain.append(Participant(float(info[0]), info[1], 'leader'))

    fantasy_problem.addVariable('Team leader', leader_domain)

    variables.append('Team leader')

    fantasy_problem.addConstraint(AllDifferentConstraint(), variables)
    fantasy_problem.addConstraint(max_sum_constraint, variables)

    solutions = fantasy_problem.getSolutions()
    max_solution = fantasy_problem.getSolution()
    max_solution_weight = get_total_weight(max_solution)
    for solution in solutions:
        curr_w = get_total_weight(solution)
        if curr_w > max_solution_weight:
            max_solution = solution
            max_solution_weight = curr_w

    print('Total score: {:.1f}'.format(max_solution_weight))
    for member in max_solution:
        print(member + ': ' + max_solution[member].get_name())

from constraint import *


def notOverlappingConstraint(*args):
    dict_days = dict()
    for arg in args:
        info = arg.split('_')
        day = info[0]
        time = int(info[1])

        if len(dict_days) > 0:
            if day in dict_days.keys():
                list_day = dict_days[day]
                if time in list_day or time - 1 in list_day or time + 1 in list_day:
                    return False
        dict_days.setdefault(day, []).append(time)
    return True


def mlConstraint(*args):
    vezba = args[-1]
    info = vezba.split('_')
    vezba_time = int(info[1])
    lectures_set = set()
    for arg in args[:-1]:
        info = arg.split('_')
        time = int(info[1])
        lectures_set.add(time)
    if vezba_time not in lectures_set:
        return True
    return False


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    casovi_AI = int(input())
    casovi_ML = int(input())
    casovi_R = int(input())
    casovi_BI = int(input())
    variables = list()
    ML_vars = list()

    AI_casovi = list()
    for i in range(casovi_AI):
        AI_casovi.append('AI_cas_' + str(i + 1))
        variables.append('AI_cas_' + str(i + 1))

    ML_casovi = list()
    for i in range(casovi_ML):
        ML_casovi.append('ML_cas_' + str(i + 1))
        variables.append('ML_cas_' + str(i + 1))
        ML_vars.append('ML_cas_' + str(i + 1))

    R_casovi = list()
    for i in range(casovi_R):
        R_casovi.append('R_cas_' + str(i + 1))
        variables.append('R_cas_' + str(i + 1))

    BI_casovi = list()
    for i in range(casovi_BI):
        BI_casovi.append('BI_cas_' + str(i + 1))
        variables.append('BI_cas_' + str(i + 1))

    AI_predavanja_domain = ["Mon_11", "Mon_12", "Wed_11", "Wed_12", "Fri_11", "Fri_12"]
    ML_predavanja_domain = ["Mon_12", "Mon_13", "Mon_15", "Wed_12", "Wed_13", "Wed_15", "Fri_11", "Fri_12", "Fri_15"]
    R_predavanja_domain = ["Mon_10", "Mon_11", "Mon_12", "Mon_13", "Mon_14", "Mon_15", "Wed_10", "Wed_11", "Wed_12",
                           "Wed_13", "Wed_14", "Wed_15", "Fri_10", "Fri_11", "Fri_12", "Fri_13", "Fri_14", "Fri_15"]
    BI_predavanja_domain = ["Mon_10", "Mon_11", "Wed_10", "Wed_11", "Fri_10", "Fri_11"]

    AI_vezbi_domain = ["Tue_10", "Tue_11", "Tue_12", "Tue_13", "Thu_10", "Thu_11", "Thu_12", "Thu_13"]
    ML_vezbi_domain = ["Tue_11", "Tue_13", "Tue_14", "Thu_11", "Thu_13", "Thu_14"]
    BI_vezbi_domain = ["Tue_10", "Tue_11", "Thu_10", "Thu_11"]

    # ---Tuka dodadete gi promenlivite--------------------
    problem.addVariables(AI_casovi, AI_predavanja_domain)
    problem.addVariables(ML_casovi, ML_predavanja_domain)
    problem.addVariables(R_casovi, R_predavanja_domain)
    problem.addVariables(BI_casovi, BI_predavanja_domain)
    problem.addVariable('AI_vezbi', AI_vezbi_domain)
    problem.addVariable('ML_vezbi', ML_vezbi_domain)
    problem.addVariable('BI_vezbi', BI_vezbi_domain)
    variables.append('AI_vezbi')
    variables.append('ML_vezbi')
    variables.append('BI_vezbi')

    ML_vars.append('ML_vezbi')

    # ---Tuka dodadete gi ogranichuvanjata----------------

    problem.addConstraint(notOverlappingConstraint, variables)
    problem.addConstraint(mlConstraint, ML_vars)

    # ----------------------------------------------------
    solution = problem.getSolution()

    print(solution)

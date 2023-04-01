from constraint import *


def max_num_papers_term(*args):
    T1_counter, T2_counter, T3_counter, T4_counter = (0, 0, 0, 0)
    for p in args:
        if p == 'T1':
            T1_counter += 1
        elif p == 'T2':
            T2_counter += 1
        elif p == 'T3':
            T3_counter += 1
        else:
            T4_counter += 1

    return T1_counter <= 4 and T2_counter <= 4 and T3_counter <= 4 and T4_counter <= 4


if __name__ == '__main__':
    num = int(input())
    papers = dict()
    paper_info = input()
    variables = list()
    topics_dict = dict()

    while paper_info != 'end':
        title, topic = paper_info.split(' ')
        variables.append(str(title) + " (" + str(topic) + ")")
        papers[title] = topic
        topics_dict.setdefault(topic, []).append(str(title) + " (" + str(topic) + ")")
        paper_info = input()

    # Tuka definirajte gi promenlivite

    domain = [f'T{i + 1}' for i in range(num)]

    problem = Problem(BacktrackingSolver())

    # Dokolku vi e potrebno moze da go promenite delot za dodavanje na promenlivite
    problem.addVariables(variables, domain)

    # Tuka dodadete gi ogranichuvanjata
    problem.addConstraint(max_num_papers_term, variables)

    for topics in topics_dict:
        if len(topics_dict[topics]) <= 4:
            list_papers = topics_dict[topics]
            problem.addConstraint(AllEqualConstraint(), list_papers)

    result = problem.getSolution()

    # Tuka dodadete go kodot za pechatenje
    list_result = list(result.keys())
    list_result.sort(key=lambda x: int(x.split()[0][5:]))
    sorted_result = {k: result[k] for k in list_result}
    for paper in sorted_result:
        print(paper + ": " + sorted_result[paper])

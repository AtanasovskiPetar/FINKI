from constraint import *


# Симона слободни термини: 13:00-15:00, 16:00-17:00, 19:00-20:00 [13, 14, 16, 19]
# Марија слободни термини: 14:00-16:00, 18:00-19:00 [14, 15, 18]
# Петар слободни термини: 12:00-14:00, 16:00-20:00 [12, 13, 16, 17, 18, 19]

def at_least_two(simona, marija, petar):
    if simona == 1:
        return marija == 1 or petar == 1
    else:
        return False


def possible(simona, marija, petar, meeting):
    simona_free_times = [13, 14, 16, 19]
    marija_presence = [14, 15, 18]
    petar_presence = [12, 13, 16, 17, 18, 19]

    if marija == 1 and meeting not in marija_presence:
        return False
    if petar == 1 and meeting not in petar_presence:
        return False
    if simona == 1 and meeting not in simona_free_times:
        return False
    return True


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())

    # ---Dadeni se promenlivite, dodadete gi domenite-----
    problem.addVariable("Marija_prisustvo", [0, 1])
    problem.addVariable("Simona_prisustvo", [0, 1])
    problem.addVariable("Petar_prisustvo", [0, 1])
    problem.addVariable("vreme_sostanok", range(12, 21))
    # ----------------------------------------------------

    # ---Tuka dodadete gi ogranichuvanjata----------------
    simona_presence = [13, 14, 16, 19]
    problem.addConstraint(lambda meeting, simona: meeting in simona_presence and simona == 1,
                          ("vreme_sostanok", "Simona_prisustvo"))
    problem.addConstraint(at_least_two, ["Simona_prisustvo", "Marija_prisustvo", "Petar_prisustvo"])
    problem.addConstraint(possible, ["Simona_prisustvo", "Marija_prisustvo", "Petar_prisustvo", "vreme_sostanok"])

    # ----------------------------------------------------

    [print(solution) for solution in problem.getSolutions()]

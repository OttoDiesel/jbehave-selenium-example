In dieser Beispiel-Story wird gezeigt, wie Composite Steps angewendet werden.
Der erste Step der Szenarien ist jeweils ein Verbund-Step und besteht aus zwei anderen Steps.
Siehe: http://jbehave.org/reference/stable/composite-steps.html

!-- Falls die Story in Eclipse nicht richtig dargestellt wird:
!-- 1. JBehave-Eclipse-Plugin runterladen.
!-- 2. Im Plugin Sprache auf Deutsch stellen.

Szenario: Kunde ändert seine Adresse
Gegeben der User ist erfolgreich eingeloggt auf der Startseite
Wenn der User die Funktion Adressdaten ändern benutzt
Dann kann der User seine Adresse ändern

Szenario: Kunde ändert seine Kontodaten
Gegeben der User ist erfolgreich eingeloggt auf der Startseite
Wenn der User die Funktion Kontodaten ändern benutzt
Dann kann der User seine Kontodaten ändern

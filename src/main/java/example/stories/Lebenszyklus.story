In dieser Beispiel-Story wird gezeigt, wie Lebenszyklen funktionieren.
Siehe: https://github.com/jbehave/jbehave-core/blob/master/examples/core/src/main/java/org/jbehave/examples/core/stories/lifecycle.story

!-- Falls die Story in Eclipse nicht richtig dargestellt wird:
!-- 1. JBehave-Eclipse-Plugin runterladen.
!-- 2. Im Plugin Sprache auf Deutsch stellen.

Erzählung:
Um zu sehen, wie Lebenszyklen funktionieren
Als ein an JBehave interessierter User
Möchte ich mir diese Story näher ansehen

Lebenszyklus:
Vorher:
Gegeben der User ist eingeloggt auf der Startseite
Und es wird keine Fehlermeldung angezeigt
Nach:
Ergebnis: FEHLER
Gegeben die Systemdaten werden zurückgesetzt

Szenario: Kunde ändert seine Adresse
Wenn der User die Funktion Adressdaten ändern benutzt
Dann kann der User seine Adresse ändern

Szenario: Kunde ändert seine Kontodaten
Wenn der User die Funktion Kontodaten ändern benutzt
Dann kann der User seine Kontodaten ändern

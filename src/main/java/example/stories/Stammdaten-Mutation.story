In dieser Beispiel-Story wird gezeigt, wie eine Story von einer anderen Story aus aufgerufen werden kann.
Siehe: http://jbehave.org/reference/stable/given-stories.html

!-- Falls die Story in Eclipse nicht richtig dargestellt wird:
!-- 1. JBehave-Eclipse-Plugin runterladen.
!-- 2. Im Plugin Sprache auf Deutsch stellen.

VorgegebeneStories: example/stories/Login.story

Szenario: Kunde ändert seine Adresse
!-- Hier gibt es noch ein Problem mit JBehave 4.0-beta-8!
Wenn der User die Funktion Adressdaten ändern benutzt
Dann kann der User seine Adresse ändern

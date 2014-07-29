Hier wird gezeigt, wie tabellarische Parameter verwendet werden.
Wie man sieht, lassen sich damit z.B. allgemeine Testdaten definieren.
Auf diese kann man dann von anderen Steps aus zugreifen. Den Code dazu muss man selbst schreiben.
Siehe: http://jbehave.org/reference/stable/tabular-parameters.html und http://jbehave.org/reference/stable/outcomes-table.html

!-- Falls die Story in Eclipse nicht richtig dargestellt wird:
!-- 1. JBehave-Eclipse-Plugin runterladen.
!-- 2. Im Plugin Sprache auf Deutsch stellen.

Szenario: Definieren allgmeiner Testdaten

Gegeben es existieren diese Benutzer:
|Name |Username|Passwort|
|-----|--------|--------|
|Admin|super   |duper   |
|Kunde|okie    |dokie   |

Und diese Systeme sind vorhanden:
|Name|URL                |
|----|-------------------|
|DEV |http://sut.dev.net |
|TEST|http://sut.test.net|
|INT |http://sut.dev.net |

CREATE TABLE mashData (measurementTime timestamp NOT NULL,mash float ,flow float ,herms float, hlt float );
CREATE TABLE runStatus (running boolean);
CREATE TABLE runMode (runMode varchar(20));
CREATE TABLE targetTemps (secondsElapsed int(11),  temperature float);
CREATE TABLE hltTemps (secondsElapsed int(11),  temperature float);

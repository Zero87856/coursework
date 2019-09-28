CREATE TABLE answers (answerSet INTEGER PRIMARY KEY UNIQUE NOT NULL, answerA STRING NOT NULL, answerB STRING NOT NULL, answerC STRING NOT NULL, answerD STRING NOT NULL, answerTrue STRING NOT NULL);
CREATE TABLE questions (questionNo INTEGER PRIMARY KEY NOT NULL UNIQUE, questionContent STRING NOT NULL, answerSet STRING NOT NULL UNIQUE REFERENCES answers (answerSet), questionDifficulty INTEGER NOT NULL, questionWin INTEGER NOT NULL REFERENCES SuccessRates (questionWin));
CREATE TABLE successRates (questionWin INTEGER PRIMARY KEY, questionNo INTEGER REFERENCES questions (questionNo) UNIQUE NOT NULL, successCount INTEGER, attempt INTEGER);
19/09/2019 : due to outdated database file being the primary, uploded ddl to git to try and find a way to overwrite it
28/09/2019 : New database built, added to git alongside the original, with hopes to replace it that way

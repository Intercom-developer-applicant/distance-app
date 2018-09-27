# Customer Records

## Environment
I wrote this program with Java 8 in VS Code with the default Java Extension Pack, which includes Maven and JUnit.

## Runtime Arguments
VS Code allows definition of Runtime Arguments in launch.json. I have set the URL for the customer data and the co-ordinates of the Intercom office here. They are sent as arguments to the main program.

## Tests
Unit tests check sections of the code. I would have preferred to mock some of the data to keep the tests isolated, but could not due to time constraints. As-is, there are a few tests which would better be described as Integration tests. I have retained them for completeness.

## Results
Results are written to both System Terminal and a text file `target/Results.txt`. Included below is the output from my program:
```
4: Ian Kehoe
5: Nora Dempsey
6: Theresa Enright
8: Eoin Ahearn
11: Richard Finnegan
12: Christina McArdle
13: Olive Ahearn
15: Michael Ahearn
17: Patricia Cahill
23: Eoin Gallagher
24: Rose Enright
26: Stephen McArdle
29: Oliver Ahearn
30: Nick Enright
31: Alan Behan
39: Lisa Ahearn
```
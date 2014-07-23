Meta:

Narrative:
As a user
I want to view my shopping lists
So that I can select shopping list to work with

Scenario: user has no shopping lists
Given user U is logged in
And user U has no shopping lists
When user U views shopping lists
Then results are empty

Scenario: user has some shopping lists
Given user U is logged in
And user U has following shopping lists:
|title|createDate|archived|
|A    |2014-05-08|-       |
|B    |2014-03-14|+       |
|C    |2014-06-22|-       |
When user U views shopping lists
Then results contains:
|title|
|C    |
|A    |
selenium-ide-transformer
========================

Transform actions from a standard selenium ide testCase.html into another language.  The primary use of this is to create jUnit.java files.

Considerations & Steps
========================
1) Selenium IDE and Selenium Builder allow simplified access to functional testing.
2) WebDriver allows better control and functionality but needs a higher developer skill level.
3) Exporting from Selenium IDE/Selenium Builder adds a build step.
4) TestCase files should be kept in native Selenium IDE/Selenium Builder format.
5) TestCase files should be converted to WebDriver *.java files by Ant.
6) CI System runs TestCase *.java files within Selenium Grid.
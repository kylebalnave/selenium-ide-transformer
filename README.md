selenium-ide-transformer
========================

Transform actions from a standard selenium ide testCase.html into another language.  The primary use of this is to create jUnit.java files.


========================
I don't see the point of having a great tool like Selenium Grid if the only people who can use it are developers who know how to write WebDriver actions.
Selenium IDE might be limited but it does allow simple trial and error in order to create functional tests.  If anyone can create functional tests easily, then more people will.  The more robust tests that we make, the better confidence our code has, or the more bugs we find.  Either way it's a win, win.


========================
This project follows a few steps:-

1) Read a testCase.html file saved from Selenium IDE.

2) Parse the action &lt;table&gt;

3) Read a transformer.xml file that tells java what each action should be transformed to.

4) Create a new file with all the transformed actions.


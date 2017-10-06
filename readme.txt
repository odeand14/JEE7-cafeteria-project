Cafeteria Project.

This project consists of Three sub-modules, backend, frontend and report. As specified in the text.
They all have an individual POM file which inherits from the root.
The backend and frontend are structured mostly the same packagewise. A source folder with main and test in both,
which again contains each a java and resource folder. And subpackages depending on need.


BACKEND:
The main classes are split into two different packages "ejb" which contains the entity-java-beans
and "entity" which contains the @Entity classes. The resource folder contains the persistence.xml file

Arquillian Tests:
The backend test folder contains the tests. They are split in two different classes, one for each EJB.
There is allso a EJBtestbase which they both inherit from in order to gain access to deleter methods
and other shared values. The DeleterEJB is located in the same parent as the ejb one and contains the Deleter class.


FRONTEND:
In the main java no.odeand.enterprise.exam.frontend package we have the controllers and under
main webapp my_cantina the xhtml pages are located, one for each page plus layout.
WEB-INF contains the usual files web.xml plus jboss-web.xml

Selenium Tests:
The frontend test folder contains the page object classes under package po.
And the tests, testbase and util classes are located one directly under the no.odeand.enterprise.exam.frontend
package in test.


REPORT:
In the report folder we get the Java code coverage reports, once all the tests are run.
Or, at least if we want the full report. It should be 91% coverage on total avg instructions.


Usage:
As required.

"mvn install" from root.

"mvn wildfly:run" to start wildfly and deploy WAR.

"mvn verify -P selenium" to run tests including selenium tests.



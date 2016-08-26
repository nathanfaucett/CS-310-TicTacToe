cd acceptance
javac -cp .:../build/classes/main TicTacToeKeywords.java
java -cp .:../build/classes/main:../libs/robotframework-2.8.5.jar org.robotframework.RobotFramework TicTacToeTests.txt
cd ..

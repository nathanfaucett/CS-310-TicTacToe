*** Settings ***
Library           SwingLibrary

*** Keywords ***
Start New Game
    Start Application   edu.jsu.mcis.tictactoe.Game
    Select Window       Tic Tac Toe

Mark Location   [Arguments]     ${row}      ${col}
    ${component}=   Catenate    SEPARATOR=  Location    ${row}  ${col}
    Click On Component  ${component}

Check Location  [Arguments]     ${row}  ${col}  ${mark}
    ${component}=   Catenate    SEPARATOR=  Location    ${row}  ${col}
    Label Text Should Be    ${component}    ${mark}

Winner Should Be    [Arguments]     ${winner}
    Select Dialog   Game Over
    ${message}=     Catenate    The winner is   ${winner}
    Label Text Should Be    OptionPane.label    ${message}
    Close Dialog    Game Over
    Close Window    Tic Tac Toe


*** Test Cases ***
Win Diagonally as X
    Start New Game
    Mark Location   1   1
    Check Location  1   1   X
    Mark Location   0   1
    Check Location  0   1   O
    Mark Location   0   0
    Check Location  0   0   X
    Mark Location   0   2
    Check Location  0   2   O
    Mark Location   2   2
    Check Location  2   2   X
    Winner Should Be    X

Win Horizontally as O
    Start New Game
    Mark Location   1   1
    Check Location  1   1   X
    Mark Location   2   0
    Check Location  2   0   O
    Mark Location   0   0
    Check Location  0   0   X
    Mark Location   2   2
    Check Location  2   2   O
    Mark Location   0   2
    Check Location  0   2   X
    Mark Location   2   1
    Check Location  2   1   O
    Winner Should Be    O

Force a Tie
    Start New Game
    Mark Location   1   1
    Check Location  1   1   X
    Mark Location   0   0
    Check Location  0   0   O
    Mark Location   2   0
    Check Location  2   0   X
    Mark Location   0   2
    Check Location  0   2   O
    Mark Location   0   1
    Check Location  0   1   X
    Mark Location   2   1
    Check Location  2   1   O
    Mark Location   1   2
    Check Location  1   2   X
    Mark Location   1   0
    Check Location  1   0   O
    Mark Location   2   2
    Check Location  2   2   X
    Winner Should Be    TIE

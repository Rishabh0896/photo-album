@echo off
set /p INFILE="Please enter the input file path to draw shapes from : "
echo Select the type of view you would like to see
echo 1. Graphical
echo 2. Web
set /p VIEWTYP="Please enter the option :"
IF %VIEWTYP%==1 (
goto graph
) ELSE (
goto web
)
:graph
set /p XLEN="Please enter the Width of window :"
set /p YLEN="Please enter the Height of window :"
echo java -jar .\Assignment8.jar -in %INFILE% -v graphical %XLEN% %YLEN%
java -jar .\Assignment8.jar -in %INFILE% -v graphical %XLEN% %YLEN%

:web
set /p OUTFILE="Please enter the output file path to draw shapes to :"
echo java -jar .\Assignment8.jar -in %INFILE% -out %OUTFILE% -v web
java -jar .\Assignment8.jar -in %INFILE% -out %OUTFILE% -v web

pause

:: Assignment8 java -jar .\Assignment8.jar -in buildings.txt -out myWeb.html -v graphical 800 800
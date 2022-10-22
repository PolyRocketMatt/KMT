@echo off
set version=%1
echo Generating versioning file
echo Found version %version%
echo { "version": "%version%" } > version.json
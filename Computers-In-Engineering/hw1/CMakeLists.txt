cmake_minimum_required(VERSION 3.20)
project(hw1)

set(CMAKE_CXX_STANDARD 14)

add_executable(hw1 hw1.cpp)

configure_file(files/STR.csv files/STR.csv COPYONLY)
configure_file(files/DNA.txt files/DNA.txt COPYONLY)

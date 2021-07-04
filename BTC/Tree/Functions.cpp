#include <iostream>
#include "Functions.hpp"

/**
 * This method makes all string chars Upper chars
 * @param givenStr given string
 * @return given string in upper chars
 * @author Kacper Zawojski
 */
std::string toUpperCase(std::string givenStr)
{
	for (int i = 0; i < givenStr.length(); i++)
	{
		givenStr.at(i) = toupper(givenStr.at(i));
	}
	return givenStr;
}

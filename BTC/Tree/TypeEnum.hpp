
#ifndef TYPEENUM_HPP
#define TYPEENUM_HPP
#include <iostream>
#include "Functions.hpp"

/**
 * @author Kacper Zawojski
 */
enum typeEnum
{
	STRING,
	DOUBLE,
	INTEGER,
	WRONG
};
/**
 * This method is used to return enum based on passed string
 * @param str
 * @return
 */
typeEnum convert(std::string str)
{

	str = toUpperCase(str);
	if (toUpperCase(str) == "STRING")
	{
		return STRING;
	}
	else if (toUpperCase(str) == "DOUBLE")
	{
		return DOUBLE;
	}
	else if (toUpperCase(str) == "INTEGER")
	{
		return INTEGER;
	}
	else
	{
		return WRONG;
	}
}
#endif //TYPEENUM_HPP

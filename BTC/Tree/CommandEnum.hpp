/**
 * @author Kacper Zawojski
 * 
*/

#ifndef COMMANDENUM_HPP
#define COMMANDENUM_HPP
#include <iostream>
#include "Functions.hpp"
enum CommandEnum
{
	SEARCH,
	INSERT,
	DELETE,
	DRAW,
	STOP,
	BAD
};
CommandEnum convertCommand(std::string str)
{

	str = toUpperCase(str);
	if (toUpperCase(str) == "SEARCH")
	{
		return SEARCH;
	}
	else if (toUpperCase(str) == "INSERT")
	{
		return INSERT;
	}
	else if (toUpperCase(str) == "DELETE")
	{
		return DELETE;
	}
	else if (toUpperCase(str) == "DRAW")
	{
		return DRAW;
	}
	else if (toUpperCase(str) == "STOP")
	{
		return STOP;
	}
	else
	{
		return BAD;
	}
}
#endif //COMMANDENUM_HPP

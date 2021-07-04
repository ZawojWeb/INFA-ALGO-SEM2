#include <iostream>
#include "Tree.hpp"
#include "TypeEnum.hpp"
#include "CommandEnum.hpp"
#include "Functions.hpp"

using std::string;
template <typename T>
void createTreeInterface()
{
	Tree<T> myTree;
	bool shallIKeepSpinning = true;
	while (shallIKeepSpinning)
	{
		std::cout << "Waiting for commands... :\n";
		std::cout << "SEARCH | INSERT | DELETE | DRAW | STOP\n";
		string treeCommand;
		T passedData;
		std::cin >> treeCommand;
		treeCommand = toUpperCase(treeCommand);
		CommandEnum command = convertCommand(treeCommand);
		switch (command)
		{
		case SEARCH:
		{
			if (!(std::cin >> passedData))
			{
				std::cout << "Wrong type parameter, try again\n";
				std::cin.clear();
				std::cin.ignore(256, '\n');
				continue;
			}
			if (myTree.search(passedData) != nullptr)
			{
				std::cout << "Given element is contained in this this tree\n";
			}
			else
			{
				std::cout << "This tree does not contain given element\n";
			}
			std::cin.ignore(256, '\n');
			break;
		}

		case INSERT:
		{
			if (!(std::cin >> passedData))
			{
				std::cout << "Wrong type parameter, try again\n";
				std::cin.clear();
				std::cin.ignore(256, '\n');
				continue;
			}
			if (myTree.search(passedData) == nullptr)
			{

				myTree.insert(passedData);
				std::cout << "Given Element has been added to this tree\n";
			}
			else
			{
				std::cout << "This element is already contained in this tree\n";
			}
			std::cin.ignore(256, '\n');
			break;
		}
		case DELETE:
		{
			if (!(std::cin >> passedData))
			{
				std::cout << "Wrong type parameter, try again\n";
				std::cin.clear();
				std::cin.ignore(256, '\n');
				continue;
			}
			if (myTree.search(passedData) != nullptr)
			{
				myTree.setRoot(myTree.deletion(myTree.getRoot(), passedData));
				std::cout << "Element has been successfully deleted\n";
			}
			else
			{
				std::cout << "This tree does not contain given element\n";
			}
			std::cin.ignore(256, '\n');
			break;
		}
		case DRAW:
		{
			myTree.draw(myTree.getRoot());
			std::cin.ignore(256, '\n');
			std::cout << "\n";
			break;
		}
		case STOP:
		{
			shallIKeepSpinning = false;
			continue;
		}
		case BAD:
		{
			std::cout << "Invalid command, try again...: \n";
			std::cin.ignore(256, '\n');
			break;
		}
		}
	}
	std::cout << "Program has been successfully stopped\n";
	myTree.DestroyTheWeak();
}

int main()
{

	string typeControl;
	std::cout << "Type one of the following: \n string, double, integer \n to create given type binary tree\n";
	std::cin >> typeControl;
	std::cin.ignore();
	typeEnum typeEnumeration = convert(typeControl);
	switch (typeEnumeration)
	{

	case STRING:
	{
		std::cout << "String tree has been created\n";
		createTreeInterface<string>();
		break;
	}
	case DOUBLE:
	{
		std::cout << "Double tree has been created\n";
		createTreeInterface<double>();
		break;
	}
	case INTEGER:
	{
		std::cout << "Integer tree has been created\n";
		createTreeInterface<int>();
		break;
	}
	case WRONG:
	{
		std::cout << "Wrong type, restart program\n";
		break;
	}
	}
	return 0;
}

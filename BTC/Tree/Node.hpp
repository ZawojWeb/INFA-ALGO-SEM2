
#include <cstdio>

#ifndef NODE_HPP
#define NODE_HPP

/**
 * Node class is a container for a Comparable type T variable, and other two Nodes that are this Node sons.
 * Node objects are used in Tree structure.
 * @version 1.0
 * @author Kacper Zawojski
  */
template <typename T>
class Node
{
private:
	Node<T> *leftSon = NULL;
	Node<T> *rightSon = NULL;

	T container;

public:
	explicit Node(T value)
	{
		this->container = value;
	}
	Node<T> *getLeftSon()
	{
		return leftSon;
	}
	void setLeftSon(Node<T> *node)
	{
		leftSon = node;
	}
	Node<T> *getRightSon()
	{
		return rightSon;
	}
	void setRightSon(Node<T> *node)
	{
		rightSon = node;
	}
	void setContainer(T value)
	{
		container = value;
	}
	T getContainer()
	{
		return container;
	}
	/**
	 * This method destroys this Node and all of its siblings inOrder
	 * @param currNode startingNode
	 */
	void DestroyRecursively(Node<T> *currNode)
	{
		if (currNode != nullptr)
		{
			DestroyRecursively(currNode->getLeftSon());
			DestroyRecursively(currNode->getRightSon());
			delete (currNode);
		}
	}
	~Node() = default;
};

#endif //NODE_HPP

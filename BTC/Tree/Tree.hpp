

#ifndef TREE_HPP
#define TREE_HPP

#include "Node.hpp"
/**
 * Tree class has only one field that is Node type and contains the Tree root.
 * Tree class contains method that are responsible for Tree building and managing.
 * @version 1.0
 * @author Kacper Zawojski
 */
template <typename T>
class Tree
{
private:
	Node<T> *root;

public:
	Tree()
	{
		this->root = nullptr;
	}

public:
	/**
	 * This method recursively deletes all Nodes in tree inOrder
	 */
	void DestroyTheWeak()
	{
		root->DestroyRecursively(root);
	}
	Node<T> *getRoot()
	{
		return root;
	}
	void setRoot(Node<T> *newRoot)
	{
		this->root = newRoot;
	}
	/**
     * This method traverse the tree starting with the tree root, looking for the Node with passed key.
     * @param searchedKey The Key that the method is looking for inside the tree.
     * @return If found, this method returns the Node containing passed Key, otherwise it returns null.
     */
	Node<T> *search(T searchedKey)
	{
		Node<T> *currNode = root;
		while (currNode != nullptr)
		{
			if (searchedKey < currNode->getContainer())
			{
				currNode = currNode->getLeftSon();
			}
			else if (searchedKey > currNode->getContainer())
			{
				currNode = currNode->getRightSon();
			}
			else
				break;
		}
		return currNode;
	}

	/**
     * This method inserts a new Node within the tree provided that a Node with the same Key isn't already contained in this tree.
     * @param nodeKey Key of new node
     * @throws TreeException yeah it also throws it
     */
	void insert(T nodeKey)
	{
		auto *newNode = new Node<T>(nodeKey);
		putItRightThere(newNode);
	}
	/**
     * This method is responsible for recursively identifying the Node with passed key inside the Tree and if found safely deleting it providing that the tree stays consistent
     * @param subTreeRoot passed subTreeRoot because the method is recursive
     * @param nodeKey Key passed to be deleted
     * @return this method returns new SubTree to be recursively connected to the whole tree
     * @throws TreeException yeah it throws it
     */
	Node<T> *deletion(Node<T> *subTreeRoot, T nodeKey)
	{
		if (subTreeRoot == nullptr)
		{
			return nullptr;
		}
		if (nodeKey < subTreeRoot->getContainer())
		{
			subTreeRoot->setLeftSon(deletion(subTreeRoot->getLeftSon(), nodeKey));
		}
		else if (nodeKey > subTreeRoot->getContainer())
		{
			subTreeRoot->setRightSon(deletion(subTreeRoot->getRightSon(), nodeKey));
		}
		else
		{
			if (subTreeRoot->getLeftSon() == nullptr && subTreeRoot->getRightSon() == nullptr)
			{
				delete (subTreeRoot);
				return nullptr;
			}
			else if (subTreeRoot->getLeftSon() != nullptr && subTreeRoot->getRightSon() != nullptr)
			{
				subTreeRoot->setContainer(findMinimalKeyNode(subTreeRoot->getRightSon())->getContainer());
				subTreeRoot->setRightSon(deletion(subTreeRoot->getRightSon(), subTreeRoot->getContainer()));
			}
			else
			{
				if (subTreeRoot->getLeftSon() == nullptr)
				{
					Node<T> *temporaryNode = subTreeRoot->getRightSon();
					delete subTreeRoot;
					return temporaryNode;
				}
				else
				{
					Node<T> *temporaryNode = subTreeRoot->getLeftSon();
					delete subTreeRoot;
					return temporaryNode;
				}
			}
		}
		return subTreeRoot;
	}
	/**
	* This method returns the String containing all of the Nodes inOrder.
	*/
	void draw(Node<T> *currNode)
	{
		if (currNode != nullptr)
		{
			std::cout << " ( ";
			draw(currNode->getLeftSon());
			std::cout << currNode->getContainer() << " ";
			draw(currNode->getRightSon());
			std::cout << " ) ";
		}
	}

private:
	/**
	 * This method is the key method for insert method. It finds a place for newNode and places it there
	 * @param newNode
	 */
	void putItRightThere(Node<T> *newNode)
	{
		Node<T> *TemporaryParentNode = nullptr;
		Node<T> *Seeker = root;
		while (Seeker != nullptr)
		{
			TemporaryParentNode = Seeker;
			if (newNode->getContainer() < Seeker->getContainer())
			{
				Seeker = Seeker->getLeftSon();
			}
			else
			{
				Seeker = Seeker->getRightSon();
			}
		}

		if (TemporaryParentNode == nullptr)
		{
			root = newNode;
		}
		else if (newNode->getContainer() < TemporaryParentNode->getContainer())
		{
			TemporaryParentNode->setLeftSon(newNode);
		}
		else
		{
			TemporaryParentNode->setRightSon(newNode);
		}
	}
	/**
     * This method find a Node with the lowest key in given subTree
     * @param originalNode
     * @return
     */
	Node<T> *findMinimalKeyNode(Node<T> *originalNode)
	{
		Node<T> *currNode = originalNode;
		while (currNode->getLeftSon() != nullptr)
		{
			currNode = currNode->getLeftSon();
		}
		return currNode;
	}
};

#endif //TREE_HPP

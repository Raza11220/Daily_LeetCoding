/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;
    Node* random;
    
    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/
class Solution {
public:
    Node* copyRandomList(Node* head) {
        if (head == NULL) {
            return NULL;
        }

        unordered_map<Node*, Node*> m;
        Node* oldTemp = head;

        // Step 1: Create a copy of each node and store in map
        while (oldTemp != NULL) {
            m[oldTemp] = new Node(oldTemp->val);
            oldTemp = oldTemp->next;
        }

        // Step 2: Assign next and random pointers for copied nodes
        oldTemp = head;
        while (oldTemp != NULL) {
            m[oldTemp]->next = m[oldTemp->next]; // NULL if next doesn't exist
            m[oldTemp]->random = m[oldTemp->random]; // NULL if random doesn't exist
            oldTemp = oldTemp->next;
        }

        return m[head]; // Return the head of the copied list
    }
};
class Solution {
public:
    bool isValid(string str) {
        stack<char> st;

        for(int i = 0; i < str.size(); i++) {
            if(str[i] == '(' || str[i] == '{' || str[i] == '[') {
                st.push(str[i]);
            } else { // Current character is a closing bracket
                if(st.empty()) { // Use st.empty() for clarity and efficiency
                    return false; // No opening bracket to match
                }

                // Check if the top of the stack matches the current closing bracket
                if((st.top() == '(' && str[i] == ')') ||
                   (st.top() == '{' && str[i] == '}') ||
                   (st.top() == '[' && str[i] == ']')) {
                    st.pop(); // Match found, pop the opening bracket
                } else {
                    return false; // Mismatched brackets
                }
            }
        }
        // After iterating through the string, the stack should be empty
        // if all brackets were properly matched.
        return st.empty(); // Corrected: use comparison operator or st.empty()
    }
};